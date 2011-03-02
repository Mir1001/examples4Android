package com.examples4Android.simple;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends Activity implements OnClickListener {
	public static final String TAG="HelloActivity";
	public static final String PREF_NAME="PREF_STEVCI";  //pref ime, kamor se shranjujejo pref.
	public static final String FILE_NAME_RANDOM="nakljucna.txt";
	private static final int TEST_START_ACTIVITY_ID = 1;
	Button inc, dec, show, reset, generiraj;
	TextView tv_prikaz, tv_kliki;
	EditText maxNumber;
	Stevec mojStevec;
	Stevec stInc,stDec;
	Random rnd;
	private int ugibaj;
	private  PrintWriter myfile;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Moj test");
		setContentView(R.layout.main);
		mojStevec = new Stevec();
		stInc = new Stevec(0);
		stDec = new Stevec(0);
		inc = (Button) findViewById(R.id.btnInc);
		dec = (Button) findViewById(R.id.btnDec);
		show = (Button) findViewById(R.id.btnShow);
		reset = (Button) findViewById(R.id.btnReset);
		generiraj = (Button) findViewById(R.id.btnGenerirajRandomDo);
		tv_prikaz = (TextView) findViewById(R.id.textViewPrikaz);
		tv_kliki = (TextView) findViewById(R.id.textViewKliki);
		maxNumber = (EditText) findViewById(R.id.editTextMaxRandom);
		inc.setOnClickListener(this);
		dec.setOnClickListener(this);
		show.setOnClickListener(this);
		reset.setOnClickListener(this);
		reset.setVisibility(View.GONE);

		generiraj.setOnClickListener(this);
		rnd = new Random();
		ugibaj = rnd.nextInt(21);

	}
	public void myKlik(View v) {
		switch (v.getId()) {
		case R.id.imageButtonOpen:
			//tukaj nekaj
			Toast toast =Toast.makeText(this, "GO Zdravo CAR", Toast.LENGTH_LONG);

			toast.show();
			Intent moj=new Intent(this,ByActivity.class);
			//this.startActivity(moj);
			this.startActivityForResult(moj, TEST_START_ACTIVITY_ID);
			break;
		}
	}
	@Override
	public void onStart() {
		super.onStart();
		try {
			myfile = new PrintWriter(
				    openFileOutput(FILE_NAME_RANDOM, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			Log.e(TAG,"Datoteka napake "+e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() { //pref predno user vidi nastavim prave vrednosti
		super.onResume();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem preferences
		int tmp = settings.getInt(Stevec.STEVEC_INC, 0); //pref preberem staro vrednost
		stInc.setStanje(tmp); //pref nastavim staro vrednost (od tod naprej šteje)
		showKlikRandom(); //pref osvežim na ekranu kar se vidi

	}
	@Override
	public void onPause() { //pref uporabnik ali OS zapusti pogled, potrebno shranit
		super.onPause();
		SharedPreferences settings = getSharedPreferences(PREF_NAME,0); //pref odprem
		SharedPreferences.Editor editor = settings.edit(); //pref dam v edit mode
		editor.putInt(Stevec.STEVEC_INC, stInc.getStanje()); //pref nastavim novo vrednost
		editor.commit(); //pref shranim novo vrednost
		if (myfile!=null) myfile.close();
	}

	private void showKlikRandom() {
		String a;
		a="Dec klik:"+stDec.getStanje()+" Inc klik:"+stInc.getStanje()+" Nakljucna:"+ugibaj;
		tv_kliki.setText(a);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

        // See which child activity is calling us back.

        switch (requestCode) {

            case TEST_START_ACTIVITY_ID: 
            	Toast toast = Toast.makeText(this,"resultCode="+resultCode , Toast.LENGTH_LONG);
            	toast.show();
            	break;
        }
	}

         
	@Override
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnInc) {
			mojStevec.inc();
			stInc.inc();
			showKlikRandom();
			reset.setVisibility(View.VISIBLE);
		} else
			if (arg0.getId()==R.id.btnDec) {
				mojStevec.dec();
				stDec.inc();
				showKlikRandom();
			} else
				if (arg0.getId()==R.id.btnShow) {
					tv_prikaz.setText(""+mojStevec.getStanje());
				} else
					if (arg0.getId()==R.id.btnReset) {
						mojStevec.reset();
						//tv_prikaz.setText(""+mojStevec.getStanje());
					} else
						if (arg0.getId()==R.id.btnGenerirajRandomDo) {
							try {
								ugibaj = rnd.nextInt(Integer.parseInt(""+maxNumber.getText()));
								if (myfile!=null) myfile.println(""+ugibaj);
							} catch (Exception e) {
								Log.w(TAG, "Pretvarjas:"+maxNumber.getText()+" "+e.toString());
							}
							showKlikRandom();
						}
	}
}