package com.tingyun.chart.qbhz.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationWebactionTopnTest extends TingyunChartTestCase {
	
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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-webaction-topn")
				.put("applicationId", applicationId).put("instanceId", instanceId).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());
		// 获取action_id的数据
		String sql_action="SELECT DISTINCT 	(a.action_id) from (SELECT	0 AS dimension,		action_id AS action_id,		sum(duration_total) / 30 AS duration_wall,	sum(count) AS point_count	FROM	NL_APP_WEB_ACTION$table_postfix	WHERE	1 > 0	AND TIMESTAMP >= $sql_begintime	AND TIMESTAMP < $sql_endtime	AND application_id = 127070	AND application_instance_id IN ($instanceId)	AND count > 0	GROUP BY	action_id	ORDER BY	duration_wall DESC,	action_id) a";
		sql_action = sql_action.replace("$instanceId", instanceId);
		sql_action=createQuery(sql_action, endtime, timePeriod);

		// 获取sql的数据
		String sql = "select    round(round(sum(duration_total)/$tmTick/600,4),3) as duration_wall,sum(count) as count,round(round(sum(duration_total)/sum(count)/1000,4),3) as duration_total,round(round(max(duration_max)/1000,4),3) as duration_max,round(round(min(duration_min)/1000,4),3) as duration_min,round(round(sqrt((sum(duration_squaresum) - sum(duration_total)  * sum(duration_total) / sum(count))/(sum(count)-1))/1000,4),3) as duration_square,$sql_tmTick,action_id as action_id  from NL_APP_WEB_ACTION$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_instance_id in ( $instanceId)  and count > 0	 AND action_id in ( 54209 ) group by tmTick,action_id order by tmTick asc";
		sql = sql.replace("$instanceId", instanceId);
		sql = sql.replace("$sql_action", sql_action);

		sql=createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			// 钟墙比
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			// 访问量
			Assert.assertEquals(String.valueOf(rs.getInt(2)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			// 平均响应时间
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(3)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());
			//最大值
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(4)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(3).getValue());
			//最小值
			Assert.assertEquals(String.valueOf(rs.getBigDecimal(5)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(4).getValue());
			//todo 平方差
			//Assert.assertEquals(String.valueOf(rs.getBigDecimal(6)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(5).getValue());


			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
