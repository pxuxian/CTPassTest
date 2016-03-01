package com.ailk.obs.ctpass.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpClient {

	public static void main(String[] args) throws JSONException {
		String APP_KEY = "F160F54369DF9235902A85943112E908";
		String APP_ID = "0100000000000001";
		String txtOTP = "123132";
		String txtMobile = "123";
		String PCFlag = "0";
		String Timestamp = DateUtil.format(new Date());
		String Signature = APP_ID + txtOTP + txtMobile + PCFlag + Timestamp;
		String encryptSignature = "";
		try {
			encryptSignature = Cryptogram.encryptByKey(Signature, APP_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject params = new JSONObject();
		params.put("APP_ID", APP_ID);
		params.put("txtOTP", txtOTP);
		params.put("txtMobile", txtMobile);
		params.put("PCFlag", PCFlag);
		params.put("Timestamp", Timestamp);
		params.put(Signature, encryptSignature);

		System.out.println(httpPost("http://42.99.0.103:8891/inf/CTPassInterface/AuthOTP", params));
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					return EntityUtils.toString(result.getEntity());
				} catch (Exception e) {
				}
			}
		} catch (IOException e) {
		}
		return null;
	}
}
