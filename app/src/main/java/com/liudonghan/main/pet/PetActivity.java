package com.liudonghan.main.pet;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.recycler.ADRecyclerView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class PetActivity extends ADBaseActivity<PetPresenter> implements PetContract.View {

    @BindView(R.id.activity_pet_rv)
    ADRecyclerView activityPetRv;

    private PetAdapter petAdapter;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_pet;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected PetPresenter createPresenter() throws RuntimeException {
        return (PetPresenter) new PetPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        petAdapter = new PetAdapter(R.layout.item_pet);
        activityPetRv.setAdapter(petAdapter);
        mPresenter.getPetList();
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(PetContract.Presenter presenter) {
        mPresenter = (PetPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }
}
