package com.wackycodes.dummy.listeners;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public interface RootListener {

    void showToast(@Nullable String message);
    void showSnackMessage(@Nullable View view, @Nullable String message);
    void showDialog();
    void dismissDialog();

}
