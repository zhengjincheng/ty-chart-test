package com.tingyun.chart.client;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
public class JsonUtil {
//	{"code":1,"data":{"amplitude":2.5,"buyPrice":223.6,"buyQty":61,"contractName":"黄金1512","contractNo":"au1512","delivDate":20150727,"doneAmt":-1,"doneQty":318958,"exchCode":"2","fLimitPrice":208.45,"handNum":1000,"holdQty":215628,"lowPrice":218.1,"newPrice":223.7,"openPrice":219,"rFPrice":4.4,"rFRate":1.97,"rLimitPrice":230.4,"sellPrice":223.7,"sellQty":26,"todaySettlePrice":7.04424793E10,"topPrice":223.7,"updateTime":"2015-07-27 15:30:01","variCode":"au","yDayClosePrice":223.7,"yDaySettlePrice":219.45},"message":"成功","success":true}
	
//	{"amplitude":2.5,"buyPrice":223.6,"buyQty":61,"contractName":"黄金1512","contractNo":"au1512","delivDate":20150727,"doneAmt":-1,"doneQty":318958,"exchCode":"2","fLimitPrice":208.45,"handNum":1000,"holdQty":215628,"lowPrice":218.1,"newPrice":223.7,"openPrice":219,"rFPrice":4.4,"rFRate":1.97,"rLimitPrice":230.4,"sellPrice":223.7,"sellQty":26,"todaySettlePrice":7.04424793E10,"topPrice":223.7,"updateTime":"2015-07-27 15:30:01","variCode":"au","yDayClosePrice":223.7,"yDaySettlePrice":219.45}
	public static <T>T  get(String json, Class<T> x) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper map=new ObjectMapper();
		return  map.readValue(json, x);
	}
	
}
