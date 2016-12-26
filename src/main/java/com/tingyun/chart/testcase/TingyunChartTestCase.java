package com.tingyun.chart.testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
import com.tingyun.page.impl.LoginPage;

import junit.framework.TestCase;

public class TingyunChartTestCase extends TestCase {
	protected ChartBean getCharBean(ChartTestInput input) {
		CloseableHttpClient client;
		HttpClientFactory f;
		f = new HttpClientFactory();
		WebDriver driver = null;
		driver = new PhantomJSDriver();
		// 让浏览器访问 server 报表
		driver.get(input.getLoginUrl());
		LoginPage loginPage = new LoginPage(driver);
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
			// HttpPost httppost = new HttpPost("https://www.xrcj.cn");
			// 创建参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> iter = input.getParams().entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				formparams.add(new BasicNameValuePair((String) entry.getKey(),(String) entry.getValue()));
			}
			
			UrlEncodedFormEntity uefEntity;
			CloseableHttpResponse response=null;
			try {
				uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httppost.setEntity(uefEntity);
		
				 response = client.execute(httppost);
					System.out.println("=======result==begin====");

					System.out.println("executing request " + httppost.getURI());

					HttpEntity entity = response.getEntity();
					if (entity != null) {
						System.out.println("StatusCode="+response.getStatusLine().getStatusCode());
						String content=EntityUtils.toString(entity, "ISO-8859-1");
						System.out.println(content);
						ChartBean b =JsonUtil.get(content, ChartBean.class);
						System.out.println(b);
						return b;
					}
			}catch(Exception e){
				System.out.println("--getCharBean--" + e.getMessage());
			} finally {
				System.out.println("=======result===end=====");
				// 关闭连接,释放资源
				if (response!=null){
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
