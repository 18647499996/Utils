package com.liudonghan.main.activity.html;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class HtmlTextPresenter extends ADBaseSubscription<HtmlTextContract.View> implements HtmlTextContract.Presenter {


    HtmlTextPresenter(HtmlTextContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}