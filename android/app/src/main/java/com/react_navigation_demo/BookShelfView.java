package com.react_navigation_demo;

import android.content.Context;
import android.widget.LinearLayout;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;

/**
 * Created by CD on 2019/3/11.
 */

public class BookShelfView extends LinearLayout implements LifecycleEventListener {

    ReactContext mReactContext;
    Context mContext;
    public BookShelfView(Context context) {
        super(context);
        this.mContext = context;
        //initView();
    }

    public void setReactContext(ReactContext reactContext) {
        mReactContext = reactContext;
    }

    //form com.facebook.react.bridge.LifecycleEventListener
    @Override
    public void onHostResume() {
        //refresh();
    }

    @Override
    public void onHostPause() {

    }

    //form com.facebook.react.bridge.LifecycleEventListener
    @Override
    public void onHostDestroy() {
        mReactContext.removeLifecycleEventListener(this);
    }
}