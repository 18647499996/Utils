package com.liudonghan.main.activity.error;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADCrashErrorManager;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ErrorActivity extends ADBaseActivity<ErrorPresenter> implements ErrorContract.View {

    @BindView(R.id.activity_error_tv_content)
    TextView activityErrorTvContent;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_error;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(this).setMiddleTitleBgRes("异常捕获");
    }

    @Override
    protected ErrorPresenter createPresenter() throws RuntimeException {
        return (ErrorPresenter) new ErrorPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        activityErrorTvContent.setText(ADCrashErrorManager.getErrorDetailsFromIntent(this, getIntent()));

    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(ErrorContract.Presenter presenter) {
        mPresenter = (ErrorPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }
}
