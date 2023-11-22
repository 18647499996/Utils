package com.liudonghan.main.activity.image;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADImageUtils;
import com.liudonghan.utils.ADScreenUtils;
import com.liudonghan.view.radius.ADImageView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ImageViewActivity extends ADBaseActivity<ImageViewPresenter> implements ImageViewContract.View {

    @BindView(R.id.activity_img_cover)
    ADImageView activityImgCover;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_image_view;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ImageViewPresenter createPresenter() throws RuntimeException {
        return (ImageViewPresenter) new ImageViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
    }

    @Override
    protected void addListener() throws RuntimeException {
        Point screenSize = ADScreenUtils.getInstance().getScreenSize(this);
        Glide.with(this)
                .load("https://cn.bing.com/th?id=OHR.SarekSweden_ZH-CN9728518595_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp")
                .into(ADImageUtils.getInstance()
                        .setImageWidthOrHeight(activityImgCover, -1, screenSize.x / 2));
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(ImageViewContract.Presenter presenter) {
        mPresenter = (ImageViewPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }
}
