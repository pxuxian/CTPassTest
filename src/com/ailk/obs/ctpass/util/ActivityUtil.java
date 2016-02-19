package com.ailk.obs.ctpass.util;

import android.content.Context;
import android.widget.Toast;

public class ActivityUtil {

	public static void reportToast(Context context, String message) {
		Toast mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		mToast.show();
	}
}
