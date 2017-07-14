package com.heaven.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.test.R;
import com.heaven.test.entity.GridViewEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 * GridViewAdapter
 */

public class GridViewAdapter extends BaseAdapter {

    private List<GridViewEntity> list;
    private Context context;

    public GridViewAdapter(Context context, List<GridViewEntity> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View contertView, ViewGroup parent){
        ViewHolder holder = null;
        if(contertView == null){
            holder = new ViewHolder();
            contertView = LayoutInflater.from(context).inflate(R.layout.adaper_grid_view_item,null);
            holder.iv_icon = (ImageView) contertView.findViewById(R.id.iv_item);
            holder.tv_name = (TextView) contertView.findViewById(R.id.tv_item);

            contertView.setTag(holder);
        } else {
            holder = (ViewHolder) contertView.getTag();
        }
        holder.iv_icon.setBackgroundResource(list.get(position).getImageView());
        holder.tv_name.setText(list.get(position).getTitleName());

        return contertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
    }
}