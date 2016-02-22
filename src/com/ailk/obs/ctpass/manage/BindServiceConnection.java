package com.ailk.obs.ctpass.manage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import cn.com.chinatelecom.ctpass.aidl.AIDLCallback;
import cn.com.chinatelecom.ctpass.aidl.ServiceAIDL;

import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class BindServiceConnection implements ServiceConnection {
	private static final String TAG = "BindServiceConnection";

	private ServiceAIDL ctpassAIDLService;
	private Handler handler;

	public BindServiceConnection(Handler handler) {
		this.handler = handler;
	}

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
				HandlerUtil.send(handler, Constants.CASE_CONN, result, "00".equals(result));
				Log.d(TAG, "demo callback:" + result);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	};

}
