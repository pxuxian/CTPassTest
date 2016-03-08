package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.FileUtil;

public class ReportActivity extends Activity {
	private LinearLayout linearLayoutAll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_activity);
		this.initView();
	}

	private void initView() {
		String filePath = LogUtil.GetReportFilePath();
		String[] fileNameList = FileUtil.getFileNames(filePath);
		linearLayoutAll = ((LinearLayout) this.findViewById(R.id.gd_all));

		for (final String fileName : fileNameList) {
			String caseName = FileUtil.parseFileName(fileName);
			LinearLayout linearLayout = new LinearLayout(this);
			Button textViewReportName = new Button(this);
			textViewReportName.setText(caseName);
			textViewReportName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			textViewReportName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra("fileName", fileName);
					intent.setClass(ReportActivity.this, TestReportActivity.class);
					startActivity(intent);
				}
			});
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
