package com.dream.timetask.utils;

import java.util.Calendar;

import android.content.Context;

import com.dream.timetask.alarm.SetAlarm;
import com.dream.timetask.domin.InterceptTask;

/**
 * 闹钟任务工具类
 * 
 * @author 温坤哲
 * 
 */
public class TaskUtils {
	/**
	 * 启动开始和结束的闹钟任务
	 * 
	 * @param _id
	 *            闹钟任务的id号
	 * @param task
	 *            启动任务的对象
	 */
	public static void setStartAndEndAlarm(Context context, long _id,
			InterceptTask task) {
		long startInterval = getInterval.get(task.getRepet(),
				task.getStartHour(), task.getStartMinute());

		long endInterval = getInterval.get(task.getRepet(), task.getEndHour(),
				task.getEndMinute());

		SetAlarm.set(context, startInterval, false, (int) _id);
		SetAlarm.set(context, endInterval, true, (int) _id);
	}

	/**
	 * 启动开始和结束的闹钟任务
	 * 
	 * @param task
	 *            启动任务的对象
	 */
	public static void setStartAndEndAlarm(Context context, InterceptTask task) {
		setStartAndEndAlarm(context, task.get_id(), task);
	}
	
	/**
	 * 开始任务，即立即设为拦截来电模式且将设备设为静音模式
	 */
	public static void startTask(Context context) {
		SoundsUtils.setSilent(context);
	}
	
	/**
	 * 是否现在应该处于静音+拦截的状态,是否现在就应该设置为静音+拦截所有来电
	 * 
	 * @return
	 */
	public static boolean isNow(InterceptTask task) {
		boolean isNow = false;
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(Calendar.DAY_OF_WEEK) == task.getRepet()) {
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			if (hour > task.getStartHour() && hour < task.getEndHour()) {
				isNow =  true;
			} else if (hour == task.getStartHour() && hour < task.getEndHour()) {
				if (minute > task.getStartMinute()
						|| minute == task.getStartMinute())
					isNow =  true;
			} else if (hour == task.getStartHour() && hour == task.getEndHour()) {
				if (minute == task.getStartMinute()
						|| (minute > task.getStartMinute() && minute < task
								.getEndMinute())) {
					isNow = true;
				}
			}
		}
		return isNow;
	}
}
