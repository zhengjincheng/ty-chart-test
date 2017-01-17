package com.tingyun.chart.jvm.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class JvmSessionTest extends TingyunChartTestCase {

	private String endtime = JvmTestConst.endtime;
	private String applicationId=JvmTestConst.applicationId;
	private String vm_id=JvmTestConst.vm_id;
	private String context_path_id="2123";
	
	public void queryByEndtimeAndTimePeriod(String endtime, int timePeriod) throws SQLException {

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("jvm-session")
				.put("applicationId", applicationId).put("context_path_id", "2123").put("vm_id", vm_id).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select      active_sessions_total as active_sessions_total,CASE sum(active_sessions_total) WHEN 0 THEN 0 ELSE (sum(avg_alive_time_total) / sum(active_sessions_total) ) END as avg_session_time,expired_sessions_total as expired_sessions_total,rejected_sessions_total as rejected_sessions_total,$sql_tmTick  from NL_VM_HTTP_SESSIONS$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = $vm_id  and context_path_id = $context_path_id and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$vm_id", vm_id);
		sql = sql.replace("$context_path_id", context_path_id);

		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		
		int i = 0;
		while (rs.next()) {
			Assert.assertEquals("活跃会话数", b.getSeries().get(0).getName());
			Assert.assertEquals("过期会话数", b.getSeries().get(1).getName());
			Assert.assertEquals("拒绝会话数", b.getSeries().get(2).getName());

			Assert.assertEquals(String.valueOf(rs.getInt(1)), b.getSeries().get(0).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getInt(4)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			Assert.assertEquals(String.valueOf(rs.getInt(7)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
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
}
