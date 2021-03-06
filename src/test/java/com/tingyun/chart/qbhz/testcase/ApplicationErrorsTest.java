package com.tingyun.chart.qbhz.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationErrorsTest extends TingyunChartTestCase {
	
	private String endtime = ApplicationTestConst.endtime;
	private String applicationId=ApplicationTestConst.applicationId;
	private String instanceId=ApplicationTestConst.instanceId;

	

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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-errors")
				.put("applicationId", applicationId).put("instanceId", instanceId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select  round(round(sum(error_count)/sum(count),2),1) as error_percent,sum(error_count) as error_count,sum(count) as count,$sql_tmTick  from NL_APP_APPLICATION_OVERVIEW$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_instance_id in ( $instanceId)  and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$instanceId", instanceId);

		sql=createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			// 错误率
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			// 错误量
			Assert.assertEquals(String.valueOf(rs.getInt(2)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			// 访问量
			Assert.assertEquals(String.valueOf(rs.getInt(3)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
