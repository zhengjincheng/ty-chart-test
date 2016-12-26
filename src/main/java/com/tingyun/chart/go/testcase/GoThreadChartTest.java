package com.tingyun.chart.go.testcase;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class GoThreadChartTest extends TingyunChartTestCase {
	@Test
	public void test1() {
		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("go-thread")
				.put("applicationId", "127118").put("vm_id", "422").put("timePeriod", "4320")
				.put("timeType", "1");
		ChartBean b = getCharBean(input);
		Assert.assertEquals(1482472800000L, b.getSeries().get(0).getData().get(0).getX());
	}
}
