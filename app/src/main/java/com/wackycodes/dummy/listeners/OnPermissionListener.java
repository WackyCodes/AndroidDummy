package com.wackycodes.dummy.listeners;

public interface OnPermissionListener {
    int PERMISSION_CODE_STORAGE = 1;
    int PERMISSION_CODE_LOCATION = 2;
    int PERMISSION_CODE_CAMERA = 3;

    void onPermissionGranted( int PERMISSION_CODE );
    void onPermissionGranted( int PERMISSION_CODE, boolean isGrant );

    boolean isStoragePermissionGranted();
    boolean isLocationPermissionGranted();
    boolean isCameraPermissionGranted();

    void requestForPermission( int permissionCode );
    void requestForcePermission( int permissionCode );

    interface OnPermissionDialogListener{
        void showPermissionDialog( int permissionCode, boolean isForcePermission );
        void acceptLaterAction( int permissionCode );
    }


}
