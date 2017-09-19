package cn.qizhidao.demo.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import cn.qizhidao.demo.adapter.NewsClassAdapter;
import cn.qizhidao.demo.adapter.NewsListAdapter;
import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.presenter.NewsListPresenter;
import cn.qizhidao.demo.util.Constant;
import cn.qizhidao.demo.view.INewsListView;

public class MainActivity extends BaseActivity<NewsListPresenter> implements INewsListView
        , SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.main_news_list)
    RecyclerView listView;

    @BindView(R.id.main_news_class)
    RecyclerView classView;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.main_news_refre_layout)
    SwipeRefreshLayout refreLayout;

    private TextView tmpView;

    private String[] newsClasss = {"头条", "娱乐", "军事", "汽车", "财经", "笑话", "体育", "科技"};

    private NewsListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        refreLayout.setOnRefreshListener(this);
        refreLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        initListView();

        initClassView();

        toolBar.setTitle("新闻");
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        mPresenter.getNewsList();
    }

    /**
     * 初始化列表组件
     */
    private void initListView() {
        listView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new NewsListAdapter(R.layout.adapter_main, new ArrayList());
        listView.setAdapter(listAdapter);
        RecyclerViewDivider.with(this).asSpace().
                color(getResources().getColor(R.color.color_d3d3d3))
                .build().addTo(listView);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                intent.putExtra(Constant.Intent_Tag.NEWS_ID, listAdapter.getData().get(position).getNews_id());
                intent.putExtra(Constant.Intent_Tag.TABLE_NUM, mPresenter.getTableNum());
                startActivity(intent);
            }
        });
        //加载更多
        listAdapter.setEnableLoadMore(true);
        listAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        }, listView);
    }

    /**
     * 初始化分类组件
     */
    private void initClassView() {
        final LinearLayoutManager newsClassManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        classView.setLayoutManager(newsClassManager);
        NewsClassAdapter newsClassAdapter = new NewsClassAdapter(R.layout.adapter_news_class, Arrays.asList(newsClasss));
        classView.setAdapter(newsClassAdapter);
        classView.post(new Runnable() {
            @Override
            public void run() {
                View view = newsClassManager.getChildAt(0);
                TextView tv = view.findViewById(R.id.adapter_news_class_info);
                tmpView = tv;
                tv.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        newsClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (tmpView != null) {
                    tmpView.setTextColor(getResources().getColor(android.R.color.black));
                }
                tmpView = (TextView) view;
                tmpView.setTextColor(getResources().getColor(R.color.colorPrimary));

                mPresenter.selectTableNum(position + 1);
                listView.scrollToPosition(0);
            }
        });
    }

    @Override
    protected NewsListPresenter createPresener() {
        return new NewsListPresenter(this);
    }

    @Override
    public void getNewsListSucces(List<NewsList.Data> newsList) {
        if (mPresenter.isLoadMore()) {
            listAdapter.addData(newsList);
        } else {
            listAdapter.setNewData(newsList);
        }
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void hideRefresh() {
        refreLayout.setRefreshing(false);
        listAdapter.loadMoreComplete();
    }

    @Override
    public void showRefresh() {
        refreLayout.setRefreshing(true);
    }

    @Override
    public void selectTableNum() {

    }
}
