package com.example.readcalllog;

import java.util.List;

import com.example.readcalllog.CallLogDetailDataRes.DetailItem;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CallLogDetailAdapter extends BaseAdapter {

	private List<DetailItem> _callLogDetailDataRes;
	private Context _context;
	public CallLogDetailAdapter(Context context, List<DetailItem> callLogDetailDataRes){
		_callLogDetailDataRes = callLogDetailDataRes;
		_context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _callLogDetailDataRes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _callLogDetailDataRes.get(position);
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
			convertView = LayoutInflater.from(_context).inflate(R.layout.detail_item, null);
		}
		
		TextView detailDate = (TextView) convertView.findViewById(R.id.detail_date);
		TextView detailType = (TextView) convertView.findViewById(R.id.detail_type);
		TextView detailDuration = (TextView) convertView.findViewById(R.id.detail_duration);
		
		DetailItem item = _callLogDetailDataRes.get(position);
		detailDate.setText(item.date);
		detailType.setText(item.type);
		if (!"未接来电".equals(item.type)) {
			detailDuration.setText(item.duration);
		}
		
		return convertView;
	}

}
