package com.timo.dream.ui.activity.kotlinmain

import com.timo.base.BaseTools
import com.timo.base.view.tablayout.listener.OnTabSelectListener
import com.timo.dream.R
import com.timo.dream.mvp.MVPBaseActivity
import com.timo.dream.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

class KotlinMainActivity : MVPBaseActivity<KotlinMainContract.View, KotlinMainPresenter>(), KotlinMainContract.View {
    private var Fragment_0: Home0Fragment? = null
    private var Fragment_1: Home1Fragment? = null
    private var Fragment_2: Home2Fragment? = null
    private var Fragment_3: Home3Fragment? = null
    private var Fragment_4: Home4Fragment? = null
    override fun getContentResId(): Int = R.layout.activity_main
    override fun initEvent() {
        val transaction = supportFragmentManager.beginTransaction()
        val currentTabPosition = 0
        Fragment_0 = Home0Fragment()
        Fragment_1 = Home1Fragment()
        Fragment_2 = Home2Fragment()
        Fragment_3 = Home3Fragment()
        Fragment_4 = Home4Fragment()
        transaction.add(R.id.fragment, Fragment_0, "0")
        transaction.add(R.id.fragment, Fragment_1, "1")
        transaction.add(R.id.fragment, Fragment_2, "2")
        transaction.add(R.id.fragment, Fragment_3, "3")
        transaction.add(R.id.fragment, Fragment_4, "4")
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
                transaction.show(Fragment_0)
                transaction.hide(Fragment_1)
                transaction.hide(Fragment_2)
                transaction.hide(Fragment_3)
                transaction.hide(Fragment_4)
                transaction.commitAllowingStateLoss()
            }
            1 -> {
                transaction.hide(Fragment_0)
                transaction.show(Fragment_1)
                transaction.hide(Fragment_2)
                transaction.hide(Fragment_3)
                transaction.hide(Fragment_4)
                transaction.commitAllowingStateLoss()
            }
            2 -> {
                transaction.hide(Fragment_0)
                transaction.hide(Fragment_1)
                transaction.show(Fragment_2)
                transaction.hide(Fragment_3)
                transaction.hide(Fragment_4)
                transaction.commitAllowingStateLoss()
            }
            3 -> {
                transaction.hide(Fragment_0)
                transaction.hide(Fragment_1)
                transaction.hide(Fragment_2)
                transaction.show(Fragment_3)
                transaction.hide(Fragment_4)
                transaction.commitAllowingStateLoss()
            }
            4 -> {
                transaction.hide(Fragment_0)
                transaction.hide(Fragment_1)
                transaction.hide(Fragment_2)
                transaction.hide(Fragment_3)
                transaction.show(Fragment_4)
                transaction.commitAllowingStateLoss()
            }
            else -> {
            }
        }

    }
}

