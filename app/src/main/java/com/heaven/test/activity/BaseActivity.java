package com.heaven.test.activity;

import android.app.Activity;
import android.os.Bundle;

import org.xutils.x;

/**
 * Created by Administrator on 2017/7/12.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
