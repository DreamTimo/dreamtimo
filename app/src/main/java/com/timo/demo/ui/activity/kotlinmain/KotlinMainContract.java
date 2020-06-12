package com.timo.demo.ui.activity.kotlinmain;


import android.content.Context;

import com.timo.base.mvp.BasePresenter;
import com.timo.base.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KotlinMainContract {
    public interface View extends BaseView {
        void showFragment(int position);
    }

    public interface Presenter extends BasePresenter<View> {
        String[] getTitles();

        int[] getSelect();

        int[] getSelected();

        void getData(Context context);
    }
}
