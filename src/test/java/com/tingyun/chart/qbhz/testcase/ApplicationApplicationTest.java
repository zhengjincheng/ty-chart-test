package com.tingyun.chart.qbhz.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationApplicationTest extends TingyunChartTestCase {

	private String endtime = "2016-12-30 11:10";

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
		int timePeriod = 60*24;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}
	@Test
	public void test_15D() throws SQLException {
		// 设置结束时间
		String endtime = this.endtime;
		// 查询时间跨度
		int timePeriod = 60*24*15;
		queryByEndtimeAndTimePeriod(endtime, timePeriod);
	}

	public void queryByEndtimeAndTimePeriod(String endtime, int timePeriod) throws SQLException {

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-application")
				.put("applicationId", "127050").put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select   case when sum(mongodb_count) =0 then 0 else round(round(sum(mongodb_time_total)/sum(success_count),4),3) end as mongodb_time_total,sum(mongodb_count) as mongodb_count,case when sum(redis_count) = 0 then 0 else sum(redis_time_total)/sum(success_count) end as redis_time_total,sum(redis_count) as redis_count,case when sum(memcached_count) = 0 then 0 else sum(memcached_time_total)/sum(success_count) end as memcached_time_total,sum(memcached_count) as memcached_count,case when sum(external_count) =0 then 0 else sum(external_time_total)/sum(success_count) end as external_time_total,sum(external_count) as external_count,case when sum(database_count) =0 then 0 else round(round(sum(database_time_total)/sum(success_count),4),3) end as database_time_total,sum(database_count) as database_count,case when sum(count) = 0 then 0 else sum(application_time_total)/sum(success_count) end as application_time_total,sum(count) as count,sum(success_count) as success_count,case when sum(queue_count) = 0 then 0 else sum(queue_time_total)/sum(success_count) end  as queue_time_total,sum(queue_count) as queue_count,$sql_tmTick  from NL_APP_APPLICATION_OVERVIEW$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_id = 127050 and count > 0	 group by tmTick order by tmTick asc";
		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		int i = 0;
		int database_time_total_i = 0;
		//1.如果性能指标的值为0时不展示，2.单位 秒，毫秒的问题 
		while (rs.next()) {
			
			//数据库响应时间 
			if (rs.getDouble(9)!=0){
				Assert.assertEquals(String.valueOf(rs.getDouble(9)), b.getSeriesByName("数据库调用时间").getData().get(database_time_total_i).getY());
				database_time_total_i++;
			}
			//应用层时间
			//Assert.assertEquals(String.valueOf(rs.getDouble(11)), b.getSeriesByName("应用层时间").getData().get(i).getY());
			//MongoDB
			//Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeriesByName("MongoDB响应时间").getData().get(i).getY());
			//Redis
			//Assert.assertEquals(String.valueOf(rs.getDouble(3)), b.getSeriesByName("Redis响应时间").getData().get(i).getY());
			// 外部服务时间
			//Assert.assertEquals(String.valueOf(rs.getDouble(7)), b.getSeriesByName("外部服务时间").getData().get(i).getY());
			

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
