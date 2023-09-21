package com.liudonghan.main.activity.style;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class TextStylePresenter extends ADBaseSubscription<TextStyleContract.View> implements TextStyleContract.Presenter {


    TextStylePresenter(TextStyleContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}