package com.ailk.obs.ctpass.module;

import com.ailk.obs.ctpass.util.LocalConfig;
import com.ailk.obs.ctpass.util.TimestampUtil;

public class AuthToken {

	private String appId = LocalConfig.DEVICE_NO;
	private String seqId;
	private String random;
	private String pcCode;
	private String token;

	public AuthToken() {
	}

	public AuthToken(String seqId, String random, String pcCode, String token) {
		this.seqId = seqId;
		this.random = random;
		this.pcCode = pcCode;
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getPcCode() {
		return pcCode;
	}

	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		String cr = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("APPID:").append(appId).append(cr);
		sb.append("SeqID:").append(seqId).append(cr);
		sb.append("Radom:").append(random).append(cr);
		sb.append("PC:").append(pcCode).append(cr);
		sb.append("S串:").append(token).append(cr);
		sb.append("Token生成时间:").append(TimestampUtil.getTimeStamp()).append(cr);
		return sb.toString();
	}

}
