package com.dream.timetask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageButton btn_addFragment;
	private ImageButton btn_addFragment2;
	private ImageButton btn_addFragment3;

	private FragmentManager manager;

	private int preViewId = R.id.btn_addFragment;

	public static final String ALARM_ACTION = "ALARM_ACTION";

	/**
	 * 是否拦截来电的标志
	 */
	public static boolean isIntercept;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_addFragment = (ImageButton) this.findViewById(R.id.btn_addFragment);
		btn_addFragment.setOnClickListener(this);

		btn_addFragment2 = (ImageButton) this
				.findViewById(R.id.btn_addFragment2);
		btn_addFragment2.setOnClickListener(this);

		btn_addFragment3 = (ImageButton) this
				.findViewById(R.id.btn_addFragment3);
		btn_addFragment3.setOnClickListener(this);

		manager = this.getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction
				.replace(R.id.fragment_center, AddInterceptTaskFragment.getInstance());
		transaction.commit();
	}

	@Override
	public void onClick(View v) {

		if (preViewId == v.getId()) {
			return;
		}
		preViewId = v.getId();
		FragmentTransaction transaction = manager.beginTransaction();

		switch (v.getId()) {
		case R.id.btn_addFragment:
			transaction.replace(R.id.fragment_center,
					AddInterceptTaskFragment.getInstance());
			break;
		case R.id.btn_addFragment2:
			transaction.replace(R.id.fragment_center,
					ListAllTaskFragment.getInstance());
			break;
		case R.id.btn_addFragment3:
			break;
		}

		transaction.commit();

	}

}
