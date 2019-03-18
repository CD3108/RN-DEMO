package com.react_navigation_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CD on 2019/3/11.
 */

public class ExampleInterface extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;

    public ExampleInterface(ReactApplicationContext reactContext) {
        super(reactContext);
        setupLifecycleEventListener(reactContext);
        setupActivityResultListener(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ExampleInterface";
    }

    /**
     * 这里是原生代码处理消息的函数。
     * <p>
     * 回调参数的对应关系，java -> js
     * Boolean -> Bool
     * Integer -> Number
     * Double -> Number
     * Float -> Number
     * String -> String
     * Callback -> function
     * ReadableMap -> Object
     * ReadableArray -> Array
     *
     * @param msg RN传过来的参数
     * @return void 函数不能又返回值
     */
    @ReactMethod
    public void handleMessage(String msg) {
        Log.e("RNMessage", "receive message from handleMessage:" + msg);
        Toast.makeText(reactContext, "receive message from handleMessage:" + msg, Toast.LENGTH_LONG).show();
    }


    @ReactMethod
    public void FUCK_U(String msg) {
        Log.e("RNMessage", "receive message from FUCK_U:" + msg);
        Toast.makeText(reactContext, "receive message from FUCK_U:" + msg, Toast.LENGTH_LONG).show();
    }


    @ReactMethod
    public void handleCallback(String msg, Callback callback) {
        Log.e("RNMessage", "receive message from handleCallback:" + msg);
        Toast.makeText(reactContext, "JAVA 接收:" + msg, Toast.LENGTH_LONG).show();
        callback.invoke("这是 java 返回的 msg");
    }


    @ReactMethod
    public void Go2Contacts(String msg) {
        Log.e("RNMessage", "receive message from Go2Contacts:" + msg);
    /*调用联系人页面*/
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        this.reactContext.startActivityForResult(intent, 555, new Bundle());
    }


    private void setupActivityResultListener(ReactApplicationContext reactContext) {
        reactContext.addActivityEventListener(new BaseActivityEventListener() {
            @Override
            public void onActivityResult(Activity activity, int requestCode,
                                         int resultCode, Intent data) {
                if (requestCode != 555 || resultCode != Activity.RESULT_OK) {
                    return;
                }
                /*如果选取ok,开始读取联系人信息*/
                String msg = pareContactMsg(data.getData());
                /*发送给RN*/
                sendMsgToRN(msg);
            }
        });
    }

    private void sendMsgToRN(String msg) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("AndroidToRNMessage", msg);
    }






    private String pareContactMsg(Uri uri) {
        Cursor cursor = null;
        Cursor phoneNumberCursor = null;
        String msg = "";
        try {
            cursor = reactContext.getContentResolver().query(uri, null, null, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.
                        getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = cursor
                        .getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                String phoneNumber = "123";
                //phoneNumber=cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                if (hasPhoneNumber == 1) {
                    phoneNumberCursor = reactContext.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract
                                    .CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{String.valueOf(id)}, null);
                    if (phoneNumberCursor != null && phoneNumberCursor.moveToFirst()) {
                        phoneNumber = phoneNumberCursor.getString(
                                phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        );
                    }
                }
                msg = "{姓名: " + name + ", 电话号码:" + phoneNumber + "}";
            }
        }catch (Exception e){
            Log.e("Contacts Exception",e.toString());
        }
        finally {
            if (null != phoneNumberCursor) {
                phoneNumberCursor.close();
            }
            if (null != cursor) {
                cursor.close();
            }
            return msg;
        }
    }






    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("AA", "我是一个常量，我来自Native");
        return constants;
    }








    private void setupLifecycleEventListener(ReactApplicationContext reactContext) {
        reactContext.addLifecycleEventListener(new LifecycleEventListener() {
            @Override
            public void onHostResume() {
                Log.e("onHostResume", "onHostResume: ");
            }

            @Override
            public void onHostPause() {
                Log.e("onHostResume", "onHostPause: ");
            }

            @Override
            public void onHostDestroy() {
                Log.e("onHostResume", "onHostDestroy: ");
            }
        });
    }
}
