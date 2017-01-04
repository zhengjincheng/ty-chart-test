package com.tingyun.chart.bean;

public class Label {
	private String align;

	private String textAlign;

	private String verticalAlign;

	public void setAlign(String align) {

		this.align = align;

	}

	public String getAlign() {

		return this.align;

	}

	public void setTextAlign(String textAlign) {

		this.textAlign = textAlign;

	}

	public String getTextAlign() {

		return this.textAlign;

	}

	public void setVerticalAlign(String verticalAlign) {

		this.verticalAlign = verticalAlign;

	}

	public String getVerticalAlign() {

		return this.verticalAlign;

	}

	@Override
	public String toString() {
		return "Label [align=" + align + ", textAlign=" + textAlign + ", verticalAlign=" + verticalAlign + "]";
	}
	
}
