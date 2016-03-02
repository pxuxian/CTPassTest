package com.ailk.obs.ctpass.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.RadioButton;
import android.widget.TextView;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.manage.AuthMixTokenManager;
import com.ailk.obs.ctpass.manage.AuthTokenManager;
import com.ailk.obs.ctpass.manage.BindServiceConnection;
import com.ailk.obs.ctpass.manage.BindServiceManager;
import com.ailk.obs.ctpass.manage.OTPManager;
import com.ailk.obs.ctpass.manage.UpdatePCManager;
import com.ailk.obs.ctpass.util.ActivityUtil;
import com.ailk.obs.ctpass.util.SharedPreferencesWrapper;

public class CaseActivity extends Activity {
	private Button mButtonBindService;
	private Button mButtonConnectOMA;
	private Button mButtomGetCTPassToken;
	private TextView mEditTextCellPhoneAuth;
	private Button mButtonAuthTokenByOTA;
	private Button mButtonGenTokenByOTAPC;
	private Button mButtonGenTokenByOTANewPC;
	private Button mButtonMixTokenAuth;
	private Button mButtonMixTokenAuthByPC;
	private Integer etOMAOTPLength;
	private Button mButtonGenOTP;
	private Button mButtonGenMixOTP;
	private Button mButtonGenMixOTPByPC;
	private Button mButtonGenMixOTPByNewPC;
	private Button mButtonGenOTPOTA;
	private Button mButtonGenOTPOTAByPC;
	private Button mButtonGenOTPOTAByNewPC;
	private Button mButtonUpdatePC;
	private String currentMobileNumber;
	private AsyncProvider mAsyncProvider = new AsyncProvider();
	private BindServiceManager bindServiceManager = new BindServiceManager();
	private AuthTokenManager authTokenManage = new AuthTokenManager();

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
				reportToast(msg.getData().getString("RESULT"));
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
				Button buttonToken = null;
				if (msg.arg1 == 0) {
					buttonToken = mButtonAuthTokenByOTA;
				} else if (msg.arg1 == 1) {
					buttonToken = mButtonGenTokenByOTAPC;
				} else {
					buttonToken = mButtonGenTokenByOTANewPC;
				}
				if (msg.getData().getBoolean("FLAG")) {
					buttonToken.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					buttonToken.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case Constants.CASE_AUTH_MixToken:
				reportToast(msg.getData().getString("RESULT"));
				Button buttonMixToken = null;
				if (msg.arg1 == 0) {
					buttonMixToken = mButtonMixTokenAuth;
				} else {
					buttonMixToken = mButtonMixTokenAuthByPC;
				}
				if (msg.getData().getBoolean("FLAG")) {
					buttonMixToken.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					buttonMixToken.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case Constants.CASE_OTP_OMA:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonGenOTP.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonGenOTP.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case Constants.CASE_OTP_MixOMA:
				reportToast(msg.getData().getString("RESULT"));
				Button buttonMixOMA = null;
				if (msg.arg1 == 0) {
					buttonMixOMA = mButtonGenMixOTP;
				} else if (msg.arg1 == 1) {
					buttonMixOMA = mButtonGenMixOTPByPC;
				} else {
					buttonMixOMA = mButtonGenMixOTPByNewPC;
				}
				if (msg.getData().getBoolean("FLAG")) {
					buttonMixOMA.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					buttonMixOMA.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			case Constants.CASE_OTP_OTA:
				reportToast(msg.getData().getString("RESULT"));
				Button buttonOTPOTA = null;
				if (msg.arg1 == 0) {
					buttonOTPOTA = mButtonGenOTPOTA;
				} else if (msg.arg1 == 1) {
					buttonOTPOTA = mButtonGenOTPOTAByPC;
				} else {
					buttonOTPOTA = mButtonGenOTPOTAByNewPC;
				}
				if (msg.getData().getBoolean("FLAG")) {
					buttonOTPOTA.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					buttonOTPOTA.setBackgroundColor(Constants.COLOR_RED);
				}
				break;
			case Constants.CASE_OTP_UpdatePC:
				reportToast(msg.getData().getString("RESULT"));
				if (msg.getData().getBoolean("FLAG")) {
					mButtonUpdatePC.setBackgroundColor(Constants.COLOR_GREEN);
				} else {
					mButtonUpdatePC.setBackgroundColor(Constants.COLOR_RED);
				}
				break;

			default:
				break;
			}
		};
	};
	private BindServiceConnection serviceConnection = new BindServiceConnection(handler);
	private AuthMixTokenManager authMixTokenManager = new AuthMixTokenManager(handler, mAsyncProvider);
	private OTPManager oTPManager = new OTPManager(handler, mAsyncProvider);
	private UpdatePCManager updatePCManager = new UpdatePCManager(handler);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.case_activity);
		this.initView();
	}

	private void initView() {
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
		mButtonGenMixOTP = (Button) findViewById(R.id.buttonGenMixOTP);
		mButtonGenMixOTPByPC = (Button) findViewById(R.id.buttonGenMixOTPByPC);
		mButtonGenMixOTPByNewPC = (Button) findViewById(R.id.buttonGenMixOTPByNewPC);
		mButtonGenOTPOTA = (Button) findViewById(R.id.buttonGenOTPOTA);
		mButtonGenOTPOTAByPC = (Button) findViewById(R.id.buttonGenOTPOTAByPC);
		mButtonGenOTPOTAByNewPC = (Button) findViewById(R.id.buttonGenOTPOTAByNewPC);
		mButtonUpdatePC = (Button) findViewById(R.id.buttonUpdatePC);

		// 默认动态口令长度为6
		RadioButton mButtonOMAOTPLength = (RadioButton) findViewById(R.id.btn_etOTPLength6);
		this.onClickOTPLength6(mButtonOMAOTPLength);
		mButtonOMAOTPLength.setChecked(true);

		this.initMobileNumber();

		// 绑定服务
		mButtonBindService.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				if (setMobileNumber()) {
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
				}else{
					return;
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
					authTokenManage.authTokenOTA(currentMobileNumber, pcFlag, serviceConnection, mAsyncProvider,
							handler);
				} else {
					reportToast("请先绑定服务");
				}
			}

		}
		mButtonAuthTokenByOTA.setOnClickListener(new OATListener("0"));
		mButtonGenTokenByOTAPC.setOnClickListener(new OATListener("1"));
		mButtonGenTokenByOTANewPC.setOnClickListener(new OATListener("2"));

		// 融合Token认证
		class OATPCListener implements OnClickListener {
			private String pcFlag;

			public OATPCListener(String pcFlag) {
				this.pcFlag = pcFlag;
			}

			@Override
			public void onClick(View v) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					authMixTokenManager.authMixToken(pcFlag, serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		}
		mButtonMixTokenAuth.setOnClickListener(new OATPCListener("0"));
		mButtonMixTokenAuthByPC.setOnClickListener(new OATPCListener("1"));

		// GET OTP 认证 OMA 方式
		mButtonGenOTP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					oTPManager.getOTPByOMA(etOMAOTPLength, serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		});

		// GET MIX OTP认证
		class OATPCMixListener implements OnClickListener {
			private String pcFlag;

			public OATPCMixListener(String pcFlag) {
				this.pcFlag = pcFlag;
			}

			@Override
			public void onClick(View v) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					oTPManager.getOTPMixByOMA(etOMAOTPLength, pcFlag, serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		}
		mButtonGenMixOTP.setOnClickListener(new OATPCMixListener("0"));
		mButtonGenMixOTPByPC.setOnClickListener(new OATPCMixListener("1"));
		mButtonGenMixOTPByNewPC.setOnClickListener(new OATPCMixListener("2"));

		// GET OTP 认证 OTA 方式
		class OATPCOtaListener implements OnClickListener {
			private String pcFlag;

			public OATPCOtaListener(String pcFlag) {
				this.pcFlag = pcFlag;
			}

			@Override
			public void onClick(View v) {
				if (serviceConnection.getCtpassAIDLService() != null) {
					oTPManager.getOTPByOTA(currentMobileNumber, etOMAOTPLength, pcFlag, serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		}
		mButtonGenOTPOTA.setOnClickListener(new OATPCOtaListener("0"));
		mButtonGenOTPOTAByPC.setOnClickListener(new OATPCOtaListener("1"));
		mButtonGenOTPOTAByNewPC.setOnClickListener(new OATPCOtaListener("2"));

		// 修改PC码
		mButtonUpdatePC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (serviceConnection.getCtpassAIDLService() != null) {
					updatePCManager.updatePC(serviceConnection);
				} else {
					reportToast("请先绑定服务");
				}
			}
		});
	}

	public void onClickOTPLength6(View view) {
		this.etOMAOTPLength = 6;
	}

	public void onClickOTPLength7(View view) {
		this.etOMAOTPLength = 7;
	}

	public void onClickOTPLength8(View view) {
		this.etOMAOTPLength = 8;
	}

	public void reportToast(String message) {
		ActivityUtil.reportToast(this, message);
	}

	// 自动输入手机号码
	private void initMobileNumber() {
		String mobileNumber = SharedPreferencesWrapper.getCacheInstance().readString(
				SharedPreferencesWrapper.MOBILE_NUMBER, "");
		if (mobileNumber != null && !"".equals(mobileNumber.trim())) {
			currentMobileNumber = mobileNumber;
			mEditTextCellPhoneAuth.setText(mobileNumber);
		}
	}

	public boolean setMobileNumber() {
		final String cellPhone = mEditTextCellPhoneAuth.getText().toString().trim();
		SharedPreferencesWrapper.getCacheInstance().writeString(SharedPreferencesWrapper.MOBILE_NUMBER, cellPhone);
		if (cellPhone.equals("") || cellPhone.length() == 0 || cellPhone == null) {
			reportToast("请输入认证手机号");
			return false;
		} else {
			return true;
		}
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
