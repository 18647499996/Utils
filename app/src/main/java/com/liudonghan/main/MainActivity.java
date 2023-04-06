package com.liudonghan.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBaseExceptionManager;
import com.liudonghan.mvp.ADBaseRequestResult;
import com.liudonghan.utils.ADContentProviderUtils;
import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.utils.ADScreenRecordUtils;
import com.liudonghan.utils.ADTextStyleUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends ADBaseActivity<MainPresenter> implements MainContract.View, ADPicturePhotoUtils.ADImageFileCallback, ADNetworkUtils.OnNetworkUtilsChangeListener {

    private Button textView;
    private ADNetworkUtils.NetworkReceive networkReceive;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_main;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected MainPresenter createPresenter() throws RuntimeException {
        return (MainPresenter) new MainPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        Log.e("Mac_Liu", "activity路径：" + getLocalClassName());
        videoAdapter = new VideoAdapter(R.layout.item_video);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(videoAdapter);
        textView = (Button) findViewById(R.id.btn_3);
        findViewById(R.id.btn_1).setOnClickListener(v -> networkReceive = ADNetworkUtils.getInstance().setNetworkListener(MainActivity.this, MainActivity.this));
        findViewById(R.id.btn_2).setOnClickListener(v -> ADNetworkUtils.getInstance().unregisterReceiver(MainActivity.this, networkReceive));
        // 我通过了你的好友验证请求，12现在我们可以开始聊天了   13534536434和18647499996和15210176281
        Log.d("Mac_Liu", "验证字符串：" + ADRegexUtils.getInstance().getMobileAcute("电话：18647499996 欢迎来电"));
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            this.startActivity(intent);
            ADTextStyleUtils.getInstance()
                    // 上下文
                    .from(MainActivity.this)
                    // 构建view
                    .findView(textView)
                    // 原始文本属性
                    .originAttr(22, R.color.colorAccent)
                    // 改变文本属性
                    .changeAttr(14, R.color.colorPrimary)
                    // 改变位置
                    .changePosition(0, 1)
                    // 设置文本图标属性
                    .drawablesIconAttr(R.mipmap.ic_launcher)
                    // 设置图标大小
                    .drawablesSize(textView.getHeight(), textView.getHeight())
                    // 设置文本
                    .textDescription("￥18990.00")
                    // 构建
                    .builder();
            ADScreenRecordUtils.getInstance().register(MainActivity.this, isScreenRecord -> Log.d("录屏监听：", String.valueOf(isScreenRecord)));
        });
        ADPicturePhotoUtils.getInstance().init(this).onCallBack(this);
        Log.i("Mac_Liu", "ip address " + ADNetworkUtils.getInstance().getIPAddress(true));
        findViewById(R.id.btn_4).setOnClickListener(v -> Observable.unsafeCreate((Observable.OnSubscribe<List<ADContentProviderUtils.ADFileModel>>) subscriber -> {
            List<ADContentProviderUtils.ADFileModel> contentProviderList = ADContentProviderUtils.getInstance(this).getFileModel(ADContentProviderUtils.ContentType.pdf);
            subscriber.onNext(contentProviderList);
        })
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ADBaseRequestResult<List<ADContentProviderUtils.ADFileModel>>() {
                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(ADBaseExceptionManager.ApiException e) {

                    }

                    @Override
                    protected void onNextListener(List<ADContentProviderUtils.ADFileModel> adFileModels) {
                        Log.i("Mac_Liu", adFileModels.toString());
                        videoAdapter.setNewData(adFileModels);
                    }
                }));
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException, IOException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void handleResult(File file) {

    }

    @Override
    public void onConnect(ADNetworkUtils.NetworkType netType) {
        switch (netType) {
            case NETWORK_5G:
                Log.d("Mac_Liu", "网络监听：5G");
                break;
            case NETWORK_WIFI:
                Log.d("Mac_Liu", "网络监听：WIFI");
                break;
            case NETWORK_2G:
                Log.d("Mac_Liu", "网络监听：2G");
                break;
            case NETWORK_3G:
                Log.d("Mac_Liu", "网络监听：3G");
                break;
            case NETWORK_4G:
                Log.d("Mac_Liu", "网络监听：4G");
                break;
            default:
                Log.d("Mac_Liu", "网络监听：无网络");
                break;
        }
    }

    @Override
    public void onDisConnect() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = (MainPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}