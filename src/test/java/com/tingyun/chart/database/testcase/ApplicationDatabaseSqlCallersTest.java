package com.tingyun.chart.database.testcase;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationDatabaseSqlCallersTest  extends TingyunChartTestCase {

	private String endtime = ApplicationDatabaseTestConst.endtime;
	private String applicationId = ApplicationDatabaseTestConst.applicationId;
	private String databaseId = ApplicationDatabaseTestConst.databaseId;
	private String sqlmetricId = "1919595";
	private String callerActionId = "54209";

	
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
				.chartid("application-database-sql-callers").put("applicationId", applicationId)
				.put("databaseId", databaseId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());
		
		
		String avg_sql="select truncate(sum(duration_total),3) as duration_total from (select sum(duration_total) as duration_total from NL_APP_DATABASE$table_postfix where 1 > 0 AND timestamp >= $sql_begintime AND timestamp < $sql_endtime  and sql_metric_id in ($sqlmetricId) AND application_id =  $applicationId AND count > 0  group by caller_action_id) t";
		avg_sql = avg_sql.replace("$applicationId", applicationId);
		avg_sql = avg_sql.replace("$sqlmetricId", sqlmetricId);
		avg_sql = createQuery(avg_sql, endtime, timePeriod);
		ResultSet rs1 = executeQuery(avg_sql);
		Double avg=1.0;
		while (rs1.next()) {
			 avg= rs1.getDouble(1);
		}
		
		String sql = "select   sum(duration_total)*100/$avg as duration_total_caller_percent,sum(duration_total)/1000 as duration_total,sum(duration_total)/sum(count)/1000 as duration_total_average,sum(count) as count,caller_action_id as caller_action_id from NL_APP_DATABASE$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime AND  sql_metric_id in ($sqlmetricId ) AND application_id = $applicationId AND count > 0  group by caller_action_id order by duration_total_caller_percent asc";
		sql = sql.replace("$avg", String.valueOf(avg));

		sql = sql.replace("$databaseId", databaseId);
		sql = sql.replace("$applicationId", applicationId);
		sql = sql.replace("$sqlmetricId", sqlmetricId);
		sql = sql.replace("$callerActionId", callerActionId);

		
		sql = createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		//只验证第一行数据
		while (rs.next() && i<1) {
			//耗时百分比
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(1).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()), b.getSeries().get(0).getData().get(i).getY());			
			//调用耗时
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(2).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			//平均耗时
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(3).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());
			//访问量
			Assert.assertEquals(String.valueOf(rs.getInt(4)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(3).getValue());

			i++;
		}
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}
}


