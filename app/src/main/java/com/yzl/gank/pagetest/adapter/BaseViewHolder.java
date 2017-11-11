package com.yzl.gank.pagetest.adapter;

/**
 * Created by shen on 2017/5/8.
 */

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author 沈小建 on 2017/1/6 0006.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> viewArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        viewArray = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int id) {
        View view = viewArray.get(id);
        if (null == view) {
            view = itemView.findViewById(id);
            viewArray.put(id, view);
        }
        return (T) view;
    }
}
