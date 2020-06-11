package com.timo.dream;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

/**
 * Created by yangc on 2017/9/4.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class ImagePreviewLoader implements IZoomMediaLoader {

    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
        simpleTarget.onResourceReady();
        if (path.equals("1")){
            Glide.with(context)
                    .load(R.drawable.a1)
                    .into(imageView);
        }else if (path.equals("2")){
            Glide.with(context)
                    .load(R.drawable.a2)
                    .into(imageView);
        }else if (path.equals("3")){
            Glide.with(context)
                    .load(R.drawable.a3)
                    .into(imageView);
        }else if (path.equals("4")){
            Glide.with(context)
                    .load(R.drawable.a4)
                    .into(imageView);
        }else if (path.equals("5")){
            Glide.with(context)
                    .load(R.drawable.a5)
                    .into(imageView);
        }else if (path.equals("6")){
            Glide.with(context)
                    .load(R.drawable.a6)
                    .into(imageView);
        }else if (path.equals("7")){
            Glide.with(context)
                    .load(R.drawable.a7)
                    .into(imageView);
        }else if (path.equals("8")){
            Glide.with(context)
                    .load(R.drawable.a8)
                    .into(imageView);
        }else if (path.equals("9")){
            Glide.with(context)
                    .load(R.drawable.a9)
                    .into(imageView);
        }else if (path.equals("10")){
            Glide.with(context)
                    .load(R.drawable.a10)
                    .into(imageView);
        }else if (path.equals("11")){
            Glide.with(context)
                    .load(R.drawable.a11)
                    .into(imageView);
        }else if (path.equals("12")){
            Glide.with(context)
                    .load(R.drawable.a12)
                    .into(imageView);
        }else if (path.equals("13")){
            Glide.with(context)
                    .load(R.drawable.a13)
                    .into(imageView);
        }else if (path.equals("14")){
            Glide.with(context)
                    .load(R.drawable.a14)
                    .into(imageView);
        }else if (path.equals("15")){
            Glide.with(context)
                    .load(R.drawable.a15)
                    .into(imageView);
        }else if (path.equals("16")){
            Glide.with(context)
                    .load(R.drawable.a16)
                    .into(imageView);
        }else if (path.equals("17")){
            Glide.with(context)
                    .load(R.drawable.a17)
                    .into(imageView);
        }else if (path.equals("18")){
            Glide.with(context)
                    .load(R.drawable.a18)
                    .into(imageView);
        }else if (path.equals("19")){
            Glide.with(context)
                    .load(R.drawable.b1)
                    .into(imageView);
        }else if (path.equals("20")){
            Glide.with(context)
                    .load(R.drawable.b2)
                    .into(imageView);
        }else if (path.equals("21")){
            Glide.with(context)
                    .load(R.drawable.b3)
                    .into(imageView);
        }else if (path.equals("22")){
            Glide.with(context)
                    .load(R.drawable.b4)
                    .into(imageView);
        }else if (path.equals("23")){
            Glide.with(context)
                    .load(R.drawable.b5)
                    .into(imageView);
        }else if (path.equals("24")){
            Glide.with(context)
                    .load(R.drawable.c1)
                    .into(imageView);
        }else if (path.equals("25")){
            Glide.with(context)
                    .load(R.drawable.c2)
                    .into(imageView);
        }else if (path.equals("26")){
            Glide.with(context)
                    .load(R.drawable.c3)
                    .into(imageView);
        }else if (path.equals("27")){
            Glide.with(context)
                    .load(R.drawable.c4)
                    .into(imageView);
        }else if (path.equals("28")){
            Glide.with(context)
                    .load(R.drawable.c5)
                    .into(imageView);
        }else if (path.equals("29")){
            Glide.with(context)
                    .load(R.drawable.c6)
                    .into(imageView);
        }else if (path.equals("30")){
            Glide.with(context)
                    .load(R.drawable.c7)
                    .into(imageView);
        }else if (path.equals("31")){
            Glide.with(context)
                    .load(R.drawable.d1)
                    .into(imageView);
        }else if (path.equals("32")){
            Glide.with(context)
                    .load(R.drawable.d2)
                    .into(imageView);
        }else if (path.equals("33")){
            Glide.with(context)
                    .load(R.drawable.d3)
                    .into(imageView);
        }else if (path.equals("34")){
            Glide.with(context)
                    .load(R.drawable.d4)
                    .into(imageView);
        }else if (path.equals("40")){
            Glide.with(context)
                    .load(R.drawable.e1)
                    .into(imageView);
        }else if (path.equals("41")){
            Glide.with(context)
                    .load(R.drawable.e2)
                    .into(imageView);
        }else if (path.equals("42")){
            Glide.with(context)
                    .load(R.drawable.e3)
                    .into(imageView);
        }else if (path.equals("43")){
            Glide.with(context)
                    .load(R.drawable.e4)
                    .into(imageView);
        }else if (path.equals("44")){
            Glide.with(context)
                    .load(R.drawable.e5)
                    .into(imageView);
        }else if (path.equals("45")){
            Glide.with(context)
                    .load(R.drawable.e6)
                    .into(imageView);
        }else if (path.equals("46")){
            Glide.with(context)
                    .load(R.drawable.e7)
                    .into(imageView);
        }else if (path.equals("47")){
            Glide.with(context)
                    .load(R.drawable.e8)
                    .into(imageView);
        }else if (path.equals("48")){
            Glide.with(context)
                    .load(R.drawable.e9)
                    .into(imageView);
        }else if (path.equals("49")){
            Glide.with(context)
                    .load(R.drawable.e10)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }

    }

    @Override
    public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
        simpleTarget.onResourceReady();
        if (path.equals("1")){
            Glide.with(context)
                    .load(R.drawable.a1)
                    .into(imageView);
        }else if (path.equals("2")){
            Glide.with(context)
                    .load(R.drawable.a2)
                    .into(imageView);
        }else if (path.equals("3")){
            Glide.with(context)
                    .load(R.drawable.a3)
                    .into(imageView);
        }else if (path.equals("4")){
            Glide.with(context)
                    .load(R.drawable.a4)
                    .into(imageView);
        }else if (path.equals("5")){
            Glide.with(context)
                    .load(R.drawable.a5)
                    .into(imageView);
        }else if (path.equals("6")){
            Glide.with(context)
                    .load(R.drawable.a6)
                    .into(imageView);
        }else if (path.equals("7")){
            Glide.with(context)
                    .load(R.drawable.a7)
                    .into(imageView);
        }else if (path.equals("8")){
            Glide.with(context)
                    .load(R.drawable.a8)
                    .into(imageView);
        }else if (path.equals("9")){
            Glide.with(context)
                    .load(R.drawable.a9)
                    .into(imageView);
        }else if (path.equals("10")){
            Glide.with(context)
                    .load(R.drawable.a10)
                    .into(imageView);
        }else if (path.equals("11")){
            Glide.with(context)
                    .load(R.drawable.a11)
                    .into(imageView);
        }else if (path.equals("12")){
            Glide.with(context)
                    .load(R.drawable.a12)
                    .into(imageView);
        }else if (path.equals("13")){
            Glide.with(context)
                    .load(R.drawable.a13)
                    .into(imageView);
        }else if (path.equals("14")){
            Glide.with(context)
                    .load(R.drawable.a14)
                    .into(imageView);
        }else if (path.equals("15")){
            Glide.with(context)
                    .load(R.drawable.a15)
                    .into(imageView);
        }else if (path.equals("16")){
            Glide.with(context)
                    .load(R.drawable.a16)
                    .into(imageView);
        }else if (path.equals("17")){
            Glide.with(context)
                    .load(R.drawable.a17)
                    .into(imageView);
        }else if (path.equals("18")){
            Glide.with(context)
                    .load(R.drawable.a18)
                    .into(imageView);
        }else if (path.equals("19")){
            Glide.with(context)
                    .load(R.drawable.b1)
                    .into(imageView);
        }else if (path.equals("20")){
            Glide.with(context)
                    .load(R.drawable.b2)
                    .into(imageView);
        }else if (path.equals("21")){
            Glide.with(context)
                    .load(R.drawable.b3)
                    .into(imageView);
        }else if (path.equals("22")){
            Glide.with(context)
                    .load(R.drawable.b4)
                    .into(imageView);
        }else if (path.equals("23")){
            Glide.with(context)
                    .load(R.drawable.b5)
                    .into(imageView);
        }else if (path.equals("24")){
            Glide.with(context)
                    .load(R.drawable.c1)
                    .into(imageView);
        }else if (path.equals("25")){
            Glide.with(context)
                    .load(R.drawable.c2)
                    .into(imageView);
        }else if (path.equals("26")){
            Glide.with(context)
                    .load(R.drawable.c3)
                    .into(imageView);
        }else if (path.equals("27")){
            Glide.with(context)
                    .load(R.drawable.c4)
                    .into(imageView);
        }else if (path.equals("28")){
            Glide.with(context)
                    .load(R.drawable.c5)
                    .into(imageView);
        }else if (path.equals("29")){
            Glide.with(context)
                    .load(R.drawable.c6)
                    .into(imageView);
        }else if (path.equals("30")){
            Glide.with(context)
                    .load(R.drawable.c7)
                    .into(imageView);
        }else if (path.equals("31")){
            Glide.with(context)
                    .load(R.drawable.d1)
                    .into(imageView);
        }else if (path.equals("32")){
            Glide.with(context)
                    .load(R.drawable.d2)
                    .into(imageView);
        }else if (path.equals("33")){
            Glide.with(context)
                    .load(R.drawable.d3)
                    .into(imageView);
        }else if (path.equals("34")){
            Glide.with(context)
                    .load(R.drawable.d4)
                    .into(imageView);
        }else if (path.equals("40")){
            Glide.with(context)
                    .load(R.drawable.e1)
                    .into(imageView);
        }else if (path.equals("41")){
            Glide.with(context)
                    .load(R.drawable.e2)
                    .into(imageView);
        }else if (path.equals("42")){
            Glide.with(context)
                    .load(R.drawable.e3)
                    .into(imageView);
        }else if (path.equals("43")){
            Glide.with(context)
                    .load(R.drawable.e4)
                    .into(imageView);
        }else if (path.equals("44")){
            Glide.with(context)
                    .load(R.drawable.e5)
                    .into(imageView);
        }else if (path.equals("45")){
            Glide.with(context)
                    .load(R.drawable.e6)
                    .into(imageView);
        }else if (path.equals("46")){
            Glide.with(context)
                    .load(R.drawable.e7)
                    .into(imageView);
        }else if (path.equals("47")){
            Glide.with(context)
                    .load(R.drawable.e8)
                    .into(imageView);
        }else if (path.equals("48")){
            Glide.with(context)
                    .load(R.drawable.e9)
                    .into(imageView);
        }else if (path.equals("49")){
            Glide.with(context)
                    .load(R.drawable.e10)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }

    }

    @Override
    public void onStop(@NonNull Fragment context) {
        Glide.with(context).onStop();
    }

    @Override
    public void clearMemory(@NonNull Context c) {
        Glide.get(c).clearMemory();
    }
}
