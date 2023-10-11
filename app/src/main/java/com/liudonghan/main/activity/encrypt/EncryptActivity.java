package com.liudonghan.main.activity.encrypt;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADEncryptManager;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class EncryptActivity extends ADBaseActivity<EncryptPresenter> implements EncryptContract.View {

    @BindView(R.id.activity_encrypt_tv_md5)
    TextView activityEncryptTvMd5;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_encrypt;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected EncryptPresenter createPresenter() throws RuntimeException {
        return (EncryptPresenter) new EncryptPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @OnClick(R.id.activity_encrypt_tv_md5)
    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {
        switch (view.getId()){
            case R.id.activity_encrypt_tv_md5:
                String s = ADEncryptManager.getInstance().encryptMessageDigestMD5("https://cn.bing.com/th?id=OHR.RedBellied_IT-IT9861324167_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
                activityEncryptTvMd5.setText(s);
                break;
        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(EncryptContract.Presenter presenter) {
        mPresenter = (EncryptPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
