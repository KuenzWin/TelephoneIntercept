package com.dream.timetask.utils;

/**
 * 数字显示工具
 * 
 * @author 温坤哲
 * 
 */
public class NumberUtils {

	/**
	 * 格式化字符串将一位数的数字变成两位数，即在十位数位+0
	 * 
	 * @param data
	 * @return 格式化后的字符串
	 */
	public static String formatNumber(int data) {
		if (data < 10)
			return "0" + data;
		return data + "";
	}
}
