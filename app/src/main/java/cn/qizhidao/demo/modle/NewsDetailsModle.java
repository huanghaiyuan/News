package cn.qizhidao.demo.modle;

import cn.qizhidao.demo.bean.NewsDetails;
import cn.qizhidao.demo.http.OnResponeListener;
import cn.qizhidao.demo.http.RetrofitHelper;
import cn.qizhidao.demo.modle.i.INewsDetailsModle;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsDetailsModle implements INewsDetailsModle {
    @Override
    public void getNewsDetils(String newsId, int tableNum, final OnResponeListener listener) {
        RetrofitHelper.getInstance().getService()
                .getNewsDetils(newsId,tableNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetails>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsDetails newsDetils) {
                            listener.success(newsDetils);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
