package com.tingyun.chart.testcase;

import java.util.HashMap;

public class TimeRangeRegister {
	
	 static HashMap<String,TimeRange> trs=new HashMap<String,TimeRange>();
	 static {
		 TimeRange tr1=new TimeRange(TimeRange.MIN_30,30,1);
		 TimeRange tr2=new TimeRange(TimeRange.HOUR_1,60,2);
		 trs.put(tr1.getKey(),tr1);
		 trs.put(tr2.getKey(),tr2);
	 }
	 public static TimeRange  get(String key){
		return trs.get(key);
	}
}
