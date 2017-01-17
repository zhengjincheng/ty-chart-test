package com.tingyun.chart.bean;

public class TooltipData {
	private String title;
	private String value;
	private String unit;

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "TooltipData [title=" + title + ", value=" + value + ", unit=" + unit + "]";
	}
	
	
}
