package com.lbh.friendcircledemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lbh.friendcircledemo.R;

public class ListCommentAdapter extends BaseAdapter {

	private String commentText;
	private Context context;
	private String name;

	public ListCommentAdapter(Context mainActivity, String text,String username) {
		this.context = mainActivity;
		this.commentText = text;
		this.name=username;

	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_comment_list,
					null);
			holder = new ViewHolder();

			holder.tv_name_admire = (TextView) convertView
					.findViewById(R.id.tv_name_admire);
			holder.tv_huifu = (TextView) convertView
					.findViewById(R.id.tv_huifu);
			holder.tv_name_another = (TextView) convertView
					.findViewById(R.id.tv_name_another);
			holder.tv_maohao = (TextView) convertView
					.findViewById(R.id.tv_maohao);
			holder.tv_comment_text = (TextView) convertView
					.findViewById(R.id.tv_comment_text);

			convertView.setTag(holder);
		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_name_admire.setText(name);

		holder.tv_comment_text.setText(commentText);
		return convertView;
	}

	private class ViewHolder {
		TextView tv_name_admire;
		TextView tv_huifu;
		TextView tv_name_another;
		TextView tv_maohao;
		TextView tv_comment_text;
	}
}
