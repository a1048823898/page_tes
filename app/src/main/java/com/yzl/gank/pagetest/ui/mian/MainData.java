package com.yzl.gank.pagetest.ui.mian;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.arch.paging.TiledDataSource;
import android.support.annotation.NonNull;

import com.yzl.gank.pagetest.bean.GankData;
import com.yzl.gank.pagetest.http.AppService;
import com.yzl.gank.pagetest.http.BaseResponse;
import com.yzl.gank.pagetest.http.RetrofitApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by 10488 on 2017-11-11.
 */

public class MainData extends AndroidViewModel {

    /**
     * 每次需要10个数据.
     */
    private static final int NEED_NUMBER = 10;

    /**
     * 福利第一页.
     */
    private static final int PAGE_FIRST = 1;

    /**
     * 分页.
     */
    private int mPage = PAGE_FIRST;


    /**
     * 列表数据.
     */
    private LiveData<PagedList<GankData>> mDataLiveData;

    public MainData(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<GankData>> getDataLiveData() {
        initPageList();
        return mDataLiveData;
    }

    /**
     * 初始化pageList.
     */
    private void initPageList() {
        //获取dataSource,列表数据都从这里获取,
        final TiledDataSource<GankData> tiledDataSource = new TiledDataSource<GankData>() {

            /**
             * 需要的总个数,如果数量不定,就传COUNT_UNDEFINED.
             */
            @Override
            public int countItems() {
                return DataSource.COUNT_UNDEFINED;
            }

            /**
             * 返回需要加载的数据.
             * 这里是在线程异步中执行的,所以我们可以同步请求数据并且返回
             * @param startPosition 现在第几个数据
             * @param count 加载的数据数量
             */
            @Override
            public List<GankData> loadRange(int startPosition, int count) {
                List<GankData> gankDataList = new ArrayList<>();

                //这里我们用retrofit获取数据,每次获取十条数据,数量不为空,则让mPage+1
                try {
                    Response<BaseResponse<List<GankData>>> execute = RetrofitApi.getInstance().mRetrofit.create(AppService.class)
                            .getWelfare1(mPage, NEED_NUMBER).execute();
                    gankDataList.addAll(execute.body().getResults());

                    if (!gankDataList.isEmpty()) {
                        mPage++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return gankDataList;
            }
        };

        //这里我们创建LiveData<PagedList<GankData>>数据,
        mDataLiveData = new LivePagedListProvider<Integer, GankData>() {

            @Override
            protected DataSource<Integer, GankData> createDataSource() {
                return tiledDataSource;
            }
        }.create(0, new PagedList.Config.Builder()
                .setPageSize(NEED_NUMBER) //每次加载的数据数量
                //距离本页数据几个时候开始加载下一页数据(例如现在加载10个数据,设置prefetchDistance为2,则滑到第八个数据时候开始加载下一页数据).
                .setPrefetchDistance(NEED_NUMBER)
                //这里设置是否设置PagedList中的占位符,如果设置为true,我们的数据数量必须固定,由于网络数据数量不固定,所以设置false.
                .setEnablePlaceholders(false)
                .build());
    }
}
