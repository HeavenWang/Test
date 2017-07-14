package com.heaven.test.activity.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heaven.test.R;
import com.heaven.test.adapter.ListViewMenuAdapter;
import com.heaven.test.view.FilpperView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 * ListViewActivity
 */

public class ListViewActivity extends Activity {

    private ListView lv_view;
    private ListViewMenuAdapter adapter;
    private List<String> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        findViewById();
        ininData();
    }

    public void findViewById(){
        lv_view = (ListView) findViewById(R.id.lv_menu_view);
    }

    public void ininData(){
        menuList = new ArrayList<String>();
        String[] menu = new String[]{ "ExpandableList 仿QQ好友栏 ", "GridView", "ListView侧滑",
                "FilpperView" };

        for(int i = 0; i < menu.length; ++i){
            menuList.add(menu[i]);
        }

        adapter = new ListViewMenuAdapter(this,menuList);
        lv_view.setAdapter(adapter);
        lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent(position);
            }
        });
    }

    public void intent(int position){
        if(position == 0){
            startActivity(new Intent(this, ExpandableListActivity.class));
        } else if(position == 1){
            startActivity(new Intent(this, GridViewActivity.class));
        } else if(position == 2){
            startActivity(new Intent(this, SlideListViewActivity.class));
        } else if(position == 3){
            startActivity(new Intent(this, FilpperView.class));
        }
    }

}
