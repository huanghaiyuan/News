package cn.qizhidao.demo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qizhidao.demo.R;
import cn.qizhidao.demo.adapter.NewsListAdapter;
import cn.qizhidao.demo.adapter.NewsClassAdapter;
import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.presenter.NewsListPresenter;
import cn.qizhidao.demo.util.Constant;
import cn.qizhidao.demo.view.INewsListView;

public class MainActivity extends BaseActivity<NewsListPresenter> implements INewsListView {
    @BindView(R.id.main_news_list)
    RecyclerView newsListView;

    @BindView(R.id.main_news_class)
    RecyclerView newsClassView;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    private TextView tmpView;

    private NewsListAdapter newsListAdapter;

    private String[] newsClasss = {"头条", "娱乐", "军事", "汽车", "财经", "笑话", "体育", "科技"};

    private List<NewsList.Data> mData = new ArrayList<>();

    private int tableNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsListView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewDivider.with(this).asSpace().
                color(getResources().getColor(R.color.color_d3d3d3))
                .build().addTo(newsListView);

        newsClassView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        NewsClassAdapter adapter = new NewsClassAdapter(R.layout.adapter_news_class, Arrays.asList(newsClasss));
        newsClassView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (tmpView != null) {
                    tmpView.setTextColor(getResources().getColor(android.R.color.black));
                }
                tmpView = (TextView) view;
                tmpView.setTextColor(getResources().getColor(R.color.colorPrimary));
                tableNum = position + 1;
                mPresenter.getNewsList(tableNum, 10);
                newsListView.scrollToPosition(0);
            }
        });

        toolBar.setTitle("新闻");
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        mPresenter.getNewsList(tableNum, 10);
    }

    @Override
    protected NewsListPresenter createPresener() {
        return new NewsListPresenter(this);
    }

    @Override
    public void getNewsListSucces(final NewsList newsList) {
        if (!mData.isEmpty())
            mData.clear();
        mData.addAll(newsList.getData());

        if (newsListAdapter == null) {
            newsListAdapter = new NewsListAdapter(R.layout.adapter_main, mData);
            newsListView.setAdapter(newsListAdapter);
        } else {
            newsListAdapter.notifyDataSetChanged();
        }
        newsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                intent.putExtra(Constant.Intent_Tag.NEWS_ID, mData.get(position).getNews_id());
                intent.putExtra(Constant.Intent_Tag.TABLE_NUM, tableNum);
                startActivity(intent);
            }
        });
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
