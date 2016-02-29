package com.ailk.obs.ctpass.app;

import android.app.Application;

import com.ailk.obs.ctpass.util.SharedPreferencesWrapper;

public class CTPassTestApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SharedPreferencesWrapper.init(this);
	}
}
