package com.examples4Android.simple;

public class Rezultat {
	int tock;
	String ime;
	long id;
	final static long NO_ID=-1;
	public Rezultat() {
		tock=0;
		ime="N/A";
		id = NO_ID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTock() {
		return tock;
	}
	public void setTock(int tock) {
		this.tock = tock;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
}
