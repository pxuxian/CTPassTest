package com.ailk.obs.ctpass.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.conn.BindServiceConnection;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.manage.AuthTokenManager;
import com.ailk.obs.ctpass.manage.BindServiceManager;
import com.ailk.obs.ctpass.module.AuthToken;
import com.ailk.obs.ctpass.util.ActivityUtil;

public class CaseActivity extends Activity {
	private AsyncProvider mAsyncProvider;
	private Builder mAlertDialog;
	private Button mButtonBindService;
	private Button mButtonConnectOMA;
	private Button mButtomGetCTPassToken;
	private TextView mEditTextCellPhoneAuth;
	private Button mButtonAuthTokenByOTA;
	private Button mButtonGenTokenByOTAPC;
	private Button mButtonGenTokenByOTANewPC;

	private BindServiceManager bindServiceManager = new BindServiceManager();
	private AuthTokenManager authTokenManage = new AuthTokenManager();

	// TOKEN OTA认证，检查认证结果
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				reportToast("IsSupport 结果: " + msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("flag")) {
					mButtonConnectOMA.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonConnectOMA.setBackgroundColor(Constants.COLOR_RED);
				}
				break;
			case 2:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("flag")) {
					mButtomGetCTPassToken.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtomGetCTPassToken.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case 3:
				reportToast(msg.getData().getString("RESULT"));
				Button button = null;
				if (msg.arg1 == 0) {
					button = mButtonAuthTokenByOTA;
				} else if (msg.arg1 == 1) {
					button = mButtonGenTokenByOTAPC;
				} else {
					button = mButtonGenTokenByOTANewPC;
				}
				if (msg.getData().getBoolean("flag")) {
					button.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					button.setBackgroundColor(Constants.COLOR_RED);
				}
				break;
			default:
				break;
			}
		};
	};
	private BindServiceConnection serviceConnection = new BindServiceConnection(handler);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.case_activity);
		this.initView();
	}

	private void initView() {
		mAsyncProvider = new AsyncProvider();
		mAlertDialog = new AlertDialog.Builder(this);
		mButtonBindService = (Button) findViewById(R.id.buttonBindService);
		mButtonConnectOMA = (Button) findViewById(R.id.buttonConnectOMA);
		mButtomGetCTPassToken = (Button) findViewById(R.id.buttonGenToken);
		mEditTextCellPhoneAuth = (EditText) findViewById(R.id.textCellPhoneOTA);
		mButtonAuthTokenByOTA = (Button) findViewById(R.id.buttonGenTokenByOTA);
		mButtonGenTokenByOTAPC = (Button) findViewById(R.id.buttonGenTokenByOTAPC);
		mButtonGenTokenByOTANewPC = (Button) findViewById(R.id.buttonGenTokenByOTANewPC);

		mButtonBindService.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				boolean flag = bindServiceManager.getCTPassService(view.getContext(), serviceConnection);
				if (flag) {
					reportToast("绑定服务成功");
					mButtonBindService.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					reportToast("绑定服务失败");
					mButtonBindService.setBackgroundColor(Constants.COLOR_RED);
				}
				try {
					bindServiceManager.getSingInfo(getPackageManager().getPackageInfo("cn.com.chinatelecom.ctpass",
							PackageManager.GET_SIGNATURES));
					bindServiceManager.getSignInfo(getPackageManager().getPackageInfo("cn.com.chinatelecom.ctpass",
							PackageManager.GET_SIGNATURES));
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		mButtonConnectOMA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					try {
						serviceConnection.getCtpassAIDLService().connectCTPassService();
						reportToast("通道连接建立中...");
						return;
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else {
					reportToast("请先绑定服务");
				}
				mButtonConnectOMA.setBackgroundColor(Constants.COLOR_RED);
			}
		});

		class OATListener implements OnClickListener {
			private String pcFlag;

			public OATListener(String pcFlag) {
				this.pcFlag = pcFlag;
			}

			@Override
			public void onClick(View v) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					final String cellPhone = mEditTextCellPhoneAuth.getText().toString().trim();
					if (cellPhone.equals("")) {
						reportToast("请输入认证手机号");
						return;
					}
					authTokenManage.authTokenOTA(cellPhone, pcFlag, serviceConnection, mAsyncProvider, handler);
				}
			}

		}
		mButtonAuthTokenByOTA.setOnClickListener(new OATListener("0"));
		mButtonGenTokenByOTAPC.setOnClickListener(new OATListener("1"));
		mButtonGenTokenByOTANewPC.setOnClickListener(new OATListener("2"));

		mButtomGetCTPassToken.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 自动获取seqid等然后获取s串,再进行认证
				mAsyncProvider.getSeqIDRandom(new RequestListener() {
					@Override
					public void onComplete(final Object response) {
						final AuthToken authToken = authTokenManage.genarateAuthToken(response, "", serviceConnection);
						String token = authToken == null ? null : authToken.toString();
						new AlertDialog.Builder(CaseActivity.this).setTitle("Token参数").setMessage(token)
								.setPositiveButton("验证token", new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										authTokenManage.authTokenOnly(authToken, mAsyncProvider, handler);
									}
								}).setNegativeButton("取消", null).setCancelable(false).create().show();
					}

					@Override
					public void onInvokerError(final String e) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								reportToast(e);
								mButtomGetCTPassToken.setBackgroundColor(Constants.COLOR_RED);
							}
						});
					}

				});

			}
		});

	}

	public void reportToast(String message) {
		ActivityUtil.reportToast(this, message);
	}

	public void showAlert(String title, String message) {
		mAlertDialog.setTitle(title).setMessage(message).setPositiveButton("确定", null).setCancelable(false).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			unbindService(serviceConnection);
		} catch (Exception e) {
		}
		try {
		} catch (Exception e) {
		}
	}

}
