package com.timo.dream.ui.activity.projectweb;


import android.view.View;

import com.timo.base.BaseConstancts;
import com.timo.base.BaseTools;
import com.timo.base.base.base_webview.CommonWebView;
import com.timo.base.view.TitleBar;
import com.timo.dream.R;
import com.timo.dream.mvp.MVPBaseActivity;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProjectWebActivity extends MVPBaseActivity<ProjectWebContract.View, ProjectWebPresenter> implements ProjectWebContract.View {

    CommonWebView mWebview;
    TitleBar      mTitle;

    @Override
    protected int getContentResId() {
        return R.layout.activity_project;
    }

    @Override
    protected void initEvent() {
        mWebview = findViewById(R.id.webview);
        mTitle = findViewById(R.id.title);
        BaseTools.setTitleBar(mTitle, "项目展示", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getSerializableExtra(BaseConstancts.Params) == null) {
            BaseTools.loadWeb(mWebview, "https://fir.im");
        } else {
            BaseTools.loadWeb(mWebview, "https://gitee.com/androidworkmanager/projects");
        }

    }

}
