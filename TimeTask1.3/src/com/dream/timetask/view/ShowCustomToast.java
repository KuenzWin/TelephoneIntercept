package com.dream.timetask.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.timetask.R;

/**
 * 自定义Toast类
 * 
 * @author 温坤哲
 * 
 */
public class ShowCustomToast {

	/**
	 * 显示自定义的Toast
	 * 
	 * @param context
	 *            上下文对象
	 * @param msg
	 *            Toast内容
	 */
	public static void show(Context context, String msg) {
		Toast toast = new Toast(context);

		View view = View.inflate(context, R.layout.view_toast, null);

		TextView view_toast_msg = (TextView) view
				.findViewById(R.id.view_toast_msg);
		view_toast_msg.setText(msg);

		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 50);
		toast.setView(view);
		toast.show();
	}

	public static void show(Context context, int msg) {
		Toast toast = new Toast(context);

		View view = View.inflate(context, R.layout.view_toast, null);

		TextView view_toast_msg = (TextView) view
				.findViewById(R.id.view_toast_msg);
		view_toast_msg.setText(msg);

		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 50);
		toast.setView(view);
		toast.show();
	}

}
