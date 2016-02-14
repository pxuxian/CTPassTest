package com.ailk.obs.ctpass.conn;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import cn.com.chinatelecom.ctpass.aidl.AIDLCallback;
import cn.com.chinatelecom.ctpass.aidl.ServiceAIDL;

import com.ailk.obs.ctpass.module.TestResult;

public class BindServiceConnection implements ServiceConnection {
	private static final String TAG = "BindServiceConnection";
	
	private ServiceAIDL ctpassAIDLService;
	
	

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "onServiceDisconnected");
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(TAG, "onServiceConnected");
		ctpassAIDLService = ServiceAIDL.Stub.asInterface(service);
		try {
			if (ctpassAIDLService != null) {
				ctpassAIDLService.registerCallback(mCallback);
			}
		} catch (RemoteException e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
	
	public ServiceAIDL getCtpassAIDLService() {
		return ctpassAIDLService;
	}


	private AIDLCallback.Stub mCallback = new AIDLCallback.Stub() {

		@Override
		public void connectCTPassServiceCallBack(String result) {
			try {
				Log.d(TAG, "demo callback:" + result);
				Message msg = new Message();
				Bundle dataBundle = new Bundle();
				dataBundle.putString("RESULT", result);
				msg.what = 1;
				msg.setData(dataBundle);
				TestResult.messageMap.put("messageMap", msg);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	};
	
}
