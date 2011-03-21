package com.examples4Android.simple;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MenuPreferences extends PreferenceActivity {
	public static final String TAG = "MenuPreferences";
	SharedPreferences prefs;
	ApplicationExample app;
	public static final String PREF_ISOK="ISOK";
	public static final String PREF_FREQENCY = "FREQENCY";
	public static final String PREF_DEBUG_LOCATION = "PREF_DEBUG_LOCATION";
	public static boolean isOK=true;
	public static int frequency=25;
	public static String debug_location="localhost";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ApplicationExample) this.getApplication();
		addPreferencesFromResource(R.xml.menu_preferences);
	}
	@Override
	public void onPause() {
		super.onPause();
		SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(app); 
		isOK = settings.getBoolean(PREF_ISOK, true);
		Toast.makeText(app, "isOK:"+isOK, Toast.LENGTH_LONG).show();
		frequency =  Integer.parseInt(settings.getString(PREF_FREQENCY, "25"));
		debug_location = settings.getString(PREF_DEBUG_LOCATION, "localhost");
		//app.setSettingsMenu(); //if something has been changed
	}
}