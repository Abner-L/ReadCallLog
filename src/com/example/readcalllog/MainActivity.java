package com.example.readcalllog;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.readcalllog.CallLogDataRes.Item;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	private List<Item> callLogRes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.lv);
		CallLogDataRes dataRes = new CallLogDataRes(this);
		callLogRes = dataRes.getCallLog();

		ListAdapter adapter = new BaseAdapter(this, callLogRes);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		System.out.println("start");
		

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.e("on item click ", "被调用了");
		Item item = callLogRes.get(position);
		String number = item.number;
		Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + number));
		startActivity(intent);
	}

	
}
