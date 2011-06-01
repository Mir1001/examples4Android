/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.examples4Android.simple.alarm;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import java.util.ArrayList;

import com.examples4Android.simple.HelloActivity;
import com.examples4Android.simple.R;
import com.examples4Android.simple.Stevec;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;

import android.util.Log;
import android.widget.Toast;

/**
 * This is an example of implementing an application service that will run in
 * response to an alarm, allowing us to move long duration work out of an intent
 * receiver.
 * 
 * @see AlarmService
 * @see AlarmService_Alarm
 */
public class MyAlarmService extends Service {
	private static final String TAG = "MyAlarmService";
	NotificationManager mNM;
	MyAlarmTask mytask;

	@Override
	public void onCreate() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Toast.makeText(this, "Start A", Toast.LENGTH_SHORT).show();
		// show the icon in the status bar
		showNotification();
	}

	@Override
	public void onDestroy() {
		// Cancel the notification -- we use the same ID that we had used to
		// start it
		mNM.cancel(R.string.alarm_stop);

		// Tell the user we stopped.
		Toast.makeText(this, "Konec A", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "Starting onStart Event");
		if (mytask == null
				|| mytask.getStatus().equals(AsyncTask.Status.FINISHED)) {
			mytask = new MyAlarmTask();
			mytask.execute((Void[]) null);
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/**
	 * Show a notification while this service is running.
	 */
	private void showNotification() {
		// In this sample, we'll use the same text for the ticker and the
		// expanded notification

		// Set the icon, scrolling text and timestamp
		Notification notification = new Notification(R.drawable.joke,
				"Zagnal sem notification", System.currentTimeMillis());

		// The PendingIntent to launch our activity if the user selects this
		// notification
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, HelloActivity.class), 0); // alarmActivity

		// Set the info for the views that show in the notification panel.
		notification.setLatestEventInfo(this, "Notif.  moj alarm",
				"Tukaj malo daljša razlaga", contentIntent);

		// Send the notification.
		// We use a layout id because it is a unique number. We use it later to
		// cancel.
		mNM.notify(R.string.alarm_start, notification);
	}

	private class MyAlarmTask extends AsyncTask<Void, String, Void> {
		private static final String TAG = "MyAlarmTask";
		int tmp;

		@Override
		protected Void doInBackground(Void... params) {

			Log.i(TAG, "Starting doInBackgroundNew");
			SharedPreferences settings = getSharedPreferences(
					HelloActivity.PREF_NAME, 0);
			tmp = settings.getInt(Stevec.STEVEC_INC, 0);
			publishProgress("Prebral:" + tmp);
			// DODAJ LOGIKO!
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Log.i(TAG, "Starting onProgressUpdate in ServiceNew");

			Context context = getApplicationContext();

			String expandedTitle = getString(R.string.alarm_name) + " ("
					+ values[0] + ")";
			Toast.makeText(context, "Stevec=" + tmp + " " + expandedTitle,
					Toast.LENGTH_SHORT).show();
			// Toast.makeText(context, expandedTitle,
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onPostExecute(Void result) {
			stopSelf();
		}
	}

}
