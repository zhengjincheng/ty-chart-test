package com.tingyun.chart.bean;

public class Marker {

	private String symbol;

	private String fillColor;

	private String lineColor;

	private double lineWidth;

	private int radius;

	public void setSymbol(String symbol) {

		this.symbol = symbol;

	}

	public String getSymbol() {

		return this.symbol;

	}

	public void setFillColor(String fillColor) {

		this.fillColor = fillColor;

	}

	public String getFillColor() {

		return this.fillColor;

	}

	public void setLineColor(String lineColor) {

		this.lineColor = lineColor;

	}

	public String getLineColor() {

		return this.lineColor;

	}

	public void setLineWidth(double lineWidth) {

		this.lineWidth = lineWidth;

	}

	public double getLineWidth() {

		return this.lineWidth;

	}

	public void setRadius(int radius) {

		this.radius = radius;

	}

	public int getRadius() {

		return this.radius;

	}

	@Override
	public String toString() {
		return "Marker [symbol=" + symbol + ", fillColor=" + fillColor + ", lineColor=" + lineColor + ", lineWidth="
				+ lineWidth + ", radius=" + radius + "]";
	}
	

}