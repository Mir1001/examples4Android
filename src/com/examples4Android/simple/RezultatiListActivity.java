package com.examples4Android.simple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
//Step 4.6
public class RezultatiListActivity extends ListActivity implements OnItemClickListener  {
	private static final int EXIT_DIALOG=1; 
	ApplicationExample app;
	Menu mMenu;
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
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		switch (id) {
		case EXIT_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Ali želiti zapustiti listo?")
			.setCancelable(false)
			.setPositiveButton("Da", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int id) {
					
					RezultatiListActivity.this.setResult(RESULT_CANCELED);
					RezultatiListActivity.this.finish();
				}
				
			})
			.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					RezultatiListActivity.this.setResult(RESULT_OK);
					dialog.cancel();
				}
			});
			return builder.create();
	
		}
		return null;
	}

	public void izhod(View v) {
		showDialog(EXIT_DIALOG);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Toast.makeText(this, "Pritisnili ste:"+app.stevci.getItem(position).getStanje(), Toast.LENGTH_LONG).show();

	}

	//menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.rezultati_menu, mMenu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogTest:
			showDialog(EXIT_DIALOG);
			return true;
		case R.id.itemSettings:
			Intent i = new Intent();
			i.setClass(this, MenuPreferences.class);
			startActivityForResult(i, R.id.itemSettings);
			return true;

		default:// Generic catch all for all the other menu resources
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)
				.show();
				return true;
			}
			break;
		}

		return false;
	}


}
