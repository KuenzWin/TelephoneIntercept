package com.dream.timetask.utils;

import java.util.Calendar;

import android.app.AlarmManager;

/**
 * 获取应该过多长时间才要触发闹钟，分为27种情况讨论
 * 
 * @author 温坤哲
 * 
 */
public class getInterval {

	public static long get(int day, int hour, int min) {
		
		Calendar calendar = Calendar.getInstance();

		int curDay = calendar.get(Calendar.DAY_OF_WEEK);
		int curHour = calendar.get(Calendar.HOUR_OF_DAY);
		int curMin = calendar.get(Calendar.MINUTE);

		int dayMin = day - curDay;
		int hourMin = hour - curHour;
		int minMin = min - curMin;

		if (dayMin == 0 && hourMin == 0 && minMin == 0)
			return 0;

		// 1、星期数相等
		if (dayMin == 0) {
			// 1.1、小时数相同，分钟数不同
			if (hourMin == 0) {
				// 1.1.1、分钟差比现在的小，说明时间已过，只能等下一星期
				if (minMin < 0) {
					dayMin = 6;
					hourMin = 23;
					minMin += 60;
					// 1.1.2、分钟数比现在的大，说明时间未过，今天即可实现
				} else if (minMin > 0) {
					dayMin = 0;
					hourMin = 0;
				}
			}// 1.2、如果小时数比现在大，说明时间未过
			else if (hourMin > 0) {
				dayMin = 0;
				if (minMin < 0) {
					hourMin--;
					minMin += 60;
				}
				// 1.3如果小时数比现在小，说明时间已过，只能等下个星期
			} else if (hourMin < 0) {
				dayMin = 6;
				hourMin += 24;
				if (minMin < 0) {
					hourMin--;
					minMin += 60;
				}
			}
			// 2、如果星期数比现在大，说明时间未过，可以等待
		} else if (dayMin > 0) {
			// 2.1、小时数比现在大的情况
			if (hourMin == 0) {
				// 2.1.1分钟数比现在小的情况
				if (minMin < 0) {
					dayMin--;
					hourMin = 23;
					minMin += 60;
				}
			} // 2.2 小时数比现在小的情况
			else if (hourMin > 0) {
				if (minMin < 0) {
					hourMin--;
					minMin += 60;
				}

			}// 2.3小时数比现在小得情况
			else if (hourMin < 0) {
				dayMin--;
				hourMin += 24;
				if (minMin < 0) {
					hourMin--;
					minMin += 60;
				}
			}
			// 3、天数比现在小
		} else if (dayMin < 0) {
			dayMin += 7;
			// 3.1小时数相等
			if (hourMin == 0) {
				// 3.1、分钟数比现在小
				if (minMin < 0) {
					dayMin--;
					hourMin = 23;
					minMin += 60;
				}

			} // 3.2、小时数比现在大
			else if (hourMin > 0) {
				if (minMin < 0) {
					hourMin--;
					minMin += 60;
				}
			}// 3.3、小时数比现在小
			else if (hourMin < 0) {
				dayMin--;
				hourMin += 24;
				if (minMin < 0) {
					dayMin--;
					hourMin--;
					minMin += 60;
				}
			}
		}

		long mill = dayMin * AlarmManager.INTERVAL_DAY + hourMin
				* AlarmManager.INTERVAL_HOUR + minMin * (60 * 1000)
				- calendar.get(Calendar.SECOND) * 1000;
		return mill;
	}

}
