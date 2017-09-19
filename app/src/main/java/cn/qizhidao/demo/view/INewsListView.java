package cn.qizhidao.demo.view;

import android.view.View;

import java.util.List;

import cn.qizhidao.demo.bean.NewsList;

/**
 * Created by Administrator on 2017/9/14.
 */

public interface INewsListView {
    void getNewsListSucces(List<NewsList.Data> newsList);
    void fail(String msg);
    void showRefresh();
    void hideRefresh();
    void selectTableNum();
}
