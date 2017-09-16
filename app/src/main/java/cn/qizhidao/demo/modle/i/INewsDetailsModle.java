package cn.qizhidao.demo.modle.i;

import cn.qizhidao.demo.http.OnResponeListener;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface INewsDetailsModle {
    void getNewsDetils(String newsId, int tableNum, OnResponeListener listener);
}
