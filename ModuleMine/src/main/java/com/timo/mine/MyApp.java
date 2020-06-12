package com.timo.mine;

import com.alibaba.android.arouter.launcher.ARouter;
import com.previewlibrary.ZoomMediaLoader;
import com.timo.base.BaseApplication;
import com.timo.base.BuildConfig;
import com.timo.mine.image_preview.ImagePreviewLoader;

/**
 * Created by 45590 on 2019/4/24.
 */

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ZoomMediaLoader.getInstance().init(new ImagePreviewLoader());
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
