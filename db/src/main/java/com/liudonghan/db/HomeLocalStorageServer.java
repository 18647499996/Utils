package com.liudonghan.db;



import com.liudonghan.db.dao.HomeEntityDao;
import com.liudonghan.db.entity.HomeEntity;
import com.liudonghan.db.entity.NewHomePageListModel;
import com.liudonghan.utils.ADGsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:7/19/23
 */
public class HomeLocalStorageServer {


    private static volatile HomeLocalStorageServer instance = null;

    private HomeLocalStorageServer() {
    }

    public static HomeLocalStorageServer getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (HomeLocalStorageServer.class) {
                // double checkout
                if (null == instance) {
                    instance = new HomeLocalStorageServer();
                }
            }
        }
        return instance;
    }

    /**
     * 保存首页数据
     *
     * @param newHomePageListModels 数据类型
     */
    public void saveHomeEntity(List<NewHomePageListModel> newHomePageListModels) {
        List<HomeEntity> homeEntityList = new ArrayList<>();
        for (int i = 0; i < newHomePageListModels.size(); i++) {
            homeEntityList.add(new HomeEntity(
                    newHomePageListModels.get(i).getId(),
                    newHomePageListModels.get(i).getName(),
                    newHomePageListModels.get(i).getSubtitle(),
                    ADGsonUtils.toJson(newHomePageListModels.get(i).getContent()),
                    true,
                    newHomePageListModels.get(i).getJump_path(),
                    newHomePageListModels.get(i).getModel_type(),
                    newHomePageListModels.get(i).getSort()));
        }
        GreenDaoManager.getInstance().baseHomeDao().insertOrReplaceInTx(homeEntityList);
    }

    /**
     * 获取首页列表
     *
     * @return List<HomeEntity>
     */
    public List<HomeEntity> getHomeList() {
        return GreenDaoManager
                .getInstance()
                .baseHomeDao()
                .queryBuilder()
                .orderAsc(HomeEntityDao.Properties.Sort)
                .build()
                .list();
    }
}
