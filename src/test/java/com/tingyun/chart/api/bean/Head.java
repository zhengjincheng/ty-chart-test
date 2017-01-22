package com.tingyun.chart.api.bean;

import java.util.List;

public class Head {
	private List<Categories> categories;

	private List<Serieses> serieses;

	private List<Rows> rows;

	private List<Cols> cols;

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Categories> getCategories() {
		return this.categories;
	}

	public void setSerieses(List<Serieses> serieses) {
		this.serieses = serieses;
	}

	public List<Serieses> getSerieses() {
		return this.serieses;
	}

	public void setRows(List<Rows> rows) {
		this.rows = rows;
	}

	public List<Rows> getRows() {
		return this.rows;
	}

	public void setCols(List<Cols> cols) {
		this.cols = cols;
	}

	public List<Cols> getCols() {
		return this.cols;
	}

}