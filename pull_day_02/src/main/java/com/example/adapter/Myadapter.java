package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.Bean;
import com.example.pull_day_02.R;

import java.util.List;

public class Myadapter extends BaseAdapter {
    List<Bean.ResultsBean> list;
    Context context;

    public Myadapter(List<Bean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
              convertView =View.inflate(context,R.layout.text,null);
             holder = new Holder();
             holder.imageView = convertView.findViewById(R.id.image);
             holder.textView  = convertView.findViewById(R.id.name);
             convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.textView.setText(list.get(position).getType());
        Glide.with(context).load(list.get(position).getUrl()).into(holder.imageView);
        return convertView;
    }

    class Holder {
        TextView textView;
        ImageView imageView;
    }
}
