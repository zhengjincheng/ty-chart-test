package com.tingyun.chart.database.testcase;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;
import com.tingyun.chart.web.testcase.ApplicationWebactionTestConst;

import junit.framework.Assert;

public class ApplicationDatabaseOpThroughputTest extends TingyunChartTestCase {

	private String endtime = ApplicationWebactionTestConst.endtime;
	private String applicationId = ApplicationWebactionTestConst.applicationId;
	private String instanceId = ApplicationWebactionTestConst.instanceId;

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
				.chartid("application-database-op-throughput").put("applicationId", applicationId)
				.put("instanceId", instanceId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据  只验证 type =1 
		String sql = "select sum(count)/$tmTick as count_throughtout,sum(count) as count,$sql_tmTick,operation_type as operation_type from NL_APP_DATABASE$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_instance_id in ( $instanceId)  AND count > 0	AND operation_type=1   group by tmTick,operation_type order by tmTick asc";
		sql = sql.replace("$instanceId", instanceId);

		sql = createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			//吞吐率
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(1).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue()), b.getSeries().get(0).getData().get(i).getY());			
			//调用次数
			Assert.assertEquals(String.valueOf(rs.getInt(2)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			

			i++;
		}
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}
}


