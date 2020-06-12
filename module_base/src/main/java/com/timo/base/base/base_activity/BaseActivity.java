package com.timo.base.base.base_activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.timo.base.BaseConstancts;
import com.timo.base.BaseTools;
import com.timo.base.R;
import com.timo.base.tools.permissions.PermissionUtils;
import com.timo.base.tools.permissions.permission_interface.BasePermissionInterface;
import com.timo.base.tools.permissions.permission_interface.PermissionGrantedListener;
import com.timo.base.tools.utils.math.RegexUtil;

import java.util.List;

/**
 * 预留：设置布局
 */

public abstract class BaseActivity extends SuperActivity implements BasePermissionInterface {
    private String tag_phone;

    public void toGetPermission(PermissionGrantedListener listener) {
        setPermissionListener(listener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_storage), BaseConstancts.requestCode_app, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
        } else {
            if (listener != null) {
                listener.onGranted();
            }
        }
    }

    @Override
    public void toOpenCamera(PermissionGrantedListener listener) {
        setPermissionListener(listener);
        if (PermissionUtils.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA)) {
            if (listener != null) {
                listener.onGranted();
            }
        } else {
            PermissionUtils.requestPermissions(this, getString(R.string.app_name), BaseConstancts.requestCode_camera, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void toOpenCallPhone(String phone) {
        if (TextUtils.isEmpty(phone) || !RegexUtil.getInstance().isTel(phone)) {
            BaseTools.showToast(getString(R.string.error_please_edit_right_phone));
            return;
        }
        this.tag_phone = phone;
        if (PermissionUtils.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        } else {
            PermissionUtils.requestPermissions(this, "需要拨打电话权限", BaseConstancts.requestCode_phone, Manifest.permission.CALL_PHONE);
        }
    }

    @Override
    public void toOpenSendSms(String phone) {
        if (TextUtils.isEmpty(phone) || !RegexUtil.getInstance().isTel(phone)) {
            BaseTools.showToast(getString(R.string.error_please_edit_right_phone));
            return;
        }
        this.tag_phone = phone;
        if (PermissionUtils.hasPermissions(this, Manifest.permission.SEND_SMS)) {
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + tag_phone));
            startActivity(intent);
        } else {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_sms), BaseConstancts.requestCode_sms, Manifest.permission.SEND_SMS);
        }
    }

    @Override
    public void toOpenStorage(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_storage), BaseConstancts.requestCode_storage, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenStorage() {
        toOpenStorage(null);
    }

    @Override
    public void toOpenCalendar(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.WRITE_CALENDAR)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_calendar), BaseConstancts.requestCode_calendar, Manifest.permission.WRITE_CALENDAR);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenCalendar() {
        toOpenCalendar(null);
    }

    @Override
    public void toOpenContacts(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.WRITE_CONTACTS)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_contacts), BaseConstancts.requestCode_contacts, Manifest.permission.WRITE_CONTACTS);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenContacts() {
        toOpenContacts(null);
    }

    @Override
    public void toOpenLocation(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_location), BaseConstancts.requestCode_location, Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenLocation() {
        toOpenLocation(null);
    }

    @Override
    public void toOpenAudio(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_audio), BaseConstancts.requestCode_audio, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenAudio() {
        toOpenAudio(null);
    }

    @Override
    public void toOpenSensors(PermissionGrantedListener grantedListener) {
        setPermissionListener(grantedListener);
        if (!PermissionUtils.hasPermissions(this, Manifest.permission.BODY_SENSORS)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_sensors), BaseConstancts.requestCode_sensors, Manifest.permission.BODY_SENSORS);
        } else {
            if (grantedListener != null) {
                grantedListener.onGranted();
            }
        }
    }

    @Override
    public void toOpenSensors() {
        toOpenSensors(null);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
    }

    public void showToast(String toast) {
        BaseTools.showToast(toast);
    }

    public void setText(TextView textView, String text) {
        BaseTools.setText(textView, text);
    }
}
