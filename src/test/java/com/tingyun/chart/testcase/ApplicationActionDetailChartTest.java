package com.tingyun.chart.testcase;

import org.junit.Test;

import com.tingyun.chart.bean.ChartBean;

public class ApplicationActionDetailChartTest extends TingyunChartTestCase {
	@Test
	public void test1() {
		ChartTestInput input = ChartTestInput.build().userName("sina").passWord("1").chartid("application-action-detail")
				.put("applicationId", "127032").put("instanceId", "0").put("detailId", "2147493367").put("timePeriod", "30");
		ChartBean b = getCharBean(input);
	}
}
