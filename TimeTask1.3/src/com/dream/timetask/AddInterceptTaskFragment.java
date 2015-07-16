package com.dream.timetask;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.TimePickerDialog;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dream.timetask.alarm.SetAlarm;
import com.dream.timetask.dao.TimeTaskDao;
import com.dream.timetask.domin.InterceptTask;
import com.dream.timetask.utils.NumberUtils;
import com.dream.timetask.utils.SoundsUtils;
import com.dream.timetask.utils.TaskUtils;
import com.dream.timetask.utils.getInterval;
import com.dream.timetask.view.CustomListDialog;
import com.dream.timetask.view.ShowCustomToast;

/**
 * 添加任务的Fragment
 * 
 * @author 温坤哲
 * 
 */
public class AddInterceptTaskFragment extends Fragment implements OnClickListener {

	private static AddInterceptTaskFragment instance;

	private InterceptTask task;

	private TextView tv_startTime;
	private TextView tv_endTime;
	private TextView tv_repet;

	private CustomListDialog dialog;

	private TimeTaskDao dao;

	private AudioManager manager;

	/**
	 * 获取单例类AddTaskFragment
	 * 
	 * @return AddTaskFragment.this
	 */
	public static AddInterceptTaskFragment getInstance() {
		if (instance == null)
			instance = new AddInterceptTaskFragment();
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup containerView,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.view_addtask, null);

		Button btn_startTime = (Button) view.findViewById(R.id.btn_startTime);
		btn_startTime.setOnClickListener(this);

		Button btn_endTime = (Button) view.findViewById(R.id.btn_endTime);
		btn_endTime.setOnClickListener(this);

		Button btn_repet = (Button) view.findViewById(R.id.btn_repet);
		btn_repet.setOnClickListener(this);

		Button btn_addTask = (Button) view.findViewById(R.id.btn_addTask);
		btn_addTask.setOnClickListener(this);

		tv_startTime = (TextView) view.findViewById(R.id.tv_startTime);
		tv_endTime = (TextView) view.findViewById(R.id.tv_endTime);
		tv_repet = (TextView) view.findViewById(R.id.tv_repet);

		dialog = new CustomListDialog(this.getActivity(), this.getResources()
				.getStringArray(R.array.string_weekDays), tv_repet)
				.setTitle("选择重复时间");
		// dialog.setPositiveListener(this);

		dao = new TimeTaskDao(this.getActivity());

		new MyTask().execute();

		return view;
	}

	/**
	 * 异步任务来查询数据中有的任务记录，并在后台启动它们
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyTask extends AsyncTask<Void, Void, List<InterceptTask>> {

		@Override
		protected List<InterceptTask> doInBackground(Void... params) {
			return dao.findAll();
		}

		@Override
		protected void onPostExecute(List<InterceptTask> tasks) {
			// TODO Auto-generated method stub
			super.onPostExecute(tasks);
			for (Iterator<InterceptTask> iterator = tasks.iterator(); iterator
					.hasNext();) {

				InterceptTask task = iterator.next();

				isNow(task);

				TaskUtils.setStartAndEndAlarm(getActivity(), task);
			}
		}

	}

	/**
	 * 判断是不是现在启动任务
	 * 
	 * @param task
	 *            任务对象
	 */
	private void isNow(InterceptTask task) {
		if (TaskUtils.isNow(task))
			TaskUtils.startTask(this.getActivity());
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onClick(View v) {

		Calendar calendar = Calendar.getInstance();

		if (task == null) {
			task = new InterceptTask();
		}

		switch (v.getId()) {
		case R.id.btn_startTime:
			showTimeDialog(calendar, R.id.btn_startTime);
			break;
		case R.id.btn_endTime:
			showTimeDialog(calendar, R.id.btn_endTime);
			break;
		case R.id.btn_repet:
			dialog.show();
			break;
		case R.id.btn_addTask:

			if (!this.isVaild(task)) {
				ShowCustomToast.show(this.getActivity(), "开始时间必须小于结束时间");
				return;
			}

			setTaskRept();

			if (TaskUtils.isNow(task)) {
				TaskUtils.startTask(this.getActivity());
			}

			long _id = dao.insert(task);

			ShowCustomToast.show(this.getActivity(), "添加提醒成功");

			/*
			 * ShowCustomToast.show(this.getActivity(), "_id" + _id + "\t" +
			 * task.toString());
			 */

			TaskUtils.setStartAndEndAlarm(this.getActivity(), _id, task);
			this.setBank();
			break;

		}
	}

	/**
	 * 设置任务的重复时间
	 */
	public void setTaskRept() {
		String repet = tv_repet.getText().toString();
		if (repet.equals("星期一"))
			task.setRepet(Calendar.MONDAY);
		else if (repet.equals("星期二"))
			task.setRepet(Calendar.TUESDAY);
		else if (repet.equals("星期三"))
			task.setRepet(Calendar.WEDNESDAY);
		else if (repet.equals("星期四"))
			task.setRepet(Calendar.THURSDAY);
		else if (repet.equals("星期五"))
			task.setRepet(Calendar.FRIDAY);
		else if (repet.equals("星期六"))
			task.setRepet(Calendar.SATURDAY);
		else if (repet.equals("星期日"))
			task.setRepet(Calendar.SUNDAY);
	}

	/**
	 * 将各个TextView内容设为空
	 */
	private void setBank() {
		task = null;
		tv_startTime.setText("");
		tv_endTime.setText("");
		tv_repet.setText("");
	}

	/**
	 * 显示时间选择对话框
	 * 
	 * @param calendar
	 *            日期类
	 * @param viewId
	 *            判断是按下开始按钮还是按下结束按钮显示的
	 */
	private void showTimeDialog(Calendar calendar, final int viewId) {
		new TimePickerDialog(this.getActivity(),
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						if (viewId == R.id.btn_startTime) {
							task.setStartHour(hourOfDay);
							task.setStartMinute(minute);
							tv_startTime.setText(NumberUtils
									.formatNumber(hourOfDay)
									+ ":"
									+ NumberUtils.formatNumber(minute));
						} else {
							task.setEndHour(hourOfDay);
							task.setEndMinute(minute);
							tv_endTime.setText(NumberUtils
									.formatNumber(hourOfDay)
									+ ":"
									+ NumberUtils.formatNumber(minute));
						}
					}
				}, calendar.get(Calendar.HOUR_OF_DAY),
				(viewId == R.id.btn_startTime ? calendar.get(Calendar.MINUTE)
						: calendar.get(Calendar.MINUTE) + 1), true).show();
	}

	/**
	 * 输入是否合法，即开始时间是否小于结束时间
	 * 
	 * @param task
	 *            任务对象
	 * @return
	 */
	private boolean isVaild(InterceptTask task) {
		boolean isVa = false;

		if (task != null) {
			if (task.getStartHour() < task.getEndHour())
				isVa = true;
			if (task.getStartHour() == task.getEndHour()) {
				if (task.getStartMinute() < task.getEndMinute()) {
					isVa = true;
				}
			}
		}

		return isVa;
	}

}
