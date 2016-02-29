package com.ailk.obs.ctpass.manage;

import android.os.Handler;

import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.util.HandlerUtil;
import com.ailk.obs.ctpass.util.LocalConfig;

public class UpdatePCManager {
	private Handler handler;

	public UpdatePCManager(Handler handler) {
		this.handler = handler;
	}

	public void updatePC(final BindServiceConnection serviceConnection) {
		// 调用远程ctpass接口修改PC码
		try {
			serviceConnection.getCtpassAIDLService().updatePassword(LocalConfig.DEVICE_NO, null);
			HandlerUtil.send(handler, Constants.CASE_OTP_UpdatePC, "PC码修改成功", true);
		} catch (Exception e) {
			HandlerUtil.send(handler, Constants.CASE_TOAST, "MSG", "PC码修改失败" + e.getMessage());
			e.printStackTrace();
		}
	}
}
