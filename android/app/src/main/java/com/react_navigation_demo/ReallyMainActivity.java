package com.react_navigation_demo;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by CD on 2019/3/11.
 */

public class ReallyMainActivity extends Activity {

    ReactRootView mReactRootView;
    ReactInstanceManager mReactInstanceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_really_main);
        mReactRootView = (ReactRootView) findViewById(R.id.rnview);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .addPackage(new MainReactPackage())
                .addPackage(new AnExampleReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        mReactRootView.startReactApplication(mReactInstanceManager, "react_navigation_demo", null);

    }


}
