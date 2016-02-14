package cn.com.chinatelecom.ctpass.aidl;
interface UDunCallback{
	/**resultCode以200为成功*/
	void callBack(int resultCode,String CTPassID,boolean isEnctypted,String resultDesc);
	
}