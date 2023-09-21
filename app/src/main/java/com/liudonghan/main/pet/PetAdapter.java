package com.liudonghan.main.pet;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.db.entity.PetEntity;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/20/23
 */
public class PetAdapter extends BaseQuickAdapter<PetEntity, BaseViewHolder> {


    public PetAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PetEntity item) {

    }
}
