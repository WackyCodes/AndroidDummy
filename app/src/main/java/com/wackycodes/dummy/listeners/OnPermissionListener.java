package com.wackycodes.dummy.listeners;

public interface OnPermissionListener {
    int PERMISSION_CODE_STORAGE = 1;
    int PERMISSION_CODE_LOCATION = 2;
    void onPermissionGranted( int PERMISSION_CODE );
}
