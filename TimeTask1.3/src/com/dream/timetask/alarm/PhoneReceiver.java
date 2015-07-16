package com.dream.timetask.alarm;

import java.lang.reflect.Method;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;
import com.dream.timetask.MainActivity;
import com.dream.timetask.utils.ContactUtils;
import com.dream.timetask.utils.MyPhoneStateListener;
import com.dream.timetask.utils.NotificationUtils;

/**
 * µÁª∞¿πΩÿ¿‡
 * 
 * @author Œ¬¿§’‹
 * 
 */
public class PhoneReceiver extends BroadcastReceiver {

	private String TAG = "tag";
	private TelephonyManager telMgr;
	private MyPhoneStateListener listener;

	@Override
	public void onReceive(Context context, Intent intent) {
		telMgr = (TelephonyManager) context
				.getSystemService(Service.TELEPHONY_SERVICE);

		System.out.println("receive");

		if (listener == null) {
			listener = new MyPhoneStateListener(context, telMgr);
		}

		telMgr.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}

}
