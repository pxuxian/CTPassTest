package com.ailk.obs.ctpass.manage;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

public class BindServiceManager {
	private static final String BIND_ACTION = "cn.com.chinatelecom.ctpass.service";
	private static final String BIND_PACKAGE = "cn.com.chinatelecom.ctpass";
	
	public boolean getCTPassService(Context context, ServiceConnection serviceConnection) {
		try {
			Intent intent = new Intent();
			intent.setAction(BIND_ACTION);
			intent.setPackage(BIND_PACKAGE);
			context.startService(intent);
			return context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void getSingInfo(PackageInfo packageInfo) {
		try {
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			parseSignature(sign.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSignInfo(PackageInfo packageInfo) {
		try {
			for (Signature signature : packageInfo.signatures) {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA1");
				md.update(signature.toByteArray());
				String something = new String(Base64.encode(md.digest(), 0));
				Log.e("Hash key", something);
				System.out.println("Hash key" + something);

			}
		} catch (Exception e) {
			Log.e("exception", e.toString());
		}

	}

	public void parseSignature(byte[] signature) {
		try {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(new ByteArrayInputStream(signature));
			String publickey = cert.getPublicKey().toString();

			String signNumber = cert.getSerialNumber().toString();
			System.out.println("getSignature:" + cert.getSignature().toString());
			System.out.println("signName:" + cert.getSigAlgName());
			System.out.println("publickey:" + publickey);
			System.out.println("signNumber:" + signNumber);
			System.out.println("subjectDN:" + cert.getSubjectDN().toString());
		} catch (CertificateException e) {
			e.printStackTrace();
		}
	}

}
