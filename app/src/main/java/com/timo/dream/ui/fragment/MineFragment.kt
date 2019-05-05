package com.timo.dream.ui.fragment

import android.text.Html
import android.view.View
import com.timo.base.BaseTools
import com.timo.base.base.base_fragment.BaseFragment
import com.timo.dream.R
import com.timo.dream.ui.activity.projectweb.ProjectWebActivity
import kotlinx.android.synthetic.main.fragment_mine.*

import java.util.ArrayList

/**
 * Created by lykj on 2017/9/12.
 */

class MineFragment : BaseFragment() {

    lateinit var data: ArrayList<String>

    override fun getContentResId(): Int = R.layout.fragment_mine

    override fun initEvent(view: View) {
        BaseTools.setTitleBar(title, "我的")
        data = ArrayList()
        data.add("http://aiwolvju.b0.upaiyun.com/uploads/20171211/fbfrebpymwaadvb9uckzmyb1iw81axet.tmp")
        data.add("http://aiwolvju.b0.upaiyun.com/uploads/20171211/fbfrebpymwaadvb9uckzmyb1iw81axet.tmp")
        data.add("http://aiwolvju.b0.upaiyun.com/uploads/20171211/fbfrebpymwaadvb9uckzmyb1iw81axet.tmp")
        data.add("http://aiwolvju.b0.upaiyun.com/uploads/20171211/fbfrebpymwaadvb9uckzmyb1iw81axet.tmp")
        val html = "<font color='#ff0000'>点击-></font><font color='#0000FF'>项目<font>"
        val charSequence = Html.fromHtml(html)
        textview.text = charSequence
        textview.setOnClickListener {
            startActivityNoFinish(ProjectWebActivity::class.java)
        }

    }
}
