package com.nanda.crashreport.helper;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.nanda.crashreport.app.AppConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Nandagopal on 7/30/2017.
 */

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final static String TAG = CustomExceptionHandler.class.getSimpleName();
    private final Context context;
    private final Thread.UncaughtExceptionHandler rootHandler;

    public CustomExceptionHandler(Context context) {
        this.context = context;
        rootHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            handleUncaughtException(sw.toString());
        } catch (Exception e) {
            Log.e(TAG, "Exception Logger failed!", e);
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, ex.getMessage() + " Application will close!", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rootHandler.uncaughtException(thread, ex);
    }

    private void handleUncaughtException(String throwable) {

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + AppConstants.LOG_DIRECTORY);
        if (!file.exists())
            file.mkdir();
        else {
            File logFile = new File(file.getAbsolutePath(), System.currentTimeMillis() + ".txt");
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new FileWriter(logFile, true), 1024);
                out.write(throwable);
                out.newLine();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
