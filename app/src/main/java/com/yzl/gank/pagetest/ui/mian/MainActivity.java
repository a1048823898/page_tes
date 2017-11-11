package com.yzl.gank.pagetest.ui.mian;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yzl.gank.pagetest.R;
import com.yzl.gank.pagetest.adapter.MyAdapter;
import com.yzl.gank.pagetest.bean.GankData;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private MainData mMainData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMainData = ViewModelProviders.of(this).get(MainData.class);
    initView();
    initList();
}

    private void initView() {
        mRv = findViewById(R.id.rv);
    }

    private void initList() {

        final MyAdapter myAdapter = new MyAdapter(new DiffCallback<GankData>() {

            @Override
            public boolean areItemsTheSame(@NonNull GankData oldUser, @NonNull GankData newUser) {
                return oldUser.get_id().equals(newUser.get_id());
            }

            @Override
            public boolean areContentsTheSame(@NonNull GankData oldUser, @NonNull GankData newUser) {
                return oldUser.getUrl().equals(newUser.getUrl());
            }
        });

        mRv.setAdapter(myAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mMainData.getDataLiveData().observe(this, new Observer<PagedList<GankData>>() {
            @Override
            public void onChanged(@Nullable PagedList<GankData> gankData) {
                myAdapter.setList(gankData);
            }
        });
    }
}
