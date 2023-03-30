package com.liudonghan.main;

import com.liudonghan.mvp.ADBaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public class MainPresenter extends ADBaseSubscription<MainContract.View> implements MainContract.Presenter {

    protected MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }
}
