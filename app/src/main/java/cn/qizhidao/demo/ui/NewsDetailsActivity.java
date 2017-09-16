package cn.qizhidao.demo.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.news_details_content)
    WebView content;
    @BindView(R.id.news_details_source)
    TextView source;
    @BindView(R.id.news_details_readed_count)
    TextView readedCount;
    @BindView(R.id.news_details_time)
    TextView time;
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detils);
        ButterKnife.bind(this);
        mPresenter.getNewsDetils(getIntent().getStringExtra(Constant.Intent_Tag.NEWS_ID),
                getIntent().getIntExtra(Constant.Intent_Tag.TABLE_NUM, 1));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailsActivity.this.finish();
            }
        });
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
        content.loadDataWithBaseURL(null, newsDetils.getData().getContent(), "text/html", "utf-8", null);
        time.setText("发表时间:" + DateUtils.longToDate(newsDetils.getData().getEdit_time()));
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
