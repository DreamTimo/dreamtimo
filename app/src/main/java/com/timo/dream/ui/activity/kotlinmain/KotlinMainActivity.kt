package com.timo.dream.ui.activity.kotlinmain

import com.timo.base.BaseTools
import com.timo.base.view.tablayout.listener.OnTabSelectListener
import com.timo.dream.Constants
import com.timo.dream.R
import com.timo.dream.mvp.MVPBaseActivity
import com.timo.dream.ui.fragment.DreamFragment
import com.timo.dream.ui.fragment.HomeFragment
import com.timo.dream.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class KotlinMainActivity : MVPBaseActivity<KotlinMainContract.View, KotlinMainPresenter>(), KotlinMainContract.View {
    private var mHomeFragment: HomeFragment? = null
    private var mDreamFragment: DreamFragment? = null
    private var mMineFragment: MineFragment? = null
    override fun getContentResId(): Int = R.layout.activity_main
    override fun initEvent() {
        val transaction = supportFragmentManager.beginTransaction()
        val currentTabPosition = 0
        mHomeFragment = HomeFragment()
        mDreamFragment = DreamFragment()
        mMineFragment = MineFragment()
        transaction.add(R.id.fragment, mHomeFragment, Constants.homeFragment)
        transaction.add(R.id.fragment, mDreamFragment, Constants.dreamFragment)
        transaction.add(R.id.fragment, mMineFragment, Constants.mineFragment)
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
        mPresenter.getData()
        val transaction = supportFragmentManager.beginTransaction()
        when (position) {
            0 -> {
                transaction.hide(mDreamFragment)
                transaction.hide(mMineFragment)
                transaction.show(mHomeFragment)
                transaction.commitAllowingStateLoss()
                BaseTools.loadWeb(test_webview, "file:///android_asset/snow.html")
            }
            1 -> {
                transaction.hide(mMineFragment)
                transaction.hide(mHomeFragment)
                transaction.show(mDreamFragment)
                transaction.commitAllowingStateLoss()
                BaseTools.loadWeb(test_webview, "file:///android_asset/sakura.html")
            }
            2 -> {
                transaction.hide(mHomeFragment)
                transaction.hide(mDreamFragment)
                transaction.show(mMineFragment)
                transaction.commitAllowingStateLoss()
                BaseTools.loadWeb(test_webview, "file:///android_asset/snow.html")
            }
            else -> {
            }
        }

    }
}

