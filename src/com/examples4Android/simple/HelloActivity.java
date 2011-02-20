package com.examples4Android.simple;

import android.app.Activity;
import android.os.Bundle;

public class HelloActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Moj test");
        setContentView(R.layout.main);
    }
}