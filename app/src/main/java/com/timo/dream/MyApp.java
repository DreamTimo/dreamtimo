package com.timo.dream;

import com.previewlibrary.ZoomMediaLoader;
import com.timo.base.BaseApplication;

/**
 * Created by 45590 on 2019/4/24.
 */

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ZoomMediaLoader.getInstance().init(new ImagePreviewLoader());
    }
}