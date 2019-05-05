package com.timo.dream.ui.fragment

import android.graphics.Color
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.webkit.WebViewClient
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.dream.R
import kotlinx.android.synthetic.main.fragment_dream.*

/**
 * Created by lykj on 2017/9/12.
 */

class HomeFragment : BaseFragment() {

    override fun getContentResId(): Int = R.layout.fragment_dream

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(title_home, "首页")
        drawlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        BaseTools.setTitleBar(web_title, "个人介绍") {
            if (web_mine.canGoBack()) {
                web_mine.goBack()
            } else {
                drawlayout.closeDrawers()
            }
        }

        drawlayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) = Unit

            override fun onDrawerOpened(drawerView: View) =
                    BaseTools.loadWeb(web_mine, "file:///android_asset/my.html")

            override fun onDrawerClosed(drawerView: View) = Unit

            override fun onDrawerStateChanged(newState: Int) = Unit
        })
        web_mine.setWebViewClient(object : WebViewClient() {})
    }

}
