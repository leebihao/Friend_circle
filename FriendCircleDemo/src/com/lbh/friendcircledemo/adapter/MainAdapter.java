package com.lbh.friendcircledemo.adapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lbh.friendcircledemo.R;
import com.lbh.friendcircledemo.activitys.ImagePagerActivity;
import com.lbh.friendcircledemo.activitys.UserDataPageActivity;
import com.lbh.friendcircledemo.activitys.WebViewActivity;
import com.lbh.friendcircledemo.domain.UserData;
import com.lbh.friendcircledemo.views.ContentTextView;
import com.lbh.friendcircledemo.views.PhotoGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainAdapter extends BaseAdapter {

	private static Context context;// 上下文

	private List<UserData> infos;// 用户数量集合

	private String[] imageUrls;// 模拟图片的集合

	private ImageLoader imageLoader;

	private ImageView iv_admire;// 电赞按钮

	private ImageView iv_comment;// 评论按钮

	private int btnMenuHeight;

	private LinearLayout ll_edit_msg;
	private EditText et_send_msg;
	private Button btn_send;

	// private Handler handler;

	private PopupWindow popupWindow;

	private int measuredWidth;// 测量宽度
	private int measureHeight;// 测量高度

	int maxContentTextLine = 3;// contentText正常显示的最大行数

	public MainAdapter(Context context, List<UserData> infos,
			String[] imageUrls, ImageLoader loader, LinearLayout ll_edit_msg,
			EditText et_send_msg, Button btn_send) {

		this.context = context;
		this.infos = infos;
		this.imageUrls = imageUrls;
		this.imageLoader = loader;
		this.ll_edit_msg = ll_edit_msg;
		this.et_send_msg = et_send_msg;
		this.btn_send = btn_send;
	}

	@Override
	public int getCount() {

		return infos.size();
	}

	@Override
	public UserData getItem(int position) {
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;

		if (convertView == null) {

			holder = new ViewHolder();

			convertView = View.inflate(context,
					R.layout.item_friends_circle_list, null);

			holder.iv_user_icon = (ImageView) convertView
					.findViewById(R.id.iv_user_icon);
			holder.iv_user_icon.setFocusable(true);
			holder.iv_user_icon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					Intent intent =new Intent();
					intent.setClass(context, UserDataPageActivity.class);
					context.startActivity(intent);
				}
			});
			
			holder.tv_user_name = (TextView) convertView
					.findViewById(R.id.tv_user_name);
			holder.tv_content = (ContentTextView) convertView
					.findViewById(R.id.tv_content);
			holder.tv_more_text = (TextView) convertView
					.findViewById(R.id.tv_more_text);
			holder.ll_show_photo = (LinearLayout) convertView
					.findViewById(R.id.ll_show_photo);
			holder.iv_content = (ImageView) convertView
					.findViewById(R.id.iv_content);
			holder.gv_show_photo = (PhotoGridView) convertView
					.findViewById(R.id.gv_show_photo);

			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.iv_menu = (ImageView) convertView.findViewById(R.id.iv_menu);
			holder.ll_admire_user = (LinearLayout) convertView
					.findViewById(R.id.ll_admire_user);
			// holder.tv_left_admire_photo = (ImageView) convertView
			// .findViewById(R.id.tv_left_admire_photo);
			holder.tv_admire_name = (TextView) convertView
					.findViewById(R.id.tv_admire_name);
			holder.v_diver = convertView.findViewById(R.id.v_diver);
			holder.lv_comment = (ListView) convertView
					.findViewById(R.id.lv_comment);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_user_name.setText(infos.get(position).getUserName());
		// holder.tv_content.setText(infos.get(position).getContentText());

		String text = "https://www.baidu.com/在当今世界风云变换、"
				+ "物欲横流的时代拥有一颗备受熏陶的热爱自然、社会和人类的心至为重要。一个人在坠地的那刻起就有着一颗特殊的爱，"
				+ "不仅仅是源自于母爱，更多的是来自己自己内心的细胞，我们可以说是“自尊自爱”之心。"
				+ "唯有懂得爱惜自己尊重自己的人才能“己所不欲勿施于人”，才能做到“老吾老以及人之老，幼吾幼以及人之幼”，"
				+ "进而具备“穷则独善其身，达则兼济天下”的中华民族传统美德。这样一说范围就被我扩大了。";
		Pattern pattern = Pattern.compile(
				"[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.]]*",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);

		// holder.tv_content.setText(R.string.content);
		SpannableString sps = new SpannableString(text);
		int startPaint = 0;
		while (matcher.find(startPaint)) {
			int endPaint = matcher.end();
			String hit = matcher.group();
			ClickableSpan clickPan = new NoLineClickPan(hit);

			sps.setSpan(clickPan, endPaint - hit.length(), endPaint,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			startPaint = endPaint;
		}
		// holder.tv_content.setText("我要加你为好友");
		holder.tv_content.setText(sps);
		// 设置text中超链接可以被点击
		holder.tv_content.setMovementMethod(LinkMovementMethod.getInstance());

		holder.tv_content.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				showDialog(holder);

				// AlertDialog.Builder builder=new AlertDialog.Builder(context);
				// AlertDialog dialog = builder.create();
				// dialog.setTitle("test");
				// dialog.
				return true;
			}
		});
		holder.tv_content.setHeight(holder.tv_content.getLineHeight()
				* maxContentTextLine);

		holder.iv_content.post(new Runnable() {

			@Override
			public void run() {
				holder.tv_more_text.setVisibility(holder.tv_content
						.getLineCount() > maxContentTextLine ? View.VISIBLE
						: View.GONE);

			}
		});

		holder.tv_more_text.requestFocus();
		holder.tv_more_text.setOnClickListener(new OnClickListener() {

			private boolean isTextExpand;

			@Override
			public void onClick(View v) {
				isTextExpand = !isTextExpand;
				// Toast.makeText(context, "点击了更多操作", 0).show();
				holder.tv_content.clearAnimation();
				final int deltaValue;
				final int startValue = holder.tv_content.getHeight();
				int durationMillis = 350;
				if (isTextExpand) {
					deltaValue = holder.tv_content.getLineHeight()
							* holder.tv_content.getLineCount() - startValue;
					// RotateAnimation animation = new RotateAnimation(0, 180,
					// Animation.RELATIVE_TO_SELF, 0.5f,
					// Animation.RELATIVE_TO_SELF, 0.5f);
					// animation.setDuration(durationMillis);
					// animation.setFillAfter(true);
					// holder.tv_more_text.startAnimation(animation);
					// isTextExpand = true;
				} else {
					deltaValue = holder.tv_content.getLineHeight()
							* maxContentTextLine - startValue;
					// RotateAnimation animation = new RotateAnimation(180, 0,
					// Animation.RELATIVE_TO_SELF, 0.5f,
					// Animation.RELATIVE_TO_SELF, 0.5f);
					// animation.setDuration(durationMillis);
					// animation.setFillAfter(true);
					// holder.tv_more_text.startAnimation(animation);
					// isTextExpand = false;
				}

				Animation animation = new Animation() {
					protected void applyTransformation(float interpolatedTime,
							Transformation t) {
						holder.tv_content
								.setHeight((int) (startValue + deltaValue
										* interpolatedTime));
					}

				};
				animation.setDuration(durationMillis);
				holder.tv_content.startAnimation(animation);
			}
		});
		// gridview显示图片
		// ImageLoader.getInstance().displayImage(imagelist.get(position),
		// holder.gv_show_photo, new SimpleImageLoadingListener());

		if (imageUrls != null) {
			holder.gv_show_photo.setVisibility(View.VISIBLE);
		}
		if (imageUrls.length == 4) {
			holder.gv_show_photo.setNumColumns(2);
		} else {
			holder.gv_show_photo.setNumColumns(3);
		}
		holder.gv_show_photo.setAdapter(new GridViewAdapter(context,
				imageLoader, imageUrls));
		holder.gv_show_photo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				startImageViewPageActivity(position);
			}
		});

		btnMenuHeight = holder.iv_menu.getHeight();
		/**
		 * 点击右下角菜单，弹出评论和点赞菜单
		 */
		holder.iv_menu.requestFocus();
		holder.iv_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showMenuPopupWindow(v, holder);
			}
		});

		// imageLoader.displayImage(imagelist.get(position), holder.)
		return convertView;
	}

	/**
	 * 弹出复制菜单
	 */
	protected void showDialog(final ViewHolder holder) {

		String[] s = { "copy" };
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(s, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				ClipboardManager clipboardManager = (ClipboardManager) context
						.getSystemService(Context.CLIPBOARD_SERVICE);
				clipboardManager.setText(holder.tv_content.getText());
				clipboardManager.getText();
			}
		});
		builder.create().show();
	}

	/**
	 * 点赞和评论功能
	 */
	protected void showMenuPopupWindow(View v, final ViewHolder holder) {

		View view = View.inflate(context, R.layout.item_popupwindow, null);
		iv_admire = (ImageView) view.findViewById(R.id.iv_admire);
		iv_comment = (ImageView) view.findViewById(R.id.iv_comment);

		iv_admire.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				holder.ll_admire_user.setVisibility(View.VISIBLE);

				holder.tv_admire_name.setVisibility(View.VISIBLE);
				// handler.sendEmptyMessage(0);
				// TextView tv_user_name = new TextView(context);
				// tv_user_name.setText("习大大");
				// tv_user_name.setTextSize(14);
				// tv_user_name.setTextColor(Color.BLUE);
				// holder.ll_admire_user.addView(tv_user_name);

				if (holder.tv_admire_name.getText() != null) {
					holder.tv_admire_name
							.setText(","
									+ "习大大,习近平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，邓小平，");
				} else {
					holder.tv_admire_name.setText("习大大");
				}

			}
		});

		iv_comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Message msg = Message.obtain();
				// msg.what = 0;
				// msg.obj = holder.lv_comment;
				// handler.sendMessage(msg);

				ll_edit_msg.setVisibility(View.VISIBLE);
				et_send_msg.requestFocus();

				InputMethodManager manager = (InputMethodManager) et_send_msg
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				manager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

				btn_send.setOnClickListener(new EditCommentListener(holder));

			}
		});

		dismissPopupWindow();

		popupWindow = new PopupWindow(view, -2, -2);

		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		popupWindow.setFocusable(true);

		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		measuredWidth = view.getMeasuredWidth();
		measureHeight = view.getMeasuredHeight();

		popupWindow.showAsDropDown(v, -measuredWidth, -measureHeight);
		ScaleAnimation sa = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(200);
		AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
		aa.setDuration(200);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(aa);
		set.addAnimation(sa);
		view.startAnimation(set);
	}

	/**
	 * 点击每张图片，就跳转到viewpage页查询放大图片
	 * 
	 * @param position
	 */
	protected void startImageViewPageActivity(int position) {

		Intent intent = new Intent();
		intent.setClass(context, ImagePagerActivity.class);
		intent.putExtra("position", position);
		intent.putExtra("imagelist", imageUrls);

		context.startActivity(intent);
		((Activity) context).overridePendingTransition(R.anim.activity_anim_in,
				R.anim.activity_anim_out);
	}

	private class ViewHolder {

		ImageView iv_user_icon;
		TextView tv_user_name;
		ContentTextView tv_content;
		TextView tv_more_text;
		LinearLayout ll_show_photo;
		ImageView iv_content;
		PhotoGridView gv_show_photo;
		TextView tv_time;
		ImageView iv_menu;

		LinearLayout ll_admire_user;
		TextView tv_admire_name;
		// ImageView tv_left_admire_photo;

		View v_diver;
		ListView lv_comment;

	}

	/**
	 * 隐藏popupwindow
	 */
	private void dismissPopupWindow() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}

	private class EditCommentListener implements OnClickListener {

		private ViewHolder holder;

		public EditCommentListener(ViewHolder holder) {

			this.holder = holder;

		}

		@Override
		public void onClick(View v) {
			String text = et_send_msg.getText().toString();// 评论内容

			if (text != null) {
				holder.lv_comment.setVisibility(View.VISIBLE);
			}
			holder.lv_comment.setAdapter(new ListCommentAdapter(context, text,
					"习大大"));

			if (!TextUtils.isEmpty(text)) {
				et_send_msg.setText(null);
			}
			ll_edit_msg.setVisibility(View.GONE);
		}

	}

	private class NoLineClickPan extends ClickableSpan {

		String text;// 显示的文本内容

		public NoLineClickPan(String text) {

			this.text = text;
		}

		@Override
		public void updateDrawState(TextPaint ds) {

			ds.setColor(Color.BLUE);// 设置文本颜色
			ds.setUnderlineText(false);// 去掉超链接下划线
		}

		@Override
		public void onClick(View widget) {
			Toast.makeText(context, "跳转到webview", 0).show();
			Intent intent = new Intent();
			intent.setClass(context, WebViewActivity.class);
			intent.putExtra("url", text);
			context.startActivity(intent);

		}

	}
}
