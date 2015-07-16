package com.dream.timetask.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.dream.timetask.R;

/**
 * 自定义List对话框类
 * 
 * @author 温坤哲
 * 
 */
public class CustomListDialog extends Dialog implements
		android.view.View.OnClickListener, OnItemClickListener {

	private Context context;
	private int mPosition = -1;// 上次选中的位置
	private ArrayList<HashMap<String, Object>> arrayList;
	private String item;
	private ListDialogAdapter mAdapter;

	private TextView view;

	public CustomListDialog(Context context, String[] items, TextView view) {
		super(context, R.style.dialo_style);

		setContentView(R.layout.view_radiobutton_dialog);

		this.context = context;
		this.view = view;

		arrayList = new ArrayList<HashMap<String, Object>>();

		// 初始化ArrayList集合各项是否被选中的状态
		for (int i = 0; i < items.length; i++) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("item", items[i]);
			hashMap.put("checked", false);
			arrayList.add(hashMap);
		}

		// 初始化ListView并且为ListView绑定适配器
		ListView listView = (ListView) this
				.findViewById(R.id.view_lv_radioButton);
		mAdapter = new ListDialogAdapter(context, arrayList);
		listView.setAdapter(mAdapter);

		listView.setOnItemClickListener(this);
	}

	/**
	 * 设置对话框标题栏
	 * 
	 * @param title
	 *            对话框标题栏文字
	 * @return 对话框本身
	 */
	public CustomListDialog setTitle(String title) {
		TextView view_dialog_title = (TextView) this
				.findViewById(R.id.view_dialog_title);
		view_dialog_title.setText(title);
		return this;
	}

	@Override
	public void onClick(View v) {
		this.dismiss();
	}

	public String getItem() {
		return item;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view1, int pPosition,
			long id) {
		// mPosition即为上次选中的子项，若重复选择，则不对ListView进行处理
		if (mPosition == pPosition) {
			this.dismiss();
			return;
		} else if (-1 != mPosition) {
			// 若是新选中的子项，则更新ArrayList中存储的状态
			HashMap<String, Object> map = arrayList.get(mPosition);
			map.put("checked", false);
		}
		// 将新选中的覆盖旧选中的
		mPosition = pPosition;
		HashMap<String, Object> map = arrayList.get(mPosition);
		map.put("checked", true);
		// 取出到底是哪个被选中
		item = (String) map.get("item");
		view.setText(item);
		mAdapter.notifyDataSetChanged();
		this.dismiss();
	}

}
