package com.tingyun.chart.testcase;
 
public class SqlBean {
	
	private TimeRange tr;
	private String tmTick;
	private String start_time;
	private String end_time;
	
	public String getTmTick() {
		return tmTick;
	}
	
	public String getStart_time() {
		return start_time;
	}
	
	public String getEnd_time() {
		return end_time;
	}
	
	
	@Override
	public String toString() {
		return "SqlBean [tmTick=" + tmTick + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}

	public SqlBean(String key){
		TimeRange tr= TimeRangeRegister.get(key);
		this.end_time="(floor(UNIX_TIMESTAMP(now()+30)/60)-22616640)";
		this.start_time="("+this.end_time+"-"+ tr.getRange()+ ")";
		String tmTick= "FROM_UNIXTIME(($start_time+22616640+ floor(floor((timestamp-$start_time)/$tick))*$tick)*60)";
		String tmp1=tmTick.replace("$start_time", start_time);
		String tmp2= tmp1.replace("$tick", String.valueOf(tr.getTick()));
		this.tmTick=tmp2;
	}
	public static void main(String [] string){
		SqlBean n=new SqlBean(TimeRange.HOUR_1);
		System.out.println(n.toString());
	}
	
}
