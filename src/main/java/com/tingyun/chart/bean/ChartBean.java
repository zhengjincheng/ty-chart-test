package com.tingyun.chart.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ChartBean {
	private Chart chart;


	private Legend legend;


	private List<Series> series ;


	private int aggregateBy;


	private double aggregateValue;


	private Params params;
	
	@JsonProperty(value = "xAxis")
	private List<XAxis> xAxis ;

	@JsonProperty(value = "yAxis")
	private List<YAxis> yAxis ;


	public void setChart(Chart chart){

	this.chart = chart;

	}

	public Chart getChart(){

	return this.chart;

	}

	public void setLegend(Legend legend){

	this.legend = legend;

	}

	public Legend getLegend(){

	return this.legend;

	}

	public void setSeries(List<Series> series){

	this.series = series;

	}

	public List<Series> getSeries(){

	return this.series;

	}

	public void setAggregateBy(int aggregateBy){

	this.aggregateBy = aggregateBy;

	}

	public int getAggregateBy(){

	return this.aggregateBy;

	}

	public void setAggregateValue(double aggregateValue){

	this.aggregateValue = aggregateValue;

	}

	public double getAggregateValue(){

	return this.aggregateValue;

	}

	public void setParams(Params params){

	this.params = params;

	}

	public Params getParams(){

	return this.params;

	}

	public void setXAxis(List<XAxis> xAxis){

	this.xAxis = xAxis;

	}

	public List<XAxis> getXAxis(){

	return this.xAxis;

	}

	public void setYAxis(List<YAxis> yAxis){

	this.yAxis = yAxis;

	}

	public List<YAxis> getYAxis(){

	return this.yAxis;

	}

	@Override
	public String toString() {
		return "ChartBean [chart=" + chart + ", legend=" + legend + ", series=" + series + ", aggregateBy="
				+ aggregateBy + ", aggregateValue=" + aggregateValue + ", params=" + params + ", xAxis=" + xAxis
				+ ", yAxis=" + yAxis + "]";
	}
	
}
