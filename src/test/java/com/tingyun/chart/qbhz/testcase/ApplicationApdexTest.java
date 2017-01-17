package com.tingyun.chart.qbhz.testcase;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class ApplicationApdexTest extends TingyunChartTestCase {

	private String endtime = "2016-12-24 12:30";

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

		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-apdex")
				.put("applicationId", "127050").put("timePeriod", String.valueOf(timePeriod))
				.put("timeType", "2").put("endTime", endtime);
		// 获得接口数据
		ChartBean b = getCharBean(input);
		System.out.println(input.toString());

		// 获取sql的数据
		String sql = "select    ROUND(ROUND((sum(satisfied_count)+sum(tolerable_count)/2)/sum(success_count),4),3) as apdex,sum(satisfied_count) as satisfied_count,sum(tolerable_count) as tolerable_count,sum(frustrated_count) as frustrated_count,sum(success_count) as success_count,$sql_tmTick  from NL_APP_APPLICATION_OVERVIEW$table_postfix	 where  timestamp >= $sql_begintime AND timestamp < $sql_endtime and application_id = 127050 and count > 0	 group by tmTick order by tmTick asc";
		ResultSet rs = executeQuery(sql, endtime, timePeriod);
		int i = 0;
		while (rs.next()) {
			// todo
			Assert.assertEquals(String.valueOf(rs.getFloat(1)), b.getSeries().get(0).getData().get(i).getY());
			//满意次数
			Assert.assertEquals(String.valueOf(rs.getInt(2)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(1).getValue());
			//可忍受的次数
			Assert.assertEquals(String.valueOf(rs.getInt(3)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(2).getValue());
			//令人沮丧的次数
			Assert.assertEquals(String.valueOf(rs.getInt(4)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(3).getValue());
			//成功访问次数
			Assert.assertEquals(String.valueOf(rs.getInt(5)), b.getSeries().get(0).getData().get(i).getTooltipObject().getData().get(4).getValue());

			i++;
		}
		if (i==0){
			Assert.fail("数据库中未查到数据"+sql);
		}
	}
}
