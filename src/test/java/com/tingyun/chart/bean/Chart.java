package com.tingyun.chart.bean;

public class Chart {

	private String backgroundColor;

	private int width;

	private int height;

	private String plotBackgroundColor;

	private String plotBorderColor;

	private int plotBorderWidth;

	private int spacingTop;

	private int spacingBottom;

	private int spacingLeft;

	private int spacingRight;

	private String type;

	public void setBackgroundColor(String backgroundColor) {

		this.backgroundColor = backgroundColor;

	}

	public String getBackgroundColor() {

		return this.backgroundColor;

	}

	public void setWidth(int width) {

		this.width = width;

	}

	public int getWidth() {

		return this.width;

	}

	public void setHeight(int height) {

		this.height = height;

	}

	public int getHeight() {

		return this.height;

	}

	public void setPlotBackgroundColor(String plotBackgroundColor) {

		this.plotBackgroundColor = plotBackgroundColor;

	}

	public String getPlotBackgroundColor() {

		return this.plotBackgroundColor;

	}

	public void setPlotBorderColor(String plotBorderColor) {

		this.plotBorderColor = plotBorderColor;

	}

	public String getPlotBorderColor() {

		return this.plotBorderColor;

	}

	public void setPlotBorderWidth(int plotBorderWidth) {

		this.plotBorderWidth = plotBorderWidth;

	}

	public int getPlotBorderWidth() {

		return this.plotBorderWidth;

	}

	public void setSpacingTop(int spacingTop) {

		this.spacingTop = spacingTop;

	}

	public int getSpacingTop() {

		return this.spacingTop;

	}

	public void setSpacingBottom(int spacingBottom) {

		this.spacingBottom = spacingBottom;

	}

	public int getSpacingBottom() {

		return this.spacingBottom;

	}

	public void setSpacingLeft(int spacingLeft) {

		this.spacingLeft = spacingLeft;

	}

	public int getSpacingLeft() {

		return this.spacingLeft;

	}

	public void setSpacingRight(int spacingRight) {

		this.spacingRight = spacingRight;

	}

	public int getSpacingRight() {

		return this.spacingRight;

	}

	public void setType(String type) {

		this.type = type;

	}

	public String getType() {

		return this.type;

	}

	@Override
	public String toString() {
		return "Chart [backgroundColor=" + backgroundColor + ", width=" + width + ", height=" + height
				+ ", plotBackgroundColor=" + plotBackgroundColor + ", plotBorderColor=" + plotBorderColor
				+ ", plotBorderWidth=" + plotBorderWidth + ", spacingTop=" + spacingTop + ", spacingBottom="
				+ spacingBottom + ", spacingLeft=" + spacingLeft + ", spacingRight=" + spacingRight + ", type=" + type
				+ "]";
	}
	

}