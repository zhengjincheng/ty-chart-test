package com.tingyun.chart.go.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class GoProcessDescriptorsTest extends TingyunChartTestCase {
	private String endtime = "2016-12-30 08:51";

	
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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("go-process-descriptors")
				.put("applicationId", "127050").put("vm_id", "424").put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());
		
		// 获取sql的数据
		String sql = "select  round(sum(fd_total)/sum(count),1) as fd_count,$sql_tmTick  from NL_VM_GO_PROCESS$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and vm_id = 424 and count > 0	 group by tmTick order by tmTick asc";
		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		int i = 0;
		while (rs.next()) {
			// 对结果进行比较
			//次数
			Assert.assertEquals(String.valueOf(rs.getDouble(1)), b.getSeries().get(0).getData().get(i).getY());
			i++;
		}
		//
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
	
}
