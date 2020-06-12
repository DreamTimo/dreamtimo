package com.timo.home

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.base.route.RouteConstant
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by lykj on 2017/9/12.
 */
@Route(path = RouteConstant.fragment_home)
class HomeFragment : BaseFragment() {

    override fun getContentResId(): Int = R.layout.fragment_home

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(title_home, "个人首页") {
            if (view_webview.canGoBack()){
                view_webview.goBack()
            }
        }
        view_webview.load("file:///android_asset/my.html")
    }
}