package com.ailk.obs.ctpass;

import org.json.JSONObject;

import android.util.Log;

import com.ailk.obs.ctpass.http.AsyncTask;

public class AsyncProvider {
	APIProvider provider;

	public AsyncProvider() {
		provider = APIProvider.getInstance();
	}

	public void getSeqIDRandom(final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {
			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.genSeqIDAndRandom();
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.e("getSeqIDRandom", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();
	}

	public void authToken(final String s, final String seqID, final String random, final String pcFlag,
			final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {
			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.authToken(s, seqID, random, pcFlag);
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.d(TAG, "00003");
					Log.e("authToken", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();

	}

	public void authTokenByOTA(final String mobile, final String seqID, final String random, final String pcFlag,
			final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {

			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.authTokenByOTA(mobile, seqID, random, pcFlag);
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.e("authTokenByOTA", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();

	}

	public void authMixToken(final String s, final String seqID, final String random, final String pcFlag,
			final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {

			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.authMixToken(s, seqID, random, pcFlag);
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.e("authMixToken", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();
	}

	public void genOTPByOTA(final String mobile, final String optLen, final String pcFlag,
			final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {

			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.genOTPByOTA(mobile, optLen, pcFlag);
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.e("genOTPByOTA", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();
	}

	public void checkAuthResult(final String seqID, final String random, final RequestListener listener) {
		new AsyncTask<Void, Void, Object>() {

			@Override
			protected Object doInBackground(Void... params) {
				try {
					JSONObject result = provider.checkResult(seqID, random);
					Log.d(TAG, "00001");
					return result;
				} catch (Exception e) {
					Log.e("checkAuthResult", e.getMessage(), e);
					return e;
				}
			}

			@Override
			protected void onPostExecute(Object result) {
				super.onPostExecute(result);
				if (result instanceof Exception) {
					Exception e = (Exception) result;
					listener.onInvokerError(e.getMessage());
				} else {
					listener.onComplete(result);
				}
			}
		}.exe();
	}

	public static interface RequestListener {
		/**
		 * 当请求完成时被调用
		 * 
		 */
		public void onComplete(Object response);

		public void onInvokerError(String e);

	}

}
