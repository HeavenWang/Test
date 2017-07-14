package com.heaven.test.activity.listview;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.heaven.test.R;
import com.heaven.test.adapter.GridViewAdapter;
import com.heaven.test.entity.GridViewEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * GridView
 */

public class GridViewActivity extends Activity implements View.OnClickListener {

    private Boolean VISIBILITY = false,VISIBILITY1 = false;
    private LinearLayout lt_menu,lt_menu1;
    private ImageView iv_menu;
    private GridView gridView,gridView1;
    private int[] PIC = { R.mipmap.gv_pic1,R.mipmap.gv_pic2,R.mipmap.gv_pic3,
                            R.mipmap.gv_pic4,R.mipmap.gv_pic5,R.mipmap.gv_pic6,
                            R.mipmap.gv_pic7,R.mipmap.gv_pic8, R.mipmap.gv_pic9};
    private String[] TITLE = {"旗帜僵尸", "铠甲僵尸", "书生见识",
                                "铁桶僵尸", "尸娃僵尸","舞蹈僵尸" ,
                                "黄金蘑菇", "贪睡蘑菇", "大头蘑菇"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        findViewById();
        ininData();
    }

    public void findViewById(){
        gridView = (GridView) findViewById(R.id.gv_test);
        lt_menu = (LinearLayout) findViewById(R.id.lt_grid_view_title_menu);
        iv_menu = (ImageView) findViewById(R.id.iv_grid_view_title);
        gridView1 = (GridView) findViewById(R.id.gv_test1);
        lt_menu1 = (LinearLayout) findViewById(R.id.lt_grid_view_title_menu1);
        lt_menu.setOnClickListener(this);
        lt_menu1.setOnClickListener(this);
    }

    public void ininData(){
        final List<GridViewEntity> list = new ArrayList<GridViewEntity>();
        for(int i = 0; i < PIC.length; ++i){
            GridViewEntity data = new GridViewEntity();
            data.setImageView(PIC[i]);
            data.setTitleName(TITLE[i]);
            list.add(data);
        }
        GridViewAdapter adapter = new GridViewAdapter(this,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this, "您点击了"+list.get(position).getTitleName().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        final List<GridViewEntity> list1 = new ArrayList<GridViewEntity>();
        for(int i = 0; i < PIC.length; ++i){
            GridViewEntity data = new GridViewEntity();
            data.setImageView(PIC[i]);
            data.setTitleName(TITLE[i]);
            list1.add(data);
        }
        GridViewAdapter adapter1 = new GridViewAdapter(this,list1);
        gridView1.setAdapter(adapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notifica();

                Toast.makeText(GridViewActivity.this, "您点击了"+list1.get(position).getTitleName().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Notifica(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        Notification notification = new Notification.Builder(this)
            .setSmallIcon(R.mipmap.el_guo_jia)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setContentTitle("Test Notification")
            .setContentText("This is Notification test").getNotification();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lt_grid_view_title_menu:
                if(VISIBILITY){
                    iv_menu.setBackgroundResource(R.mipmap.gv_left);
                    gridView.setVisibility(View.GONE);
                    VISIBILITY = false;
                } else {
                    VISIBILITY = true;
                    iv_menu.setBackgroundResource(R.mipmap.gv_down);
                    gridView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.lt_grid_view_title_menu1:
                if(VISIBILITY1){
                    gridView1.setVisibility(View.GONE);
                    VISIBILITY1 = false;
                } else {
                    VISIBILITY1 = true;
                    gridView1.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}