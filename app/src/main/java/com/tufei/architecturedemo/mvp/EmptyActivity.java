package com.tufei.architecturedemo.mvp;

import android.os.Bundle;

import com.tufei.architecturedemo.R;
import com.tufei.architecturedemo.di.ActivityBindingModule;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * 如果Activity简单到不需要注入任何东西的时候，也要在{@link ActivityBindingModule}声明它
 * 除非你的Activity不再是继承自{@link DaggerAppCompatActivity}
 *
 * @author tufei
 * @date 2017/9/12
 */
public class EmptyActivity extends DaggerAppCompatActivity {
    private static final String TAG = "EmptyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }
}
