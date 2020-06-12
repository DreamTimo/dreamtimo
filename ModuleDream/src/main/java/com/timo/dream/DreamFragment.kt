package com.timo.dream

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.base.route.RouteConstant
import kotlinx.android.synthetic.main.fragment_dream.*

/**
 * Created by 蔡永汪 on 2019/8/15.
 */
@Route(path = RouteConstant.fragment_dream)
class DreamFragment : BaseFragment() {
    override fun getContentResId(): Int {
        return R.layout.fragment_dream
    }

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(view_title_dream, "app功能描述")
        view_webview.load("file:///android_asset/snow.html")
    }
}