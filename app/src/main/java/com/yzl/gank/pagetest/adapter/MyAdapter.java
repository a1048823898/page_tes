package com.yzl.gank.pagetest.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yzl.gank.pagetest.R;
import com.yzl.gank.pagetest.bean.GankData;

/**
 * Created by 10488 on 2017-11-11.
 */

public class MyAdapter extends PagedListAdapter<GankData, BaseViewHolder> {

    private Context mContext;

    public MyAdapter(@NonNull DiffCallback<GankData> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        ImageView imageView = holder.getView(R.id.iv);

        GankData data = getItem(position);
        tvName.setText(data.getCreatedAt());
        Glide.with(mContext).load(data.getUrl()).into(imageView);
    }
}