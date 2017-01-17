package com.tingyun.chart.jvm.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class JvmThreadPoolTest extends TingyunChartTestCase {

	private String endtime = JvmTestConst.endtime;
	private String applicationId=JvmTestConst.applicationId;
	private String vm_id=JvmTestConst.vm_id;
	private String thread_pool_id=JvmTestConst.thread_pool_id;
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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("jvm-thread-pool")
				.put("applicationId", applicationId).put("vm_id", vm_id).put("thread_pool_id", thread_pool_id).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select  sum(threads_busy_total)/sum(count) as thread_pool_active,MAX(threads_busy_max) as thread_pool_active_max,MIN(threads_busy_min) as thread_pool_active_min,(sum(threads_total) - sum(threads_busy_total))/sum(count) as thread_pool_idle,MAX(convert(threads_total,signed) - convert(threads_busy_max,signed)) as thread_pool_idle_max,MIN(convert(threads_total,signed) - convert(threads_busy_min,signed)) as thread_pool_idle_min,$sql_tmTick  from NL_VM_THREAD_POOL$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = $vm_id and thread_pool_id = $thread_pool_id and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$vm_id", vm_id);
		sql = sql.replace("$thread_pool_id", thread_pool_id);
		sql=createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			Assert.assertEquals(String.valueOf(rs.getInt(1)), b.getSeries().get(0).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getInt(4)), b.getSeries().get(1).getData().get(i).getY());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
