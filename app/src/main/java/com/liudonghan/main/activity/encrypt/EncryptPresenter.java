package com.liudonghan.main.activity.encrypt;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class EncryptPresenter extends ADBaseSubscription<EncryptContract.View> implements EncryptContract.Presenter {


    EncryptPresenter(EncryptContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}