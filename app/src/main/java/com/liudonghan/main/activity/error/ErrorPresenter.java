package com.liudonghan.main.activity.error;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ErrorPresenter extends ADBaseSubscription<ErrorContract.View> implements ErrorContract.Presenter {


    ErrorPresenter(ErrorContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}