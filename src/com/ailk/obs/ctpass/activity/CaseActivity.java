package com.ailk.obs.ctpass.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.manage.AuthMixTokenManager;
import com.ailk.obs.ctpass.manage.AuthTokenManager;
import com.ailk.obs.ctpass.manage.BindServiceConnection;
import com.ailk.obs.ctpass.manage.BindServiceManager;
import com.ailk.obs.ctpass.manage.OTPManager;
import com.ailk.obs.ctpass.util.ActivityUtil;

public class CaseActivity extends Activity {
	private Builder mAlertDialog;
	private Button mButtonBindService;
	private Button mButtonConnectOMA;
	private Button mButtomGetCTPassToken;
	private TextView mEditTextCellPhoneAuth;
	private Button mButtonAuthTokenByOTA;
	private Button mButtonGenTokenByOTAPC;
	private Button mButtonGenTokenByOTANewPC;
	private Button mButtonMixTokenAuth;
	private Button mButtonMixTokenAuthByPC;
	private Button mButtonGenOTP;
	private EditText etOMAOTPLength;
	private AsyncProvider mAsyncProvider = new AsyncProvider();
	private BindServiceManager bindServiceManager = new BindServiceManager();
	private AuthTokenManager authTokenManage = new AuthTokenManager();
	private OTPManager oTPManager = new OTPManager();

	// TOKEN OTA认证，检查认证结果
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.CASE_TOAST:
				reportToast(msg.getData().getString("MSG"));
				break;

			case Constants.CASE_CONN:
				reportToast("IsSupport 结果: " + msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonConnectOMA.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonConnectOMA.setBackgroundColor(Constants.COLOR_RED);
				}
				break;
			case Constants.CASE_AUTH_TOKEN:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtomGetCTPassToken.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtomGetCTPassToken.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case Constants.CASE_AUTH_TOKEN_OTA:
				reportToast(msg.getData().getString("RESULT"));
				Button button = null;
				if (msg.arg1 == 0) {
					button = mButtonAuthTokenByOTA;
				} else if (msg.arg1 == 1) {
					button = mButtonGenTokenByOTAPC;
				} else {
					button = mButtonGenTokenByOTANewPC;
				}
				if (msg.getData().getBoolean("FLAG")) {
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
	private AuthMixTokenManager authMixTokenManager = new AuthMixTokenManager(handler, mAsyncProvider);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.case_activity);
		this.initView();
	}

	private void initView() {
		mAlertDialog = new AlertDialog.Builder(this);
		mButtonBindService = (Button) findViewById(R.id.buttonBindService);
		mButtonConnectOMA = (Button) findViewById(R.id.buttonConnectOMA);
		mButtomGetCTPassToken = (Button) findViewById(R.id.buttonGenToken);
		mEditTextCellPhoneAuth = (EditText) findViewById(R.id.textCellPhoneOTA);
		mButtonAuthTokenByOTA = (Button) findViewById(R.id.buttonGenTokenByOTA);
		mButtonGenTokenByOTAPC = (Button) findViewById(R.id.buttonGenTokenByOTAPC);
		mButtonGenTokenByOTANewPC = (Button) findViewById(R.id.buttonGenTokenByOTANewPC);
		mButtonMixTokenAuth = (Button) findViewById(R.id.buttonMixTokenAuth);
		mButtonMixTokenAuthByPC = (Button) findViewById(R.id.buttonMixTokenAuthByPC);
		mButtonGenOTP = (Button) findViewById(R.id.buttonGenOTP);
		etOMAOTPLength = (EditText) findViewById(R.id.etOTPLength);

		// 绑定服务
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

		// 建立机卡连接
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

		// get ctpass token by oma
		mButtomGetCTPassToken.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					authTokenManage.authTokenOMA(serviceConnection, mAsyncProvider, handler);
				} else {
					reportToast("请先绑定服务");
				}
			}
		});

		// get ctpass token by ota
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
				} else {
					reportToast("请先绑定服务");
				}
			}

		}
		mButtonAuthTokenByOTA.setOnClickListener(new OATListener("0"));
		mButtonGenTokenByOTAPC.setOnClickListener(new OATListener("1"));
		mButtonGenTokenByOTANewPC.setOnClickListener(new OATListener("2"));

		// 融合Token认证无pc码
		class OATPCListener implements OnClickListener {
			private String pcFlag;

			public OATPCListener(String pcFlag) {
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
					authMixTokenManager.authMixToken(pcFlag, serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		}
		mButtonMixTokenAuth.setOnClickListener(new OATPCListener("0"));
		mButtonMixTokenAuthByPC.setOnClickListener(new OATPCListener("1"));

		// OTP认证
		mButtonGenOTP.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (serviceConnection.getCtpassAIDLService() != null) {

					int otpLength = -1;
					try {
						otpLength = Integer.parseInt(etOMAOTPLength.getText().toString().trim());
					} catch (Exception e) {
						showAlert("OTP长度转换错误", "OTP长度转换错误,请输入数字");
						return;
					}
					oTPManager.getOTPByOMA(otpLength, serviceConnection, handler);
				} else {
					reportToast("请先绑定服务");
				}
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
