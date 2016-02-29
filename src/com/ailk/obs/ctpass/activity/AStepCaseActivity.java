package com.ailk.obs.ctpass.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;

import com.ailk.obs.ctpass.R;

public class AStepCaseActivity extends Activity {
	private CheckBox mButtonBindService;
	//private Button 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.astep_case_activity);
		this.initView();
	}

	private void initView() {
		mButtonBindService = (CheckBox) findViewById(R.id.buttonBindService);
		final String pCFlagBindService = mButtonBindService.isChecked() == true ? "1" : "0";
		
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
