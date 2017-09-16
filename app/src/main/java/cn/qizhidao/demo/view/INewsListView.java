package cn.qizhidao.demo.view;

import cn.qizhidao.demo.bean.NewsList;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface INewsListView {
    void getNewsListSucces(NewsList newsList);
    void fail(String msg);
}
