package com.examples4Android.simple;

public class Stevec {
	public static final String STEVEC_INC="INCREMENT_BUTTON"; 
  private int i;
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
