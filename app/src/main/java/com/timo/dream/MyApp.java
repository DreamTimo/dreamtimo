package com.timo.dream;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.previewlibrary.ZoomMediaLoader;
import com.timo.base.BaseApplication;
import com.timo.base.BaseTools;
import com.timo.httplib.network.MyHttp;
import com.timo.httplib.network.MyHttpListener;

/**
 * Created by 45590 on 2019/4/24.
 */

public class MyApp extends BaseApplication {
    boolean isDebug=true;
    @Override
    public void onCreate() {
        super.onCreate();
        ZoomMediaLoader.getInstance().init(new ImagePreviewLoader());
        MyHttp.init("http://10.0.129.195:8888", new MyHttpListener() {
            @Override
            public void showLoadingDialog(Context context) {
                
            }

            @Override
            public void dissmissLoadingDialog() {

            }

            @Override
            public void showToast(String message) {

            }

            @Override
            public void logE(Throwable e) {
                BaseTools.log(e);
            }
        });
        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
