package com.lbh.friendcircledemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lbh.friendcircledemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class GridViewAdapter extends BaseAdapter {

	private Context context;

	private ImageLoader imageLoader;

	private String[] imageUrls;

	private LayoutInflater mInflater;

	private DisplayImageOptions options;
	public GridViewAdapter(Context context, ImageLoader imageLoader,
			String[] imageUrls) {

		this.context = context;
		this.imageLoader = imageLoader;
		this.imageUrls = imageUrls;
		mInflater = LayoutInflater.from(context);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
	}

	@Override
	public int getCount() {
		return imageUrls.length;
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

		ViewHolder holder = null;
		if (convertView == null) {
			// view = getLayoutInflater().inflate(R.layout.item_grid_image,
			// parent, fa
			// convertView=getLayoutInflater().

			convertView = mInflater.inflate(R.layout.item_gridview_list,
					parent, false);

			holder = new ViewHolder();
			holder.iv_item_gridview = (ImageView) convertView
					.findViewById(R.id.iv_item_gridview);

			if (imageUrls!=null) {
				
			
			imageLoader.displayImage(imageUrls[position],
					holder.iv_item_gridview,options,new ImageLoadingListener() {
						
						@Override
						public void onLoadingStarted(String arg0, View arg1) {
							
						}
						
						@Override
						public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
							
						}
						
						@Override
						public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
							
						}
						
						@Override
						public void onLoadingCancelled(String arg0, View arg1) {
							
						}
					});
			
			}
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	private class ViewHolder {

		ImageView iv_item_gridview;
	}
}
