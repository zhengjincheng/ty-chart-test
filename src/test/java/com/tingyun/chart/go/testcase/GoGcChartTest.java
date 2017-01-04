package com.tingyun.chart.go.testcase;

import com.tingyun.chart.bean.ChartBean;
import com.tingyun.chart.testcase.ChartTestInput;
import com.tingyun.chart.testcase.TingyunChartTestCase;

import junit.framework.Assert;

public class GoGcChartTest extends TingyunChartTestCase {
	public void test30min() {
		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("go-gc")
				.put("applicationId", "127118").put("vm_id", "422").put("timePeriod", "30").put("timeType", "1");
		ChartBean b = getCharBean(input);
		Assert.assertEquals(1, b.getSeries().get(0).getData().size());//第一个性能指标，数据的个数
		Assert.assertEquals(3, b.getSeries().size());//几个性能指标
		Assert.assertEquals(1, b.getSeries().get(3).getData().get(0).getY());//第一个性能指标的 第一个数据
	}

	
}
