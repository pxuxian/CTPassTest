package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.constant.Constants;

public class AuthTokenTask implements Runnable {
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

	public void postTask() {
		handler.post(this);
	}

	public void postDelayedTask(int delay) {
		handler.postDelayed(this, delay);
	}

	public void removeCallbacks() {
		handler.removeCallbacks(this);
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
					String result = obj.getString("Result");
					String cr = System.getProperty("line.separator");
					if (result.equals("0")) {
						removeCallbacks();
						StringBuilder sb = new StringBuilder();
						sb.append("SeqID:").append(seqId).append(cr);
						sb.append("Random:").append(random).append(cr);

						// handler处理
						dataBundle.putString("RESULT", "认证成功  " + sb.toString());
						dataBundle.putBoolean("flag", true);
						handler.sendMessage(msg);
					} else if (result.equals("2")) {
						removeCallbacks();
						StringBuilder sb = new StringBuilder();
						sb.append("SeqID:").append(seqId).append(cr);
						sb.append("Random:").append(random).append(cr);
						// handler处理
						dataBundle.putString("RESULT", "检查认证结果失败  " + sb.toString());
						handler.sendMessage(msg);
					} else {
						invokeCount = invokeCount + 1;
						if (invokeCount >= 3) {
							removeCallbacks();
						}
						postDelayedTask(3 * 1000);// 设置延迟时间
					}

				} catch (Exception e) {
					// handler处理
					dataBundle.putString("RESULT", "调用认证结果检查接口异常" + e.getMessage());
					handler.sendMessage(msg);
				}

				if (invokeCount >= 3) {
					removeCallbacks();
				}

			}
		});

	}

}
