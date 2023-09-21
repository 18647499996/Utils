package com.liudonghan.main;

import com.liudonghan.db.entity.PetEntity;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/20/23
 */
public class PetModel {
    private List<PetEntity> list;

    public List<PetEntity> getList() {
        return list;
    }

    public void setList(List<PetEntity> list) {
        this.list = list;
    }
}
