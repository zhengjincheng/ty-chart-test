package com.tingyun.chart.go.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class GoMemoryOpMcacheSysInuseTest extends TingyunChartTestCase {
	private String endtime = "2017-01-03 13:30";

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
				.chartid("go-memory-op-mcache-sys-inuse").put("applicationId", "127050").put("vm_id", "424")
				.put("timePeriod", String.valueOf(timePeriod)).put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据 round(,1)
		String sql = "select  round(sum(mcache_sys_total)/sum(count),1) as countTotal,sum(mcache_sys_max)/sum(count) as countMax,sum(mcache_sys_min)/sum(count) as countMin,round(sum(mcache_inuse_total)/sum(count),1) as countTotalTo,sum(mcache_inuse_max)/sum(count) as countMaxTo,sum(mcache_inuse_min)/sum(count) as countMinTo,$sql_tmTick  from NL_VM_GO_MEMORY_OP$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = 424 and count > 0	 group by tmTick order by tmTick asc";
		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		int i = 0;
		while (rs.next()) {
			// 对结果进行比较
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			Assert.assertEquals(String.valueOf(rs.getDouble(4)), b.getSeries().get(1).getData().get(i).getY());

			i++;
		}
		//
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}

}
