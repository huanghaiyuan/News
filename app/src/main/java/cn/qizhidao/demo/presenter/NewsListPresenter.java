package cn.qizhidao.demo.presenter;

import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.http.OnResponeListener;
import cn.qizhidao.demo.modle.NewsListModle;
import cn.qizhidao.demo.modle.i.INewsListModle;
import cn.qizhidao.demo.view.INewsListView;

/**
 * Created by Administrator on 2017/9/14.
 */

public class NewsListPresenter extends BasePresenter<INewsListView> {
    public INewsListModle modle;

    public NewsListPresenter(INewsListView view) {
        onAttach(view);
        modle = new NewsListModle();
    }

    public void getNewsList(int  tableNum,int pageSize){

        modle.getNewsList(tableNum,pageSize, new OnResponeListener<NewsList>() {
            @Override
            public void success(NewsList newsList) {
                getView().getNewsListSucces(newsList);
            }

            @Override
            public void failed(String s) {

            }
        });
    }
}
