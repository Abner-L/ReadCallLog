package com.example.readcalllog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog.Calls;

public class CallLogDetailDataRes {
	
	private Context _context;
	public CallLogDetailDataRes(Context context){
		_context = context;
	}
	
	public class DetailItem{
		String date;
		String type;
		String duration;
	}

	@SuppressLint("SimpleDateFormat") public List<DetailItem> getCallLogDetailDataRes(String name, String number){
		ContentResolver resolver = _context.getContentResolver();
		String[] projection = {Calls.CACHED_NAME, Calls.DATE, Calls.TYPE, Calls.DURATION};
		String selection = Calls.CACHED_NAME + "=? and " + Calls.NUMBER + "=?";
		String[] selectionArgs = {name,number};
		String sortOrder = Calls.DEFAULT_SORT_ORDER;
		Cursor detailCur = resolver.query(Calls.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
		
		List<DetailItem>callLogDetailDataRes = new ArrayList<CallLogDetailDataRes.DetailItem>();
		while(detailCur.moveToNext()){
			long dateLong = detailCur.getLong(detailCur.getColumnIndex(Calls.DATE));
			int typeInt = detailCur.getInt(detailCur.getColumnIndex(Calls.TYPE));
			long durationLong = detailCur.getLong(detailCur.getColumnIndex(Calls.DURATION));
			
			String type = "";
			switch (typeInt) {
			case 1:
				type = "来电：";
				break;
			case 2:
				type = "外拨电话：";
				break;
			case 3:
				type = "未接来电";
				break;
			default:break;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy' 年 'M' 月 'd' 日 'EEE' 'HH:mm");
			Date date1 = new Date(dateLong);
			String date = sdf.format(date1);
			
			System.out.println("name: " + name + "   duration: " + durationLong);
			String duration = timeFormat(durationLong);
					
			DetailItem item = new DetailItem();
			item.date = date;
			item.duration = duration;
			item.type = type;
			callLogDetailDataRes.add(item);
			
		}
		detailCur.close();
		return callLogDetailDataRes;
	}
	
	private String timeFormat(long time){
		long hours = time/3600;
		long minutes = (time%3600) / 60;
		long seconds = (time%3600) % 60;
		
		if (hours > 0) {
			return hours + " 时 " + minutes + " 分 " + seconds + " 秒 ";
		}else {
			return minutes + " 分 " + seconds + " 秒 ";
		}
	}
	
}
