package com.tingyun.chart.go.testcase;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class GoGctimeChartTest extends TingyunChartTestCase {
	@Test
	/**
	 * 按照当前30分钟内数据
	 * 1.删除 30分钟内数据库中的数据
	 * 2.按照当前时间插入30分钟内的数据
	 * 3.查询
	 * 4.验证
	 */
	public void test30min() {
		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("go-gctime")
				.put("applicationId", "127118").put("vm_id", "422").put("timePeriod", "30")
				.put("timeType", "1");
		ChartBean b = getCharBean(input);
		Assert.assertEquals(1, b.getSeries().get(0).getData().size());
		Assert.assertEquals(1, b.getSeries().size());
		Assert.assertEquals(3.0, b.getSeries().get(0).getData().get(0).getY());
		Assert.assertEquals("毫秒", b.getYAxis().get(0).getTickUnit());
	}
}
