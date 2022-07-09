package com.wackycodes.dummy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wackycodes.dummy.R;
import com.wackycodes.dummy.other.SharedPref;

import java.util.Timer;
import java.util.TimerTask;

/*******************************************************************************
 * Wackycodes Design & Development - Copyright (c) 2022.
 *
 *  This file created by Shailendra Lodhi  on  02/03/22, 2:47 PM
 *  Check : https://linktr.ee/wackycodes
 *  -------------------------------------------------------------------------------------------------
 *  File Name : ActivitySplash.java
 *  Description :
 *  --------------------------  Updates History -----------------------------------------------------
 *  S.No. -|-  Updated By -|- Updated Date -|- Remarks --
 *  1.    -    Shailendra    -   2/3/2022   -   File Created
 *
 ******************************************************************************/

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (SharedPref.isUserLogin( ActivitySplash.this)){
                    startActivity( new Intent( ActivitySplash.this, ActivityRootMain.class ));
                }else{
                    startActivity( new Intent( ActivitySplash.this, ActivityRootAuth.class ));
                }
                ActivitySplash.this.finish();
            }
        }, 2500);

    }


}