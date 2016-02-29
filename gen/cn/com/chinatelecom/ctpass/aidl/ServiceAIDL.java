/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\workspace\\CTPassTest\\src\\cn\\com\\chinatelecom\\ctpass\\aidl\\ServiceAIDL.aidl
 */
package cn.com.chinatelecom.ctpass.aidl;
public interface ServiceAIDL extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements cn.com.chinatelecom.ctpass.aidl.ServiceAIDL
{
private static final java.lang.String DESCRIPTOR = "cn.com.chinatelecom.ctpass.aidl.ServiceAIDL";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an cn.com.chinatelecom.ctpass.aidl.ServiceAIDL interface,
 * generating a proxy if needed.
 */
public static cn.com.chinatelecom.ctpass.aidl.ServiceAIDL asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof cn.com.chinatelecom.ctpass.aidl.ServiceAIDL))) {
return ((cn.com.chinatelecom.ctpass.aidl.ServiceAIDL)iin);
}
return new cn.com.chinatelecom.ctpass.aidl.ServiceAIDL.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
cn.com.chinatelecom.ctpass.aidl.AIDLCallback _arg0;
_arg0 = cn.com.chinatelecom.ctpass.aidl.AIDLCallback.Stub.asInterface(data.readStrongBinder());
this.registerCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(DESCRIPTOR);
cn.com.chinatelecom.ctpass.aidl.AIDLCallback _arg0;
_arg0 = cn.com.chinatelecom.ctpass.aidl.AIDLCallback.Stub.asInterface(data.readStrongBinder());
this.unregisterCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_connectCTPassService:
{
data.enforceInterface(DESCRIPTOR);
this.connectCTPassService();
reply.writeNoException();
return true;
}
case TRANSACTION_getCTPassToken:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _result = this.getCTPassToken(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getMixedTokenM:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg4;
_arg4 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.getMixedTokenM(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_getOTP:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getOTP(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getMixedOTP:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg3;
_arg3 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.getMixedOTP(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_readKeyPair:
{
data.enforceInterface(DESCRIPTOR);
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg0;
_arg0 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
java.util.List<cn.com.chinatelecom.ctpass.aidl.UDunKeyPair> _result = this.readKeyPair(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_genSignKeyPair:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg4;
_arg4 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.genSignKeyPair(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_genEncryptKeyPair:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
byte[] _arg4;
_arg4 = data.createByteArray();
byte[] _arg5;
_arg5 = data.createByteArray();
byte[] _arg6;
_arg6 = data.createByteArray();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg7;
_arg7 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.genEncryptKeyPair(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
reply.writeNoException();
return true;
}
case TRANSACTION_RSACipherEnc:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
byte[] _arg1;
_arg1 = data.createByteArray();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg4;
_arg4 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.RSACipherEnc(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_RSASign:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
int _arg3;
_arg3 = data.readInt();
java.lang.String _arg4;
_arg4 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg5;
_arg5 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.RSASign(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
return true;
}
case TRANSACTION_deleteKeyPair:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg3;
_arg3 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.deleteKeyPair(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_readICCID:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg1;
_arg1 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.readICCID(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_verifySignature:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg3;
_arg3 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.verifySignature(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_readPublicKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg2;
_arg2 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.readPublicKey(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_writeCer:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
byte[] _arg2;
_arg2 = data.createByteArray();
java.lang.String _arg3;
_arg3 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg4;
_arg4 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.writeCer(_arg0, _arg1, _arg2, _arg3, _arg4);
reply.writeNoException();
return true;
}
case TRANSACTION_readCer:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg2;
_arg2 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.readCer(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_readKeyNames:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg1;
_arg1 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.readKeyNames(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_updatePassword:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
cn.com.chinatelecom.ctpass.aidl.UDunCallback _arg1;
_arg1 = cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.asInterface(data.readStrongBinder());
this.updatePassword(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements cn.com.chinatelecom.ctpass.aidl.ServiceAIDL
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void registerCallback(cn.com.chinatelecom.ctpass.aidl.AIDLCallback callFunc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callFunc!=null))?(callFunc.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallback(cn.com.chinatelecom.ctpass.aidl.AIDLCallback callFunc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callFunc!=null))?(callFunc.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void connectCTPassService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_connectCTPassService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getCTPassToken(java.lang.String AppID, java.lang.String SeqID, java.lang.String Random) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(AppID);
_data.writeString(SeqID);
_data.writeString(Random);
mRemote.transact(Stub.TRANSACTION_getCTPassToken, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void getMixedTokenM(java.lang.String appID, java.lang.String seqID, java.lang.String random, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback ctPassCallBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(seqID);
_data.writeString(random);
_data.writeString(pcFlag);
_data.writeStrongBinder((((ctPassCallBack!=null))?(ctPassCallBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getMixedTokenM, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getOTP(int otpLength) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(otpLength);
mRemote.transact(Stub.TRANSACTION_getOTP, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
     * 获取OTP,融合oma和ota的方式
     * @param appID 从CTPass平台分配的appID
     * @param otpLength OTP长度,6-8
     * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
     * @param result 结果回调
     */
@Override public void getMixedOTP(java.lang.String appID, int otpLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeInt(otpLength);
_data.writeString(pcFlag);
_data.writeStrongBinder((((result!=null))?(result.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_getMixedOTP, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 读取密钥对
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 * @return 密钥对
	 */
@Override public java.util.List<cn.com.chinatelecom.ctpass.aidl.UDunKeyPair> readKeyPair(cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<cn.com.chinatelecom.ctpass.aidl.UDunKeyPair> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((logCallback!=null))?(logCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_readKeyPair, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(cn.com.chinatelecom.ctpass.aidl.UDunKeyPair.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 生成签名密钥对
	 * @param appID  从CTPass平台分配的appID
	 * @param keyName 密钥对名称,1-32字符
	 * @param keyLength  签名密钥对长度 {@link UDunKeyPair#KEY_LENGTH_1024},
	 *            {@link UDunKeyPair#KEY_LENGTH_2048}
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param genSignCallBack 结果回调,包含日志信息
	 */
@Override public void genSignKeyPair(java.lang.String appID, java.lang.String keyName, int keyLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback genSignCallBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyName);
_data.writeInt(keyLength);
_data.writeString(pcFlag);
_data.writeStrongBinder((((genSignCallBack!=null))?(genSignCallBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_genSignKeyPair, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void genEncryptKeyPair(java.lang.String appID, java.lang.String keyName, int keyLength, java.lang.String pcFlag, byte[] modual, byte[] prvexp, byte[] cer, cn.com.chinatelecom.ctpass.aidl.UDunCallback genEncryptCallBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyName);
_data.writeInt(keyLength);
_data.writeString(pcFlag);
_data.writeByteArray(modual);
_data.writeByteArray(prvexp);
_data.writeByteArray(cer);
_data.writeStrongBinder((((genEncryptCallBack!=null))?(genEncryptCallBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_genEncryptKeyPair, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 调用RSA私钥进行指模运算,用于签名和解密,hash签名最好使用{@link #RSASign}
	 * 
	 * @param appID  从CTPass平台分配的appID
	 * @param data 填充好的待 解密 的数据,
	 * @param keyName 密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 */
@Override public void RSACipherEnc(java.lang.String appID, byte[] data, java.lang.String keyName, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeByteArray(data);
_data.writeString(keyName);
_data.writeString(pcFlag);
_data.writeStrongBinder((((logCallback!=null))?(logCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_RSACipherEnc, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void RSASign(java.lang.String appID, java.lang.String hash, java.lang.String keyName, int keyPairLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(hash);
_data.writeString(keyName);
_data.writeInt(keyPairLength);
_data.writeString(pcFlag);
_data.writeStrongBinder((((logCallback!=null))?(logCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_RSASign, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 删除密钥对
	 * 
	 * @param keyName 要删除的密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param delCallBack 删除结果回调
	 * @param appID  从CTPass平台分配的appID
	 */
@Override public void deleteKeyPair(java.lang.String appID, java.lang.String keyName, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback delCallBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyName);
_data.writeString(pcFlag);
_data.writeStrongBinder((((delCallBack!=null))?(delCallBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_deleteKeyPair, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 读取ICCID
	 * @param callback 操作结果回调
	 */
@Override public void readICCID(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_readICCID, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void verifySignature(java.lang.String hash, java.lang.String signature, java.lang.String signKeyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback verifyCallback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(hash);
_data.writeString(signature);
_data.writeString(signKeyPairName);
_data.writeStrongBinder((((verifyCallback!=null))?(verifyCallback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_verifySignature, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 根据密钥对名称读取公钥
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            密钥对名称
	 * @param callback 操作结果回调 返回128byte或者256byte的公钥
	 */
@Override public void readPublicKey(java.lang.String appID, java.lang.String keyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyPairName);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_readPublicKey, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
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
@Override public void writeCer(java.lang.String appID, java.lang.String keyPairName, byte[] cer, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyPairName);
_data.writeByteArray(cer);
_data.writeString(pcFlag);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_writeCer, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 读取证书
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            证书名称,同密钥对名称
	 * @param callback 操作结果回调
	 */
@Override public void readCer(java.lang.String appID, java.lang.String keyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeString(keyPairName);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_readCer, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 读取所有密钥对的名称
	 */
@Override public void readKeyNames(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_readKeyNames, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 更新PC码
	 */
@Override public void updatePassword(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(appID);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_updatePassword, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_connectCTPassService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getCTPassToken = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getMixedTokenM = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getOTP = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getMixedOTP = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_readKeyPair = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_genSignKeyPair = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_genEncryptKeyPair = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_RSACipherEnc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_RSASign = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_deleteKeyPair = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_readICCID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_verifySignature = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_readPublicKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_writeCer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_readCer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_readKeyNames = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_updatePassword = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
}
public void registerCallback(cn.com.chinatelecom.ctpass.aidl.AIDLCallback callFunc) throws android.os.RemoteException;
public void unregisterCallback(cn.com.chinatelecom.ctpass.aidl.AIDLCallback callFunc) throws android.os.RemoteException;
public void connectCTPassService() throws android.os.RemoteException;
public java.lang.String getCTPassToken(java.lang.String AppID, java.lang.String SeqID, java.lang.String Random) throws android.os.RemoteException;
public void getMixedTokenM(java.lang.String appID, java.lang.String seqID, java.lang.String random, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback ctPassCallBack) throws android.os.RemoteException;
public java.lang.String getOTP(int otpLength) throws android.os.RemoteException;
/**
     * 获取OTP,融合oma和ota的方式
     * @param appID 从CTPass平台分配的appID
     * @param otpLength OTP长度,6-8
     * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
     * @param result 结果回调
     */
public void getMixedOTP(java.lang.String appID, int otpLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback result) throws android.os.RemoteException;
/**
	 * 读取密钥对
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 * @return 密钥对
	 */
public java.util.List<cn.com.chinatelecom.ctpass.aidl.UDunKeyPair> readKeyPair(cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException;
/**
	 * 生成签名密钥对
	 * @param appID  从CTPass平台分配的appID
	 * @param keyName 密钥对名称,1-32字符
	 * @param keyLength  签名密钥对长度 {@link UDunKeyPair#KEY_LENGTH_1024},
	 *            {@link UDunKeyPair#KEY_LENGTH_2048}
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param genSignCallBack 结果回调,包含日志信息
	 */
public void genSignKeyPair(java.lang.String appID, java.lang.String keyName, int keyLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback genSignCallBack) throws android.os.RemoteException;
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
public void genEncryptKeyPair(java.lang.String appID, java.lang.String keyName, int keyLength, java.lang.String pcFlag, byte[] modual, byte[] prvexp, byte[] cer, cn.com.chinatelecom.ctpass.aidl.UDunCallback genEncryptCallBack) throws android.os.RemoteException;
/**
	 * 调用RSA私钥进行指模运算,用于签名和解密,hash签名最好使用{@link #RSASign}
	 * 
	 * @param appID  从CTPass平台分配的appID
	 * @param data 填充好的待 解密 的数据,
	 * @param keyName 密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param logCallback 日志信息回调,不想获取日志信息可以传null
	 */
public void RSACipherEnc(java.lang.String appID, byte[] data, java.lang.String keyName, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException;
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
public void RSASign(java.lang.String appID, java.lang.String hash, java.lang.String keyName, int keyPairLength, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback logCallback) throws android.os.RemoteException;
/**
	 * 删除密钥对
	 * 
	 * @param keyName 要删除的密钥对名称
	 * @param pcFlag 0:不需要PC码,1:需要pc码(旧UI),2:需要pc码(新UI)
	 * @param delCallBack 删除结果回调
	 * @param appID  从CTPass平台分配的appID
	 */
public void deleteKeyPair(java.lang.String appID, java.lang.String keyName, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback delCallBack) throws android.os.RemoteException;
/**
	 * 读取ICCID
	 * @param callback 操作结果回调
	 */
public void readICCID(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
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
public void verifySignature(java.lang.String hash, java.lang.String signature, java.lang.String signKeyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback verifyCallback) throws android.os.RemoteException;
/**
	 * 根据密钥对名称读取公钥
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            密钥对名称
	 * @param callback 操作结果回调 返回128byte或者256byte的公钥
	 */
public void readPublicKey(java.lang.String appID, java.lang.String keyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
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
public void writeCer(java.lang.String appID, java.lang.String keyPairName, byte[] cer, java.lang.String pcFlag, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
/**
	 * 读取证书
	 * @param appID  从CTPass平台分配的appID
	 * @param keyPairName
	 *            证书名称,同密钥对名称
	 * @param callback 操作结果回调
	 */
public void readCer(java.lang.String appID, java.lang.String keyPairName, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
/**
	 * 读取所有密钥对的名称
	 */
public void readKeyNames(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
/**
	 * 更新PC码
	 */
public void updatePassword(java.lang.String appID, cn.com.chinatelecom.ctpass.aidl.UDunCallback callback) throws android.os.RemoteException;
}
