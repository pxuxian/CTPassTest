package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.ProgressDialogUtil;

public class AStepActivity extends Activity {

	private Button mButtonGoFormal;
	private Button mButtonGoTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.astep_activity);
		this.initView();
	}

	private void initView() {
		mButtonGoFormal = (Button) findViewById(R.id.btn_formal_astep);
		mButtonGoTest = (Button) findViewById(R.id.btn_test_astep);
		mButtonGoFormal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressDialogUtil.showProgress(AStepActivity.this, "��ʽϵͳ���ڲ����У����Ժ󡣡�����");
				
				
			}
		});

		mButtonGoTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ProgressDialogUtil.showProgress(AStepActivity.this, "����ϵͳ���ڲ����У����Ժ󡣡�����");
			}
		});
	}

}
