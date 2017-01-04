package com.tingyun.chart.testcase;

import java.util.HashMap;
import java.util.Map;

public class ChartTestInput {
	//private final static String contextUrl="http://reportlocal.tingyun.com/server";
	private final static String contextUrl="http://www.ty.com/server";

	private String userName;
	private String password;
	private String chartid;
	private String chartUrl;
	private Map<String,String> params=new HashMap<String,String>();
	
	public String getLoginUrl(){
		return contextUrl+"/transactionGroup/getAllTransactionGroup";
	}
	public String getChartUrl() {
		if (chartUrl==null){
			return contextUrl+"/highcharts-chart-data/LATEST/accounts/applications/charts/"+chartid+".json";
		}
		return chartUrl;
	}
	public void setChartUrl(String chartUrl) {
		this.chartUrl = chartUrl;
	}
	public String getContextUrl() {
		return contextUrl;
	}
//	public void setContextUrl(String contextUrl) {
//		this.contextUrl = contextUrl;
//	}
	public String getChartid() {
		return chartid;
	}
	public void setChartid(String chartid) {
		this.chartid = chartid;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public static ChartTestInput build(){
		return new ChartTestInput();
	}
	
	public ChartTestInput chartid(String charid){
		this.setChartid(charid);
		return this;
	}
	public ChartTestInput userName(String userName){
		this.setUserName(userName);
		return this;
	}
	public ChartTestInput passWord(String password){
		this.setPassword(password);
		return this;
	}
	public ChartTestInput put(String key,String value){
		params.put(key, value);
		return this;
	}
	@Override
	public String toString() {
		return "ChartTestInput [userName=" + userName + ", password=" + password + ", chartid=" + chartid
				+ ", chartUrl=" + chartUrl + ", params=" + params + "]";
	}
	
}
