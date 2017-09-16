package cn.qizhidao.demo.http;

public interface OnResponeListener<T> {
    void success(T t);

    void failed(String s);
}