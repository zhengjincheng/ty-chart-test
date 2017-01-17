package com.tingyun.chart.jvm.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;
/**
 * Heap memory Usage(MB)
 * @author Administrator
 *
 */
public class ApplicationJvmHeapTest extends TingyunChartTestCase {

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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-jvm-heap")
				.put("applicationId", applicationId).put("vm_id", vm_id).put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select round(round(sum(heap_used_total)/sum(count),4),3) as heap_used_total,MAX(heap_used_max) as heap_used_total_max,MIN(heap_used_min) as heap_used_total_min,round(round(sum(heap_max_total)/sum(count),4),3) as heap_max_total,MAX(heap_max_max) as heap_max_total_max,MIN(heap_max_min) as heap_max_total_min,round(round(sum(heap_committed_total)/sum(count),4),3) as heap_committed_total,MAX(heap_committed_max) as heap_committed_total_max,MIN(heap_committed_min) as heap_committed_total_min,$sql_tmTick  from NL_VM_MEMORY$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = $vm_id  and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$vm_id", vm_id);
		sql=createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			// todo
			Assert.assertEquals(String.valueOf(rs.getFloat(1)), b.getSeries().get(0).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getFloat(4)), b.getSeries().get(1).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getFloat(7)), b.getSeries().get(2).getData().get(i).getY());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
