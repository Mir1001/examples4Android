package com.examples4Android.simple;

import java.util.ArrayList;

import android.app.Application;

public class ApplicationExample extends Application {
	//Step 4.1
	//Step 4.2 popravi AndroidManifest.xml
	public ArrayList<Stevec> lista;
	StevecArrayAdapter stevci; //Step 4.9 Globalna lista
	Stevec mojStevec;
	Stevec stInc,stDec;
	public void onCreate() {
        super.onCreate(); //ne pozabi
        lista = new ArrayList<Stevec>(); //inicializirat
         init();
        stevci = new StevecArrayAdapter(this, R.layout.stevec_layout,lista); //Step 4.10 Globalna lista
        
	}
	public void init() {
		mojStevec = new Stevec();
		mojStevec.setName("Skupni");
		stInc = new Stevec();
		stInc.setName("Increment btn");
		stDec = new Stevec();
		stDec.setName("Decrement btn");
		lista.add(mojStevec);
		lista.add(stInc);
		lista.add(stDec);
		
	}
	public void add(Stevec a) {
		//lista.add(a);
		stevci.add(a);  //Step 4.10 Globalna lista
	}
	public void remove(Stevec a) {
		//lista.add(a);
		if (a!=null)
		stevci.remove(a);  //Step 4.10 Globalna lista
	}
}
