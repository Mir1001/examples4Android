package com.examples4Android.simple;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends Activity implements OnClickListener {
	public static final String TAG="HelloActivity";
	Button inc, dec, show, reset, generiraj;
	TextView tv_prikaz, tv_kliki;
	EditText maxNumber;
	Stevec mojStevec;
	Stevec stInc,stDec;
	Random rnd;
	private int ugibaj;
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
        showKlikRandom();
        
    }
    private void showKlikRandom() {
    	String a;
    	a="Dec klik:"+stDec.getStanje()+" Inc klik:"+stInc.getStanje()+" Nakljucna:"+ugibaj;
    	tv_kliki.setText(a);
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
							} catch (Exception e) {
								Log.w(TAG, "Pretvarjas:"+maxNumber.getText()+" "+e.toString());
							}
							showKlikRandom();
						}
	}
}