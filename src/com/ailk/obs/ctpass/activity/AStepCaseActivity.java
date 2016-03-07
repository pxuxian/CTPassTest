package com.ailk.obs.ctpass.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.ailk.obs.ctpass.APIProvider;
import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.manage.AuthTokenManager;
import com.ailk.obs.ctpass.manage.BindServiceConnection;
import com.ailk.obs.ctpass.manage.BindServiceManager;
import com.ailk.obs.ctpass.report.ReportUtil;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class AStepCaseActivity extends Activity {
	private String phoneNumber;
	private String pcCode;
	private int hostUrl;
	private List<CheckBox> allCheckBoxs = new ArrayList<CheckBox>();
	private CheckBox mButtonBindService;
	private CheckBox mButtonConnect;
	private CheckBox mButtonGenToken;
	private Button mButtonSelectall;
	private Button mButtonStepTest;
	private AuthTokenManager authTokenManage = new AuthTokenManager();
	private AsyncProvider mAsyncProvider = new AsyncProvider();

	// 检查认证结果
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.CASE_TOAST:
				break;

			case Constants.CASE_BIND:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonBindService.setBackgroundColor(Constants.COLOR_GREEN);
					ReportUtil.report("1", "BindService", true);
				} else {
					mButtonBindService.setBackgroundColor(Constants.COLOR_RED);
					ReportUtil.report("1", "BindService", false);
				}
				testConnect();
				break;
			case Constants.CASE_CONN:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonConnect.setBackgroundColor(Constants.COLOR_GREEN);
					ReportUtil.report("2", "建立机卡连接", true);
				} else {
					mButtonConnect.setBackgroundColor(Constants.COLOR_RED);
					ReportUtil.report("2", "建立机卡连接", false);
				}
				testOthers();
				break;

			case Constants.CASE_AUTH_TOKEN:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonGenToken.setBackgroundColor(Constants.COLOR_GREEN);
					ReportUtil.report("3", "Get CTPass Token(OMA)", true);
				} else {
					mButtonGenToken.setBackgroundColor(Constants.COLOR_RED);
					ReportUtil.report("3", "Get CTPass Token(OMA)", false);
				}
				break;

			default:
				break;
			}
		};
	};
	private BindServiceConnection serviceConnection = new BindServiceConnection(handler);
	private BindServiceManager bindServiceManager = new BindServiceManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.astep_case_activity);
		this.initView();
	}

	private void initView() {

		mButtonBindService = (CheckBox) findViewById(R.id.buttonBindService);
		allCheckBoxs.add(mButtonBindService);
		mButtonConnect = (CheckBox) findViewById(R.id.buttonConnect);
		allCheckBoxs.add(mButtonConnect);
		mButtonGenToken = (CheckBox) findViewById(R.id.buttonGenToken);
		allCheckBoxs.add(mButtonGenToken);
		mButtonStepTest = (Button) findViewById(R.id.bt_aStepTest);
		mButtonSelectall = (Button) findViewById(R.id.bt_selectall);

		Intent intent = getIntent();
		phoneNumber = intent.getStringExtra("phoneNumber");
		pcCode = intent.getStringExtra("pcCode");
		hostUrl = Integer.parseInt(intent.getStringExtra("hostUrl"));
		if (hostUrl == 0) {
			APIProvider.hostURL = APIProvider.RELEASE_HOST_URL;
		} else {
			APIProvider.hostURL = APIProvider.DEBUG_HOST_URL;
		}

		mButtonStepTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// 绑定服务
				if (mButtonBindService.isChecked()) {
					bindServiceManager.bindService(view.getContext(), serviceConnection, handler);
				}
			}
		});

		// 一键测试
		mButtonSelectall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Boolean checked = allCheckBoxs.get(0).isChecked();
				for (CheckBox caseCheckBox : allCheckBoxs) {
					caseCheckBox.setChecked(!checked);
				}

			}
		});

	}

	private void testConnect() {
		// 建立机卡连接
		if (mButtonConnect.isChecked()) {
			if (serviceConnection.getCtpassAIDLService() != null) {
				try {
					serviceConnection.getCtpassAIDLService().connectCTPassService();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			} else {
				HandlerUtil.send(handler, Constants.CASE_CONN, "建立机卡连接失败", false);
			}

		}
	}

	private void testOthers() {
		// get ctpass token by oma
		if (mButtonGenToken.isChecked()) {
			if (serviceConnection.getCtpassAIDLService() != null) {
				authTokenManage.authTokenOMA(serviceConnection, mAsyncProvider, handler);
			} else {
				HandlerUtil.send(handler, Constants.CASE_AUTH_TOKEN, "Token认失败", false);
			}
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
