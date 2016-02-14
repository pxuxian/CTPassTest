package cn.com.chinatelecom.ctpass.aidl;

import cn.com.chinatelecom.ctpass.aidl.AIDLCallback;
import cn.com.chinatelecom.ctpass.aidl.UDunKeyPair;
import cn.com.chinatelecom.ctpass.aidl.UDunCallback;

interface ServiceAIDL {

	void registerCallback(AIDLCallback callFunc);
	
	void unregisterCallback(AIDLCallback callFunc);
	
	void connectCTPassService();
	
	String getCTPassToken(String AppID, String SeqID, String Random);
	
	
	void getMixedTokenM(String appID, String seqID, String random, String pcFlag,UDunCallback ctPassCallBack);
	
	String getOTP(int otpLength);
	
	/**
     * 获取OTP,融合oma和ota的方式
     * @param appID 从CTPass平台分配的appID
     * @param otpLength OTP长度,6-8
     * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
     * @param result 结果回调
     */
    void getMixedOTP(String appID,int otpLength, String pcFlag,UDunCallback result);
	
	
	/**
	 * 读取密钥对
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 * @return 密钥对
	 */
	List<UDunKeyPair> readKeyPair(UDunCallback logCallback);

	/**
	 * 生成签名密钥对
	 * @param appID  从CTPass平台分配的appID
	 * @param keyName 密钥对名称,1-32字符
	 * @param keyLength  签名密钥对长度 {@link UDunKeyPair#KEY_LENGTH_1024},
	 *            {@link UDunKeyPair#KEY_LENGTH_2048}
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param genSignCallBack 结果回调,包含日志信息
	 */
	void genSignKeyPair(String appID,String keyName,int keyLength, String pcFlag,
			UDunCallback genSignCallBack);

	/**
	 * 生成加密密钥对
	 * @param appID  从CTPass平台分配的appID
	 * @param keyName 密钥对名称,1-32字符
	 * @param keyLength 签名密钥对长度 {@link UDunKeyPair#KEY_LENGTH_1024},
	 *            {@link UDunKeyPair#KEY_LENGTH_2048}
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param modual 公钥
	 * @param prvexp 私钥
	 * @param cer 证书
	 * @param genEncryptCallBack 结果回调,包含日志信息
	 */
	void genEncryptKeyPair(String appID,String keyName,int keyLength, String pcFlag,
			in byte[] modual, in byte[] prvexp, in byte[] cer,
			UDunCallback genEncryptCallBack);

	/**
	 * 调用RSA私钥进行指模运算,用于签名和解密,hash签名最好使用{@link #RSASign}
	 * 
	 * @param appID  从CTPass平台分配的appID
	 * @param data 填充好的待 解密 的数据,
	 * @param keyName 密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 */
	void RSACipherEnc(String appID,in byte[] data, String keyName, String pcFlag,
			UDunCallback logCallback);
	
	/**
	 *对hash值进行签名
	 * 
	 * @param appID  从CTPass平台分配的appID
	 * @param hash 待签名的hash值的16进制字符,比如:a91404ebbc0f456a4104092e5c14dbff891c7c32,大小写皆可
	 * @param keyName 密钥对名称
	 * @param keyPairLength 密钥对长度{@link UDunKeyPair#KEY_LENGTH_1024},
	 *            {@link UDunKeyPair#KEY_LENGTH_2048}
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 */
	void RSASign(String appID,String hash, String keyName,int keyPairLength, String pcFlag,
			UDunCallback logCallback);

	/**
	 * 删除密钥对
	 * 
	 * @param keyName 要删除的密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param delCallBack 删除结果回调
	 * @param appID  从CTPass平台分配的appID
	 */
	void deleteKeyPair(String appID,String keyName, String pcFlag, UDunCallback delCallBack);
	
	/**
	 * 读取ICCID
	 * @param callback 操作结果回调
	 */
	void readICCID(String appID,UDunCallback callback);
	
	/**
	 * 校验签名
	 * 
	 * @param hash
	 *            待签名的hash,RSASign的入参中的hash
	 * @param signature
	 *            签名串,RSASign的返回参数
	 * @param signKeyPairName
	 *            签名密钥对名称
	 * @param verifyCallback 操作结果回调
	 */
	void verifySignature(String hash,String signature, String signKeyPairName,UDunCallback verifyCallback);
	
	/**
	 * 根据密钥对名称读取公钥
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            密钥对名称
	 * @param callback 操作结果回调 返回128byte或者256byte的公钥
	 */
	void readPublicKey(String appID,String keyPairName,UDunCallback callback);

	/**
	 * 
	 * 写入证书
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            证书名,同密钥对名称
	 * @param cer
	 *            证书,16进制字符串
	 * @param PIN
	 *            pc码
	 * @param callback 操作结果回调
	 */
	void writeCer(String appID,String keyPairName, in byte[] cer, String pcFlag,UDunCallback callback);

	/**
	 * 读取证书
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            证书名称,同密钥对名称
	 * @param callback 操作结果回调
	 */
	void readCer(String appID,String keyPairName,UDunCallback callback);

	/**
	 * 读取所有密钥对的名称
	 */
	void readKeyNames(String appID,UDunCallback callback);
	
	/**
	 * 更新PC码
	 */
	void updatePassword(String appID,UDunCallback callback);
}