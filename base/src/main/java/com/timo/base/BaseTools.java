package com.timo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.timo.base.view.dialog.DialogListener;
import com.timo.base.view.CommonWebView;
import com.timo.base.tools.logger.Logger;
import com.timo.base.tools.utils.DialogUtils;
import com.timo.base.tools.utils.ScreenUtils;
import com.timo.base.tools.utils.ToastUtils;
import com.timo.base.tools.utils.math.MathUtils;
import com.timo.base.view.TitleBar;
import com.timo.base.view.tablayout.CommonTabLayout;
import com.timo.base.view.tablayout.TabEntity;
import com.timo.base.view.tablayout.listener.CustomTabEntity;
import com.timo.base.view.tablayout.listener.OnTabSelectListener;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 常用工具
 */
public class BaseTools {
    public BaseTools() {
    }

    public static void e(String msg) {
        if (BaseConstancts.log) {
            Log.e(BaseConstancts.TAG, msg);
        }
    }

    public static void e(Exception e) {
        if (BaseConstancts.log) {
            e.printStackTrace();
        }
    }

    public static void e(Throwable e) {
        if (BaseConstancts.log) {
            e.printStackTrace();
        }
    }

    public static void log(Object bean) {
        if (BaseConstancts.log) {
            Logger.json(new Gson().toJson(bean));
        }
    }

    public static void log(String log) {
        if (BaseConstancts.log) {
            Logger.log(Logger.ERROR, BaseConstancts.TAG, log, null);
        }
    }

    public static void logJson(String json) {
        if (BaseConstancts.log) {
            Logger.json(json);
        }
    }

    public static String getToken(String token) {
        return "Bearer " + token;
    }


    /**
     * 常用工具
     */
    public static int px2dp(float pxValue) {
        return MathUtils.getInstance().px2dp(pxValue);
    }

    public static int dp2px(float pxValue) {
        return MathUtils.getInstance().dp2px(pxValue);
    }

    public static int px2sp(float pxValue) {
        return MathUtils.getInstance().px2sp(pxValue);
    }

    public static int sp2px(float pxValue) {
        return MathUtils.getInstance().sp2px(pxValue);
    }

    public static <T> T getRandom(List<T> readomWords) {
        return MathUtils.getInstance().getRandom(readomWords);
    }

    public static int getRandomInt(int start, int end) {
        return MathUtils.getInstance().getRandomInt(start, end);
    }
    /**
     * 设置TitleBar
     */
    public static void setTitleBar(TitleBar titleBar, String title, View.OnClickListener leftClick, TitleBar.TextAction rightAction) {
        if (leftClick != null) {
            titleBar.setLeftImageResource(R.drawable.ic_back);
            titleBar.setLeftClickListener(leftClick);
        }
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle(title);
        titleBar.setTitleColor(Color.parseColor("#333333"));
        titleBar.setActionTextColor(Color.WHITE);
        if (rightAction != null) {
            titleBar.addAction(rightAction);
        }
    }

    /**
     * 设置TitleBar
     */
    public static void setTitleBar(TitleBar titleBar, String title, View.OnClickListener leftClick) {
        setTitleBar(titleBar, title, leftClick, null);
    }

    public static void setTitleBar(TitleBar titleBar, String title) {
        setTitleBar(titleBar, title, null, null);
    }

    public static void loadWeb(CommonWebView webView, String url) {
        if (webView == null || TextUtils.isEmpty(url)) {
            return;
        }
        webView.setIsShowLoading(false);
        webView.load(url);
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneInfo(Context context) {
        TelephonyManager                          mTm   = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
         String imei  = mTm.getDeviceId();
        String                                    imsi  = mTm.getSubscriberId();
        String                                    mtype = android.os.Build.MODEL; // 手机型号
        String                                    mtyb  = android.os.Build.BRAND;//手机品牌
        String                                    numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return "手机IMEI号：" + imei + "手机IESI号：" + imsi + "手机型号：" + mtype + "手机品牌：" + mtyb + "手机号码" + numer;
    }

    /**
     * 选择对话框：确定、取消
     */
    public static void showTwoChoiceDialog(Context context, String description, final DialogListener listener) {
        DialogUtils.getInstance().showTwoChoiceDialog(context, description, listener);
    }
    /**
     * 设置底部导航
     */
    public static void setNavigation(CommonTabLayout mTabLayout, String[] titles, int[] noSelectPic, int[] selectedPic, OnTabSelectListener listener) {
        if (titles.length != noSelectPic.length || noSelectPic.length != selectedPic.length) {
            BaseTools.showToast(getString(R.string.error_navigation_datas));
            return;
        }
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], selectedPic[i], noSelectPic[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        //点击监听
        if (listener != null) {
            mTabLayout.setOnTabSelectListener(listener);
        }
    }

    /**
     * 获取资源文件字符串信息
     */
    public static String getString(int id) {
        return BaseApplication.getInstance().getString(id);
    }

    /**
     * 获取Camera图片路径
     */
    public static String getCameraPicture(int resultCode, Intent data) {
        if (resultCode == 101 || resultCode == 102) {
            return data.getStringExtra("path");
        } else if (resultCode == 103) {
            showToast("请检查相机权限~");
        }
        return null;
    }

    /**
     * 设置TextView图片
     */
    public static void setTextViewDrawable(int resourceId, TextView view) {
        setTextViewDrawable(resourceId, view, 1);
    }

    /**
     * 设置TextView图片
     *
     * @param location 默认为1；1:左 2:右 3:上 4:下
     */
    public static void setTextViewDrawable(int resourceId, TextView view, int location) {
        Drawable drawable = BaseApplication.getInstance().getContext().getResources().getDrawable(resourceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (location == 1) {
            view.setCompoundDrawables(drawable, null, null, null);
        } else if (location == 2) {
            view.setCompoundDrawables(null, null, drawable, null);
        } else if (location == 3) {
            view.setCompoundDrawables(null, drawable, null, null);
        } else if (location == 4) {
            view.setCompoundDrawables(null, null, null, drawable);
        }
    }

    /**
     * 获取布局
     */
    public static View getView(Context context, int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    /**
     * 获取布局,包括父布局
     *
     * @param parent：父布局
     */
    public static View getView(Context context, int layoutId, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutId, parent, false);
    }

    /**
     * 获取Data时间
     */
    public static Date getData(String str) {
        return getData(str, BaseApplication.getInstance().getString(R.string.data_format));
    }

    public static Date getData(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Date parse = null;
        try {
            parse = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 设置文字
     *
     * @param textView:TextView
     * @param text:设置的文本，为空默认不设置
     */
    public static void setText(TextView textView, String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    /**
     * 设置文字
     *
     * @param textView:TextView
     * @param text:设置的文本
     * @param defaltText:默认文本;设置的文本为空则设置默认文本
     */
    public static void setText(TextView textView, String text, String defaltText) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText(defaltText);
        }
    }

    /**
     * Toast调用
     */
    public static void showToast(int resourceId) {
        if (isEmpty(BaseApplication.getInstance().getString(resourceId))) return;
        showToast(BaseApplication.getInstance().getString(resourceId));
    }

    /**
     * Toast调用
     */
    public static void showFragment(int currentPosition, int position, int containerId, Class[] fragments, FragmentManager fm) {

    }

    /**
     * Toast调用
     */
    public static void showToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().info(msg, Toast.LENGTH_SHORT, true, false);
    }

    public static void showSuccessToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().success(msg, Toast.LENGTH_SHORT, true, false);
    }

    public static void showErrorToast(String msg) {
        if (isEmpty(msg)) return;
        ToastUtils.getInstance().error(msg, Toast.LENGTH_SHORT, true, false);
    }

    /**
     * 获取手机号码
     */
    public static String getPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    public static String getPhone(String phone, int startIndex, int endIndex, String replaceString) {
        return phone.substring(0, startIndex) + replaceString + phone.substring(endIndex);
    }

    /**
     * 时间戳转换成字符串类型的时间
     */

    public static String getTime(String str, String formatString) {
        Long timestamp = Long.parseLong(str) * 1000;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(new Date(timestamp));
        return datetime;
    }

    public static String getTime(String str) {
        return getTime(str, BaseApplication.getInstance().getString(R.string.data_format));
    }

    /**
     * 时间戳转换成字符串类型的时间
     */
    public static String getTime(long time, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(new Date(time));
        return datetime;
    }

    public static String getTime(long time) {
        return getTime(time, BaseApplication.getInstance().getString(R.string.data_format));
    }

    /**
     * 时间戳转换成字符串类型的时间
     */
    public static String getTime(Date date, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String datetime = format.format(date);
        return datetime;
    }

    public static String getTime(Date date) {
        return getTime(date, BaseApplication.getInstance().getString(R.string.data_format));
    }

    public static Bitmap getScaleBitmap(Bitmap bitMap, int newWidth, int newHeight) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        return newBitMap;
    }

    public static byte[] getPixelsBGR(Bitmap image) {
        int bytes = image.getByteCount();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        image.copyPixelsToBuffer(buffer);
        byte[] temp = buffer.array();
        byte[] pixels = new byte[(temp.length / 4) * 3];
        for (int i = 0; i < temp.length / 4; i++) {

            pixels[i * 3] = temp[i * 4 + 2];
            pixels[i * 3 + 1] = temp[i * 4 + 1];
            pixels[i * 3 + 2] = temp[i * 4];
        }
        return pixels;
    }

    /**
     * 获取星期
     */
    public static String getWeek(int type) {
        if (type == 1) {
            return "星期日";
        } else if (type == 2) {
            return "星期一";
        } else if (type == 3) {
            return "星期二";
        } else if (type == 4) {
            return "星期三";
        } else if (type == 5) {
            return "星期四";
        } else if (type == 6) {
            return "星期五";
        } else if (type == 7) {
            return "星期六";
        }
        return null;
    }

    /**
     * 非空检验
     */
    public static boolean isNotEmpty(Object obj) {
        return MathUtils.getInstance().isNotEmpty(obj);
    }

    public static void load(Context context, String url, ImageView view) {
        if (BaseTools.isEmpty(url) || view == null) return;

        if (url.startsWith("http")) {
            Glide
                    .with(context)
                    .load(url)
                    .into(view);
        } else {
            Glide
                    .with(context)
                    .load("http://" + url)
                    .into(view);
        }

    }


    public static void load(Context context, URL url, ImageView view) {
        Glide
                .with(context)
                .load(url)
                .into(view);
    }

    public static void load(Context context, Bitmap bitmap, ImageView view) {
        Glide
                .with(context)
                .load(bitmap)
                .into(view);
    }

    public static void load(Context context, File file, ImageView view) {
        Glide
                .with(context)
                .load(file)
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view) {
        Glide
                .with(context)
                .load(resourceId)
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view, int defaultResId) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultResId)
                .error(defaultResId)
                .priority(Priority.HIGH);
        Glide
                .with(context)
                .setDefaultRequestOptions(options)
                .load(resourceId)
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view, int defaultResId, int errorResId) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultResId)
                .error(errorResId)
                .priority(Priority.HIGH);
        Glide
                .with(context)
                .setDefaultRequestOptions(options)
                .load(resourceId)
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view, RequestOptions options) {
        Glide
                .with(context)
                .setDefaultRequestOptions(options)
                .load(resourceId)
                .into(view);
    }

    /**
     * 空检验
     */
    public static boolean isEmpty(Object obj) {
        return !MathUtils.getInstance().isNotEmpty(obj);
    }

    /**
     * 获取距离
     */
    public static String getDistance(String lat1, String lng1, double lat2, double lng2) {
        String distance = "";
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            double lat = Math.abs(Double.valueOf(lat1) - lat2);
            double lng = Math.abs(Double.valueOf(lng1) - lng2);
            distance = df.format(Math.sqrt(lat * lat + lng * lng) * 100);
        } catch (Exception e) {
            e(e);
        }
        return distance;
    }

    /**
     * 获取视频的第一帧
     *
     * @param filePath
     * @return
     */
    public static Bitmap getVideoFirshBitmap(String filePath) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(filePath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * 获取mate信息
     */
    public static String getMeta(String name) {
        final Context context = BaseApplication.getInstance().getContext();
        String str = "";

        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object v = ai.metaData.get(name);
            if (v != null) {
                str = v.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return str;
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     */
    public static String getBankCard(String card) {
        return card.substring(0, 4) + "***********" + card.substring(15);
    }

    /**
     * 身份证号，中间10位星号替换
     */
    public static String getIdentity(String card) {
        return card.substring(0, 4) + "**********" + card.substring(14);
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight() {
        return ScreenUtils.getInstance().getScreenHeight();
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        return ScreenUtils.getInstance().getScreenWidth();
    }

    /**
     * 关闭流资源
     */
    public static boolean closeIo(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e(e);
            }
        }
        return true;
    }

    /**
     * 获取fragment实例
     *
     * @param clazz
     * @return
     */
    public static synchronized Fragment getFragment(Class<? extends Fragment> clazz) {
        Fragment fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    /**
     * bitmap保存成文件
     *
     * @param bmp
     * @param bitName
     * @return
     * @throws IOException
     */
    public boolean saveBitmap(Bitmap bmp, String bitName) throws IOException {
        boolean flag = false;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String sdpath = Environment.getExternalStorageDirectory() + "/";
            String mSavePath = sdpath + "lzrc/imgs";
            File f = new File(mSavePath, bitName);
            f.createNewFile();
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(f);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
