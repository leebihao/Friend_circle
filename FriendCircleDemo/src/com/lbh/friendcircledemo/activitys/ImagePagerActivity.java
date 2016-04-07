package com.lbh.friendcircledemo.activitys;

import uk.co.senab.photoview.PhotoView;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lbh.friendcircledemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImagePagerActivity extends BaseActivity {

	private ViewPager pager;

	private static final String STATE_POSITION = "STATE_POSITION";

	DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager_image);

		initView();

		initData(savedInstanceState);

	}

	private void initView() {

		pager = (ViewPager) findViewById(R.id.vp_image_show);

	}

	private void initData(Bundle savedInstanceState) {

		Bundle bundle = getIntent().getExtras();
		assert bundle != null;
		String[] imageUrls = bundle.getStringArray("imagelist");
		int pagerPosition = bundle.getInt("position", 0);

		if (savedInstanceState != null) {

			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error)
				.resetViewBeforeLoading(true).cacheOnDisk(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		pager.setAdapter(new MyPagerAdapter(imageUrls));
		pager.setCurrentItem(pagerPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	/**
	 * viewpager 的adapter
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		private String[] images;

		private LayoutInflater inflater;

		public MyPagerAdapter(String[] imageUrls) {

			this.images = imageUrls;
			this.inflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(R.layout.item_viewpager_photo,
					view, false);
			assert imageLayout != null;

			PhotoView imageView = (PhotoView) imageLayout
					.findViewById(R.id.iv_viewpager_display_image);
			final ProgressBar pb_loading = (ProgressBar) imageLayout
					.findViewById(R.id.pb_loading);

			imageLoader.displayImage(images[position], imageView, options,
					new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String arg0, View arg1) {

							pb_loading.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String arg0, View arg1,
								FailReason arg2) {

							Toast.makeText(ImagePagerActivity.this, "加载图片失败！",
									Toast.LENGTH_SHORT).show();
							pb_loading.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingComplete(String arg0, View arg1,
								Bitmap arg2) {

							pb_loading.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingCancelled(String arg0, View arg1) {

							pb_loading.setVisibility(View.GONE);
						}

					});

			
			view.addView(imageLayout, 0);
			return imageLayout;

		}
	}

	/**
	 * activity间转换动画
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.activity_anim_in,
				R.anim.activity_anim_out);
	}
}
