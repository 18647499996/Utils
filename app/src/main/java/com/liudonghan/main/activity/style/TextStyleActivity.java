package com.liudonghan.main.activity.style;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.adapter.MainMenuAdapter;
import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADTextStyleUtils;
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
public class TextStyleActivity extends ADBaseActivity<TextStylePresenter> implements TextStyleContract.View, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.activity_text_style_rv)
    ADRecyclerView activityTextStyleRv;
    @BindView(R.id.activity_text_style_btn)
    TextView activityTextStyleBtn;

    private MainMenuAdapter mainMenuAdapter;

    private String[] strings = new String[]{"添加图标", "添加样式", "省略文本"};

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_text_style;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected TextStylePresenter createPresenter() throws RuntimeException {
        return (TextStylePresenter) new TextStylePresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mainMenuAdapter = new MainMenuAdapter(R.layout.item_menu);
        activityTextStyleRv.setAdapter(mainMenuAdapter);
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
    public void setPresenter(TextStyleContract.Presenter presenter) {
        mPresenter = (TextStylePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                ADTextStyleUtils.getInstance().setCompoundDrawables(this, activityTextStyleBtn, R.mipmap.ic_launcher, 0, R.mipmap.ic_launcher, 0);
                break;
            case 1:
                ADTextStyleUtils.getInstance()
                        // 上下文
                        .from(this)
                        // 构建view
                        .findView(activityTextStyleBtn)
                        // 原始文本属性
                        .originAttr(22, R.color.colorAccent)
                        // 改变文本属性
                        .changeAttr(14, R.color.colorPrimary)
                        // 改变位置
                        .changePosition(0, 1)
                        // 设置文本图标属性
                        .drawablesIconAttr(R.mipmap.ic_launcher)
                        // 设置文本
                        .textDescription("￥18990.00")
                        // 构建
                        .builder();
                break;
            case 2:
                // 省略文本
                ADTextStyleUtils.getInstance().setTextEllipsis(this, activityTextStyleBtn, "在神舟十六号载人飞行任务新闻发布会上，林西强表示，近期，我国载人月球探测工程登月阶段任务已启动实施，计划在2030年前实现中国人首次登陆月球，开展月球科学考察及相关技术试验，突破掌握载人地月往返、月面短期驻留、人机联合探测等关键技术，完成“登、巡、采、研、回”等多重任务，形成独立自主的载人月球探测能力。", "…");
                break;
            default:
                break;
        }
    }
}
