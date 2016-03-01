package com.ailk.obs.ctpass.activity;

import java.util.Date;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.DateUtil;
import com.ailk.obs.ctpass.util.HttpClient;

public class AStepCaseActivity extends Activity {
	private CheckBox mButtonBindService;

	// private Button

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.astep_case_activity);
		this.initView();
	}

	private void initView() {
		// mButtonBindService = (CheckBox) findViewById(R.id.buttonBindService);
		// final String pCFlagBindService = mButtonBindService.isChecked() ==
		// true ? "1" : "0";

		String url = "http://42.99.0.103:8891/inf/CTPassInterface/AuthOTP";
		String APP_KEY = "F160F54369DF9235902A85943112E908";
		String APP_ID = "0100000000000001";
		String txtOTP = "123132";
		String txtMobile = "123";
		String PCFlag = "0";
		String Timestamp = DateUtil.formatDate(new Date());
		String Signature = APP_ID + txtOTP + txtMobile + PCFlag + Timestamp;
		String encryptSignature = "";
		try {
		//	encryptSignature = Cryptogram.encryptByKey(Signature, APP_KEY);
			encryptSignature="F160F54369DF9235902A85943112E908F160F54369DF9235902A85943112E908";

			JSONObject params = new JSONObject();
			params.put("APP_ID", APP_ID);
			params.put("txtOTP", txtOTP);
			params.put("txtMobile", txtMobile);
			params.put("PCFlag", PCFlag);
			params.put("Timestamp", Timestamp);
			params.put(Signature, encryptSignature);
			HttpClient.httpPost(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bt_selectall(View view) {
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
