package com.lbh.friendcircledemo.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lbh.friendcircledemo.R;
import com.lbh.friendcircledemo.adapter.UserPageItemListAdapter;
import com.lbh.friendcircledemo.views.UserDataItemNoScrollView;

public class UserDataPageActivity extends BaseActivity {

	private ListView lv_user_page;// 个人相册页最外层ListView

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();

		initData();
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_data_page);
		lv_user_page = (ListView) findViewById(R.id.lv_user_data_page);

	}

	/**
	 * 填充数据
	 */
	private void initData() {

		lv_user_page.setAdapter(new UserPageAdapter());
	}

	private class UserPageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = View.inflate(UserDataPageActivity.this,
						R.layout.item_user_data_page, null);
				holder.tv_time = (TextView) convertView
						.findViewById(R.id.tv_time);
				holder.lv_item_noscroll = (UserDataItemNoScrollView) convertView
						.findViewById(R.id.lv_item_noscroll);

				convertView.setTag(holder);
			} else {

				holder = (ViewHolder) convertView.getTag();
			}

			holder.tv_time.setText("今天");
			holder.lv_item_noscroll.setAdapter(new UserPageItemListAdapter(
					UserDataPageActivity.this));
			holder.lv_item_noscroll
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {

							Toast.makeText(UserDataPageActivity.this,
									"turn to other pages", 0).show();
						}
					});

			return convertView;
		}

	}

	static class ViewHolder {
		TextView tv_time;
		UserDataItemNoScrollView lv_item_noscroll;
	}

}
