package com.tingyun.chart.testcase;

public class TimeRange {
	public static final String MIN_30="MIN_30";
	public static final String HOUR_1="HOUR_1";

	private String key;
	private int  range;
	private int  tick;
	
	public TimeRange(String key, int range, int tick) {
		this.key = key;
		this.range = range;
		this.tick = tick;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getTick() {
		return tick;
	}
	public void setTick(int tick) {
		this.tick = tick;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
