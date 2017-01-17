package com.tingyun.chart.go.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationGoRuntimeFreesTest extends TingyunChartTestCase {
	private String endtime = GoTestConst.endtime;
	private String applicationId = GoTestConst.applicationId;
	private String vm_id = GoTestConst.vm_id;

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
				.chartid("application-go-runtime-frees").put("applicationId", applicationId).put("vm_id", vm_id)
				.put("timePeriod", String.valueOf(timePeriod)).put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据 round(,1)
		String sql = "select  round( sum(frees_total)/$tmTick,1) as countTotal,$sql_tmTick  from NL_VM_GO_RUNTIME$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = $vm_id and count > 0	 group by tmTick order by tmTick asc";
		sql = sql.replace("$vm_id", vm_id);
		sql=createQuery(sql, endtime, timePeriod);
		ResultSet rs = executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			// 对结果进行比较
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());

			i++;
		}
		//
		if (i == 0) {
			Assert.fail("数据库中未查到数据" + sql);
		}
	}

}
