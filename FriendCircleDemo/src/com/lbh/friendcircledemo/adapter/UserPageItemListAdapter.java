package com.lbh.friendcircledemo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbh.friendcircledemo.R;

public class UserPageItemListAdapter extends BaseAdapter {

	private Activity mActivity;

	// private ViewHolder holder;
	public UserPageItemListAdapter(Activity mActivity) {

		this.mActivity = mActivity;

	}

	@Override
	public int getCount() {
		return 2;
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
			convertView = View.inflate(mActivity,
					R.layout.item_user_data_page_details, null);
			
			holder.iv_photo = (ImageView) convertView
					.findViewById(R.id.iv_photo);
			holder.tv_content_text = (TextView) convertView
					.findViewById(R.id.tv_content_text);
			holder.tv_image_size = (TextView) convertView
					.findViewById(R.id.tv_image_size);
			
			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}


		
		return convertView;
	}

	static class ViewHolder {
		ImageView iv_photo;
		TextView tv_content_text;
		TextView tv_image_size;
	}
}
