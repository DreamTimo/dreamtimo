package com.timo.dream.ui.activity.kotlinmain;

import android.content.Context;

import com.timo.dream.R;
import com.timo.dream.bean.api.GetDataService;
import com.timo.dream.mvp.BasePresenterImpl;
import com.timo.httplib.network.MyHttp;
import com.timo.httplib.network.MySubscriber_onError;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KotlinMainPresenter extends BasePresenterImpl<KotlinMainContract.View> implements KotlinMainContract.Presenter {
    private String[] mTitles          = {"起航", "中心", "我的"};
    private int[]    mIconSelectIds   = {R.mipmap.icon_home_checked, R.mipmap.icon_dream_checked, R.mipmap.icon_mine_checked};
    private int[]    mIconUnselectIds = {R.mipmap.icon_home_no_check, R.mipmap.icon_dream_no_check, R.mipmap.icon_mine_no_check};

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

    @Override
    public void getData(Context context) {
        addSubscription(MyHttp.getStringApi(context, GetDataService.class).getData(), new MySubscriber_onError<String>(context, "加载", true) {
            @Override
            protected void _onNext(String o) {
                showToast("1111111111111111"+o);
            }

            @Override
            protected void _onError(String message) {
                showToast(".............."+message);
            }
        });
    }
}
