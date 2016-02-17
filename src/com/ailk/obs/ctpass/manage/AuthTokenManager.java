package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.conn.BindServiceConnection;
import com.ailk.obs.ctpass.constant.Constants;
import com.ailk.obs.ctpass.module.AuthToken;
import com.ailk.obs.ctpass.util.LocalConfig;

public class AuthTokenManager {

	private static final String TAG = AuthTokenManager.class.getSimpleName();

	public AuthToken genarateAuthToken(Object response, String pcCode, BindServiceConnection serviceConnection) {
		try {
			if (response == null) {
				return null;
			}
			JSONObject obj = (JSONObject) response;
			JSONObject resultJsonObject = obj.getJSONObject("GenReqAndRandomResponse");
			String seqId = resultJsonObject.getString("SeqID");
			String random = resultJsonObject.getString("Random");
			String token = serviceConnection.getCtpassAIDLService()
					.getCTPassToken(LocalConfig.DEVICE_NO, seqId, random);
			return new AuthToken(seqId, random, pcCode, token);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}

	public void authTokenOnly(final AuthToken authToken, AsyncProvider mAsyncProvider, final Handler handler) {
		final Message msg = new Message();
		final Bundle dataBundle = new Bundle();
		msg.setData(dataBundle);
		msg.what = Constants.HandlerCase.AUTH_TOKEN;

		if (authToken == null) {
			return;
		}
		String pcFlag = "0";
		if (authToken.getPcCode() == null || authToken.getPcCode().length() == 0) {
			pcFlag = "0";
		} else {
			pcFlag = "1";
		}
		if (authToken.getToken() == null) {
			return;
		}
		if (authToken.getToken().substring(0, 2).equals("00")) {
			mAsyncProvider.authToken(authToken.getToken(), authToken.getSeqId(), authToken.getRandom(), pcFlag,
					new RequestListener() {
						@Override
						public void onComplete(final Object response) {
							JSONObject obj = (JSONObject) response;
							try {
								JSONObject resultJsonObject = obj.getJSONObject("AuthCTPassTokenResponse");
								StringBuilder sb = new StringBuilder(authToken.toString());
								sb.append("认证结果:").append(resultJsonObject.toString());
								// handler处理
								dataBundle.putString("RESULT", sb.toString());
								dataBundle.putBoolean("flag", true);
								handler.sendMessage(msg);
							} catch (Exception e) {
								dataBundle.putString("RESULT", "访问异常：" + e.getMessage());
								handler.sendMessage(msg);
								Log.e(TAG, e.getMessage(), e);
							}
						}

						@Override
						public void onInvokerError(final String e) {
							// handler处理
							dataBundle.putString("RESULT", "访问异常：" + e);
							handler.sendMessage(msg);
							Log.e(TAG, "访问异常", new Exception());
						}

					});
		} else {
			// handler处理
			dataBundle.putString("RESULT", "获取token失败,不验证token：" + authToken.getToken());
			handler.sendMessage(msg);
			Log.e(TAG, "获取token失败,不验证token：" + authToken.toString(), new Exception());
		}

	}

}
