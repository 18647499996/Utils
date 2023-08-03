package com.liudonghan.db;


import com.liudonghan.db.dao.HomeEntityDao;
import com.liudonghan.db.entity.HomeEntity;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;

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
     * @param homeEntity 首页数据
     * @return long 自增id
     */
    public long saveHomeEntity(HomeEntity homeEntity) {
        return GreenDaoManager.getInstance().baseHomeDao().insertOrReplace(homeEntity);
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
                .where(HomeEntityDao.Properties.Id.in(12, 14))
                .orderAsc(HomeEntityDao.Properties.Sort)
                .build()
                .list();
    }

    /**
     * 获取首页列表（ 分页查询 ）
     *
     * @param page  页数 todo 这里默认从第0页开始
     * @param limit 分页大小
     * @return List<HomeEntity>
     */
    public List<HomeEntity> getHomeList(int page, int limit) {
        return GreenDaoManager
                .getInstance()
                .baseHomeDao()
                .queryBuilder()
                .limit(limit)
                .offset(page)
                .orderAsc(HomeEntityDao.Properties.Sort)
                .build()
                .list();
    }

    /**
     * 根据ID查询首页数据
     *
     * @param id 自增ID
     * @return HomeEntity
     */
    public HomeEntity findHomeEntityById(int id) {
        QueryBuilder<HomeEntity> homeEntityQueryBuilder = GreenDaoManager
                .getInstance()
                .baseHomeDao()
                .queryBuilder();
        homeEntityQueryBuilder.where(HomeEntityDao.Properties.Id.eq(id));
        homeEntityQueryBuilder.join(HomeEntityDao.Properties.Id, HomeEntity.class, HomeEntityDao.Properties.Id);
        return homeEntityQueryBuilder.build().unique();
    }
}
