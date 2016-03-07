package com.ailk.obs.ctpass.manage;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;

import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class BindServiceManager {
	private static final String BIND_ACTION = "cn.com.chinatelecom.ctpass.service";
	private static final String BIND_PACKAGE = "cn.com.chinatelecom.ctpass";

	public void bindService(Context context, ServiceConnection serviceConnection, Handler handler) {

		boolean flag = getCTPassService(context, serviceConnection);
		if (flag) {
			HandlerUtil.send(handler, Constants.CASE_BIND, "绑定服务成功", true);
		} else {
			HandlerUtil.send(handler, Constants.CASE_BIND, "绑定服务失败", false);
		}
	}

	public boolean getCTPassService(Context context, ServiceConnection serviceConnection) {
		try {
			Intent intent = new Intent();
			intent.setAction(BIND_ACTION);
			intent.setPackage(BIND_PACKAGE);
			context.startService(intent);
			return context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
