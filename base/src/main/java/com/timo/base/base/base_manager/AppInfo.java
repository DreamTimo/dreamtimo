package com.timo.base.base.base_manager;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import com.timo.base.BaseApplication;
import com.timo.base.BaseConstancts;
import com.timo.base.tools.utils.SPUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

/**
 * 应用信息
 */

public class AppInfo {
    private AppInfo() {
    }

    /**
     * 修改log日志信息，设置是否进入后台运行
     *
     * @param log          是否开启log日志
     * @param logTag       log日志的tag
     * @param exit_to_back 是否退出到后台运行
     * @param exit_time    主页退出时间
     */
    public static void init(boolean log, String logTag, boolean exit_to_back, int exit_time) {
        BaseConstancts.log = log;
        BaseConstancts.TAG = logTag;
        BaseConstancts.exit_to_back = exit_to_back;
        BaseConstancts.exit_time = exit_time;
    }

    public static void init(boolean log, String logTag) {
        init(log, logTag, false, 2000);
    }

    public static void init(boolean log) {
        init(log, "timo", false, 2000);
    }

    /**
     * 得到应用程序包名
     *
     * @return
     */
    public static String getPackageName() {
        String packageName = BaseApplication.getInstance().getContext().getPackageName();
        return packageName;
    }

    /**
     * 获取版本信息
     *
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            String packageName = getPackageName();
            versionCode = BaseApplication.getInstance().getContext().getPackageManager().getPackageInfo(
                    packageName, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName() {
        String versionName = "";
        try {
            versionName = BaseApplication.getInstance().getContext().getPackageManager().getPackageInfo(
                    getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void setIsFirst() {
        SPUtils.getInstance().save(getVersionName(), false);
    }

    public static String apiHost() {
        String ahiHost = "";
        try {
            ApplicationInfo appInfo = BaseApplication.getInstance().getContext().getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            ahiHost = appInfo.metaData.getString("api_host");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ahiHost;
    }

    public static void setFirstFlag() {
        SPUtils.getInstance().save(getVersionName(), false);
    }

    public static boolean isFirst() {
        boolean isFirst = (boolean) SPUtils.getInstance().get(getVersionName(), true);
        return isFirst;
    }

    public static boolean isLogin(Class userBean) {
        return getUser(userBean) == null ? false : true;
    }

    /**
     * 注销登陆逻辑
     */
    public static void clearUser(Class userBean) {
        SPUtils.getInstance().save(userBean.getSimpleName(), "");
    }

    /**
     * 保存用户信息
     */
    public static void saveUser(Object userBean) {
        saveCacheData(userBean);
    }

    /**
     * 保存数据
     */

    public static void saveBeanData(Object data) {
        saveUser(data);
    }

    /**
     * 保存数据
     */

    public static void clearData(Class data) {
        clearUser(data);
    }

    /**
     * 获取用户信息
     */
    public static Object getBeanData(Class data) {
        return getUser(data);
    }


    /**
     * 保存密码
     */
    public static void savePass(Object pass) {
        saveUser(pass);
    }

    public static Object getPass(Class clazz) {
        return getUser(clazz);
    }

    /**
     * 获取用户信息
     */
    public static Object getUser(Class userBean) {
        return getCacheData(userBean.getSimpleName());
    }

    /**
     * 保存缓存数据
     */
    public static void saveCacheData(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String objString = new String(Base64.encode(
                    byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            SPUtils.getInstance().save(obj.getClass().getSimpleName(), objString);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存数据
     */
    public static Object getCacheData(String key) {
        Object obj = null;
        try {
            String str = SPUtils.getInstance().get(key, "").toString();
            if (str.length() <= 0)
                return null;
            obj = null;
            byte[] mobileBytes = Base64.decode(str.toString().getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    mobileBytes);
            ObjectInputStream objectInputStream;
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {

        }
        return obj;
    }

    /**
     * 描述: 打开App
     *
     * @param packageName 包名
     */
    public static void startApp(String packageName) {
        if (TextUtils.isEmpty(packageName)) return;
        BaseApplication.getInstance().getContext().startActivity(BaseApplication.getInstance().getContext().getPackageManager().getLaunchIntentForPackage(packageName));
    }

    /**
     * 是否安装了指定包名的App
     *
     * @param packageName App包名
     * @return
     */
    public static boolean isInstallApp(String packageName) {
        PackageManager manager = BaseApplication.getInstance().getContext().getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo info = pkgList.get(i);
            if (info.packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * 描述：打开并安装文件.
     *
     * @param file apk文件路径
     */
    public static void installApk(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        BaseApplication.getInstance().getContext().startActivity(intent);
    }

    /**
     * 描述：卸载程序.
     *
     * @param packageName 包名
     */
    public static void uninstallApk(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        BaseApplication.getInstance().getContext().startActivity(intent);
    }

    /**
     * need < uses-permission android:name ="android.permission.GET_TASKS"/>
     * <p>
     * 判断是否前台运行
     * <p>
     * 之前，使用该接口需要 android.permission.GET_TASKS
     * 即使是自己开发的普通应用，只要声明该权限，即可以使用getRunningTasks接口。
     * 但从L开始，这种方式以及废弃。
     * 应用要使用该接口必须声明权限android.permission.REAL_GET_TASKS
     * 而这个权限是不对三方应用开放的。（在Manifest里申请了也没有作用）
     * 系统应用（有系统签名）可以调用该权限。
     */
    public static boolean isRunningForeground() {
        ActivityManager am = (ActivityManager) BaseApplication.getInstance().getContext().getSystemService(BaseApplication.getInstance().getContext().ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(BaseApplication.getInstance().getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param className 判断的服务名字 "com.xxx.xx..XXXService"
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) BaseApplication.getInstance().getContext().getSystemService(BaseApplication.getInstance().getContext().ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = (ActivityManager.RunningServiceInfo) l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }
        return isRunning;
    }

    /**
     * 停止服务.
     *
     * @param className the class name
     * @return true, if successful
     */
    public static boolean stopRunningService(String className) {
        Intent intent = null;
        boolean ret = false;
        try {
            intent = new Intent(BaseApplication.getInstance().getContext(), Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent != null) {
            ret = BaseApplication.getInstance().getContext().stopService(intent);
        }
        return ret;
    }

    /**
     * 获取PackageInfo
     *
     * @return PackageInfo
     */
    public static PackageInfo getPackageInfo() {
        PackageManager packageManager = BaseApplication.getInstance().getContext().getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(BaseApplication.getInstance().getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取版本名称
     * String
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        return getPackageInfo().versionName;
    }

    /**
     * 获取版本号
     * int
     *
     * @return 当前应用的版本号
     */
    public int getVersionCode(Context context) {
        return getPackageInfo().versionCode;
    }

    /**
     * 获取应用签名
     *
     * @param pkgName 包名
     * @return 返回应用的签名
     */
    public static String getSign(String pkgName) {
        try {
            PackageInfo pis = BaseApplication.getInstance().getContext().getPackageManager()
                    .getPackageInfo(pkgName,
                            PackageManager.GET_SIGNATURES);
            return hexDigest(pis.signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将签名字符串转换成需要的32位签名
     *
     * @param paramArrayOfByte 签名byte数组
     * @return 32位签名字符串
     */
    private static String hexDigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
                98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0; ; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0   支持4.1.2,4.1.23.4.1.rc111这种形式
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion xloading_error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

}