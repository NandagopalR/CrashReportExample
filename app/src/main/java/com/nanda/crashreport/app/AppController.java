package com.nanda.crashreport.app;

import android.app.Application;
import android.os.Environment;

import com.nanda.crashreport.helper.CustomExceptionHandler;

import java.io.File;

/**
 * Created by Nandagopal on 8/16/2017.
 */

public class AppController extends Application {

    private String errorLogPath;

    @Override
    public void onCreate() {
        super.onCreate();

        errorLogPath = Environment.getExternalStorageDirectory() + File.separator + AppConstants.LOG_DIRECTORY;
        new CustomExceptionHandler(this, errorLogPath);

    }
}
