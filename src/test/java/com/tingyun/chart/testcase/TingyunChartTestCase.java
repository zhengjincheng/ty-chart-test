package com.tingyun.chart.testcase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.client.HttpClientFactory;
import com.tingyun.chart.client.JsonUtil;
import com.tingyun.chart.database.DBUtil;
import com.tingyun.page.impl.LoginPage;

public class TingyunChartTestCase  {
	public int UTC20130101MINTER = (int) ((parseTimestamp("2013-01-01 00:00:00", TimeZone.getTimeZone("GMT"))).getTime()
			/ 60000);
	public final static String POSTFIX_1MINTER = "";// _MIN
	public final static String POSTFIX_60MINTER = "_HOUR";
	public final static String POSTFIX_1440MINTER = "_DAY";
	public final static int TABLE_TICK_1 = 1;
	public final static int TABLE_TICK_15 = 15;
	public final static int TABLE_TICK_60 = 60;
	public final static int TABLE_TICK_1440 = 1440;

	public static final int MILL_1MINTER = 1000 * 60;
	public static final int MILL_5MINTER = MILL_1MINTER * 5;
	public static final int MILL_1HOUR = MILL_1MINTER * 60;
	public static final int MILL_1DAY = MILL_1HOUR * 24;
	public static final int MILL_GMT0800 = MILL_1HOUR * 8;

	/**
	 * 计算绝对时间(分钟)
	 * @param timeString
	 * @return
	 */
	private int getAbsoluteMinTimestamp(String timeString) {
		// return (int) ((parseTimestamp(timeString,
		// TimeZone.getTimeZone("GMT"))).getTime() / 60000);
		return (int) ((parseTimestamp(timeString, null)).getTime() / 60000);// 目前时区没转，应该按照GMT时区
	}

	/**
	 * 计算结束时间 yyyy-MM-dd HH:mm
	 * @param timeString
	 * @param tmDataTick
	 * @param relativeTimeRange
	 * @return
	 */
	private String getEndtime(String timeString, int tmDataTick, int relativeTimeRange) {
		long endTimeMill = parseTimestamp(timeString, null).getTime();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(endTimeMill);

		if (tmDataTick >= ChartCriteria.TICK_1D) {
			// 天粒度表 数据默认使用东八区数据,所以在查询数据时减八个小时时间。
			int hour = c.get(Calendar.HOUR_OF_DAY);
			if (hour < 8) {
				endTimeMill = endTimeMill / MILL_1DAY * MILL_1DAY - MILL_GMT0800 + MILL_1DAY;
			} else {
				endTimeMill = endTimeMill / MILL_1DAY * MILL_1DAY - MILL_GMT0800;
			}
		} else if (tmDataTick >= ChartCriteria.TICK_1H) {
			endTimeMill = endTimeMill / MILL_1HOUR * MILL_1HOUR;
		} else {
			if (relativeTimeRange > 60) {
				// endTimeMill = endTimeMill - MILL_1MINTER * 2;
				endTimeMill = endTimeMill / MILL_5MINTER * MILL_5MINTER;
			}
		}
		c.setTimeInMillis(endTimeMill);

		// criteria.setEndTime(c.getTime());
		Date d = new Date(endTimeMill);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(d);

	}
	/**
	 * 计算相对2013-01-01 00:00 时间(分钟)
	 * @param timeString
	 * @return
	 */
	private int getRelativeMinTimestamp(String timeString) {
		return getAbsoluteMinTimestamp(timeString) - UTC20130101MINTER;
	}

	public int getTmDataTick(int relativeTimeRange) {
		return ChartCriteria.getTmDataTick(relativeTimeRange);
	}

	public String getTablePostFix(int tick, String endtime) {
		Timestamp end_time = parseTimestamp(endtime, null);
		int betweenDay = (int) (new Date().getTime() / (24 * 3600 * 1000) - end_time.getTime() / (24 * 3600 * 1000));
		return getTablePostFix(tick, betweenDay);
	}
	
	/**
	 * 计算查询表后缀
	 * @param tick
	 * @param betweenDay
	 * @return
	 */
	private String getTablePostFix(int tick, int betweenDay) {
		if (betweenDay <= 30 && tick < TABLE_TICK_60) {
			return POSTFIX_1MINTER;
		} else if (betweenDay <= 90 && tick < TABLE_TICK_1440) {
			return POSTFIX_60MINTER;
		} else {
			return POSTFIX_1440MINTER;
		}
	}

	private Timestamp parseTimestamp(String timeString, TimeZone timeZone) {
		Timestamp time = null;
		String format1 = "yyyy-MM-dd HH:mm:ss";
		String format2 = "yyyy-MM-dd HH:mm";
		String format3 = "yyyy-MM-dd";

		try {
			time = parseFormattedTimestamp(timeString, format1, timeZone);
		} catch (ParseException ex) {
			try {
				time = parseFormattedTimestamp(timeString, format2, timeZone);
			} catch (ParseException ex2) {
				try {
					time = parseFormattedTimestamp(timeString, format3, timeZone);
				} catch (ParseException ex3) {
					//
				}

			}
		}

		return time;
	}

	private Timestamp parseFormattedTimestamp(String timeString, String format, TimeZone timeZone)
			throws ParseException {
		Date date = null;

		if (timeString == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (timeZone != null) {
			sdf.setTimeZone(timeZone);
		}

		date = sdf.parse(timeString);
		return (date == null) ? null : (new Timestamp(date.getTime()));
	}

	public String getInitSqlFile() {
		return "./src/main/resources/" + this.getClass().getSimpleName() + ".sql";
	}
	
	/**
	 * 执行sql 模版语句
	 * @param sql
	 * @param endtime
	 * @param timePeriod
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, String endtime, int timePeriod) throws SQLException {
		DBUtil u = new DBUtil();
		Connection conn = u.getConnection();
		Statement stmt = conn.createStatement();
		// 计算sql语句中的开始时间和结束时间戳以分钟为单位，以2013年为基准
		int tmTick = getTmDataTick(timePeriod);
		endtime= getEndtime(endtime,tmTick,timePeriod);
		int sql_endtime = getRelativeMinTimestamp(endtime);
		int sql_begintime = sql_endtime - timePeriod;
		int sql_begintime_abs = sql_begintime + UTC20130101MINTER;
		// 计算查询时间粒度
		// 计算表后缀
		String table_postfix = getTablePostFix(tmTick, endtime);
		String sql_tmTick = "FROM_UNIXTIME((" + sql_begintime_abs + " +floor(floor((timestamp-" + sql_begintime + ")/"
				+ tmTick + "))*" + tmTick + ")*60) as tmTick";
		sql = sql.replace("$sql_tmTick", sql_tmTick);
		sql = sql.replace("$sql_begintime", String.valueOf(sql_begintime));
		sql = sql.replace("$sql_endtime", String.valueOf(sql_endtime));
		sql = sql.replace("$table_postfix", table_postfix);
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("sql=  " + sql);

		return rs;

	}

	private String currUserName;

	protected ChartBean getCharBean(ChartTestInput input) {
		CloseableHttpClient client;
		HttpClientFactory f;
		f = new HttpClientFactory();
		WebDriver driver = null;
		driver = new PhantomJSDriver();
		// 让浏览器访问 server 报表
		driver.get(input.getLoginUrl());
		LoginPage loginPage = new LoginPage(driver);
		// 判断是否需要登陆
		loginPage.login(input.getUserName(), input.getPassword());

		Set<Cookie> x = driver.manage().getCookies();
		for (Cookie item : x) {
			BasicClientCookie y = new BasicClientCookie(item.getName(), item.getValue());
			y.setPath(item.getPath());
			y.setExpiryDate(item.getExpiry());
			y.setDomain(item.getDomain());
			y.setAttribute(item.getName(), item.getValue());
			f.getCookieStore().addCookie(y);
		}
		client = f.createSSLClientDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(input.getChartUrl());
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iter = input.getParams().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			formparams.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}

		UrlEncodedFormEntity uefEntity;
		CloseableHttpResponse response = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);

			response = client.execute(httppost);
			System.out.println("=======result==begin====");

			System.out.println("executing request " + httppost.getURI());

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println("StatusCode=" + response.getStatusLine().getStatusCode());
				String content = EntityUtils.toString(entity, "ISO-8859-1");
				System.out.println(content);
				ChartBean b = JsonUtil.get(content, ChartBean.class);
				System.out.println(b);
				return b;
			}
		} catch (Exception e) {
			System.out.println("--getCharBean--" + e.getMessage());
		} finally {
			System.out.println("=======result===end=====");
			// 关闭连接,释放资源
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			driver.quit();
		}
		return null;
	}

}
