package com.heaven.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven.test.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 * ExpandableListAdapter
 */

public class ExpandableListAdapter extends  BaseExpandableListAdapter{

    private Context mContext;
    private List<String> mGroup;           //组列表
    private List<List<String>> mChild;     //子列表
    private List<List<Integer>> mLogou;     //子列表

    public ExpandableListAdapter(Context context, List<String> group, List<List<String>> child,
                                 List<List<Integer>> logou){
        this.mContext = context;
        this.mGroup = group;
        this.mChild = child;
        this.mLogou = logou;
    }

    //  获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChild.get(groupPosition).get(childPosition);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChild.get(groupPosition).size();
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int parentPos) {
        return parentPos;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;
        if(view == null){
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_expandlist_group_item, null);
            holder.groupName = (TextView)view.findViewById(R.id.tv_expandlist_group_name);
            holder.arrow = (ImageView)view.findViewById(R.id.iv_expandlist_group);
            view.setTag(holder);
        }else{
            holder = (GroupHolder)view.getTag();
        }

        //判断是否已经打开列表
        if(isExpanded){
            holder.arrow.setBackgroundResource(R.mipmap.el_arrow_down);
        }else{
            holder.arrow.setBackgroundResource(R.mipmap.el_arrow_right);
        }
        holder.groupName.setText(mGroup.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_expandlist_child_item, null);
            holder.childAvatar = (ImageView) view.findViewById(R.id.iv_expandlist_child_avatar);
            holder.childName = (TextView) view.findViewById(R.id.tv_expandlist_child_name);
            holder.childContent = (TextView) view.findViewById(R.id.tv_expandlist_child_content);
            holder.divider = (ImageView) view.findViewById(R.id.iv_expandlist_child_divider);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }

        if(childPosition == getChildrenCount(groupPosition)-1){
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.childAvatar.setImageResource(mLogou.get(groupPosition).get(childPosition));
        holder.childName.setText(mChild.get(groupPosition).get(childPosition));
        holder.childContent.setText(mChild.get(groupPosition).get(childPosition));

        return view;
    }

    //自己定义一个获得文字信息的方法
    TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView textView = new TextView(mContext);
            textView.setLayoutParams(lp);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(80, 0, 0, 0);
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
        return textView;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        public TextView groupName;
        public ImageView arrow;
    }

    class ChildHolder{
        public TextView childName,childContent;
        public ImageView childAvatar;
        public ImageView divider;
    }
}
