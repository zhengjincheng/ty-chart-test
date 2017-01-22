package com.tingyun.chart.api.bean;

import java.util.List;

public class Dataset {
	private Head head;

	private List<Data> data;

	public void setHead(Head head) {
		this.head = head;
	}

	public Head getHead() {
		return this.head;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Data> getData() {
		return this.data;
	}

}