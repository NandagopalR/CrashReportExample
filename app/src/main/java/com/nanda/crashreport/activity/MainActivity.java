package com.nanda.crashreport.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nanda.crashreport.R;

public class MainActivity extends AppCompatActivity {

    private String[] exceptionArraySample = new String[]{"test", "exception"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.e("Exception Test", exceptionArraySample[3]);

    }
}
