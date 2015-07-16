package com.dream.timetask.alarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.dream.timetask.MainActivity;
import com.dream.timetask.R;
import com.dream.timetask.utils.SoundsUtils;

public class AlarmReceiver extends BroadcastReceiver {

	private AudioManager mAudioManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(MainActivity.ALARM_ACTION)) {

			mAudioManager = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);

			AlarmManager manager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			Intent intent2 = new Intent(MainActivity.ALARM_ACTION);
			PendingIntent pi = null;

			boolean isFirst = intent.getBooleanExtra("isFirst", false);
			System.out.println("isFirst:" + isFirst);
			boolean isEnd = intent.getBooleanExtra("isEnd", false);

			if (isEnd) {
				int endRequestId1 = intent.getIntExtra("endRequestId", -1);
				System.out.println("endRequestId1:" + endRequestId1);

				SoundsUtils.setNormal(context);
				this.stopIntercept();
				System.out.println("normal");

				if (isFirst) {
					System.out.println("end first");

					int endRequestId = intent.getIntExtra("endRequestId", -1);
					System.out.println("endRequestId:" + endRequestId);

					intent2.putExtra("endRequestId", endRequestId);
					intent2.putExtra("isEnd", true);
					intent2.putExtra("isFirst", false);

					pi = PendingIntent.getBroadcast(context, endRequestId,
							intent2, 0);
					restartAlarm(manager, pi);
					System.out.println("重开end闹钟");
				}

			} else {
				SoundsUtils.setSilent(context);
				this.startIntercept();
				System.out.println("silent");

				if (isFirst) {
					int startRequest = intent.getIntExtra("startRequest", -1);
					intent2.putExtra("isFirst", false);

					pi = PendingIntent.getBroadcast(context, startRequest,
							intent2, 0);

					this.restartAlarm(manager, pi);
					System.out.println("重开start闹钟");
				}
			}

		}
	}

	/**
	 * 重开闹钟
	 * 
	 * @param manager
	 *            闹钟管理者
	 * @param pi
	 */
	private void restartAlarm(AlarmManager manager, PendingIntent pi) {
		manager.setRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + AlarmManager.INTERVAL_DAY * 7
						- Calendar.getInstance().get(Calendar.SECOND) * 1000,
				AlarmManager.INTERVAL_DAY, pi);
	}

	/**
	 * 开启拦截电话
	 * 
	 */
	private void startIntercept() {
		MainActivity.isIntercept = true;
		System.out.println("开启拦截");
	}

	/**
	 * 停止拦截电话
	 * 
	 */
	private void stopIntercept() {
		MainActivity.isIntercept = false;
		System.out.println("停止拦截");
	}

}
