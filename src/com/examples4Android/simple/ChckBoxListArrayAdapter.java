package com.examples4Android.simple;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ChckBoxListArrayAdapter extends ArrayAdapter<Article> {
	LayoutInflater mInflater;
	
	public ChckBoxListArrayAdapter(Context context, int textViewResourceId,
			final List<Article> objects) {
		super(context, textViewResourceId, objects);
		mInflater = LayoutInflater.from(context);
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Article tmp = getItem(position);
        View vi = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            vi = mInflater.inflate(R.layout.chkbox_list_layout, null);
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textViewName);
            holder.ck = (CheckBox) vi.findViewById(R.id.chckBox);
            holder.price = (TextView) vi.findViewById(R.id.textViewPrice);
            
            holder.ck.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	getItem(position).setSelected(holder.ck.isChecked());
                }
            });
            holder.text.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	boolean t = holder.ck.isChecked();
                	tmp.setSelected(!t);
	                holder.ck.setChecked(!t);
                }
            });
            holder.price.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	boolean t = holder.ck.isChecked();
                	tmp.setSelected(!t);
	                holder.ck.setChecked(!t);
                }
            });
            
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        
        holder.ck.setChecked(getItem(position).getSelected());
        holder.text.setText(tmp.getName());
        holder.price.setText(String.valueOf(tmp.getPrice()));
        
        return vi;
    }
	public class ViewHolder {
        public CheckBox ck;
        public TextView text;
        public TextView price;
    }

}
