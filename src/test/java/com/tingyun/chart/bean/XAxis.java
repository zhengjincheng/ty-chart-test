package com.tingyun.chart.bean;

import java.util.Arrays;
import java.util.List;

public class XAxis {
	private DateTimeLabelFormats dateTimeLabelFormats;

	private String gridLineColor;

	private String gridLineDashStyle;

	private int gridLineWidth;

	private Labels labels;

	private int minTickInterval;

	private List<PlotLines> plotLines;

	private String tickColor;

	private Title title;

	private String type;
	
	private String[] categories;
	
	private Number min;
	
	private Number max;
	
	private String tickUnit;

	public String getTickUnit() {
		return tickUnit;
	}

	public void setTickUnit(String tickUnit) {
		this.tickUnit = tickUnit;
	}

	public Number getMin() {
		return min;
	}

	public void setMin(Number min) {
		this.min = min;
	}

	public Number getMax() {
		return max;
	}

	public void setMax(Number max) {
		this.max = max;
	}


	
	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public void setDateTimeLabelFormats(DateTimeLabelFormats dateTimeLabelFormats) {

		this.dateTimeLabelFormats = dateTimeLabelFormats;

	}

	public DateTimeLabelFormats getDateTimeLabelFormats() {

		return this.dateTimeLabelFormats;

	}

	public void setGridLineColor(String gridLineColor) {

		this.gridLineColor = gridLineColor;

	}

	public String getGridLineColor() {

		return this.gridLineColor;

	}

	public void setGridLineDashStyle(String gridLineDashStyle) {

		this.gridLineDashStyle = gridLineDashStyle;

	}

	public String getGridLineDashStyle() {

		return this.gridLineDashStyle;

	}

	public void setGridLineWidth(int gridLineWidth) {

		this.gridLineWidth = gridLineWidth;

	}

	public int getGridLineWidth() {

		return this.gridLineWidth;

	}

	public void setLabels(Labels labels) {

		this.labels = labels;

	}

	public Labels getLabels() {

		return this.labels;

	}

	public void setMinTickInterval(int minTickInterval) {

		this.minTickInterval = minTickInterval;

	}

	public int getMinTickInterval() {

		return this.minTickInterval;

	}

	public void setPlotLines(List<PlotLines> plotLines) {

		this.plotLines = plotLines;

	}	

	public List<PlotLines> getPlotLines() {

		return this.plotLines;

	}	

	public void setTickColor(String tickColor) {

		this.tickColor = tickColor;

	}

	public String getTickColor() {

		return this.tickColor;

	}

	public void setTitle(Title title) {

		this.title = title;

	}

	public Title getTitle() {

		return this.title;

	}

	public void setType(String type) {

		this.type = type;

	}

	public String getType() {

		return this.type;

	}

	@Override
	public String toString() {
		return "XAxis [dateTimeLabelFormats=" + dateTimeLabelFormats + ", gridLineColor=" + gridLineColor
				+ ", gridLineDashStyle=" + gridLineDashStyle + ", gridLineWidth=" + gridLineWidth + ", labels=" + labels
				+ ", minTickInterval=" + minTickInterval + ", plotLines=" + plotLines + ", tickColor=" + tickColor
				+ ", title=" + title + ", type=" + type + ", categories=" + Arrays.toString(categories) + ", min=" + min
				+ ", max=" + max + ", tickUnit=" + tickUnit + "]";
	}

	
	
}
