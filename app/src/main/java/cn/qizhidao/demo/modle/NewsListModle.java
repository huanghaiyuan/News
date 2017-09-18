package cn.qizhidao.demo.modle;

import android.widget.Toast;

import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.http.OnResponeListener;
import cn.qizhidao.demo.http.RetrofitHelper;
import cn.qizhidao.demo.modle.i.INewsListModle;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/14.
 */

public class NewsListModle implements INewsListModle {
    @Override
    public void getNewsList(int tanleNum, int page,int pageSize, final OnResponeListener listener) {
        RetrofitHelper.getInstance().getService().getNewsList(tanleNum,page, pageSize).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsList newsList) {
                        listener.success(newsList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listener.failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
