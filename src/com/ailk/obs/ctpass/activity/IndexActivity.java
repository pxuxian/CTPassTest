package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ailk.obs.ctpass.APIProvider;
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.SystemSwitch;

public class IndexActivity extends Activity {
	private TextView tvServer;
	private Button mButtonGoTestCase;
	private Button mButtonChangeServer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_activity);
		this.initView();
	}

	private void initView() {
		tvServer = (TextView) findViewById(R.id.tvServer);
		mButtonChangeServer = (Button) findViewById(R.id.btn_change_server);
		mButtonGoTestCase = (Button) findViewById(R.id.btn_test);

		mButtonChangeServer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(IndexActivity.this, ChangeServerActivity.class));
			}
		});

		mButtonGoTestCase.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(IndexActivity.this, CaseActivity.class));
			}

		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (APIProvider.RELEASE_HOST_URL.equals(APIProvider.hostURL)) {
			SystemSwitch.broadcaseSystem(this, true);
			tvServer.setText("当前使用的是正式系统地址");
		} else if (APIProvider.DEBUG_HOST_URL.equals(APIProvider.hostURL)) {
			SystemSwitch.broadcaseSystem(this, false);
			tvServer.setText("当前使用的是测试系统地址");
		} else {
			SystemSwitch.broadcaseSystem(this, true);
			APIProvider.hostURL = APIProvider.RELEASE_HOST_URL;
			tvServer.setText("当前使用的是正式系统地址");
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
