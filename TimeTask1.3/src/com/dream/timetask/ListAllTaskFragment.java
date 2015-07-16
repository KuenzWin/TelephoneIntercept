package com.dream.timetask;

import java.util.Calendar;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dream.timetask.dao.TimeTaskDao;
import com.dream.timetask.domin.InterceptTask;
import com.dream.timetask.utils.NumberUtils;
import com.dream.timetask.view.CustomProgressDialog;

/**
 * 以列表的形式列出所有的时间任务
 * 
 * @author 温坤哲
 * 
 */
public class ListAllTaskFragment extends ListFragment {

	private static ListAllTaskFragment instance;

	/**
	 * 返回ListAllTaskFragment单例类
	 * 
	 * @return ListAllTaskFragment自身
	 */
	public static ListAllTaskFragment getInstance() {
		if (instance == null)
			instance = new ListAllTaskFragment();
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		new LoadTask().execute();
	}

	/**
	 * 异步任务查询时间任务数据库中的数据
	 * 
	 * @author 温坤哲
	 * 
	 */
	private class LoadTask extends AsyncTask<Void, Void, List<InterceptTask>> {

		/**
		 * 自定义的进度对话框
		 */
		private CustomProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new CustomProgressDialog(getActivity()).setMsg("请等待");
			dialog.show();
		}

		@Override
		protected List<InterceptTask> doInBackground(Void... params) {
			return new TimeTaskDao(getActivity()).findAll();
		}

		@Override
		protected void onPostExecute(List<InterceptTask> result) {
			super.onPostExecute(result);
			dialog.dismiss();

			setListAdapter(new MyAdapter(result));
		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private class MyAdapter extends BaseAdapter {

		private List<InterceptTask> tasks;

		private MyAdapter(List<InterceptTask> tasks) {
			this.tasks = tasks;
		}

		@Override
		public int getCount() {
			return tasks.size();
		}

		@Override
		public Object getItem(int position) {
			return tasks.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;

			if (convertView == null) {
				view = View
						.inflate(getActivity(), R.layout.view_listtask, null);
			} else {
				view = convertView;
			}

			InterceptTask task = tasks.get(position);

			TextView tv_task = (TextView) view.findViewById(R.id.tv_task);

			String day = null;

			switch (task.getRepet()) {
			case Calendar.SUNDAY:
				day = "星期日\t";
				break;
			case Calendar.MONDAY:
				day = "星期一\t";
				break;
			case Calendar.TUESDAY:
				day = "星期二\t";
				break;
			case Calendar.WEDNESDAY:
				day = "星期三\t";
				break;
			case Calendar.THURSDAY:
				day = "星期四\t";
				break;
			case Calendar.FRIDAY:
				day = "星期五\t";
				break;
			case Calendar.SATURDAY:
				day = "星期六\t";
				break;
			}

			tv_task.setText(day + NumberUtils.formatNumber(task.getStartHour())
					+ ":" + NumberUtils.formatNumber(task.getStartMinute())
					+ " - " + NumberUtils.formatNumber(task.getEndHour()) + ":"
					+ NumberUtils.formatNumber(task.getEndMinute()));
			return view;
		}

	}

}
