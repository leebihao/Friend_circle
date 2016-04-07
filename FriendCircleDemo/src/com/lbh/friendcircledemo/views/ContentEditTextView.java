package com.lbh.friendcircledemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.EditText;

public class ContentEditTextView extends EditText {

	public ContentEditTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ContentEditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ContentEditTextView(Context context) {
		super(context);
	}

	/**
	 * 禁止edittext可编辑
	 */
	@Override
	protected boolean getDefaultEditable() {
		return false;
	}

	/**
	 * 不弹出默认的上下文菜单
	 */
	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
	}
}
