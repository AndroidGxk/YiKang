package com.yikangcheng.admin.yikang.activity.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;

/**
 * 作者：古祥坤 on 2019/5/19 23:24
 * 邮箱：1724959985@qq.com
 */
public class WuliuAdapter extends BaseAdapter {
    ArrayList<String> arrayList = new ArrayList<>();

    public void addAll(ArrayList<String> arrayList) {
        this.arrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Vh vh = null;
        if (convertView == null) {
            vh = new Vh();
            convertView = View.inflate(parent.getContext(), R.layout.wuliu_recycler_item, null);
            convertView.setTag(vh);
        } else {
            vh = (Vh) convertView.getTag();
        }
        return convertView;
    }

    class Vh {
        TextView textView;
        TextView textView1;
    }
}
