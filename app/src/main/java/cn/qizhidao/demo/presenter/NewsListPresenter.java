package cn.qizhidao.demo.presenter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.http.OnResponeListener;
import cn.qizhidao.demo.modle.NewsListModle;
import cn.qizhidao.demo.modle.i.INewsListModle;
import cn.qizhidao.demo.view.INewsListView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {
    private INewsListModle modle;
    private int page = 1, tableNum = 1;
    private int pageSize = 10;
    private boolean isLoadMore = false;

    public NewsListPresenter(INewsListView view) {
        onAttach(view);
        modle = new NewsListModle();
    }

    public int getTableNum() {
        return tableNum;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void getNewsList() {
        modle.getNewsList(tableNum, page, pageSize, new OnResponeListener<NewsList>() {
            @Override
            public void success(NewsList newsList) {
                getView().hideRefresh();
                getView().getNewsListSucces(newsList.getData());
            }

            @Override
            public void failed(String s) {
                getView().fail(s);
            }
        });
    }

    public void refresh() {
        isLoadMore = false;
        page = 1;
        getView().showRefresh();
        getNewsList();
    }

    public void loadMore() {
        isLoadMore = true;
        page++;
        getNewsList();
    }

    public void selectTableNum(int num){
        isLoadMore = false;
        page = 1;
        this.tableNum = num;
        getView().selectTableNum();
        getNewsList();
    }
}
