package com.ailk.obs.ctpass;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ailk.obs.ctpass.activity.AStepActivity;
import com.ailk.obs.ctpass.activity.IndexActivity;
import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.DateUtil;

public class MainTestActivity extends Activity implements OnClickListener {

	private TextView allTestTv;
	private TextView oneceTestTv;
	private TextView reportTv;
	private TextView testLogTv;
	private TextView userNameTv;
	private TextView datetimeTv;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String str = (String) msg.obj;
				userNameTv.setText(str);
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
	}

	private void findView() {
		userNameTv = (TextView) findViewById(R.id.user_tv);
		userNameTv.setText("您好");
		datetimeTv = (TextView) findViewById(R.id.datetime_tv);
		datetimeTv.setText("今天是" + DateUtil.format(new Date()) + "，" + DateUtil.getWeekDay(new Date()));

		allTestTv = (TextView) findViewById(R.id.all_test_tv);
		oneceTestTv = (TextView) findViewById(R.id.onece_test_tv);
		reportTv = (TextView) findViewById(R.id.report_tv);
		testLogTv = (TextView) findViewById(R.id.test_log_tv);

		allTestTv.setOnClickListener(this);
		oneceTestTv.setOnClickListener(this);
		reportTv.setOnClickListener(this);
		testLogTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.all_test_tv:// 单步测试
			Intent intent = new Intent(MainTestActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
		case R.id.onece_test_tv:// 一步测试
			LogUtil.write("test....");
			Intent astepIntent = new Intent(MainTestActivity.this, AStepActivity.class);
			startActivity(astepIntent);
			// 一般用于子线程和主线程通信
			Message msg = new Message();
			msg.what = 1;
			msg.obj = "122334";
			mHandler.sendMessage(msg);

			break;
		case R.id.report_tv:// 测试报告

			break;
		case R.id.test_log_tv:// 测试日志

			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
}
