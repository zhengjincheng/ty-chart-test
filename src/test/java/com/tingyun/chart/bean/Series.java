package com.tingyun.chart.bean;

import java.util.List;

public class Series {
	private String name;

	private String type;

	private String color;

	private List<Data> data;

	private String params;

	private Marker marker;

	public void setName(String name) {

		this.name = name;

	}

	public String getName() {

		return this.name;

	}

	public void setType(String type) {

		this.type = type;

	}

	public String getType() {

		return this.type;

	}

	public void setColor(String color) {

		this.color = color;

	}

	public String getColor() {

		return this.color;

	}

	public void setData(List<Data> data) {

		this.data = data;

	}

	public List<Data> getData() {	

		return this.data;

	}

	public void setParams(String params) {

		this.params = params;
	
	}

	public String getParams() {

		return this.params;

	}

	public void setMarker(Marker marker) {

		this.marker = marker;

	}

	public Marker getMarker() {

		return this.marker;

	}

	@Override
	public String toString() {
		return "Series [name=" + name + ", type=" + type + ", color=" + color + ", data=" + data + ", params=" + params
				+ ", marker=" + marker + "]";
	}
	
}
