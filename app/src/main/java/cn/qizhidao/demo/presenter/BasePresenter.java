package cn.qizhidao.demo.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 解决内存泄露
 * @param <T>
 */
public class BasePresenter<T> {
    private Reference<T> mViewReference = null;

    public void onAttach(T view) {
        mViewReference = new WeakReference<T>(view);
    }

    public T getView() {
        return mViewReference.get();
    }

    public boolean isAttach() {
        return null != mViewReference && null != mViewReference.get();
    }

    public void onDettach() {
        if (null != mViewReference) {
            mViewReference.clear();
            mViewReference = null;
        }
    }
}