package com.example.readcalllog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.readcalllog.R.string;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.CallLog.Calls;

public class CallLogDataRes {
	Context _context;
	public CallLogDataRes(Context context){
		_context = context;
	}
	
	public class Item{
		String name;
		String type;
		String number;
		String where;
		String date;
		String callTimes;
 	}
	
	@SuppressLint("SimpleDateFormat") public List<Item> getCallLog(){
		
		int callTimesCounter = 1;
		List<Item>callLogRes = new ArrayList<CallLogDataRes.Item>();
		
		ContentResolver contentResolver = _context.getContentResolver();
		Cursor callLogCur = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null,Calls.DATE + " DESC");
//		String[]names = callLogCur.getColumnNames();
//		for (int i = 0; i < names.length; i++) {
//			System.out.println(names[i]);
//		}
		while(callLogCur.moveToNext()){
			
			String name = callLogCur.getString(callLogCur.getColumnIndex(Calls.CACHED_NAME));
			long dateLong = callLogCur.getLong(callLogCur.getColumnIndex(Calls.DATE));
			String number = callLogCur.getString(callLogCur.getColumnIndex(Calls.NUMBER));
			int typeI = callLogCur.getInt(callLogCur.getColumnIndex(Calls.TYPE));
//			String duration = callLogCur.getString(callLogCur.getColumnIndex(Calls.DURATION));
//			String where = callLogCur.getString(callLogCur.getColumnIndex("geocoded_location"));
			String callTimes = "";
			if (name == null) {
				name = "陌生人";
			}
			String type = "呼入";
			switch (typeI) {
			case 1:
				break;
			case 2:
				type = "呼出";
				break;
			case 3:
				type = "未接来电";
				break;
			default:
				break;
			}
			
			int lastItemIndex = callLogRes.size()-1;
			if (lastItemIndex >= 0) {
				Item lastItem = callLogRes.get(lastItemIndex);
				if (name.equals(lastItem.name) && number.equals(lastItem.number)) {
					if (type.equals(lastItem.type)) {
						callTimesCounter ++;
						lastItem.callTimes = "(" + callTimesCounter + ")";
						callLogRes.set(lastItemIndex, lastItem);
					}
					continue;
				}else {
					callTimesCounter = 1;
				}
			}
			
			
			Item item = new Item();
			
			
			
			//呼叫时间                                     "yyyy/MM/dd HH:mm:ss"                                                      
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd");  
			Date date2 = new Date(dateLong);
			String date = sfd.format(date2);  
			
//			String s = "1413722687056";
//			SimpleDateFormat sfd1 = new SimpleDateFormat("yyyy' 年 'M' 月 'd' 日 'EEE' 'HH:mm");  
//			Date date21 = new Date(dateLong);
//			String date1 = sfd1.format(date21); 
//			System.out.println(date1 + "          " + dateLong);
	//		System.out.println(name + "       " + date + "         " + type + "       " + number );
			
			item.date = date;
			item.name = name;
			item.number = number;
			item.type = type;
			item.callTimes = callTimes;
//			item.where = where;
			callLogRes.add(item);
		}
		callLogCur.close();
		return callLogRes;
	}

}
