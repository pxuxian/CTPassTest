package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

import com.ailk.obs.ctpass.APIProvider;
import com.ailk.obs.ctpass.R;
import com.ailk.obs.ctpass.util.SystemSwitch;

public class IndexActivity extends Activity {
	private Button mButtonGoTestCase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_activity);
		this.initView();
	}

	private void initView() {
		RadioButton mButtonRelease = (RadioButton) findViewById(R.id.btn_Release);
		this.onClickRelease(mButtonRelease);
		mButtonRelease.setChecked(true);

		mButtonGoTestCase = (Button) findViewById(R.id.btn_test);
		mButtonGoTestCase.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(IndexActivity.this, CaseActivity.class));
			}

		});

	}

	public void onClickRelease(View view) {
		APIProvider.hostURL = APIProvider.RELEASE_HOST_URL;
	}

	public void onClickDebug(View view) {
		APIProvider.hostURL = APIProvider.DEBUG_HOST_URL;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (APIProvider.RELEASE_HOST_URL.equals(APIProvider.hostURL)) {
			SystemSwitch.broadcaseSystem(this, true);
		} else if (APIProvider.DEBUG_HOST_URL.equals(APIProvider.hostURL)) {
			SystemSwitch.broadcaseSystem(this, false);
		} else {
			SystemSwitch.broadcaseSystem(this, true);
			APIProvider.hostURL = APIProvider.RELEASE_HOST_URL;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
