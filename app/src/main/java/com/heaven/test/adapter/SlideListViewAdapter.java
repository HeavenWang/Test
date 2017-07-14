package com.heaven.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heaven.test.R;
import com.heaven.test.entity.SlideListViewEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 * SlideListViewAdapter
 */

public class SlideListViewAdapter extends BaseAdapter {

    private List<SlideListViewEntity> mAppList;
    private Context mContext;

    public SlideListViewAdapter(Context context, List<SlideListViewEntity> mAppList){
        this.mContext = context;
        this.mAppList = mAppList;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public SlideListViewEntity getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;

        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_slide_list_view_item, null);
            holder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(mAppList.get(position).getName());

        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "iv_icon_click", Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"iv_icon_click",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
    }

    public boolean getSwipEnableByPosition(int position) {
        if(position % 2 == 0){
            return false;
        }
        return true;
    }
}