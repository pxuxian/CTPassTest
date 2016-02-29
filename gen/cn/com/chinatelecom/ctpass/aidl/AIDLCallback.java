/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\workspace\\CTPassTest\\src\\cn\\com\\chinatelecom\\ctpass\\aidl\\AIDLCallback.aidl
 */
package cn.com.chinatelecom.ctpass.aidl;
public interface AIDLCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements cn.com.chinatelecom.ctpass.aidl.AIDLCallback
{
private static final java.lang.String DESCRIPTOR = "cn.com.chinatelecom.ctpass.aidl.AIDLCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an cn.com.chinatelecom.ctpass.aidl.AIDLCallback interface,
 * generating a proxy if needed.
 */
public static cn.com.chinatelecom.ctpass.aidl.AIDLCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof cn.com.chinatelecom.ctpass.aidl.AIDLCallback))) {
return ((cn.com.chinatelecom.ctpass.aidl.AIDLCallback)iin);
}
return new cn.com.chinatelecom.ctpass.aidl.AIDLCallback.Stub.Proxy(obj);
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
case TRANSACTION_connectCTPassServiceCallBack:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.connectCTPassServiceCallBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements cn.com.chinatelecom.ctpass.aidl.AIDLCallback
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
@Override public void connectCTPassServiceCallBack(java.lang.String result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(result);
mRemote.transact(Stub.TRANSACTION_connectCTPassServiceCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_connectCTPassServiceCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void connectCTPassServiceCallBack(java.lang.String result) throws android.os.RemoteException;
}
