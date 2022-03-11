package com.wackycodes.dummy.listeners;

import androidx.fragment.app.Fragment;

public interface FragmentListener extends RootListener{
    void showNextFragment(Fragment fragment);
    void finishActivity();

}
