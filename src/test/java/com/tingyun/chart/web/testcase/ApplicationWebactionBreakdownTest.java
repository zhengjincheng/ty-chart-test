package com.tingyun.chart.web.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationWebactionBreakdownTest extends TingyunChartTestCase {

	private String endtime = ApplicationWebactionTestConst.endtime;
	private String applicationId = ApplicationWebactionTestConst.applicationId;
	private String instanceId = ApplicationWebactionTestConst.instanceId;
	private String actionId = ApplicationWebactionTestConst.actionId;
	private String component_metric_id = "2730312353";

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
				.chartid("application-webaction-breakdown").put("applicationId", applicationId)
				.put("instanceId", instanceId).put("actionId", actionId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select  round(round(sum(duration_total_exclusive)/sum(count),4),3) as duration_total_exclusive,max(duration_max) as duration_max,min(duration_min) as duration_min,round(sqrt((sum(duration_squaresum) - sum(duration_total)  * sum(duration_total) / sum(count))/(sum(count)-1)),3) as duration_square,sum(count) as count,$sql_tmTick,component_metric_id as component_metric_id  from NL_APP_WEB_ACTION_COMPONENT$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_instance_id in ( $instanceId)  and count > 0	 AND action_id in ( $actionId ) AND component_metric_id =$component_metric_id group by tmTick,component_metric_id order by tmTick asc";
		sql = sql.replace("$instanceId", instanceId);
		sql = sql.replace("$actionId", actionId);
		sql = sql.replace("$component_metric_id", component_metric_id);

		sql = createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			// 平均响应时间
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			// 最大值
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(2).setScale(1)),
					b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			// 最小值
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(3).setScale(1)),
					b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());
			
			// todo 平方差
			 Assert.assertEquals(String.valueOf(rs.getDouble(4)),
			 b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(3).getValue());

			i++;
		}
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}
}
