package com.timo.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.timo.base.tools.logger.AndroidLogAdapter;
import com.timo.base.tools.logger.Logger;


/**
 * 全局单例Application
 */
public class BaseApplication extends Application {

    private Context mContext;    //全局上下文
    private static BaseApplication instance;      //全局应用对象

    /**
     * 程序的入口方法
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        //分包
        MultiDex.install(this);
        //加载log
        initLog();
    }

    private void initLog() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     */
    public static BaseApplication getInstance() {
        if (null == instance) {
            instance = new BaseApplication();
        }
        return instance;
    }

    /**
     * 得到上下文
     */
    public Context getContext() {
        return mContext;
    }
}
