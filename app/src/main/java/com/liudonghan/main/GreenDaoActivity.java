package com.liudonghan.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.db.entity.HomeEntity;
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
    private int page = 0;
    private int limit = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        greenDaoManager = GreenDaoManager.getInstance();
        findViewById(R.id.add).setOnClickListener(v -> {
            String json = ADGsonUtils.getJson(this, "Home.json");
            List<NewHomePageListModel> newHomePageListModels = ADGsonUtils.jsonArrayList(json, NewHomePageListModel.class);
            for (int i = 0; i < newHomePageListModels.size(); i++) {
                HomeLocalStorageServer.getInstance().saveHomeEntity(new HomeEntity(
                        newHomePageListModels.get(i).getId(),
                        newHomePageListModels.get(i).getName(),
                        newHomePageListModels.get(i).getSubtitle(),
                        ADGsonUtils.toJson(newHomePageListModels.get(i).getContent()),
                        true,
                        newHomePageListModels.get(i).getJump_path(),
                        newHomePageListModels.get(i).getModel_type(),
                        newHomePageListModels.get(i).getSort()));
            }
            List<HomeEntity> homeEntity = HomeLocalStorageServer.getInstance().getHomeList();
            for (int i = 0; i < homeEntity.size(); i++) {
                Log.i("Mac_Liu", homeEntity.get(i).toString());
            }
        });

        findViewById(R.id.query).setOnClickListener(v -> {
            page = page + limit;
            List<HomeEntity> homeEntity = HomeLocalStorageServer.getInstance().getHomeList(page, limit);
            for (int i = 0; i < homeEntity.size(); i++) {
                Log.i("Mac_Liu", homeEntity.get(i).toString());
            }
        });
        findViewById(R.id.unique).setOnClickListener(v -> {
            HomeEntity homeEntityById = HomeLocalStorageServer.getInstance().findHomeEntityById(12);
            HomeEntity homeEntity = homeEntityById.getHomeEntity();
            Log.i("Mac_Liu",homeEntityById.toString());
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.delete).setOnClickListener(v -> {
            greenDaoManager.baseHomeDao().deleteAll();
        });
    }
}
