package com.ailk.obs.ctpass.util;


public class Cryptogram {
	/*
	private static byte[] defaultIV = {1,2,3,4,5,6,7,8};

	private static byte chr2hex(String chr) {
		if (chr.equals("0")) {
			return 0x00;
		} else if (chr.equals("1")) {
			return 0x01;
		} else if (chr.equals("2")) {
			return 0x02;
		} else if (chr.equals("3")) {
			return 0x03;
		} else if (chr.equals("4")) {
			return 0x04;
		} else if (chr.equals("5")) {
			return 0x05;
		} else if (chr.equals("6")) {
			return 0x06;
		} else if (chr.equals("7")) {
			return 0x07;
		} else if (chr.equals("8")) {
			return 0x08;
		} else if (chr.equals("9")) {
			return 0x09;
		} else if (chr.equals("A")) {
			return 0x0a;
		} else if (chr.equals("B")) {
			return 0x0b;
		} else if (chr.equals("C")) {
			return 0x0c;
		} else if (chr.equals("D")) {
			return 0x0d;
		} else if (chr.equals("E")) {
			return 0x0e;
		} else if (chr.equals("F")) {
			return 0x0f;
		}
		return 0x00;
	}

	public static byte[] HexStringToByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (chr2hex(s.substring(i * 2, i * 2 + 1)) * 0x10 + chr2hex(s
					.substring(i * 2 + 1, i * 2 + 2)));
		}
		return buf;
	}

	*//**
	 * Encrypt the data by the key.
	 * @param OriSource
	 * @return strResult
	 * @throws Exception
	 *//*
	public static String encryptByKey(String OriSource, String key) throws Exception {
		
		String strResult = "";
		try {
			
			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key),
					"DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);

			byte[] testSrc = OriSource.getBytes("utf-8");
			byte[] encoded = c3des.doFinal(testSrc);
			
			strResult = Base64Encrypt.getBASE64_byte(encoded);
			
		} catch (Exception e) {
			strResult="";
			System.out.println("Encrypt failure!!!");
		}

		return strResult;
	}
	
	*//**
	 * Decrypt the encrypted data with the key.
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 *//*
	public static String decryptByKey(String encryptedData, String key) throws Exception {
		
		String strResult = "";
		try {
			
			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key),
					"DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.DECRYPT_MODE, myKey, ivspec);

			byte[] s= Base64Encrypt.getByteArrFromBase64(encryptedData);
			byte[] encoded = c3des.doFinal(s);
			strResult = new String(encoded,"utf-8");
			
		} catch (Exception e) {
			strResult="";
			System.out.println("Decrypt failure!!!");
		}

		return strResult;
	}
	
	*//**
	 * Decrypt the encrypted data with the key.
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 *//*
	public static String getBase64HashString(String str) throws Exception{
		
		byte[] testSrc = str.getBytes("utf-8");
		MessageDigest alga = MessageDigest.getInstance("SHA-1");
		alga.update(testSrc);
		byte[] digesta = alga.digest();
		return Base64Encrypt.getBASE64_byte(digesta);
	}
	
	
	*//**
	 * Decrypt the encrypted data with the key.
	 * @param strData
	 * @return strResult
	 * @throws Exception
	 *//*
	public static String getAuthenicator(String sourceStr,String key) throws Exception{
		
		String strResult = "";
		try {
			
			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(key),
					"DESede");

			IvParameterSpec ivspec = new IvParameterSpec(defaultIV);
			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
			
			
			byte[] testSrc = sourceStr.getBytes("utf-8");
			MessageDigest alga = MessageDigest.getInstance("SHA-1");
			alga.update(testSrc);
			byte[] digesta = alga.digest();

			byte[] encoded = c3des.doFinal(digesta);
			strResult = Base64Encrypt.getBASE64_byte(encoded);
			
		} catch (Exception e) {
			strResult="";
			System.out.println("Decrypt failure!!!" + e.getMessage());
		}

		return strResult;
	}
	

	public static void main(String args[]) throws Exception {
		String SysID = "0005郭";
		String TimeStamp = "2009-10-22 13:15:20";
		String ReturnURL = "http://vnet.cn/passportinterface/test2.aspx郭";
		String Key = "86A659D3035B51B1B66DF3139F1AEC33F6651334F1E65170";
		
		try{
			
			//Get Digest.
			String Digest = getBase64HashString(SysID + TimeStamp + ReturnURL);
			System.out.println("The Base64HashString data :" + Digest);
			
			//Get 3DES data.
			String EncryptStr = encryptByKey("你好",Key);
			//String EncryptStr = "你好";
			
			System.out.println("The Encrypted data :" + EncryptStr);
			
			String DecryptStr = decryptByKey("EPjZO26ZZBk=",Key);
			
			System.out.println("The Decrypted data :" + DecryptStr);
			
			
			String A = "guo";
			A = getAuthenicator(A,Key);
			System.out.print("The Encrypted data :" + A);
				
		}
		catch(Exception Ex){
			Ex.printStackTrace();
		}	
	}*/
}