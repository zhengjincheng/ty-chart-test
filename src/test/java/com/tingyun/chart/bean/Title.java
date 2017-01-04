package com.tingyun.chart.bean;

public class Title {
	private Style style;

	private String text;

	public void setStyle(Style style) {

		this.style = style;

	}

	public Style getStyle() {

		return this.style;

	}

	public void setText(String text) {

		this.text = text;

	}

	public String getText() {

		return this.text;

	}

	@Override
	public String toString() {
		return "Title [style=" + style + ", text=" + text + "]";
	}
	
}
