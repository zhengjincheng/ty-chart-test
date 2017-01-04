package com.tingyun.chart.bean;

public class Legend {

	private String align;

	private String verticalAlign;

	public void setAlign(String align) {

		this.align = align;

	}

	public String getAlign() {

		return this.align;

	}

	public void setVerticalAlign(String verticalAlign) {

		this.verticalAlign = verticalAlign;

	}

	public String getVerticalAlign() {

		return this.verticalAlign;

	}

	@Override
	public String toString() {
		return "Legend [align=" + align + ", verticalAlign=" + verticalAlign + "]";
	}

}