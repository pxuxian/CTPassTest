package com.ailk.obs.ctpass.http;

import android.annotation.SuppressLint;

/**
 * @author wj
 * 
 */

public abstract class AsyncTask<Params, Progress, Result> extends
        android.os.AsyncTask<Params, Progress, Result> {
    
    protected final String TAG = getClass().getSimpleName();
    
    @SuppressLint("NewApi")
    public android.os.AsyncTask<Params, Progress, Result> exe(Params... params) {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            return super.executeOnExecutor(
                    android.os.AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            return execute(params);
        }
    }
}
