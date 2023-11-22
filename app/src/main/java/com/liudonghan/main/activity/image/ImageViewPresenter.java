package com.liudonghan.main.activity.image;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ImageViewPresenter extends ADBaseSubscription<ImageViewContract.View> implements ImageViewContract.Presenter {


    ImageViewPresenter(ImageViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}