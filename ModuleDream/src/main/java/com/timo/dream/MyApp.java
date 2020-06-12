package com.timo.dream;

import com.alibaba.android.arouter.launcher.ARouter;
import com.timo.base.BaseApplication;

/**
 * Created by 45590 on 2019/4/24.
 */

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
