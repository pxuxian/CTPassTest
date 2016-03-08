package com.ailk.obs.ctpass.activity;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.TestReportActivity;
import com.ailk.obs.ctpass.log.LogUtil;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.FileUtil;

public class ReportActivity extends Activity {
	private FileUtil fileUtil;
	private LogUtil logUtil;
	private LinearLayout linearLayout;
	private LinearLayout linearLayoutAll;
	private Button textViewReportName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_activity);
		this.initView();
	}

	private void initView() {

		String filePath = logUtil.GetReportFilePath();
		List<File> fileList = fileUtil.ReadFile(filePath);
		linearLayoutAll = ((LinearLayout) this.findViewById(R.id.gd_all));

		for (final File fileName : fileList) {
			String caseName = fileUtil.parseFileName(fileName.toString());
			linearLayout = new LinearLayout(this);
			textViewReportName = new Button(this);
			textViewReportName.setText(caseName);
			linearLayout.addView(textViewReportName);
			textViewReportName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra("fileName", fileName.toString());
					intent.setClass(ReportActivity.this, TestReportActivity.class);
					startActivity(intent);
					
					
					reportToast(((TextView) v).getText().toString());
				}
			});
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
