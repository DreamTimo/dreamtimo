package com.timo.base.base.base_fragment;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.timo.base.tools.permissions.PermissionUtils;
import com.timo.base.tools.permissions.permission_interface.PermissionGrantedListener;

/**
 * Created by lykj on 2017/9/10.
 */

public abstract class SuperFragment extends Fragment implements PermissionUtils.PermissionCallbacks {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent(view);
    }

    protected abstract int getContentResId();

    protected abstract void initEvent(View view);

    /**
     * 请求权限的返回值：不操作
     */
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, getPermissionlistener(), permissions, grantResults);
    }

    private PermissionGrantedListener mListener;

    public void setPermissionListener(PermissionGrantedListener listener) {
        mListener = listener;
    }

    private PermissionGrantedListener getPermissionlistener() {
        return mListener;
    }
}
