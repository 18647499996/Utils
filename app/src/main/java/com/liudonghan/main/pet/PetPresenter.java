package com.liudonghan.main.pet;

import com.liudonghan.db.GreenDaoManager;
import com.liudonghan.db.entity.PetEntity;
import com.liudonghan.main.HttpUrl;
import com.liudonghan.main.PetModel;
import com.liudonghan.main.PetService;
import com.liudonghan.mvp.ADBaseExceptionManager;
import com.liudonghan.mvp.ADBaseLogInterceptor;
import com.liudonghan.mvp.ADBaseRequestResult;
import com.liudonghan.mvp.ADBaseRetrofitManager;
import com.liudonghan.mvp.ADBaseSubscription;
import com.liudonghan.mvp.ADBaseTransformerManager;

import java.util.List;

import okhttp3.OkHttpClient;
import rx.functions.Action1;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class PetPresenter extends ADBaseSubscription<PetContract.View> implements PetContract.Presenter {


    PetPresenter(PetContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void getPetList() {
        ADBaseRetrofitManager
                .getInstance()
                .baseHttpUrl(HttpUrl.HTTP_URL)
                .baseOkHttpClient(new OkHttpClient.Builder().addInterceptor(new ADBaseLogInterceptor()).build())
                .baseRetrofitManager(PetService.class)
                .getPetList(HttpUrl.PET_KET, 1, 10)
                .compose(ADBaseTransformerManager.defaultSchedulers())
                .doOnNext(new Action1<PetModel>() {
                    @Override
                    public void call(PetModel petModel) {
                        for (int i = 0; i < petModel.getList().size(); i++) {
                            GreenDaoManager.getInstance()
                                    .getDaoSession()
                                    .getPetEntityDao()
                                    .insertOrReplace(petModel.getList().get(i));
                        }
                    }
                })
                .subscribe(new ADBaseRequestResult<PetModel>() {
                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(ADBaseExceptionManager.ApiException e) {

                    }

                    @Override
                    protected void onNextListener(PetModel petModel) {

                    }
                });
    }
}