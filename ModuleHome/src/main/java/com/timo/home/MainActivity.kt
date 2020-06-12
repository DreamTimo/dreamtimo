package com.timo.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.timo.base.route.RouteUtil

class MainActivity : AppCompatActivity() {
    private var Fragment_Home: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        Fragment_Home = RouteUtil.getHomeFragment()
        if(Fragment_Home!=null){
            transaction.add(R.id.fragment, Fragment_Home!!, "0")
            transaction.show(Fragment_Home!!)
            transaction.commit()
        }
    }
}
