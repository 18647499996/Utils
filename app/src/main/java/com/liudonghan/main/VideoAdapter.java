package com.liudonghan.main;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.utils.ADCursorManageUtils;

import java.io.File;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/4/23
 */
public class VideoAdapter extends BaseQuickAdapter<ADCursorManageUtils.ImageFolderModel, BaseViewHolder> {

    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ADCursorManageUtils.ImageFolderModel item) {
        helper.setText(R.id.item_video_tv_name, item.getDirName());
        Glide
                .with(mContext)
                .load(Uri.fromFile(new File(item.getCoverPath())))
                .into((ImageView) helper.getView(R.id.item_video_img_cover));
    }
}
