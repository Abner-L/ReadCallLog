package com.example.readcalllog;

import java.util.List;

import com.example.readcalllog.CallLogDetailDataRes.DetailItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String name = bundle.getString("name");
		String number = bundle.getString("number");
		
		ListView detailLV = (ListView) findViewById(R.id.detail_lv);
		TextView detailName = (TextView) findViewById(R.id.detail_name);
		detailName.setText(name);
		
		CallLogDetailDataRes callLogDetailDataRes = new CallLogDetailDataRes(this);
		List<DetailItem>detailItems = callLogDetailDataRes.getCallLogDetailDataRes(name, number);
		CallLogDetailAdapter adapter = new CallLogDetailAdapter(this, detailItems);
		detailLV.setAdapter(adapter);
	}
	
}
