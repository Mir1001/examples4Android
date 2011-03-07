package com.examples4Android.simple;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ByActivity extends Activity {
	ApplicationExample app;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.byby);
		app = (ApplicationExample) getApplication(); //Step 4.4


	}
	public void myKlik(View v) {
		switch (v.getId()) {
		case R.id.buttonOK:
			//tukaj nekaj
			setResult(RESULT_OK);
			finish();
			break;
		
	case R.id.buttonNOK:
		//tukaj nekaj
		setResult(RESULT_CANCELED);
		finish();
		break;
	}
	}
	@Override
	public void onStart() {
		super.onStart();

	}
	
	@Override
	public void onResume() {
		super.onResume();
	

	}
	@Override
	public void onPause() {
		super.onPause();
	
	}


}