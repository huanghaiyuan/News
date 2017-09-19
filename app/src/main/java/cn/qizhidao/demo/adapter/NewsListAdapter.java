package cn.qizhidao.demo.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.qizhidao.demo.R;
import cn.qizhidao.demo.bean.NewsList;
import cn.qizhidao.demo.util.DateUtils;

/**
 * Created by Administrator on 2017/9/15.
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsList.Data, BaseViewHolder> {
    public NewsListAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsList.Data item) {
        helper.setText(R.id.adapter_title, item.getTitle()).setText(R.id.adapter_info, item.getDigest())
                .setText(R.id.adapter_timeAndsource, DateUtils.longToDate(item.getEdit_time()) + "《" + item.getSource() + "》");
        Glide.with(mContext)
                .load(item.getTop_image())
                .apply(new RequestOptions().centerCrop().error(R.mipmap.ic_error))
                .into((ImageView) helper.getView(R.id.adapter_img));
    }
}
