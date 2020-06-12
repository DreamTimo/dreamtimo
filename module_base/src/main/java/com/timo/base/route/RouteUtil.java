package com.timo.base.route;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 路由工具类
 */
public class RouteUtil {
    public static void start(String config) {
        ARouter.getInstance().build(config).navigation();
    }

    public static void to_project_github() {
        ARouter.getInstance().build(RouteConstant.tag_project).navigation();
    }
    public static void to_project_mayun() {
        ARouter.getInstance().build(RouteConstant.tag_project).withString("Params","码云").navigation();
    }
    public static Fragment getHomeFragment() {
       return (Fragment)ARouter.getInstance().build(RouteConstant.fragment_home).navigation();
    }
    public static Fragment getDreamFragment() {
        return (Fragment)ARouter.getInstance().build(RouteConstant.fragment_dream).navigation();
    }
    public static Fragment getMineFragment() {
        return (Fragment)ARouter.getInstance().build(RouteConstant.fragment_mine).navigation();
    }


}
