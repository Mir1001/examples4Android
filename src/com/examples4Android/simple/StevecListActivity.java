package com.examples4Android.simple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
//Step 4.6
public class StevecListActivity extends ListActivity implements OnItemClickListener  {
	ApplicationExample app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Step 4.7  naredite 2 LL enega za Activity z listo in enega za vrstico
		setContentView(R.layout.stevec_list_activity);
		app = (ApplicationExample) getApplication();
		setListAdapter(app.stevci);
		this.getListView().setOnItemClickListener(this);

	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Toast.makeText(this, "Pritisnili ste:"+app.stevci.getItem(position).getStanje(), Toast.LENGTH_LONG).show();
		
	}
}
