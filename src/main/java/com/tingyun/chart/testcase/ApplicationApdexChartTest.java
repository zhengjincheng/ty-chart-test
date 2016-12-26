package com.tingyun.chart.testcase;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;

public class ApplicationApdexChartTest extends TingyunChartTestCase {

	@Test
	public void test1() {
		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-apdex")
				.put("applicationId", "127032").put("instanceId", "0").put("timeType", "1").put("timePeriod", "129600");
		ChartBean b = getCharBean(input);
	}

}
