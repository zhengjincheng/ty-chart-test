package com.tingyun.chart.jvm.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;
/**
 * CMS Perm Gen
 * @author Administrator
 *
 */
public class JvmMemoryPoolTest extends TingyunChartTestCase {

	private String endtime = JvmTestConst.endtime;
	private String applicationId=JvmTestConst.applicationId;
	private String vm_id=JvmTestConst.vm_id;

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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("jvm-memory-pool")
				.put("applicationId", applicationId).put("vm_id", vm_id).put("memory_pool_id", "20").put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select round(round(sum(memory_used_total)/sum(count),4),3) as memory_pool_used,MAX(memory_used_max) as memory_pool_used_max,MIN(memory_used_min) as memory_pool_used_min,sum(memory_max_total)/sum(count) as memory_pool_max,MAX(memory_max_max) as memory_pool_max_max,MIN(memory_max_min) as memory_pool_max_min,sum(memory_committed_total)/sum(count) as memory_pool_committed,MAX(memory_committed_max) as memory_pool_committed_max,MIN(memory_committed_min) as memory_pool_committed_min,$sql_tmTick  from NL_VM_MEMORY_POOL$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = $vm_id and memory_pool_id = 20 and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$vm_id", vm_id);

		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		int i = 0;
		while (rs.next()) {
			// 对结果进行比较
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getDouble(4)), b.getSeries().get(1).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getDouble(7)), b.getSeries().get(2).getData().get(i).getY());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
