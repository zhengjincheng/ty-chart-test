package com.tingyun.chart.bean;

public class Labels {
	private String color;
	private boolean enabled; 

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setColor(String color) {

		this.color = color;

	}

	public String getColor() {

		return this.color;

	}

	@Override
	public String toString() {
		return "Labels [color=" + color + "]";
	}
	
}
