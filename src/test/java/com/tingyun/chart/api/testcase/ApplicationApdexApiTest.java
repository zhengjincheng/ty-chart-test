package com.tingyun.chart.api.testcase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.tingyun.chart.api.bean.ChartApiBean;
import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.client.HttpClientFactory;
import com.tingyun.chart.client.JsonUtil;
import com.tingyun.chart.testcase.TingyunChartTestCase;

public class ApplicationApdexApiTest extends TingyunChartTestCase{
	
	
	
	@Test
	public void test_30min() throws SQLException {
		
		//ApiTestInput input=ApiTestInput.build().chartid("application-apdex").xAuthKey("7e3pdwga").userID("1759").applicationId("127071");
		ApiTestInput input=ApiTestInput.build().chartid("application-apdex").xAuthKey("2e6aw2f8").userID("1580942").applicationId("139726");

		HttpClientFactory f=new HttpClientFactory();

		CloseableHttpClient client = f.createSSLClientDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(input.getChartUrl());
		httppost.setHeader("X-Auth-Key", input.getxAuthKey());
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
				ChartApiBean b = JsonUtil.get(content, ChartApiBean.class);
				System.out.println(b);
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
			// driver.quit();
		}
		
	}
}
