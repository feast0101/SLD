package com.kmbt.csa.dav.pojo;

public enum WEEK {
	SUN(1), MON(2), TUE(3), WED(4), THR(5), FRI(6), SAT(7);
	
	private int wkcode;
	
	private WEEK(int s) {
		wkcode=s;
	}
	public int getWkcode() {
		return wkcode;
	}

}
