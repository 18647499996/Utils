package com.liudonghan.main.activity;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class RegexPresenter extends ADBaseSubscription<RegexContract.View> implements RegexContract.Presenter {


    RegexPresenter(RegexContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}