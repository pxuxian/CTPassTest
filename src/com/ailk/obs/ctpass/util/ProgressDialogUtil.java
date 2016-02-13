package com.ailk.obs.ctpass.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ProgressDialogUtil {
	public static void showProgress(final Context context, String msg) {
		final ProgressDialog pd = new ProgressDialog(context);
		pd.setTitle("提醒");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		pd.setMessage(msg);
		pd.show();
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pd.setProgress(i);
				}
				pd.dismiss();
				Looper.prepare();
				Toast.makeText(context, "测试完成",Toast.LENGTH_LONG).show();
			};
		}.start();
	}
	
}
