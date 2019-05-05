package com.timo.dream.ui.fragment

import android.view.View
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.dream.R

import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by lykj on 2017/9/12.
 */

class DreamFragment : BaseFragment() {

    override fun getContentResId(): Int = R.layout.fragment_home

    override fun initEvent(view: View) = BaseTools.setTitleBar(title_dream, "梦想")

}
