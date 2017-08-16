package com.nanda.crashreport.app;

import android.app.Application;

import com.nanda.crashreport.helper.CustomExceptionHandler;

/**
 * Created by Nandagopal on 8/16/2017.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new CustomExceptionHandler(this);

    }
}
