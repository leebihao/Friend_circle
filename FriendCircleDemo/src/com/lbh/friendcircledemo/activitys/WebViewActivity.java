package com.lbh.friendcircledemo.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.lbh.friendcircledemo.R;

public class WebViewActivity extends Activity implements OnClickListener {

	private WebView mWebView;

	private ImageButton btnBack;
	private ImageButton btnSize;
	private ImageButton btnShare;

	private ProgressBar pb;

	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_webview);

		mWebView = (WebView) findViewById(R.id.wv_news);
		btnBack = (ImageButton) findViewById(R.id.btn_back);
		btnSize = (ImageButton) findViewById(R.id.btn_size);
		btnShare = (ImageButton) findViewById(R.id.btn_share);

		btnBack.setOnClickListener(this);
		btnSize.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		pb = (ProgressBar) findViewById(R.id.pb_webview);

		url = getIntent().getStringExtra("url");

		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);// 表示支持js
		settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
		settings.setUseWideViewPort(true);// 支持双击缩放

		mWebView.setWebViewClient(new WebViewClient() {

			/**
			 * 网页开始加载
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				System.out.println("网页开始加载");
				pb.setVisibility(View.VISIBLE);
			}

			/**
			 * 网页加载结束
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("网页开始结束");

				pb.setVisibility(View.GONE);
			}

			/**
			 * 所有跳转的链接都会在此方法中回调
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// tel:110
				System.out.println("跳转url:" + url);
				view.loadUrl(url);

				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});

		mWebView.setWebChromeClient(new WebChromeClient() {

			/**
			 * 进度发生变化
			 */
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				System.out.println("加载进度:" + newProgress);
				super.onProgressChanged(view, newProgress);
			}

			/**
			 * 获取网页标题
			 */
			@Override
			public void onReceivedTitle(WebView view, String title) {
				System.out.println("网页标题:" + title);
				super.onReceivedTitle(view, title);
			}
		});

		mWebView.loadUrl(url);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:// 返回

			finish();
			break;
		case R.id.btn_size:// 改变文字大小

//			showChooseDialog();
			break;
		case R.id.btn_share:// 分享软件

//			showShare();
			break;

		default:
			break;
		}
	}
}
