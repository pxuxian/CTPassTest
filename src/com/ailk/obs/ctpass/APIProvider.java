package com.ailk.obs.ctpass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.ailk.obs.ctpass.util.ExceptionToString;

@SuppressLint({ "SdCardPath", "SimpleDateFormat" })
@SuppressWarnings({ "serial" })
public class APIProvider implements Serializable {

	/** 商用系统测试 */
	public static final String RELEASE_HOST_URL = "http://124.207.3.17:15282/3thCTPassDemo/interface/";// 商用系统测试
	/** 测试系统测试 */
	public static final String DEBUG_HOST_URL = "http://42.99.0.103:8891/demo/interface/";// 测试系统测试

	public static String hostURL = RELEASE_HOST_URL;// 默认正式系统地址

	// singleton
	private APIProvider() {

	}

	public JSONObject genSeqIDAndRandom() {
		Log.d("DEBUG", hostURL + "GenReqAndRandom.aspx");
		return this.httpGetInternal(hostURL + "GenReqAndRandom.aspx");
	}

	public JSONObject authToken(String s, String seqID, String random, String pcFlag) {
		Log.d("DEBUG", hostURL + "CTPassAuth.aspx?s=" + s + "&SeqID=" + seqID + "&Random=" + random + "&PCFlag="
				+ pcFlag);
		return this.httpGetInternal(hostURL + "CTPassAuth.aspx?s=" + s + "&SeqID=" + seqID + "&Random=" + random
				+ "&PCFlag=" + pcFlag);
	}

	public JSONObject authTokenByOTA(String mobile, String seqID, String random, String pcFlag) {
		Log.d("DEBUG", hostURL + "CTPassAuthByOTA.aspx?Mobile=" + mobile + "&SeqID=" + seqID + "&Random=" + random
				+ "&PCFlag=" + pcFlag);
		return this.httpGetInternal(hostURL + "CTPassAuthByOTA.aspx?Mobile=" + mobile + "&SeqID=" + seqID + "&Random="
				+ random + "&PCFlag=" + pcFlag);
	}

	public JSONObject authMixToken(String s, String seqID, String random, String pcFlag) {
		Log.d("DEBUG", hostURL + "CTPassAuthComm.aspx?s=" + s + "&SeqID=" + seqID + "&Random=" + random + "&PCFlag="
				+ pcFlag);
		return this.httpGetInternal(hostURL + "CTPassAuthComm.aspx?s=" + s + "&SeqID=" + seqID + "&Random=" + random
				+ "&PCFlag=" + pcFlag);
	}

	public JSONObject genOTPByOTA(String mobile, String optLen, String pcFlag) {
		Log.d("DEBUG", this.hostURL + "GenOTP.aspx?Mobile=" + mobile + "&OTPLen=" + optLen + "&PCFlag=" + pcFlag);
		return this.httpGetInternal(this.hostURL + "GenOTP.aspx?Mobile=" + mobile + "&OTPLen=" + optLen + "&PCFlag="
				+ pcFlag);
	}

	public JSONObject checkResult(String seqID, String random) {
		Log.d("DEBUG", hostURL + "CheckAuthResultForClient.aspx?SeqID=" + seqID + "&Random=" + random);
		return this.httpGetInternal(hostURL + "CheckAuthResultForClient.aspx?SeqID=" + seqID + "&Random=" + random);
	}

	private Object readResolve() {
		return getInstance();
	}

	private static class SingletonHolder {
		static final APIProvider INSTANCE = new APIProvider();
	}

	public static APIProvider getInstance() {
		return SingletonHolder.INSTANCE;
	}

	// http
	private JSONObject httpGetInternal(String url) {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		HttpGet httpGet = new HttpGet(url);
		StringBuilder logBuilder = new StringBuilder();
		logBuilder.append("请求参数:").append(url).append("\n");
		try {

			HttpResponse httpResponse = client.execute(httpGet);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
					.getContent()));
			for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
				builder.append(s);
			}

			bufferedReader.close();
			logBuilder.append("请求响应:").append(builder).append("\n");
			return new JSONObject(builder.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			logBuilder.append("请求异常:").append(ExceptionToString.converToStr(ex)).append("\n");
		} finally {
			Log.d("http", logBuilder.toString());
		}

		return null;
	}

}