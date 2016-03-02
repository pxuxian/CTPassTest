package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.ProgressDialogUtil;
import com.ailk.obs.ctpass.util.SharedPreferencesWrapper;

public class AStepActivity extends Activity {
	private String currentMobileNumber;
	private String currentPcCode;
	private TextView mEditTextCellPhone;
	private TextView mEditTextpcCode;
	private Button mButtonGoFormal;
	private Button mButtonGoTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.astep_activity);
		this.initView();
	}

	private void initView() {
		mEditTextCellPhone = (TextView) findViewById(R.id.textCellPhone);
		mEditTextpcCode = (TextView) findViewById(R.id.textpcCode);
		mButtonGoFormal = (Button) findViewById(R.id.btn_formal_astep);
		mButtonGoTest = (Button) findViewById(R.id.btn_test_astep);
		// 自动读取内存中的phone和pcCode
		this.initMobileNumber();
		this.initPcNumber();

		mButtonGoFormal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 自动将phone和pcCode写入内存
				if (setMobileNumber() && setPcNumber()) {
					startActivity(new Intent(AStepActivity.this, AStepCaseActivity.class));
					// ProgressDialogUtil.showProgress(AStepActivity.this,
					// "正式环境正在测试中，请稍后。。。。");
				} else {
					return;
				}
			}
		});

		mButtonGoTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 自动将phone和pcCode写入内存
				if (setMobileNumber() && setPcNumber()) {
					ProgressDialogUtil.showProgress(AStepActivity.this, "测试环境正在测试中，请稍后。。。。");
				} else {
					return;
				}
			}
		});
	}

	public void reportToast(String message) {
		ActivityUtil.reportToast(this, message);
	}

	// 自动输入手机号码
	public void initMobileNumber() {
		String mobileNumber = SharedPreferencesWrapper.getCacheInstance().readString(
				SharedPreferencesWrapper.MOBILE_NUMBER, "");
		if (mobileNumber != null && !"".equals(mobileNumber.trim())) {
			currentMobileNumber = mobileNumber;
			mEditTextCellPhone.setText(mobileNumber);
		}
	}

	public boolean setMobileNumber() {
		final String cellPhone = mEditTextCellPhone.getText().toString().trim();
		SharedPreferencesWrapper.getCacheInstance().writeString(SharedPreferencesWrapper.MOBILE_NUMBER, cellPhone);
		if (cellPhone.equals("") || cellPhone.length() == 0 || cellPhone == null) {
			reportToast("请输入认证手机号");
			return false;
		} else {
			return true;
		}
	}

	// 自动输入pc码
	public void initPcNumber() {
		String pcCode = SharedPreferencesWrapper.getCacheInstance().readString(SharedPreferencesWrapper.PC_CODE, "");
		if (pcCode != null && !"".equals(pcCode.trim())) {
			currentPcCode = pcCode;
			mEditTextpcCode.setText(pcCode);
		}
	}

	public boolean setPcNumber() {
		final String pcNumber = mEditTextpcCode.getText().toString().trim();
		SharedPreferencesWrapper.getCacheInstance().writeString(SharedPreferencesWrapper.PC_CODE, pcNumber);
		if (pcNumber.equals("") || pcNumber.length() == 0 || pcNumber == null) {
			reportToast("请输入认证PC码");
			return false;
		} else {
			return true;
		}
	}

}
