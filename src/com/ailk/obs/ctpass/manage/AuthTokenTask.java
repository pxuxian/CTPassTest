package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.constant.Constants;

public class AuthTokenTask implements Runnable {
	private static final int MAX_INVOKE_COUNT = 10;
	private AsyncProvider mAsyncProvider;
	private String seqId;
	private String random;
	private int invokeCount;
	private String pcFlag;
	private Handler handler;

	public AuthTokenTask(AsyncProvider mAsyncProvider, String seqId, String random, String pcFlag, Handler handler) {
		this.mAsyncProvider = mAsyncProvider;
		this.seqId = seqId;
		this.random = random;
		this.pcFlag = pcFlag;
		this.handler = handler;
	}

	@Override
	public void run() {
		final Message msg = new Message();
		final Bundle dataBundle = new Bundle();
		msg.setData(dataBundle);
		msg.what = Constants.HandlerCase.AUTH_TOKEN_OTA;
		msg.arg1 = Integer.valueOf(pcFlag);

		if (seqId == null && random == null) {
			return;
		}

		mAsyncProvider.checkAuthResult(seqId, random, new RequestListener() {
			@Override
			public void onInvokerError(String e) {
				if (invokeCount >= 10) {
					removeCallbacks();
				}
			}

			@Override
			public void onComplete(final Object response) {
				JSONObject obj = (JSONObject) response;
				try {
					if (obj == null) {
						return;
					}
					removeCallbacks();
					String result = obj.getString("Result");
					if (result.equals("0")) {
						sendResult("第" + invokeCount + "次认证成功: " + result, true);
					} else if (result.equals("2")) {
						sendResult("第" + invokeCount + "次检查认证结果失败: " + result, false);
					} else {
						sendResult("第" + invokeCount + "次检查认证结果失败: " + result, false);
						postDelayedTask(3 * 1000);// 设置延迟时间
					}
				} catch (Exception e) {
					sendResult("第" + invokeCount + "次调用认证结果检查接口异常" + e.getMessage(), false);
				}
				if (invokeCount >= MAX_INVOKE_COUNT) {
					sendResult("第" + invokeCount + "次检查认证结果失败: ", false);
					removeCallbacks();
				}

			}
		});

	}

	public void postDelayedTask(int delay) {
		invokeCount++;
		handler.postDelayed(this, delay);
	}

	public void removeCallbacks() {
		handler.removeCallbacks(this);
	}

	public void sendResult(String str, boolean flag) {
		Message msg = new Message();
		msg.what = Constants.HandlerCase.AUTH_TOKEN_OTA;
		msg.arg1 = Integer.valueOf(pcFlag);

		String cr = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("SeqID:").append(seqId).append(cr);
		sb.append("Random:").append(random).append(cr);

		Bundle dataBundle = new Bundle();
		dataBundle.putString("RESULT", str + cr + sb.toString());
		dataBundle.putBoolean("flag", flag);
		msg.setData(dataBundle);
		handler.sendMessage(msg);
	}

}
