package com.dream.timetask.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * 通讯录工具类
 * 
 * @author 温坤哲
 * 
 */
public class ContactUtils {

	/**
	 * 根据电话号码取得联系人姓名
	 */
	public static String getContactNameByPhoneNumber(Context context,
			String address) {
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		// 将自己添加到 msPeers 中
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection,
				ContactsContract.CommonDataKinds.Phone.NUMBER + " = '"
						+ address + "'", null, null);

		if (cursor == null) {
			return "";
		}
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);

			// 取得联系人名字
			int nameFieldColumnIndex = cursor
					.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
			String name = cursor.getString(nameFieldColumnIndex);
			return name;
		}
		return "";
	}

}
