package com.timo.dream.ui.fragment

import android.view.View
import com.timo.base.BaseTools

import com.timo.base.base.base_fragment.BaseFragment
import com.timo.dream.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by 蔡永汪 on 2019/8/15.
 */

class Home4Fragment : BaseFragment() {
    override fun getContentResId(): Int {
        return R.layout.fragment_home
    }

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(title_dream, "我的")
    }
}
