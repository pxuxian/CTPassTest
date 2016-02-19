package com.ailk.obs.ctpass.manage;

import org.json.JSONObject;

import android.os.Handler;

import com.ailk.obs.ctpass.AsyncProvider;
import com.ailk.obs.ctpass.AsyncProvider.RequestListener;

public class AuthTokenTask implements Runnable {
	private AsyncProvider mAsyncProvider;
	private String seqId;
	private String random;
	private int invokeCount;
	private Handler handler;

	public AuthTokenTask(AsyncProvider mAsyncProvider, String seqId, String random, Handler handler) {
		this.mAsyncProvider = mAsyncProvider;
		this.seqId = seqId;
		this.random = random;
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
						// reportToast("返回值null");
					}

					String result = obj.getString("Result");

					// reportToast(result);
					String cr = System.getProperty("line.separator");
					if (result.equals("0")) {
						removeCallbacks();
						StringBuilder sb = new StringBuilder();
						sb.append("SeqID:").append(seqId).append(cr);
						sb.append("Random:").append(random).append(cr);
						sb.append("认证成功");
						// reportToast("认证成功");
						// showAlert("OTA Auth认证", sb.toString());
					} else if (result.equals("2")) {
						removeCallbacks();
						StringBuilder sb = new StringBuilder();
						sb.append("SeqID:").append(seqId).append(cr);
						sb.append("Random:").append(random).append(cr);
						sb.append("认证失败");
						// showAlert("OTA Auth认证", sb.toString());
					} else {
						invokeCount = invokeCount + 1;
						// reportToast("等待认证结果..." + invokeCount + "次");
						if (invokeCount >= 11) {
							removeCallbacks();
						}
						postDelayedTask(3 * 1000);// 设置延迟时间
					}

				} catch (Exception e) {
					// reportToast("调用认证结果检查接口异常" + e.getMessage());
				}

				if (invokeCount >= 10) {
					handler.removeCallbacks(null, this);
				}

			}
		});

	}

}
