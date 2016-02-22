package com.ailk.obs.ctpass.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HandlerUtil {

	public static void send(Handler handler, int what, String result) {
		send(handler, what, "RESULT", result, null, false);
	}

	public static void send(Handler handler, int what, String result, boolean flag) {
		send(handler, what, "RESULT", result, "FLAG", flag);
	}

	public static void send(Handler handler, int what, String result, boolean flag, String... args) {
		send(handler, what, "RESULT", result, "FLAG", flag, args);
	}

	public static void send(Handler handler, int what, String dataKey, String data) {
		send(handler, what, dataKey, dataKey, null, false);
	}

	private static void send(Handler handler, int what, String dataKey, String data, String resultKey,
			boolean result, String... args) {
		// handler处理
		Message msg = new Message();
		Bundle dataBundle = new Bundle();
		dataBundle.putString(dataKey, data);
		if (resultKey != null) {
			dataBundle.putBoolean(resultKey, result);
		}
		msg.setData(dataBundle);
		msg.what = what;
		if (args.length >= 1) {
			msg.arg1 = Integer.valueOf(args[0]);
		}
		if (args.length >= 2) {
			msg.arg2 = Integer.valueOf(args[1]);
		}
		handler.sendMessage(msg);
	}

}
