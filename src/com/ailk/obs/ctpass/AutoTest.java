//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import org.json.JSONObject;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.util.Log;
//import cn.com.chinatelecom.ctpass.aidl.AIDLCallback;
//import cn.com.chinatelecom.ctpass.aidl.ServiceAIDL;
//import cn.com.chinatelecom.ctpass.aidl.UDunCallback;
//import cn.com.chinatelecom.ctpass.aidl.UDunKeyPair;
//
//import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
//import com.ailk.obs.ctpass.util.LocalConfig;
//import com.ailk.obs.ctpass.util.SystemSwitch;
//import com.ailk.obs.ctpass.util.TimestampUtil;
//
///*
//package cn.com.chinatelecom.ctpassautotest;
//
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//import org.json.JSONObject;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.util.Log;
//import cn.com.chinatelecom.ctpass.aidl.AIDLCallback;
//import cn.com.chinatelecom.ctpass.aidl.ServiceAIDL;
//import cn.com.chinatelecom.ctpass.aidl.UDunCallback;
//import cn.com.chinatelecom.ctpass.aidl.UDunKeyPair;
//
//import com.ailk.obs.ctpass.AsyncProvider.RequestListener;
//import com.ailk.obs.ctpass.util.LocalConfig;
//import com.ailk.obs.ctpass.util.SystemSwitch;
//import com.ailk.obs.ctpass.util.TimestampUtil;
//
///**
// * @author zouning
// * @time 2016-02-26 22:18
// */
//@RunWith(AndroidJUnit4.class)
//@SdkSuppress(minSdkVersion = 18)
//public class AutoTest {
//    public static final String PCCODE = "123456";
//    public static final boolean isReleaseSystem = true;
//    public static final String CTPASS_PKG_NAME = "cn.com.chinatelecom.ctpass";
//    final String CTPASS_SERVICE_ACTION = "cn.com.chinatelecom.ctpass.service";
//    public static final int UI_WAIT_TIMEOUT = 1000;
//    private UiDevice mDevice;
//    private static final String TAG = "AutoTest";
//    private ServiceAIDL mCTPassAIDLService;
//    private int maxLoop = 10;
//    private final int timeSleep = 1000;// 1s
//    private String callResult = null;
//    private AsyncProvider mAsyncProvider;
//    private final int HTTP_COUNT_DOWN_TIMEOUNT = 10;
//    
//    @Before
//    public void startMainActivityFromHomeScreen() {
//        // Initialize UiDevice instance
//        mDevice = UiDevice
//                .getInstance(InstrumentationRegistry.getInstrumentation());
//        // Start from the home screen
//        mDevice.pressHome();
//        
//       
//        // 启动ctpass
//        Intent intent = new Intent();
//        intent.setAction(CTPASS_SERVICE_ACTION);
//        intent.setPackage(CTPASS_PKG_NAME);
//        getContext().startService(intent);
//        // 等待ctpass 启动
//        mDevice.wait(Until.hasObject(By.pkg(CTPASS_PKG_NAME)),
//                UI_WAIT_TIMEOUT * 2);
//                
//        // 切换正式或者测试系统
//        if (isReleaseSystem) {
//            APIProvider.getInstance().hostURL = APIProvider.RELEASE_HOST_URL;
//            SystemSwitch.broadcaseSystem(getContext(), true);
//        } else {
//            APIProvider.getInstance().hostURL = APIProvider.DEBUG_HOST_URL;
//            SystemSwitch.broadcaseSystem(getContext(), false);
//        }
//        
//        mAsyncProvider = new AsyncProvider();
//    }
//    
//    private Context getContext() {
//        return InstrumentationRegistry.getContext();
//    }
//    
//    @Test
//    public void testCTPass() throws Exception {
//        Log.d("OnclickTest", "OnclickTest");
//        // solo.get
//        // 绑定aidl服务
//        Intent intent = new Intent();
//        intent.setAction(CTPASS_SERVICE_ACTION);
//        intent.setPackage(CTPASS_PKG_NAME);
//        
//        ServiceConnection mServiceConnection = new ServiceConnection() {
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                Log.d(TAG, "onServiceDisconnected");
//            }
//            @Override
//            public void onServiceConnected(ComponentName name,
//                    IBinder service) {
//                Log.d(TAG, "onServiceConnected");
//                mCTPassAIDLService = ServiceAIDL.Stub.asInterface(service);
//            }
//        };
//        getContext().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//                
//        // 等待绑定
//        for (int i = 0; i < maxLoop; i++) {
//            if (mCTPassAIDLService == null) {
//                sleep();
//            } else {
//                break;
//            }
//        }
//        if (mCTPassAIDLService == null) {
//            Log.e(TAG, "绑定服务超时");
//            return;
//        } else {
//            Log.d(TAG, "绑定服务成功");
//        }
//        
//        // 注册回调
//        callResult = null;
//        mCTPassAIDLService.registerCallback(new AIDLCallback.Stub() {
//            
//            @Override
//            public void connectCTPassServiceCallBack(String result)
//                    throws RemoteException {
//                callResult = result;
//            }
//        });
//        mCTPassAIDLService.connectCTPassService();
//        for (int i = 0; i < maxLoop; i++) {
//            if (callResult == null) {
//                sleep();
//            } else {
//                break;
//            }
//        }
//        if (!"00".equals(callResult)) {
//            Log.e(TAG, "机卡通道不通");
//            return;
//        } else {
//            Log.d(TAG, "机卡通道可行");
//        }
//        
//        // 获取token并且认证
//        tokenAuth("0");
//        tokenAuth("1");
//        // 获取token并且认证end
//        
//        // 生成签名密钥对
//        // 使用CountDownLatch将异步改为同步
//        String keyName = TimestampUtil.getTimeStamp();
//        final CountDownLatch genSignKeyPairLatch = new CountDownLatch(1);
//        mCTPassAIDLService.genSignKeyPair(LocalConfig.DEVICE_NO, keyName,
//                UDunKeyPair.KEY_LENGTH_1024, "0", new UDunCallback.Stub() {
//                    
//                    @Override
//                    public void callBack(int resultCode, String CTPassID,
//                            boolean isEnctypted, String resultDesc)
//                                    throws RemoteException {
//                        genSignKeyPairLatch.countDown();
//                        logCallBack("genSignKeyPair", resultCode, CTPassID,
//                                isEnctypted, resultDesc);
//                    }
//                });
//        inputPC();
//        genSignKeyPairLatch.await(HTTP_COUNT_DOWN_TIMEOUNT, TimeUnit.SECONDS);
//        // 生成签名密钥对end
//        
//        // 生成加密密钥对
//        // 使用CountDownLatch将异步改为同步
//        String encryptedKeyName = TimestampUtil.getTimeStamp();
//        final CountDownLatch writeEncryptKeyPairLatch = new CountDownLatch(1);
//        byte[] module = HexStringConvert
//                .parseHexStr2Byte(KeyPairTestData.E_1024_1.pubKey);
//        byte[] pri = HexStringConvert
//                .parseHexStr2Byte(KeyPairTestData.E_1024_1.priKey);
//        byte[] cer = HexStringConvert.parseHexStr2Byte(KeyPairTestData.CER);
//        mCTPassAIDLService.writeEncryptKeyPair(LocalConfig.DEVICE_NO,
//                encryptedKeyName, UDunKeyPair.KEY_LENGTH_1024, "0", module, pri,
//                cer, new UDunCallback.Stub() {
//                    
//                    @Override
//                    public void callBack(int resultCode, String CTPassID,
//                            boolean isEnctypted, String resultDesc)
//                                    throws RemoteException {
//                        writeEncryptKeyPairLatch.countDown();
//                        logCallBack("writeEncryptKeyPair", resultCode, CTPassID,
//                                isEnctypted, resultDesc);
//                    }
//                });
//        inputPC();
//        writeEncryptKeyPairLatch.await(HTTP_COUNT_DOWN_TIMEOUNT,
//                TimeUnit.SECONDS);
//                // 生成加密密钥对end
//                
//        // 读取密钥对列表
//        final CountDownLatch readKeyPairLatch = new CountDownLatch(1);
//        List<UDunKeyPair> uDunKeyPairs = mCTPassAIDLService
//                .readKeyPair(new UDunCallback.Stub() {
//                    
//                    @Override
//                    public void callBack(int resultCode, String CTPassID,
//                            boolean isEnctypted, String resultDesc)
//                                    throws RemoteException {
//                        readKeyPairLatch.countDown();
//                        logCallBack("genSignKeyPair", resultCode, CTPassID,
//                                isEnctypted, resultDesc);
//                    }
//                });
//        printUDUNKeyPair(uDunKeyPairs);
//        readKeyPairLatch.await(HTTP_COUNT_DOWN_TIMEOUNT, TimeUnit.SECONDS);
//        // 读取密钥对列表end
//        
//        Log.i(TAG, "=====end=====");
//    }
//    
//    private void inputPC() {
//        // 输入pc码
//        List<UiObject2> pcEditText = mDevice.wait(Until.findObjects(
//                By.clazz("android.widget.EditText").pkg(CTPASS_PKG_NAME)),
//                UI_WAIT_TIMEOUT);
//        print(pcEditText);
//        Log.d(TAG, "uiobj:" + String.valueOf(pcEditText));
//        if (pcEditText != null && pcEditText.size() > 0) {
//            pcEditText.get(0).setText(PCCODE);
//        }
//        // 点击按钮
//        List<UiObject2> confirmButton = mDevice.wait(
//                Until.findObjects(By.clazz("android.widget.Button")
//                        .pkg(CTPASS_PKG_NAME).res("android:id/button1")),
//                UI_WAIT_TIMEOUT);
//        print(confirmButton);
//        Log.d(TAG, "uiobj:" + String.valueOf(confirmButton));
//        if (confirmButton != null && confirmButton.size() > 0) {
//            confirmButton.get(0).click();
//        }
//    }
//    
//    private void print(List<UiObject2> uiObject2s) {
//        if (uiObject2s == null) {
//            Log.w(TAG, "uiObject2 list null");
//        } else {
//            Log.d(TAG, "uiObject2 list size:" + uiObject2s.size());
//            for (int i = 0; i < uiObject2s.size(); i++) {
//                Log.d(TAG,
//                        "uiObject2 item:" + String.valueOf(uiObject2s.get(i)));
//                if (i == 0) {
//                    StringBuilder sb = new StringBuilder();
//                    UiObject2 obj = uiObject2s.get(0);
//                    sb.append("ApplicationPackage:")
//                            .append(obj.getApplicationPackage());
//                    sb.append(",ClassName:").append(obj.getClassName());
//                    sb.append(",ContentDescription:")
//                            .append(obj.getContentDescription());
//                    sb.append(",ResourceName:").append(obj.getResourceName());
//                    Log.d(TAG, sb.toString());
//                }
//            }
//        }
//    }
//    
//    private void printUDUNKeyPair(List<UDunKeyPair> uDunKeyPairs) {
//        if (uDunKeyPairs == null) {
//            Log.w(TAG, "uDunKeyPairs null");
//        } else {
//            for (int i = 0; i < uDunKeyPairs.size(); i++) {
//                UDunKeyPair keyPair = uDunKeyPairs.get(i);
//                StringBuilder sb = new StringBuilder();
//                sb.append("index,").append(keyPair.index);
//                sb.append(",keyLength,").append(keyPair.keyLength);
//                sb.append(",keyPairName,").append(keyPair.keyPairName);
//                Log.d(TAG, sb.toString());
//            }
//        }
//    }
//    
//    private void sleep() {
//        try {
//            Thread.sleep(timeSleep);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private void logCallBack(String SubTag, int resultCode, String CTPassID,
//            boolean isEnctypted, String resultDesc) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(SubTag).append(",resultCode:").append(resultCode);
//        sb.append(",resultDesc:").append(resultDesc);
//        sb.append(",CTPassID:").append(CTPassID);
//        Log.d(TAG, sb.toString());
//    }
//    
//    private Object seqidResponse = null;
//    
//    private void tokenAuth(final String pcFlag) throws Exception {
//        // 获取token并且认证
//        final CountDownLatch tokenLatch = new CountDownLatch(1);
//        mAsyncProvider.getSeqIDRandom(new RequestListener() {
//            
//            @Override
//            public void onComplete(final Object response) {
//                seqidResponse = response;
//                tokenLatch.countDown();
//            }
//            
//            @Override
//            public void onInvokerError(final String e) {
//                Log.e(TAG, e);
//                tokenLatch.countDown();
//            }
//            
//        });
//        tokenLatch.await(HTTP_COUNT_DOWN_TIMEOUNT, TimeUnit.SECONDS);
//        genarateParamsAndAuthToken(seqidResponse, pcFlag);
//    }
//    
//    private void genarateParamsAndAuthToken(final Object seqIDAndRadomResponse,
//            final String pcFlag) {
//        try {
//            JSONObject obj = (JSONObject) seqIDAndRadomResponse;
//            JSONObject resultJsonObject = obj
//                    .getJSONObject("GenReqAndRandomResponse");
//            String seqID = resultJsonObject.getString("SeqID");
//            String random = resultJsonObject.getString("Random");
//            // 获取s串
//            final String tokenTemp = mCTPassAIDLService
//                    .getCTPassToken(LocalConfig.DEVICE_NO, seqID, random);
//                    
//            if (tokenTemp != null && tokenTemp.substring(0, 2).equals("00")) {
//                final CountDownLatch countDownLatch = new CountDownLatch(1);
//                mAsyncProvider.authToken(tokenTemp, seqID, random, pcFlag,
//                        new RequestListener() {
//                            
//                            @Override
//                            public void onComplete(final Object response) {
//                                JSONObject obj = (JSONObject) response;
//                                try {
//                                    JSONObject resultJsonObject = obj
//                                            .getJSONObject(
//                                                    "AuthCTPassTokenResponse");
//                                    // reportToast(resultJsonObject.toString());
//                                    Log.d(TAG, resultJsonObject.toString());
//                                } catch (Exception e) {
//                                    Log.e(TAG, e.getMessage(), e);
//                                }
//                                countDownLatch.countDown();
//                            }
//                            
//                            @Override
//                            public void onInvokerError(final String e) {
//                                Log.e(TAG, "访问网络异常" + e);
//                                countDownLatch.countDown();
//                            }
//                            
//                        });
//                countDownLatch.await(HTTP_COUNT_DOWN_TIMEOUNT,
//                        TimeUnit.SECONDS);
//            } else {
//                Log.e(TAG, "获取token失败,不验证token：" + tokenTemp);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage(), e);
//        }
//    }
//}
//*/