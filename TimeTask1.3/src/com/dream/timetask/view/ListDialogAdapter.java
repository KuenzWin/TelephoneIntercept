package com.dream.timetask.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dream.timetask.R;

/**
 * 自定义对话框类的适配器
 * 
 * @author 温坤哲
 * 
 */
public class ListDialogAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<HashMap<String, Object>> mArrayList;

	ListDialogAdapter(Context context,
			ArrayList<HashMap<String, Object>> arrayList) {
		this.context = context;
		this.mArrayList = arrayList;
	}

	public int getCount() {
		return mArrayList.size();
	}

	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(context).inflate(
				R.layout.view_dialog_listcell, null);
		TextView view_list_tv = (TextView) convertView
				.findViewById(R.id.textView);
		RadioButton view_list_rb = (RadioButton) convertView
				.findViewById(R.id.radioButton);

		view_list_tv.setText((String) mArrayList.get(position).get("item"));
		view_list_rb.setChecked((Boolean) mArrayList.get(position).get(
				"checked"));
		view_list_rb.setClickable(false);// 设置不让RadioButton获得焦点
		this.notifyDataSetChanged();
		return convertView;
	}
}
