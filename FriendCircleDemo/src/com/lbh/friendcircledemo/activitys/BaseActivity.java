package com.lbh.friendcircledemo.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.lbh.friendcircledemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseActivity extends Activity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// /**
		// * 参数配置
		// */
		// DisplayImageOptions options = new DisplayImageOptions.Builder()
		// .showImageOnLoading(R.drawable.ic_stub)
		// .showImageForEmptyUri(R.drawable.ic_empty)
		// .showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
		// .cacheOnDisk(true).considerExifParams(true)
		// .bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	/**
	 * 暴露出一个方法，便于每个子类都可以使用imageloader 默认显示效果是淡入淡出
	 * 
	 * @param uri
	 * @param iv
	 */
	public void loadImage(String uri, ImageView iv) {
		imageLoader.getInstance().displayImage(uri, iv);
	}
}
