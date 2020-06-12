package com.timo.mine

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.timo.base.route.RouteUtil

class MainActivity : AppCompatActivity() {
    private var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        mFragment = RouteUtil.getMineFragment()
        if(mFragment!=null){
            transaction.add(R.id.fragment, mFragment!!, "0")
            transaction.show(mFragment!!)
            transaction.commit()
        }
    }
}
