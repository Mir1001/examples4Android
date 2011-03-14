package com.examples4Android.simple;

public class Stevec {
	public static final String STEVEC_INC="PREF_INCREMENT_BUTTON";  //pref ime spremenljuvke
	//
	private int i;
	private String name;
	private long dbID; //id  v tabeli

	public long getDbID() {
		return dbID;
	}

	public void setDbID(long dbID) {
		this.dbID = dbID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stevec() {
		i=10;
	}

	public Stevec(int i) {
		this.i=i;
	}

	public void inc() {
		i++;
	}

	public void dec() {
		i--;
	}

	public void setStanje(int i) {
		this.i=i;
	}

	public void reset() {
		setStanje(0);
	}

	public int getStanje() {
		return i;
	}

}
