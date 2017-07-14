package com.heaven.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heaven.test.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 * ListViewMenuAdapter
 */

public class ListViewMenuAdapter extends BaseAdapter {

    private List<String> mMenuList;
    private Context mContext;

    public ListViewMenuAdapter(Context context, List<String> mMenuList){
        this.mContext = context;
        this.mMenuList = mMenuList;
    }

    @Override
    public int getCount() {
        return mMenuList.size();
    }

    @Override
    public String getItem(int position) {
        return mMenuList.get(position);
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_listview_item, null);
            holder.tvMenu = (TextView) view.findViewById(R.id.tv_list_view_menu_item);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvMenu.setText(mMenuList.get(position));
        return view;
    }

    class ViewHolder {
        TextView tvMenu;
    }
}