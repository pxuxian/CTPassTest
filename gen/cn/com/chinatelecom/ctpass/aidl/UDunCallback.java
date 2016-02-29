/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\workspace\\CTPassTest\\src\\cn\\com\\chinatelecom\\ctpass\\aidl\\UDunCallback.aidl
 */
package cn.com.chinatelecom.ctpass.aidl;
public interface UDunCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements cn.com.chinatelecom.ctpass.aidl.UDunCallback
{
private static final java.lang.String DESCRIPTOR = "cn.com.chinatelecom.ctpass.aidl.UDunCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an cn.com.chinatelecom.ctpass.aidl.UDunCallback interface,
 * generating a proxy if needed.
 */
public static cn.com.chinatelecom.ctpass.aidl.UDunCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof cn.com.chinatelecom.ctpass.aidl.UDunCallback))) {
return ((cn.com.chinatelecom.ctpass.aidl.UDunCallback)iin);
}
return new cn.com.chinatelecom.ctpass.aidl.UDunCallback.Stub.Proxy(obj);
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
case TRANSACTION_callBack:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _arg2;
_arg2 = (0!=data.readInt());
java.lang.String _arg3;
_arg3 = data.readString();
this.callBack(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements cn.com.chinatelecom.ctpass.aidl.UDunCallback
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
/**resultCode以200为成功*/
@Override public void callBack(int resultCode, java.lang.String CTPassID, boolean isEnctypted, java.lang.String resultDesc) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(resultCode);
_data.writeString(CTPassID);
_data.writeInt(((isEnctypted)?(1):(0)));
_data.writeString(resultDesc);
mRemote.transact(Stub.TRANSACTION_callBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_callBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**resultCode以200为成功*/
public void callBack(int resultCode, java.lang.String CTPassID, boolean isEnctypted, java.lang.String resultDesc) throws android.os.RemoteException;
}
