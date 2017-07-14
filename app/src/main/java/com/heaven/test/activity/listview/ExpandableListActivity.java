package com.heaven.test.activity.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.heaven.test.R;
import com.heaven.test.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 * ExpandableListActivity
 */

public class ExpandableListActivity extends Activity {

    private ExpandableListView expandableListView;

    //设置组视图的显示文字
    private List<String> group;           //组列表
    private List<List<String>> child;     //子列表
    private List<List<Integer>> logou;     //子列表
    private ExpandableListAdapter adapter;;  //数据适配器

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_expandable_list);
        findViewById();
        ininData();
    }

    public void findViewById(){
        expandableListView = (ExpandableListView) findViewById(R.id.ev_view);
    }
    /**
     * 初始化组、子列表数据
     */
    public void ininData(){
        group = new ArrayList<String>();
        child = new ArrayList<List<String>>();
        logou = new ArrayList<List<Integer>>();
        addInfo("魏",
                new String[]{ "夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修" },
                new Integer[]{ R.mipmap.el_xia_hou_dun, R.mipmap.el_zhen_ji,
                        R.mipmap.el_xu_zhu, R.mipmap.el_guo_jia,
                        R.mipmap.el_si_ma_yi, R.mipmap.el_yang_xiu }
                ) ;
        addInfo("蜀",
                new String[]{ "马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云" },
                new Integer[]{ R.mipmap.el_ma_chao, R.mipmap.el_zhang_fei,
                        R.mipmap.el_liu_bei, R.mipmap.el_zhu_ge_liang,
                        R.mipmap.el_huang_yue_ying, R.mipmap.el_zhao_yun }
                ) ;
        addInfo("吴",
                new String[]{ "吕蒙", "陆逊", "孙权", "周瑜", "孙尚香" },
                new Integer[]{ R.mipmap.el_lv_meng, R.mipmap.el_lu_xun, R.mipmap.el_sun_quan,
                        R.mipmap.el_zhou_yu, R.mipmap.el_sun_shang_xiang }
                ) ;

        adapter = new ExpandableListAdapter(this,group,child,logou);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        ExpandableListActivity.this,
                        "你点击了" + adapter.getChild(groupPosition, childPosition),
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
    /**
     * 模拟给组、子列表添加数据
     */
    private void addInfo(String g,String[] c, Integer[] l){
        group.add(g);
        List<String> childitem = new ArrayList<String>();
        List<Integer> logouitem = new ArrayList<Integer>();
        for(int i=0;i<c.length;i++){
            childitem.add(c[i]);
            logouitem.add(l[i]);
        }
        logou.add(logouitem);
        child.add(childitem);
    }
}
