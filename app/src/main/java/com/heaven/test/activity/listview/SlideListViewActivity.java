package com.heaven.test.activity.listview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.heaven.test.R;
import com.heaven.test.adapter.SlideListViewAdapter;
import com.heaven.test.entity.SlideListViewEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 * SlideListViewActivity(侧滑)
 */

public class SlideListViewActivity extends Activity {
    private List<SlideListViewEntity> mAppList;
    private SlideListViewAdapter mAdapter;
    private SwipeMenuListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        findViewById();
        ininData();
    }

    public void findViewById(){
        mAppList = new ArrayList<SlideListViewEntity>();
        mListView = (SwipeMenuListView) findViewById(R.id.listView);
    }

    public void ininData(){
        for(int i = 0;i < 3; ++i){
            SlideListViewEntity map = new SlideListViewEntity();
                map.setName("test"+i);
                map.setType(i);
            mAppList.add(map);
        }
        mAdapter = new SlideListViewAdapter(this,mAppList);
        mListView.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item

                SwipeMenuItem openItem1 = new SwipeMenuItem(getApplicationContext());
                openItem1.setBackground(new ColorDrawable(getResources().getColor(R.color.colorLightBlack)));
                openItem1.setWidth(dp2px(90));
                openItem1.setTitle("取消关注");
                openItem1.setTitleSize(18);
                openItem1.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem1);

                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(getResources().getColor(R.color.colorRed)));
                openItem.setWidth(dp2px(70));
                openItem.setTitle("Delete");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        break;
                    case 1:
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(SlideListViewActivity.this, mAppList.get(position).getName() + " click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics());
    }

}