package com.lbh.friendcircledemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.TextView;

public class ContentTextView extends TextView {

	public ContentTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ContentTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ContentTextView(Context context) {
		super(context);
	}

	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
	}
	
	
}
