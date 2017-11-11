package com.yzl.gank.pagetest.http;

/**
 * Created by shen on 2017/5/5.
 */

public class BaseResponse<T> {

    private String error;
    private T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
