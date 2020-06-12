package com.timo.dream

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.timo.base.route.RouteUtil

class MainActivity : AppCompatActivity() {
    private var myFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        myFragment = RouteUtil.getDreamFragment()
        if(myFragment!=null){
            transaction.add(R.id.fragment, myFragment!!, "0")
            transaction.show(myFragment!!)
            transaction.commit()
        }
    }
}
