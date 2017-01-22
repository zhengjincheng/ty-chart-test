package com.tingyun.chart.api.testcase;

import java.util.HashMap;
import java.util.Map;

import com.tingyun.chart.testcase.ChartTestInput;

public class ApiTestInput {
//	private final static String contextUrl="http://192.168.99.1:8080/tingyun-api-server";
	private final static String contextUrl= "http://api.tingyun.com/server/latest";

	private String userID;
	private String xAuthKey;
	
	private String chartid;
	private String applicationId;

	private String chartUrl;
	private Map<String,String> params=new HashMap<String,String>();
	
	
	public String getChartUrl() {
		if (chartUrl==null){
			return contextUrl+"/accounts/"+ userID+ "/application/"+applicationId+"/charts/"+chartid+".json";
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

	
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getxAuthKey() {
		return xAuthKey;
	}
	public void setxAuthKey(String xAuthKey) {
		this.xAuthKey = xAuthKey;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public static ApiTestInput build(){
		return new ApiTestInput();
	}
	public ApiTestInput chartid(String charid){
		this.setChartid(charid);
		return this;
	}
	
	public  ApiTestInput userID(String charid){
		this.setUserID(charid);
		return this;
	}
	public  ApiTestInput xAuthKey(String xAuthKey){
		this.setxAuthKey(xAuthKey);
		return this;
	}
	public ApiTestInput put(String key,String value){
		params.put(key, value);
		return this;
	}
	public ApiTestInput applicationId(String applicationId){
		this.setApplicationId(applicationId);
		return this;
	}
	
	
	
}
