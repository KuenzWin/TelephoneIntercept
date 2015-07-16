package com.dream.timetask.alarm;

import java.util.Calendar;

import com.dream.timetask.MainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * 设置闹钟
 * 
 * @author 温坤哲
 * 
 */
public class SetAlarm {

	/**
	 * 设置闹钟
	 * 
	 * @param context
	 *            上下文对象
	 * @param interval
	 *            时间间隔
	 * @param isEnd
	 *            是否是结束静音拦截的闹钟
	 * @param requestId
	 *            闹钟的id
	 */
	public static void set(Context context, long interval, boolean isEnd,
			int requestId) {
		AlarmManager alarms = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		int alarmType = AlarmManager.RTC_WAKEUP;

		String ALARM_ACTION = MainActivity.ALARM_ACTION;

		Intent intent = new Intent(ALARM_ACTION);
		intent.putExtra("isFirst", true);
		PendingIntent pi = null;
		requestId = requestId * 2;

		if (isEnd) {
			System.out.println("end:" + requestId);
			intent.putExtra("endRequestId", requestId);
			intent.putExtra("isEnd", true);
			pi = PendingIntent.getBroadcast(context, requestId, intent, 0);
		} else {
			System.out.println("start" + (requestId - 1));
			intent.putExtra("startRequest", requestId - 1);
			pi = PendingIntent.getBroadcast(context, requestId - 1, intent, 0);
		}

		alarms.set(alarmType, System.currentTimeMillis() + interval, pi);
	}

}
