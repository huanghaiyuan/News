package cn.qizhidao.demo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.qizhidao.demo.R;
import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.util.DateUtils;

/**
 * Created by Administrator on 2017/9/15.
 */

public class NewsClassAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NewsClassAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.adapter_news_class_info, item);
    }
}
