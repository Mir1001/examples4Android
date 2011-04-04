package com.examples4Android.simple;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ATaskActivity extends Activity {
	ApplicationExample app;
	ProgressDialog dialogWait;
	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ApplicationExample) getApplication(); //Step 4.4
		setContentView(R.layout.task_go);
	}
	public void my_click(View v) {
		switch (v.getId()) {
		case R.id.btn_go_task1:
			MojTask mt = new MojTask();
			mt.execute(5000);
			break;
		case R.id.btn_go_task22:
			MojTaskProgressBar mt2 = new MojTaskProgressBar();
			mt2.execute(100,100,100,200,100,300,100,100,200,300,100);
			break;
		
		}
	}


	private class MojTask extends AsyncTask<Integer, Void, Long> {
		@Override
		protected void onPreExecute() {
			dialogWait = 
				ProgressDialog.show(ATaskActivity.this, "", "Delam! Počakajte prosim...", true);
		}
		protected Long doInBackground(Integer... prviArgument) {
			long totalSize = 0;
			int t1=prviArgument[0];
			try {    //umetno čakamo!!!
				Thread.sleep(t1);
			} catch (InterruptedException e) {
				Log.e("ERROR", "Thread Interrupted");
			}
			totalSize = 43; //namišljeni rezultat 
			return totalSize;
		}

		protected void onPostExecute(Long tretjiArgument) {
			dialogWait.cancel();
		}
	}

	private class MojTaskProgressBar extends AsyncTask<Integer, Integer, Long> {
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(ATaskActivity.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Delam...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		protected Long doInBackground(Integer... prviArgument) {
			long totalSize = 0;
			int count = prviArgument.length;
			int t1, tmp;
			for (int i=0; i<count; i++) {
				t1=prviArgument[i];
				totalSize+=t1;
				try {
					Thread.sleep(t1);
				} catch (InterruptedException e) {
					Log.e("ERROR", "Thread Interrupted");
				}
				tmp = Math.round(((float) (i+1) / count) * 100);
				publishProgress(tmp);
			}
			return totalSize;
		}
		protected void onProgressUpdate(Integer... drugiArgument) {
			progressDialog.setProgress(drugiArgument[0]);
		}
		protected void onPostExecute(Long tretjiArgument) {
			Toast.makeText(ATaskActivity.this,"Skupaj:"+tretjiArgument,Toast.LENGTH_LONG).show();
			progressDialog.cancel();
		}
	}
}
