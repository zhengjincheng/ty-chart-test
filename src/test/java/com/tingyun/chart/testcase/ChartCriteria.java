package com.tingyun.chart.testcase;

public class ChartCriteria {
	 public final static int TICK_1M = 1;

	    public final static int TICK_2M = 2;

	    public final static int TICK_5M = 5;

	    public final static int TICK_15M = 15;

	    public final static int TICK_30M = 30;

	    public final static int TICK_1H = 60;

	    public final static int TICK_2H = 120;

	    public final static int TICK_3H = 180;

	    public final static int TICK_4H = 240;

	    public final static int TICK_5H = 300;

	    public final static int TICK_6H = 360;

	    public final static int TICK_8H = 480;

	    public final static int TICK_12H = 720;

	    public final static int TICK_16H = 960;

	    public final static int TICK_1D = 1440;

	    public final static int TICK_2D = 2880;

	    public final static int TICK_3D = 4320;

	    public final static int TICK_4D = 5760;

	    public final static int TICK_5D = 7200;

	    public final static int TICK_6D = 8640;

	    public final static int TICK_8D = 11520;

	    public final static int TICK_16D = 23040;

	    public final static int TICK_1W = 10080;

	    public final static int TICK_2W = 20160;

	    public final static int TICK_3W = 30240;

	    public final static int TICK_4W = 40320;

	    public final static int TICK_5W = 50400;

	    public final static int TICK_6W = 60480;

	    public final static int TICK_7W = 70560;

	    public final static int TICK_8W = 80640;

	    public final static int TICK_9W = 90720;

	    public final static int TICK_10W = 100800;

	    public final static int TICK_1MO = 1 * 30 * 24 * 60;

	    public final static int TICK_2MO = 2 * 30 * 24 * 60;

	    public final static int TICK_3MO = 3 * 30 * 24 * 60;

	    public final static int TICK_4MO = 4 * 30 * 24 * 60;

	    public final static int TICK_6MO = 6 * 30 * 24 * 60;

	    public final static int TICK_8MO = 8 * 30 * 24 * 60;

	    public final static int TICK_10MO = 10 * 30 * 24 * 60;

	    public final static int TICK_12MO = 12 * 30 * 24 * 60;

	    public final static int TICK_18MO = 18 * 30 * 24 * 60;

	    public final static int TICK_24MO = 24 * 30 * 24 * 60;

	    
	public static  int getTmDataTick(int relativeTimeRange) {
		if (relativeTimeRange <= ChartCriteria.TICK_30M) {
			return ChartCriteria.TICK_1M;
		} else if (relativeTimeRange <= ChartCriteria.TICK_1H) {
			return ChartCriteria.TICK_2M;
		} else if (relativeTimeRange <= ChartCriteria.TICK_6H) {
			return ChartCriteria.TICK_15M;
		} else if (relativeTimeRange <= ChartCriteria.TICK_12H) {
			return ChartCriteria.TICK_30M;
		} else if (relativeTimeRange <= ChartCriteria.TICK_1D) {
			return ChartCriteria.TICK_1H;
		} else if (relativeTimeRange <= ChartCriteria.TICK_3D) {
			return ChartCriteria.TICK_3H;
		} else if (relativeTimeRange <= ChartCriteria.TICK_1W) {
			return ChartCriteria.TICK_6H;
		} else if (relativeTimeRange <= ChartCriteria.TICK_2W) {
			return ChartCriteria.TICK_12H;
		} else if (relativeTimeRange <= ChartCriteria.TICK_1MO) {
			return ChartCriteria.TICK_1D;
		} else if (relativeTimeRange <= ChartCriteria.TICK_2MO) {
			return ChartCriteria.TICK_2D;
		} else if (relativeTimeRange <= ChartCriteria.TICK_3MO) {
			return ChartCriteria.TICK_3D;
		} else if (relativeTimeRange <= ChartCriteria.TICK_6MO) {
			return ChartCriteria.TICK_1W;
		} else {
			return ChartCriteria.TICK_2W;
		}
	}
	
	
	
}
