package com.tingyun.chart.bean;

public class Data {

	private String name;
	private long x;

	private double y;

	private String tooltip;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setX(long x) {

		this.x = x;

	}

	public long getX() {

		return this.x;

	}

	public void setY(double y) {

		this.y = y;

	}

	public double getY() {

		return this.y;

	}

	public void setTooltip(String tooltip) {

		this.tooltip = tooltip;

	}

	public String getTooltip() {

		return this.tooltip;

	}

	@Override
	public String toString() {
		return "Data [x=" + x + ", y=" + y + ", tooltip=" + tooltip + "]";
	}
	

}