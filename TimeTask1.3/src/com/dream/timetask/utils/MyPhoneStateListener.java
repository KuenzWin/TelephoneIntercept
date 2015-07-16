package com.dream.timetask.utils;

import java.lang.reflect.Method;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;
import com.dream.timetask.MainActivity;

public class MyPhoneStateListener extends PhoneStateListener {

	private Context context;
	private TelephonyManager telMgr;

	public MyPhoneStateListener(Context context, TelephonyManager telMgr) {
		this.context = context;
		this.telMgr = telMgr;
	}

	@Override
	public void onCallStateChanged(int state, String incomingNumber) {

		switch (state) {

		case TelephonyManager.CALL_STATE_RINGING:
			if (MainActivity.isIntercept) {
				endCall();
				new MyTask(context, incomingNumber).execute();
			}
		}

	}

	private class MyTask extends AsyncTask<Void, Void, String> {

		private Context context;
		private String incomingNum;

		public MyTask(Context context, String incomingNum) {
			this.context = context;
			this.incomingNum = incomingNum;
		}

		@Override
		protected String doInBackground(Void... params) {
			return ContactUtils.getContactNameByPhoneNumber(context,
					incomingNum);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			NotificationUtils.notify1(context, "已拦截来电：" + result + "\t"
					+ incomingNum);
		}

	}

	/**
	 * 挂断电话
	 */
	private void endCall() {
		Class<TelephonyManager> c = TelephonyManager.class;
		try {
			Method getITelephonyMethod = c.getDeclaredMethod("getITelephony",
					(Class[]) null);
			getITelephonyMethod.setAccessible(true);
			ITelephony iTelephony = null;
			iTelephony = (ITelephony) getITelephonyMethod.invoke(telMgr,
					(Object[]) null);
			iTelephony.endCall();
			System.out.println("end call");
		} catch (Exception e) {
		}
	}
}
