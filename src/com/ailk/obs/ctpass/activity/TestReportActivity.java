package com.ailk.obs.ctpass.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.FileUtil;

public class TestReportActivity extends Activity {
	private LinearLayout linearLayoutAll;

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
		List<String> contents = FileUtil.read(fileName);
		for (String content : contents) {
			LinearLayout linearLayout = new LinearLayout(this);
			TextView textViewReportName = new TextView(this);
			textViewReportName.setText(content);
			linearLayout.addView(textViewReportName);
			linearLayoutAll.addView(linearLayout);
		}
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
