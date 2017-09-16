package cn.qizhidao.demo.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qizhidao.demo.R;
import cn.qizhidao.demo.adapter.MainAdapter;
import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.presenter.NewsListPresenter;
import cn.qizhidao.demo.util.Constant;
import cn.qizhidao.demo.view.INewsListView;

public class MainActivity extends BaseActivity<NewsListPresenter> implements INewsListView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerViewDivider.with(this).asSpace().build().addTo(recyclerView);

        mPresenter.getNewsList(1,30);
    }

    @Override
    protected NewsListPresenter createPresener() {
        return new NewsListPresenter(this);
    }

    @Override
    public void getNewsListSucces(final NewsList newsList) {
        MainAdapter adapter = new MainAdapter(R.layout.adapter_main, newsList.getData());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this,NewsDetailsActivity.class);
                intent.putExtra(Constant.Intent_Tag.NEWS_ID,newsList.getData().get(position).getNews_id());
                startActivity(intent);
            }
        });
    }
}
