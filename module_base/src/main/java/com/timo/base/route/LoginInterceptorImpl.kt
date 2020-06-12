package com.timo.dream.route

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor


/**
 * ARouter登录拦截
 */
@Interceptor(name = "login", priority = 6)
class LoginInterceptorImpl : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        val path = postcard.path
        Log.d("ARouter","LoginInterceptorImpl $path")
        if (true){
            callback.onContinue(postcard)
        }else{
            callback.onInterrupt(null)
        }
    }

    /**
     * 初始化方法
     * 只会走一次
     */
    override fun init(context: Context?) {
        Log.d("ARouter","路由登录拦截器初始化成功")
    }
}