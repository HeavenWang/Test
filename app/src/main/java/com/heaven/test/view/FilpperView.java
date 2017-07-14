package com.heaven.test.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

import com.heaven.test.R;


/**
 * Created by Administrator on 2017/5/16.
 * FilpperView
 */

public class FilpperView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        findViewById();
    }

    public void findViewById(){
        ViewFlipper filpper = (ViewFlipper) findViewById(R.id.vf_id);
        filpper.startFlipping();
    }
}
