package com.examples4Android.simple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
//Step 4.6
public class RezultatiListActivity extends ListActivity implements OnItemClickListener  {
	ApplicationExample app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Step 4.7  naredite 2 LL enega za Activity z listo in enega za vrstico
		setContentView(R.layout.stevec_list_activity);
		app = (ApplicationExample) getApplication();
		setListAdapter(app.rezultatiList);
		this.getListView().setOnItemClickListener(this);
		setResult(RESULT_OK);
	}
	public void izhod(View v) {
		setResult(RESULT_CANCELED);
		finish();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Toast.makeText(this, "Pritisnili ste:"+app.stevci.getItem(position).getStanje(), Toast.LENGTH_LONG).show();
		
	}
}
