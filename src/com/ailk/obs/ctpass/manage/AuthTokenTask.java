package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Handler;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
import com.ailk.obs.ctpass.util.HandlerUtil;

public class AuthTokenTask implements Runnable {
	private static final int MAX_INVOKE_COUNT = 10;
	private AsyncProvider mAsyncProvider;
	private String seqId;
	private String random;
	private int invokeCount;
	private String pcFlag;
	private Handler handler;
	private int handlerCase;

	public AuthTokenTask(AsyncProvider mAsyncProvider, String seqId, String random, String pcFlag, Handler handler,
			int handlerCase) {
		this.mAsyncProvider = mAsyncProvider;
		this.seqId = seqId;
		this.random = random;
		this.pcFlag = pcFlag;
		this.handler = handler;
		this.handlerCase = handlerCase;
	}

	@Override
	public void run() {
		if (seqId == null && random == null) {
			return;
		}
		mAsyncProvider.checkAuthResult(seqId, random, new RequestListener() {
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
						postDelayedTask(4 * 1000);// 设置延迟时间
					}
				} catch (Exception e) {
					sendResult("第" + invokeCount + "次调用认证结果检查接口异常" + e.getMessage(), false);
				}
				if (invokeCount >= MAX_INVOKE_COUNT) {
					sendResult("第" + invokeCount + "次检查认证结果失败: ", false);
					removeCallbacks();
				}

			}

			@Override
			public void onInvokerError(String e) {
				if (invokeCount >= 10) {
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
		String cr = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("SeqID:").append(seqId).append(cr);
		sb.append("Random:").append(random);
		HandlerUtil.send(handler, handlerCase, str + cr + sb.toString(), flag, pcFlag);
	}

}
