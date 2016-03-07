package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.ActivityUtil;

public class ReportActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_activity);
		
		LinearLayout linearLayout = ((LinearLayout) this.findViewById(R.id.report_linearLayout));
		TextView textView1 = new TextView(this);
		textView1.setText("test1");
		linearLayout.addView(textView1);
		
		textView1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reportToast("dddddddddd");
			//	reportToast(((TextView)v).getText().toString());
			}
		});
	}

	
	public void reportToast(String message) {
		ActivityUtil.reportToast(this, message);
	}
	
}
