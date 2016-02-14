package com.ailk.obs.ctpass.util;

import android.content.Context;
import android.content.Intent;

/**
 * @author zouning
 * @time 2015-9-10 下午9:12:13
 * 
 */

public class SystemSwitch {
    public static void broadcaseSystem(Context context, boolean isReleaseSystem) {
        Intent intent = new Intent();
        intent.setAction("cn.com.chinatelecom.ctpass.switchsystem");
        intent.putExtra("EXTRA_IS_RELEASE_SYSYTEM", isReleaseSystem);
        context.sendBroadcast(intent);
    }
}
