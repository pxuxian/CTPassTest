package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ailk.obs.ctpass.R;

public class IndexActivity extends Activity {

	private Button mButtonGoTestCASE;
	private Button mButtonGoLogin;
	private Button mButtonGoLoginOTA;
	private Button mButtonGoLoginQr;
	private TextView tvServer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_activity);

		this.initView();

	}

	private void initView() {

		mButtonGoTestCASE = (Button) findViewById(R.id.btn_test);
		mButtonGoLogin = (Button) findViewById(R.id.btn_test_login);
		mButtonGoLoginOTA = (Button) findViewById(R.id.btn_test_login_ota);
		mButtonGoLoginQr = (Button) findViewById(R.id.btn_test_login_qr);
		tvServer = (TextView) findViewById(R.id.tvServer);

		mButtonGoTestCASE.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Intent intent = new Intent(IndexActivity.this,
				// MainActivity.class);
				//
				// startActivity(intent);

			}
		});

		mButtonGoLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Intent intent = new Intent(IndexActivity.this, Login.class);
				// startActivity(intent);

			}
		});

		mButtonGoLoginOTA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Intent intent = new Intent(IndexActivity.this,
				// LoginOTA.class);
				// startActivity(intent);
			}
		});

		mButtonGoLoginQr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//
				// Intent intent = new Intent(IndexActivity.this,
				// LoginQr.class);
				// startActivity(intent);

			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		// if (APIProvider.RELEASE_HOST_URL
		// .equals(APIProvider.getInstance().hostURL)) {
		// SystemSwitch.broadcaseSystem(this, true);
		// tvServer.setText("当前使用的是正式系统地址");
		// } else if (APIProvider.DEBUG_HOST_URL
		// .equals(APIProvider.getInstance().hostURL)) {
		// SystemSwitch.broadcaseSystem(this, false);
		// tvServer.setText("当前使用的是测试系统地址");
		// } else {
		// SystemSwitch.broadcaseSystem(this, true);
		// APIProvider.getInstance().hostURL = APIProvider.RELEASE_HOST_URL;
		// tvServer.setText("当前使用的是正式系统地址");
		// }

	}

	public void onClickTestUDun(View view) {
		// Intent intent = new Intent(IndexActivity.this, UDunActivity.class);
		// startActivity(intent);
	}

	public void onClickChangeServer(View view) {
		// Intent intent = new Intent(IndexActivity.this,
		// ChangeServerActivity.class);
		// startActivity(intent);
	}

	public void onClickTestAdaptive(View view) {
		// Intent intent = new Intent(IndexActivity.this,
		// AdaptiveTestActivity.class);
		// startActivity(intent);
	}

	public void onClickTestUDunOTA(View view) {
		// Intent intent = new Intent(IndexActivity.this,
		// UDunOtaActivity.class);
		// startActivity(intent);
	}

	public void onClickOthers(View view) {
		// Intent intent = new Intent(IndexActivity.this, OtherActivity.class);
		// startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
