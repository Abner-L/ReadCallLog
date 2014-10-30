package com.example.readcalllog;

import java.util.List;

import com.example.readcalllog.CallLogDataRes.Item;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class BaseAdapter extends android.widget.BaseAdapter{
	Context _context  = null;
	List<Item> _callLogRes ;
	public BaseAdapter(Context context,List<Item> callLogRes){
		_context = context;
		_callLogRes = callLogRes;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _callLogRes.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.e("getItem", "被调用了");
		return _callLogRes.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(_context).inflate(R.layout.item, null);
		}
		final Item item = _callLogRes.get(position);
		
		TextView tv_type = (TextView) convertView.findViewById(R.id.type);
		TextView tv_name = (TextView) convertView.findViewById(R.id.name);
		TextView tv_time = (TextView) convertView.findViewById(R.id.date);
		TextView tv_number = (TextView) convertView.findViewById(R.id.number);
		TextView tv_callTimes = (TextView) convertView.findViewById(R.id.call_times);
		final TextView tv_detail = (TextView) convertView.findViewById(R.id.detail);
		
		if ((item.type).equals("未接来电")) {
			tv_name.setTextColor(Color.RED);
			tv_type.setTextColor(Color.RED);
		}else {
			tv_name.setTextColor(Color.BLACK);
			tv_type.setTextColor(Color.BLACK);
		}
		
		tv_name.setText(item.name);
		tv_number.setText(item.number);
		tv_time.setText(item.date);
		tv_type.setText(item.type);
		tv_callTimes.setText(item.callTimes);
		
		tv_detail.setBackgroundColor(Color.rgb(223, 223, 223));
		tv_detail.setClickable(true);
		tv_detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("onclickListener: ", item.name + "被点击了");
				Intent intent = new Intent(_context, DetailActivity.class);
				intent.putExtra("name", item.name);
				intent.putExtra("number", item.number);
				_context.startActivity(intent);
			}
		});
		return convertView;
	}
	
}
