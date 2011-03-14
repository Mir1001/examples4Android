package com.examples4Android.simple;

import java.util.ArrayList;

import com.examples4Android.simple.data.DBAdapterStevec;

import android.app.Application;
import android.database.Cursor;

public class ApplicationExample extends Application {
	//Step 4.1
	//Step 4.2 popravi AndroidManifest.xml
	public ArrayList<Stevec> lista;
	StevecArrayAdapter stevci; //Step 4.9 Globalna lista
	Stevec mojStevec;
	Stevec stInc,stDec;
	DBAdapterStevec db;
	public ArrayList<Rezultat> rezultati;
	RezultatArrayAdapter rezultatiList; 

	public void onCreate() {
        super.onCreate(); //ne pozabi
        db = new DBAdapterStevec(this); 
        lista = new ArrayList<Stevec>(); //inicializirat
        rezultati = new ArrayList<Rezultat>(); //inicializirat
        init();
        fillFromDB();
        stevci = new StevecArrayAdapter(this, R.layout.stevec_layout,lista); //Step 4.10 Globalna lista
        rezultatiList = new RezultatArrayAdapter(this, R.layout.rezultat_layout, rezultati);
	}
	
	public void addRezultat(Rezultat tmp) {
		rezultatiList.add(tmp);
	}
	
	public void init() {
		mojStevec = new Stevec();
		mojStevec.setName("Skupni");
		stInc = new Stevec();
		stInc.setName("Increment btn");
		stDec = new Stevec();
		stDec.setName("Decrement btn");
		//lista.add(mojStevec);
		//lista.add(stInc);
		//lista.add(stDec);
		
	}
	
	public void add(Stevec a) {
		//lista.add(a);
		stevci.add(a);  //Step 4.10 Globalna lista
	}
	
	public void addStevec(String ime, int stanje) {
		Stevec tmp = new Stevec(stanje);
		tmp.setName(ime);
		addDB(tmp);
		stevci.add(tmp);  //Step 4.10 Globalna lista
	}
	
	//DB dodano
	public void fillFromDB() {
		db.open();
		Cursor c = db.getAll();
		Stevec tmp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			tmp = new Stevec();
			tmp.setName(c.getString(DBAdapterStevec.POS_NAME));
			tmp.setStanje(c.getInt(DBAdapterStevec.POS_VALUE));
			tmp.setDbID(c.getLong(DBAdapterStevec.POS__ID));
			lista.add(tmp); 
		}
		c.close();
		db.close();
	}
	public void addDB(Stevec s) {
		db.open();
		s.setDbID(db.insertStevc(s));
		db.close();	
	}
	//DB konec
	public void remove(Stevec a) {
		if (a!=null)
		stevci.remove(a);  //Step 4.10 Globalna lista
	}
}
