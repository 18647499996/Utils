package com.liudonghan.db.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:7/19/23
 */
@Entity
public class HomeEntity implements MultiItemEntity {
    @Id
    private long id;
    private String name;
    private String subName;
    private String contentJson;
    private boolean isShow;
    private String path;
    private int category;
    private int sort;

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

    @Override
    public String toString() {
        return "HomeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", contentJson='" + contentJson + '\'' +
                ", isShow=" + isShow +
                ", path='" + path + '\'' +
                ", category=" + category +
                '}';
    }
    public int getSort() {
        return this.sort;
    }
    public void setSort(int sort) {
        this.sort = sort;
    }
}
