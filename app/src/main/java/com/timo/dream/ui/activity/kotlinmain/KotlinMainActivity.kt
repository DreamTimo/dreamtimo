package com.timo.dream.ui.activity.kotlinmain

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.timo.base.BaseTools
import com.timo.base.route.RouteConstant
import com.timo.base.route.RouteUtil
import com.timo.base.view.tablayout.listener.OnTabSelectListener
import com.timo.dream.R
import com.timo.dream.mvp.MVPBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = RouteConstant.activity_main)
class KotlinMainActivity : MVPBaseActivity<KotlinMainContract.View, KotlinMainPresenter>(), KotlinMainContract.View {
    private var Fragment_Dream: Fragment? = null
    private var Fragment_Home: Fragment? = null
    private var Fragment_Mine: Fragment? = null
    override fun getContentResId(): Int = R.layout.activity_main
    override fun initEvent() {
        val transaction = supportFragmentManager.beginTransaction()
        val currentTabPosition = 0
        Fragment_Home = RouteUtil.getHomeFragment()
        Fragment_Dream = RouteUtil.getDreamFragment()
        Fragment_Mine = RouteUtil.getMineFragment()
        transaction.add(R.id.fragment, Fragment_Home!!, "0")
        transaction.add(R.id.fragment, Fragment_Dream!!, "1")
        transaction.add(R.id.fragment, Fragment_Mine!!, "2")
        transaction.commit()
        showFragment(currentTabPosition)
        BaseTools.setNavigation(tab_layout, mPresenter.titles, mPresenter.select, mPresenter.selected, object : OnTabSelectListener {
            override fun onTabSelect(position: Int) = showFragment(position)

            override fun onTabReselect(position: Int) = Unit
        })
        tab_layout.currentTab = currentTabPosition
    }

    override fun isMain(): Boolean = true
    override fun showFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when (position) {
            0 -> {
                transaction.show(Fragment_Home!!)
                transaction.hide(Fragment_Dream!!)
                transaction.hide(Fragment_Mine!!)
                transaction.commitAllowingStateLoss()
            }
            1 -> {
                transaction.hide(Fragment_Home!!)
                transaction.show(Fragment_Dream!!)
                transaction.hide(Fragment_Mine!!)
                transaction.commitAllowingStateLoss()
            }
            2 -> {
                transaction.hide(Fragment_Dream!!)
                transaction.hide(Fragment_Home!!)
                transaction.show(Fragment_Mine!!)
                transaction.commitAllowingStateLoss()
                view_webview.load("file:///android_asset/trans.html")
            }
            else -> {
            }
        }
    }
}

