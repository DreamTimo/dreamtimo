package com.timo.dream.ui.activity.kotlinmain;

import com.timo.dream.R;
import com.timo.dream.mvp.BasePresenterImpl;
/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KotlinMainPresenter extends BasePresenterImpl<KotlinMainContract.View> implements KotlinMainContract.Presenter {
    private String[] mTitles = {"首页", "梦想", "我的"};
    private int[] mIconSelectIds = {R.mipmap.icon_home_checked, R.mipmap.icon_dream_checked, R.mipmap.icon_mine_checked};
    private int[] mIconUnselectIds = {R.mipmap.icon_home_no_check, R.mipmap.icon_dream_no_check, R.mipmap.icon_mine_no_check};

    @Override
    public String[] getTitles() {
        return mTitles;
    }

    @Override
    public int[] getSelect() {
        return mIconUnselectIds;
    }

    @Override
    public int[] getSelected() {
        return mIconSelectIds;
    }

    public void getData() {
    }
}