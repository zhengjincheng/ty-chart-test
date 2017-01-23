package com.tingyun.chart.database.testcase;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.bean.Series;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationDatabaseTopnTest extends TingyunChartTestCase {

	private String endtime = ApplicationDatabaseTestConst.endtime;
	private String applicationId = ApplicationDatabaseTestConst.applicationId;
	//private String instanceId = ApplicationDatabaseTestConst.instanceId;
	
	private String databaseId = ApplicationDatabaseTestConst.databaseId;

	@Test
	public void test_30min() throws SQLException {
		// 设置结束时间
		String endtime = this.endtime;
		// 查询时间跨度
		int timePeriod = 30;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}

	@Test
	public void test_1h() throws SQLException {
		// 设置结束时间
		String endtime = this.endtime;
		// 查询时间跨度
		int timePeriod = 60;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}

	@Test
	public void test_1D() throws SQLException {
		// 设置结束时间
		String endtime = this.endtime;
		// 查询时间跨度
		int timePeriod = 60 * 24;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}

	@Test
	public void test_15D() throws SQLException {
		// 设置结束时间
		String endtime = this.endtime;
		// 查询时间跨度
		int timePeriod = 60 * 24 * 15;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}

	public void queryByEndtimeAndTimePeriod(String endtime, int timePeriod) throws SQLException {

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1")
				.chartid("application-database-topn").put("applicationId", applicationId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据 为啥除以600？
		String sql = "select  sum(duration_total)/$tmTick/600 as duration_wall,sum(duration_total)/sum(count)/1000 as duration_total,max(duration_max)/1000 as duration_max,min(duration_min)/1000 as duration_min,sum(count) as count,$sql_tmTick from NL_APP_DATABASE$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_id = $applicationId  AND count > 0	AND sql_metric_id in ($databaseId )   group by tmTick order by tmTick asc";
		sql = sql.replace("$databaseId", databaseId);
		sql = sql.replace("$applicationId", applicationId);

		
		sql = createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		String param = ("{\"databaseId\":$databaseId}").replace("$databaseId", databaseId);
		Series s=b.getSeriesByParam(param);
		
		while (rs.next() ) {
			//钟墙时间比
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(1).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()), s.getData().get(i).getY());
			//耗时
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(2).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()), s.getData().get(i).getTooltipObject().getData().get(1).getValue());
			//最大值 最小值因为以时间UNIT_MESSAGE_SECOND 为单位在tooptip 被格式成 DecimalFormat df = new DecimalFormat("0.000"); 所以设置精度后不再转为 .floatValue()
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(3).setScale(3,BigDecimal.ROUND_DOWN)), s.getData().get(i).getTooltipObject().getData().get(2).getValue());
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(4).setScale(3,BigDecimal.ROUND_DOWN)), s.getData().get(i).getTooltipObject().getData().get(3).getValue());
			//标准差todo
			//Assert.assertEquals(String.valueOf(rs.getBigDecimal(6).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()),b.getSeries().get(1).getData().get(i).getTooltipObject().getData().get(4).getValue());
			//调用次数
			Assert.assertEquals(String.valueOf(rs.getInt(5)), s.getData().get(i).getTooltipObject().getData().get(4).getValue());
			i++;
		}
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}
}

