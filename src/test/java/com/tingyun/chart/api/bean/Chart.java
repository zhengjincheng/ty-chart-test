package com.tingyun.chart.api.bean;

import java.util.List;

public class Chart {
	private String timestamp;

	private String beginTime;

	private String endTime;

	private String timeGranularity;

	private List<Dataset> dataset;

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getBeginTime() {
		return this.beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setTimeGranularity(String timeGranularity) {
		this.timeGranularity = timeGranularity;
	}

	public String getTimeGranularity() {
		return this.timeGranularity;
	}

	public void setDataset(List<Dataset> dataset) {
		this.dataset = dataset;
	}

	public List<Dataset> getDataset() {
		return this.dataset;
	}

}