package com.liudonghan.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.db.entity.HomeEntity;
import com.liudonghan.db.entity.NewHomePageListModel;
import com.liudonghan.db.GreenDaoManager;
import com.liudonghan.db.HomeLocalStorageServer;
import com.liudonghan.utils.ADGsonUtils;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:7/15/23
 */
public class GreenDaoActivity extends AppCompatActivity {

    private GreenDaoManager greenDaoManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        greenDaoManager = GreenDaoManager.getInstance();
        String json = ADGsonUtils.getJson(this, "Home.json");
        List<NewHomePageListModel> newHomePageListModels = ADGsonUtils.jsonArrayList(json, NewHomePageListModel.class);
        Log.e("Mac_Liu", newHomePageListModels.toString());
        findViewById(R.id.add).setOnClickListener(v -> HomeLocalStorageServer.getInstance().saveHomeEntity(newHomePageListModels));
        findViewById(R.id.query).setOnClickListener(v -> {
            List<HomeEntity> homeEntity = HomeLocalStorageServer.getInstance().getHomeList();
            for (int i = 0; i < homeEntity.size(); i++) {
                Log.i("Mac_Liu", homeEntity.get(i).toString());
            }
        });
        findViewById(R.id.update).setOnClickListener(v -> {


        });
        findViewById(R.id.delete).setOnClickListener(v -> {
            greenDaoManager.baseHomeDao().deleteAll();
        });
    }
}
