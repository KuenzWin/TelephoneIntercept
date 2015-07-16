package com.dream.timetask.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.dream.timetask.MainActivity;
import com.dream.timetask.R;

/**
 * 发送通知的工具类
 * 
 * @author 温坤哲
 * 
 */
public class NotificationUtils {
	/**
	 * 当开始静音和关闭静音时，向系统发送一个通知
	 * 
	 * @param context
	 * @param text
	 */
	public static void notify1(Context context, String text) {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context).setAutoCancel(true).setContentTitle(text)
				.setContentText(text).setTicker(text)
				.setSmallIcon(R.drawable.ic_launcher).setContentIntent(null)
				.setWhen(System.currentTimeMillis());

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(context, MainActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(resultPendingIntent);
		// mId allows you to update the notification later on.
		manager.notify(0, builder.build());
	}

}
