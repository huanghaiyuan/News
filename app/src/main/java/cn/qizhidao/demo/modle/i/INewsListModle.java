package cn.qizhidao.demo.modle.i;

import cn.qizhidao.demo.http.OnResponeListener;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface INewsListModle {
    void getNewsList(int tableNum, int pageSize, OnResponeListener listener);
}
