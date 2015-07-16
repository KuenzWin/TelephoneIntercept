package com.dream.timetask.utils;

import com.dream.timetask.MainActivity;

import android.content.Context;
import android.media.AudioManager;

/**
 * 控制静音与非静音
 * 
 * @author 温坤哲
 * 
 */
public class SoundsUtils {

	/**
	 * 设置设备音量回复正常
	 */
	public static void setNormal(Context context) {
		AudioManager manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		NotificationUtils.notify1(context, "关闭静音");
		VibratorUtils.vibrator(context);
		MainActivity.isIntercept = false;
	}

	/**
	 * 设置设备静音
	 */
	public static void setSilent(Context context) {
		AudioManager manager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		NotificationUtils.notify1(context, "开启静音");
		VibratorUtils.vibrator(context);
		MainActivity.isIntercept = true;
	}

}
