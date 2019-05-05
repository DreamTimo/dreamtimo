package com.timo.base.base.base_fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.timo.base.BaseConstancts;
import com.timo.base.BaseTools;
import com.timo.base.R;
import com.timo.base.tools.permissions.PermissionUtils;
import com.timo.base.tools.permissions.permission_interface.BasePermissionInterface;
import com.timo.base.tools.permissions.permission_interface.PermissionGrantedListener;
import com.timo.base.tools.utils.math.RegexUtil;

import java.util.List;

/**
 * Created by 45590 on 2018/7/13.
 */

public abstract class BaseFragment extends SuperFragment implements BasePermissionInterface {
    private String tag_phone;
    private PermissionGrantedListener mStorageListener;
    private PermissionGrantedListener mCalendarListener;
    private PermissionGrantedListener mContactsListener;
    private PermissionGrantedListener mLocationListener;
    private PermissionGrantedListener mAudioListener;
    private PermissionGrantedListener mSensorsListener;
    private PermissionGrantedListener mCameraListener;

    @Override
    public void toOpenCamera(PermissionGrantedListener listener) {
        this.mCameraListener = listener;
        setPermissionListener(listener);
        if (PermissionUtils.hasPermissions(getActivity(), Manifest.permission.CAMERA)) {
//            startActivityForResult(new Intent(getActivity(), CameraActivity.class), BaseConstancts.requestCode_camera);
            if (mCameraListener != null) {
                mCameraListener.onGranted();
            }
        } else {
            PermissionUtils.requestPermissions(this, getString(R.string.app_name), BaseConstancts.requestCode_camera, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void toOpenCallPhone(String phone) {
        if (TextUtils.isEmpty(phone) || !RegexUtil.getInstance().isTel(phone)) {
            BaseTools.showToast(getString(R.string.error_please_edit_right_phone));
            return;
        }
        this.tag_phone = phone;
        if (PermissionUtils.hasPermissions(getActivity(), Manifest.permission.CALL_PHONE)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(intent);
        } else {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_call_phone), BaseConstancts.requestCode_phone, Manifest.permission.CALL_PHONE);
        }
    }

    @Override
    public void toOpenSendSms(String phone) {
        if (TextUtils.isEmpty(phone) || !RegexUtil.getInstance().isTel(phone)) {
            BaseTools.showToast(getString(R.string.error_please_edit_right_phone));
            return;
        }
        this.tag_phone = phone;
        if (PermissionUtils.hasPermissions(getActivity(), Manifest.permission.SEND_SMS)) {
            Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + tag_phone));
            startActivity(intent);
        } else {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_sms), BaseConstancts.requestCode_sms, Manifest.permission.SEND_SMS);
        }
    }

    @Override
    public void toOpenStorage(PermissionGrantedListener grantedListener) {
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_storage), BaseConstancts.requestCode_storage, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            this.mStorageListener = grantedListener;
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
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.WRITE_CALENDAR)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_calendar), BaseConstancts.requestCode_calendar, Manifest.permission.WRITE_CALENDAR);
            this.mCalendarListener = grantedListener;
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
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.WRITE_CONTACTS)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_contacts), BaseConstancts.requestCode_contacts, Manifest.permission.WRITE_CONTACTS);
            this.mContactsListener = grantedListener;
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
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_location), BaseConstancts.requestCode_location, Manifest.permission.ACCESS_FINE_LOCATION);
            this.mLocationListener = grantedListener;
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
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.RECORD_AUDIO)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_audio), BaseConstancts.requestCode_audio, Manifest.permission.RECORD_AUDIO);
            this.mAudioListener = grantedListener;
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
        if (!PermissionUtils.hasPermissions(getActivity(), Manifest.permission.BODY_SENSORS)) {
            PermissionUtils.requestPermissions(this, getString(R.string.permission_sensors), BaseConstancts.requestCode_sensors, Manifest.permission.BODY_SENSORS);
            this.mSensorsListener = grantedListener;
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
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == BaseConstancts.requestCode_phone) {
            toOpenCallPhone(tag_phone);
        } else if (requestCode == BaseConstancts.requestCode_camera) {
            toOpenCamera(mCameraListener);
        } else if (requestCode == BaseConstancts.requestCode_sms) {
            toOpenSendSms(tag_phone);
        } else if (requestCode == BaseConstancts.requestCode_audio) {
            toOpenAudio(mAudioListener);
        } else if (requestCode == BaseConstancts.requestCode_calendar) {
            toOpenCalendar(mCalendarListener);
        } else if (requestCode == BaseConstancts.requestCode_location) {
            toOpenLocation(mLocationListener);
        } else if (requestCode == BaseConstancts.requestCode_contacts) {
            toOpenContacts(mContactsListener);
        } else if (requestCode == BaseConstancts.requestCode_sensors) {
            toOpenSensors(mSensorsListener);
        } else if (requestCode == BaseConstancts.requestCode_storage) {
            toOpenStorage(mStorageListener);
        }
    }
}
