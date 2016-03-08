package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.FileUtil;

public class TestReportActivity extends Activity {
	private FileUtil fileUtil;
	private LogUtil logUtil;
	private LinearLayout linearLayout;
	private LinearLayout linearLayoutAll;
	private TextView textViewReportName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_report_activity);
		this.initView();
	}

	private void initView() {
		Intent intent = getIntent();
		String fileName = intent.getStringExtra("fileName");
		linearLayoutAll = ((LinearLayout) this.findViewById(R.id.test_report));
		linearLayout = new LinearLayout(this);
		textViewReportName = new TextView(this);
		textViewReportName.setText("dfafa");
		linearLayout.addView(textViewReportName);
		linearLayoutAll.addView(linearLayout);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void reportToast(String message) {
		ActivityUtil.reportToast(this, message);
	}

}
