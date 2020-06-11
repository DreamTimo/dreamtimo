package com.timo.dream.ui.activity.projectweb

import com.alibaba.android.arouter.facade.annotation.Route
import com.timo.base.BaseConstancts
import com.timo.base.BaseTools
import com.timo.base.route.RouteConstant
import com.timo.dream.R
import com.timo.dream.mvp.MVPBaseActivity
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.title.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = RouteConstant.tag_project)
class ProjectWebActivity : MVPBaseActivity<ProjectWebContract.View?, ProjectWebPresenter?>(), ProjectWebContract.View {
    override fun getContentResId(): Int {
        return R.layout.activity_project
    }

    override fun initEvent() {
        if (intent.getSerializableExtra(BaseConstancts.Params) == null) {
            BaseTools.setTitleBar(view_title, "fir项目展示") { finish() }
            BaseTools.loadWeb(view_webview, "https://fir.im")
        } else {
            BaseTools.setTitleBar(view_title, "码云项目展示") { finish() }
            BaseTools.loadWeb(view_webview, "https://gitee.com/androidworkmanager/projects")
        }
    }
}