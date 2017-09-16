package cn.qizhidao.demo.presenter;

import cn.qizhidao.demo.bean.NewsDetails;
import cn.qizhidao.demo.http.OnResponeListener;
import cn.qizhidao.demo.modle.NewsDetailsModle;
import cn.qizhidao.demo.modle.i.INewsDetailsModle;
import cn.qizhidao.demo.view.INewsDetailsView;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsDetailsPresenter extends BasePresenter<INewsDetailsView> {
    private INewsDetailsModle modle;

    public NewsDetailsPresenter(INewsDetailsView view) {
        onAttach(view);
        modle = new NewsDetailsModle();
    }

    public void getNewsDetils(String newsId, int tableNum) {

        modle.getNewsDetils(newsId, tableNum, new OnResponeListener<NewsDetails>() {
            @Override
            public void success(NewsDetails newsDetils) {
                getView().getNewsDetilsSucces(newsDetils);
            }

            @Override
            public void failed(String s) {

            }
        });
    }
}
