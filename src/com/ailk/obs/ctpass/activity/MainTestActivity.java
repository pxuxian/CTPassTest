package com.ailk.obs.ctpass.activity;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.DateUtil;

public class MainTestActivity extends Activity implements OnClickListener {

	private TextView allTestTv;
	private TextView oneceTestTv;
	private TextView reportTv;
	private TextView testLogTv;
	private TextView userNameTv;
	private TextView datetimeTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		userNameTv = (TextView) findViewById(R.id.user_tv);
		userNameTv.setText("用户您好：");
		datetimeTv = (TextView) findViewById(R.id.datetime_tv);
		datetimeTv.setText("今天是" + DateUtil.format(new Date()) + "星期" + DateUtil.getWeekDay(new Date()));

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
			case R.id.all_test_tv:
				startActivity(new Intent(MainTestActivity.this, IndexActivity.class));
				break;
			case R.id.onece_test_tv:
				startActivity(new Intent(MainTestActivity.this, AStepActivity.class));
				break;
			case R.id.report_tv:
				startActivity(new Intent(MainTestActivity.this, ReportActivity.class));
				break;
			case R.id.test_log_tv:
				break;
			default:
				break;
		}
	}

}
