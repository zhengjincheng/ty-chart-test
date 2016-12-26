package com.tingyun.chart.bean;

public class DateTimeLabelFormats {

	private String millisecond;

	private String second;

	private String minute;

	private String hour;

	private String day;

	private String week;

	private String month;

	private String year;

	public void setMillisecond(String millisecond) {

		this.millisecond = millisecond;

	}

	public String getMillisecond() {

		return this.millisecond;

	}

	public void setSecond(String second) {

		this.second = second;

	}

	public String getSecond() {

		return this.second;

	}

	public void setMinute(String minute) {

		this.minute = minute;

	}

	public String getMinute() {

		return this.minute;

	}

	public void setHour(String hour) {

		this.hour = hour;

	}

	public String getHour() {

		return this.hour;

	}

	public void setDay(String day) {

		this.day = day;

	}

	public String getDay() {

		return this.day;

	}

	public void setWeek(String week) {

		this.week = week;

	}

	public String getWeek() {

		return this.week;

	}

	public void setMonth(String month) {

		this.month = month;

	}

	public String getMonth() {

		return this.month;

	}

	public void setYear(String year) {

		this.year = year;

	}

	public String getYear() {

		return this.year;

	}

	@Override
	public String toString() {
		return "DateTimeLabelFormats [millisecond=" + millisecond + ", second=" + second + ", minute=" + minute
				+ ", hour=" + hour + ", day=" + day + ", week=" + week + ", month=" + month + ", year=" + year + "]";
	}
	

}