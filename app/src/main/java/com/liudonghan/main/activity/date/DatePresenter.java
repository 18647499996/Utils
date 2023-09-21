package com.liudonghan.main.activity.date;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class DatePresenter extends ADBaseSubscription<DateContract.View> implements DateContract.Presenter {


    DatePresenter(DateContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}