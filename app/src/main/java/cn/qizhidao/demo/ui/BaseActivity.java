package cn.qizhidao.demo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

import cn.qizhidao.demo.presenter.BasePresenter;


public abstract class BaseActivity<T extends BasePresenter> extends Activity {
    public Handler baseHandler;
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseHandler = new Handler(getMainLooper());
        mPresenter = createPresener();
        mPresenter.onAttach(this);
    }

    protected abstract T createPresener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDettach();
        if (null != baseHandler) {
            baseHandler.removeCallbacksAndMessages(null);
            baseHandler = null;
        }
    }
}
