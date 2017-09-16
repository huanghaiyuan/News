package cn.qizhidao.demo.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qizhidao.demo.R;
import cn.qizhidao.demo.bean.NewsDetails;
import cn.qizhidao.demo.presenter.NewsDetailsPresenter;
import cn.qizhidao.demo.util.Constant;
import cn.qizhidao.demo.util.DateUtils;
import cn.qizhidao.demo.view.INewsDetailsView;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsDetailsActivity extends BaseActivity<NewsDetailsPresenter> implements INewsDetailsView {

    @BindView(R.id.news_details_title)
    TextView title;
    @BindView(R.id.news_details_img)
    ImageView img;
    @BindView(R.id.news_details_content)
    WebView content;
    @BindView(R.id.news_details_source)
    TextView source;
    @BindView(R.id.news_details_readed_count)
    TextView readedCount;
    @BindView(R.id.news_details_time)
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detils);
        ButterKnife.bind(this);
        mPresenter.getNewsDetils(getIntent().getStringExtra(Constant.Intent_Tag.NEWS_ID), 1);
    }


    @Override
    protected NewsDetailsPresenter createPresener() {
        return new NewsDetailsPresenter(this);
    }

    @Override
    public void getNewsDetilsSucces(NewsDetails newsDetils) {
        title.setText(newsDetils.getData().getTitle());
        source.setText("来源:" + newsDetils.getData().getSource());
        readedCount.setText(newsDetils.getData().getReply_count() + "人已读");
        Glide.with(this).load(newsDetils.getData().getTop_image()).into(img);
        content.loadDataWithBaseURL(null,newsDetils.getData().getContent(),"text/html", "utf-8", null);
        time.setText("发表时间:" + DateUtils.longToDate(newsDetils.getData().getEdit_time()));
    }
}
