package com.liudonghan.db.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.liudonghan.db.dao.HomeEntityDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.liudonghan.db.dao.DaoSession;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:7/19/23
 */
@Entity
public class HomeEntity implements MultiItemEntity {
    @Id()
    private long id;
    private String name;
    private String subName;
    private String contentJson;
    private boolean isShow;
    private String path;
    private int category;
    private int sort;
    @ToOne
    @JoinEntity(entity = HomeEntity.class, sourceProperty = "id", targetProperty = "id")
    private HomeEntity homeEntity;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1756422075)
    private transient HomeEntityDao myDao;
    @Generated(hash = 145346657)
    private transient boolean homeEntity__refreshed;

    @Generated(hash = 1001455)
    public HomeEntity(long id, String name, String subName, String contentJson,
                      boolean isShow, String path, int category, int sort) {
        this.id = id;
        this.name = name;
        this.subName = subName;
        this.contentJson = contentJson;
        this.isShow = isShow;
        this.path = path;
        this.category = category;
        this.sort = sort;
    }

    @Generated(hash = 1364639484)
    public HomeEntity() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return this.subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getContentJson() {
        return this.contentJson;
    }

    public void setContentJson(String contentJson) {
        this.contentJson = contentJson;
    }

    public boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1556928172)
    public HomeEntity getHomeEntity() {
        if (homeEntity != null || !homeEntity__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HomeEntityDao targetDao = daoSession.getHomeEntityDao();
            targetDao.refresh(homeEntity);
            homeEntity__refreshed = true;
        }
        return homeEntity;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 774513748)
    public HomeEntity peakHomeEntity() {
        return homeEntity;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 352352079)
    public void setHomeEntity(HomeEntity homeEntity) {
        synchronized (this) {
            this.homeEntity = homeEntity;
            homeEntity__refreshed = true;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 437629794)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHomeEntityDao() : null;
    }

    @Override
    public String toString() {
        return "HomeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", isShow=" + isShow +
                ", path='" + path + '\'' +
                ", category=" + category +
                ", sort=" + sort +
                ", homeEntity=" + homeEntity +
                '}';
    }
}
