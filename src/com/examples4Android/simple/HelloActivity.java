package com.examples4Android.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloActivity extends Activity implements OnClickListener {
	Button inc, dec, show;
	TextView tv_prikaz;
	Stevec mojStevec;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Moj test");
        setContentView(R.layout.main);
        mojStevec = new Stevec();
        inc = (Button) findViewById(R.id.btnInc);
        dec = (Button) findViewById(R.id.btnDec);
        show = (Button) findViewById(R.id.btnShow);
        tv_prikaz = (TextView) findViewById(R.id.textViewPrikaz);
        inc.setOnClickListener(this);
        dec.setOnClickListener(this);
        show.setOnClickListener(this);
        
    }
	@Override
	public void onClick(View arg0) {
		if (arg0.getId()==R.id.btnInc) {
			mojStevec.inc();
		} else
			if (arg0.getId()==R.id.btnDec) {
				mojStevec.dec();
			} else
				if (arg0.getId()==R.id.btnShow) {
					tv_prikaz.setText(""+mojStevec.getStanje());
				} 
	}
}