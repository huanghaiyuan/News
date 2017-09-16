package cn.qizhidao.demo.http;

import cn.qizhidao.demo.bean.NewsDetails;
import cn.qizhidao.demo.bean.NewsList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/news/get-news")
    Observable<NewsList> getNewsList(@Query("tableNum") int tableNum, @Query("pagesize")  int pageSize);

    @GET("/news/single-news")
    Observable<NewsDetails> getNewsDetils(@Query("news_id") String newsId, @Query("tableNum")  int tableNum);
}