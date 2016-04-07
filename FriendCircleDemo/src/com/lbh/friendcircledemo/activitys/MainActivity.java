package com.lbh.friendcircledemo.activitys;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lbh.friendcircledemo.R;
import com.lbh.friendcircledemo.adapter.MainAdapter;
import com.lbh.friendcircledemo.domain.UserData;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends BaseActivity implements OnScrollListener {

	private ListView lv_content;
	private LinearLayout ll_edit_msg;
	private EditText et_send_msg;
	private Button btn_send;

	/**
	 * 用户信息集合
	 */
	private List<UserData> infos;

	private UserData userData;

	// private List<String> imagelist = new ArrayList<String>();
	/**
	 * 模拟的出现9张图片集合数据
	 */
	String[] imageUrls = new String[] {
			"http://192.168.0.119:8080/imageTest/1.jpg",
			"http://192.168.0.119:8080/imageTest/2.jpg",
			"http://192.168.0.119:8080/imageTest/3.jpg",
			"http://192.168.0.119:8080/imageTest/4.jpg",
			"http://192.168.0.119:8080/imageTest/5.jpg",
			"http://192.168.0.119:8080/imageTest/6.jpg",
			"http://192.168.0.119:8080/imageTest/7.jpg",
			"http://192.168.0.119:8080/imageTest/8.jpg",
			"http://192.168.0.119:8080/imageTest/9.jpg" };

	/**
	 * 出现4张图片的情况
	 */
	// String[] imageUrls = new String[] {
	// "http://10.0.2.2:8080/imageTest/1.jpg",
	// "http://192.168.0.117:8080/imageTest/2.jpg",
	// "http://192.168.0.117:8080/imageTest/3.jpg",
	// "http://192.168.0.117:8080/imageTest/4.jpg" };

	/**
	 * 出现6张图的情况
	 */
	// String[] imageUrls = new String[] {
	// "http://10.0.2.2:8080/imageTest/1.jpg",
	// "http://192.168.0.117:8080/imageTest/2.jpg",
	// "http://192.168.0.117:8080/imageTest/3.jpg",
	// "http://192.168.0.117:8080/imageTest/4.jpg",
	// "http://192.168.0.117:8080/imageTest/5.jpg",
	// "http://192.168.0.117:8080/imageTest/6.jpg" };
	// 10.0.2.2
	private ImageLoader loader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initViews();
		initData();
	}

	/**
	 * 初始化界面
	 */
	private void initViews() {

		lv_content = (ListView) findViewById(R.id.lv_content);
		ll_edit_msg = (LinearLayout) findViewById(R.id.ll_edit);
		btn_send = (Button) findViewById(R.id.btn_send);
		et_send_msg = (EditText) findViewById(R.id.et_send_content);

		ll_edit_msg.setVisibility(View.GONE);// default ll_edit_msg not show

	}

	/**
	 * 数据的填充
	 */
	private void initData() {

		// 模拟服务器信息
		infos = new ArrayList<UserData>();
		userData = new UserData();
		for (int i = 0; i < 10; i++) {
			userData.setUserName("奥巴马");
			userData.setContentText("今天我要访问你美国ahdkhaihdhasdhliasjdiljaslijdljasldjlasjdljaslj！");
			infos.add(userData);
		}

		// for (int i = 0; i < urls.length; i++) {
		// imagelist.add(urls[i]);
		// }

		loader = imageLoader.getInstance();

		lv_content.setAdapter(new MainAdapter(this, infos, imageUrls, loader,
				ll_edit_msg, et_send_msg, btn_send));
		lv_content.setOnScrollListener(this);// 设置滑动监听，滑动时候，自动隐藏编辑框
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

//	/**
//	 * 切换展示图片的方法
//	 */
//	private void switchDisplayPhoto() {
//
//	}

	/**
	 * 滚动监听
	 * 
	 * @param view
	 * @param firstVisibleItem
	 * @param visibleItemCount
	 * @param totalItemCount
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
			InputMethodManager imm = (InputMethodManager) this
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(et_send_msg.getWindowToken(), 0);

			et_send_msg.setText("");
			ll_edit_msg.setVisibility(View.GONE);
		}

	}
}
