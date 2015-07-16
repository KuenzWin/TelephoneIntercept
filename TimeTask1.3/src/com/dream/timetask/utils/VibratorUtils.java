package com.dream.timetask.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * 手机振动工具类
 * 
 * @author 温坤哲
 * 
 */
public class VibratorUtils {
	/**
	 * 手机振动
	 * 
	 * @param context
	 */
	public static void vibrator(Context context) {
		Vibrator vibrator = (Vibrator) context
				.getSystemService(Service.VIBRATOR_SERVICE);
		vibrator.vibrate(1500);
	}
}
