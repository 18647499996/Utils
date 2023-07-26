package com.liudonghan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.liudonghan.db.dao.DaoMaster;
import com.liudonghan.db.dao.DaoSession;
import com.liudonghan.db.dao.HomeEntityDao;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:7/15/23
 */
public class GreenDaoManager {

    private DaoMaster daoMaster;

    private static volatile GreenDaoManager instance = null;

    private GreenDaoManager() {
    }

    public static GreenDaoManager getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (GreenDaoManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new GreenDaoManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化GreenDao数据库
     *
     * @param context 上下文
     * @param dbName  数据库名称
     * @return
     */
    public GreenDaoManager init(Context context, String dbName) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, dbName);
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(writableDatabase);
        return this;
    }

    /**
     * 获取数据库版本
     *
     * @return 版本号
     */
    public int getSchemaVersion() {
        return daoMaster.getSchemaVersion();
    }

    /**
     * 获取DaoSession引用
     *
     * @return DaoSession
     */
    public DaoSession getDaoSession() {
        return daoMaster.newSession();
    }

    /**
     * todo 本地存储（ 首页 ）
     *
     * @return HomeEntityDao
     */
    public HomeEntityDao baseHomeDao() {
        return daoMaster.newSession().getHomeEntityDao();
    }

}
