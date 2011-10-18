package com.examples4Android.simple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ChckBoxListActivity extends ListActivity implements OnItemClickListener {
	private static final int EXIT_DIALOG=1; 
	ApplicationExample app;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Step 4.7  naredite 2 LL enega za Activity z listo in enega za vrstico
		setContentView(R.layout.chkbox_list_activity);
		app = (ApplicationExample) getApplication();
		setListAdapter(app.checkBoxList);
		this.getListView().setOnItemClickListener(this);
		setResult(RESULT_OK);
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
