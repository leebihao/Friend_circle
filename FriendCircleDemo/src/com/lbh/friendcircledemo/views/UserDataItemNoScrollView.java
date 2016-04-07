package com.lbh.friendcircledemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class UserDataItemNoScrollView extends ListView {

	public UserDataItemNoScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public UserDataItemNoScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UserDataItemNoScrollView(Context context) {
		super(context);
	}

	/**
	 * 屏蔽listview的滑动
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}
}
