package com.liudonghan.main;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.utils.ADNetworkUtils;

import java.io.IOException;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public class BaseActivity<P> extends ADBaseActivity<ADBasePresenter> implements ADNetworkUtils.OnNetworkUtilsChangeListener {

    protected P mPresenter;
    protected ADNetworkUtils.NetworkReceive networkReceive;

    @Override
    protected int getLayout() throws RuntimeException {
        return 0;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADBasePresenter createPresenter() throws RuntimeException {
        return (ADBasePresenter) mPresenter;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        networkReceive = ADNetworkUtils.getInstance().setNetworkListener(this, this);
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException, IOException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {
        ADNetworkUtils.getInstance().unregisterReceiver(this,networkReceive);
    }

    @Override
    public void onConnect(ADNetworkUtils.NetworkType netType) {

    }

    @Override
    public void onDisConnect() {

    }
}
