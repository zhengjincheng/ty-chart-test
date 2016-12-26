package com.tingyun.chart.bean;

public class PlotLines {
	private String color;

	private String dashStyle;

	private Long value;

	private Label label;

	private double width;

	public void setColor(String color) {

		this.color = color;

	}

	public String getColor() {

		return this.color;

	}

	public void setDashStyle(String dashStyle) {

		this.dashStyle = dashStyle;

	}

	public String getDashStyle() {

		return this.dashStyle;

	}

	public void setValue(Long value) {

		this.value = value;

	}

	public Long getValue() {

		return this.value;

	}

	public void setLabel(Label label) {

		this.label = label;

	}

	public Label getLabel() {

		return this.label;

	}

	public void setWidth(double width) {

		this.width = width;

	}

	public double getWidth() {

		return this.width;

	}

	@Override
	public String toString() {
		return "PlotLines [color=" + color + ", dashStyle=" + dashStyle + ", value=" + value + ", label=" + label
				+ ", width=" + width + "]";
	}
	
}
