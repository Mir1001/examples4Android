package com.examples4Android.simple;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ChckBoxListArrayAdapter extends ArrayAdapter<String> {
	LayoutInflater mInflater;
	final List<Boolean> isCheckedList;
	
	public ChckBoxListArrayAdapter(Context context, int textViewResourceId,
			List<String> objects) {
		super(context, textViewResourceId, objects);
		mInflater = LayoutInflater.from(context);
		
		isCheckedList = new ArrayList<Boolean>();
        for(int i=0;i<objects.size();i++){
            isCheckedList.add(Boolean.FALSE);
        }
	}
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		String tmp = getItem(position);
        View vi = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            vi = mInflater.inflate(R.layout.chkbox_list_layout, null);
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textView);
            holder.ck = (CheckBox) vi.findViewById(R.id.chckBox);
            
            holder.ck.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    isCheckedList.set(position, holder.ck.isChecked());
                }
            });
            holder.text.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    isCheckedList.set(position, !holder.ck.isChecked());
	                holder.ck.setChecked(isCheckedList.get(position));
                }
            });
            
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        
        holder.ck.setChecked(isCheckedList.get(position));
        holder.text.setText(tmp);
        return vi;
    }
	public class ViewHolder {
        public CheckBox ck;
        public TextView text;
    }

}
