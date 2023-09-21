package com.liudonghan.main.activity.date;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.adapter.MainMenuAdapter;
import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADFormatUtils;
import com.liudonghan.view.recycler.ADRecyclerView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class DateActivity extends ADBaseActivity<DatePresenter> implements DateContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.activity_date_rv)
    ADRecyclerView activityDateRv;
    @BindView(R.id.activity_date_btn)
    Button activityDateBtn;

    private MainMenuAdapter mainMenuAdapter;

    private String[] strings = new String[]{"7天以前日期", "保留两位小数"};

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_date;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected DatePresenter createPresenter() throws RuntimeException {
        return (DatePresenter) new DatePresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        immersionBar.transparentStatusBar().init();
        mainMenuAdapter = new MainMenuAdapter(R.layout.item_menu);
        activityDateRv.setAdapter(mainMenuAdapter);
        mainMenuAdapter.setNewData(Arrays.asList(strings));
    }

    @Override
    protected void addListener() throws RuntimeException {
        mainMenuAdapter.setOnItemClickListener(this);
    }

    //    @OnClick(R.id.activity_date_btn_1)
    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {
//        switch (view.getId()) {
//            case R.id.activity_date_btn_1:
//                activityDateBtn1.setText(ADFormatUtils.getInstance().getBeforeDay(7));
//                break;
//        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(DateContract.Presenter presenter) {
        mPresenter = (DatePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                // 7天以前日期
                activityDateBtn.setText(ADFormatUtils.getInstance().getBeforeDay(7));
                break;
            case 1:
                // 保留两位小数
                activityDateBtn.setText(ADFormatUtils.getInstance().retain2Decimal("1988"));
                break;
            default:
                break;
        }
    }
}
