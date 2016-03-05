package com.ailk.obs.ctpass.activity;

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
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.manage.BindServiceConnection;
import com.ailk.obs.ctpass.manage.BindServiceManager;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class AStepCaseActivity extends Activity {
	private CheckBox mButtonBindService;
	private CheckBox mButtonConnect;
	private String phoneNumber;
	private String pcCode;
	private int hostUrl;
	private Button mButtonStepTest;

	// 检查认证结果
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.CASE_TOAST:
				break;

			case Constants.CASE_BIND:
				if (msg.getData().getBoolean("FLAG")) {
					mButtonBindService.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonBindService.setBackgroundColor(Constants.COLOR_RED);
				}

				break;
			case Constants.CASE_CONN:
				if (msg.getData().getBoolean("FLAG")) {
					mButtonConnect.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonConnect.setBackgroundColor(Constants.COLOR_RED);
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
		mButtonConnect = (CheckBox) findViewById(R.id.buttonConnect);
		mButtonStepTest = (Button) findViewById(R.id.bt_aStepTest);

		Intent intent = getIntent();
		phoneNumber = intent.getStringExtra("phoneNumber");
		pcCode = intent.getStringExtra("pcCode");
		String s = intent.getStringExtra("hostUrl");
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
				final String bindServiceFlag = mButtonBindService.isChecked() == true ? "1" : "0";
				if (bindServiceFlag.equals("1")) {
					bindServiceManager.bindService(view.getContext(), serviceConnection, handler);
				}

				// 建立机卡连接
				final String connectServiceFlag = mButtonConnect.isChecked() == true ? "1" : "0";
				if (connectServiceFlag.equals("1")) {
					if (serviceConnection.getCtpassAIDLService() != null) {
						try {
							serviceConnection.getCtpassAIDLService().connectCTPassService();
							return;
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					HandlerUtil.send(handler, Constants.CASE_CONN, "建立机卡连接失败", false);
				}
			}
		});

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
