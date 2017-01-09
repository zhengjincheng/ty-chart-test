package com.tingyun.chart.bean;

import java.util.List;

public class Tooltip {
	private String title ;
	private List<TooltipData> data ;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<TooltipData> getData() {
		return data;
	}
	public void setData(List<TooltipData> data) {
		this.data = data;
	}
	
	
}
