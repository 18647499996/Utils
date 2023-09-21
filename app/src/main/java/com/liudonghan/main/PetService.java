package com.liudonghan.main;

import com.liudonghan.db.entity.PetEntity;
import com.liudonghan.mvp.ADBaseResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/20/23
 */
public interface PetService {

    @GET("fapigx/pet/query?")
    Observable<ADBaseResult<PetModel>> getPetList(@Query("key") String key, @Query("page") int page, @Query("num") int limit);
}
