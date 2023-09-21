package com.liudonghan.main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.R;
import com.liudonghan.main.adapter.MainMenuAdapter;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.view.radius.ADTextView;
import com.liudonghan.view.recycler.ADRecyclerView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class RegexActivity extends ADBaseActivity<RegexPresenter> implements RegexContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.activity_regex_style_rv)
    ADRecyclerView activityRegexStyleRv;
    @BindView(R.id.activity_regex_style_btn)
    Button activityRegexStyleBtn;

    private MainMenuAdapter mainMenuAdapter;
    private String[] strings = new String[]{"格式化金额", "格式化银行卡", "手机号码脱敏"};

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_regex;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected RegexPresenter createPresenter() throws RuntimeException {
        return (RegexPresenter) new RegexPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mainMenuAdapter = new MainMenuAdapter(R.layout.item_menu);
        activityRegexStyleRv.setAdapter(mainMenuAdapter);
        mainMenuAdapter.setNewData(Arrays.asList(strings));
    }

    @Override
    protected void addListener() throws RuntimeException {
        mainMenuAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(RegexContract.Presenter presenter) {
        mPresenter = (RegexPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                activityRegexStyleBtn.setText(ADRegexUtils.getInstance().decimalFormatNumber(309876566));
                break;
            case 1:
                activityRegexStyleBtn.setText(ADRegexUtils.getInstance().decimalFormatBank("6226220142005874"));
                break;
            case 2:
                activityRegexStyleBtn.setText(ADRegexUtils.getInstance().getMobileAcute("王莹，1*讯飞耳机，中通:73198854716123 18647499996"));
                break;
            default:
                break;
        }
    }
}
