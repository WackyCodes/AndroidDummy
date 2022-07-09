package com.wackycodes.dummy.listeners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  11/03/22, 4:36 PM
 *  Check : https://linktr.ee/wackycodes
 *  ===========================================================
 *  File Name : ActivityListener.java
 *  Description :
 *  ======================   Updates History    ========================
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks
 *  1.    -    Shailendra    -   11/3/2022   -   File Created
 *
 ******************************************************************************/

public interface ActivityListener extends RootListener {
    void setToolBar(Toolbar toolbar, @Nullable String title, boolean isHomesUpEnabled );
    void setPageTitle( @NonNull String title );
    void setSubTitle( @NonNull String subTitle );
}
