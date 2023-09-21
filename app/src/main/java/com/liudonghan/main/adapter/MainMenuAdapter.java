package com.liudonghan.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.main.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/21/23
 */
public class MainMenuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_menu,item);
    }
}
