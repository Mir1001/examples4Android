package com.examples4Android.simple;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StevecArrayAdapter extends ArrayAdapter<Stevec> { //Step 4.8 POPRAVI Stevec -> Rezultati
	LayoutInflater mInflater;
	public StevecArrayAdapter(Context context, int textViewResourceId, List<Stevec> objects) { //Step 4.8 POPRAVI Stevec ->Rezultati
		super(context, textViewResourceId,objects);
	    mInflater = LayoutInflater.from(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Stevec tmp = getItem(position); //Step Step 4.7 pridobi data
		ViewHolder holder;
		// When convertView is not null, we can reuse it directly, there is no need
		// to reinflate it. We only inflate a new View when the convertView supplied
		// by ListView is null.
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.stevec_layout, null); //Step Step 4.7.5 DOLOČI ROW LL
			// Creates a ViewHolder and store references to the two children views
			// we want to bind data to.
			holder = new ViewHolder();
			holder.one = (TextView) convertView.findViewById(R.id.tvOne); //Step 4.8 POPRAVI
			// holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.two = (TextView) convertView.findViewById(R.id.tvTwo); //Step 4.8 POPRAVI		
			convertView.setTag(holder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			holder = (ViewHolder) convertView.getTag();
		}
		// Bind the data efficiently with the holder.
		holder.one.setText(""+tmp.getStanje()); //Step 4.8 POPRAVI
		holder.two.setText(tmp.getName()); //Step 4.8 POPRAVI
		//holder.icon.setImageBitmap((position & 1) == 1 ? mIcon1 : mIcon2);
		return convertView;
	}
	static class ViewHolder {
		TextView one; //Step 4.8 POPRAVI
		TextView two; //Step 4.8 POPRAVI
	}
}
