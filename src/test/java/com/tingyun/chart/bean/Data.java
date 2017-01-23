package com.tingyun.chart.bean;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;

import com.tingyun.chart.client.JsonUtil;

public class Data {

	private String name;
	private long x;

	private String y;
	private String params;
	
	

	private String tooltip;
	private String id;
	private Event events;

	public Event getEvents() {
		return events;
	}


	public void setEvents(Event events) {
		this.events = events;
	}
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public void setY(String y) {

		this.y = y;

	}

	public String getY() {

		return this.y;

	}

	public void setTooltip(String tooltip) {

		this.tooltip = tooltip;

	}

	public String getTooltip() {

		return this.tooltip;

	}

	public Tooltip getTooltipObject() {

		try {
			return JsonUtil.get(this.tooltip, Tooltip.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "Data [name=" + name + ", x=" + x + ", y=" + y + ", params=" + params + ", tooltip=" + tooltip + ", id="
				+ id + ", events=" + events + "]";
	}

	

}