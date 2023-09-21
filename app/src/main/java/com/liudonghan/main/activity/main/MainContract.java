package com.liudonghan.main.activity.main;

import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public interface MainContract {

    interface View extends ADBaseView<Presenter>{

    }

    interface Presenter extends ADBasePresenter{

    }
}
