package com.liudonghan.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.html.HtmlTextActivity;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBaseExceptionManager;
import com.liudonghan.mvp.ADBaseRequestResult;
import com.liudonghan.utils.ADAnimationUtils;
import com.liudonghan.utils.ADApplicationUtils;
import com.liudonghan.utils.ADCountDownUtils;
import com.liudonghan.utils.ADCursorManageUtils;
import com.liudonghan.utils.ADFormatUtils;
import com.liudonghan.utils.ADIntentManager;
import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.utils.ADScreenUtils;
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
    private TextView ep;
    private ImageView imageView;

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
        imageView = (ImageView) findViewById(R.id.img1);
        ep = (TextView) findViewById(R.id.textview);
        imageView.setImageResource(ADApplicationUtils.getAppIcon());
        ADTextStyleUtils.getInstance().setTextEllipsis(this, ep, "在神舟十六号载人飞行任务新闻发布会上，林西强表示，近期，我国载人月球探测工程登月阶段任务已启动实施，计划在2030年前实现中国人首次登陆月球，开展月球科学考察及相关技术试验，突破掌握载人地月往返、月面短期驻留、人机联合探测等关键技术，完成“登、巡、采、研、回”等多重任务，形成独立自主的载人月球探测能力。", "…更多");
        findViewById(R.id.btn_1).setOnClickListener(v -> networkReceive = ADNetworkUtils.getInstance().setNetworkListener(MainActivity.this, MainActivity.this));
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADIntentManager.getInstance()
                        .from(MainActivity.this)
                        .startClass(TestActivity.class)
                        .putExt("int", 99)
                        .builder();
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(v -> ADNetworkUtils.getInstance().unregisterReceiver(MainActivity.this, networkReceive));
        // 我通过了你的好友验证请求，12现在我们可以开始聊天了   13534536434和18647499996和15210176281
        ADTextStyleUtils.getInstance().setCompoundDrawables(this, textView, R.mipmap.ic_launcher, 0, R.mipmap.ic_launcher, 0);
        Log.d("Mac_Liu", "验证字符串：" + ADRegexUtils.getInstance().getMobileAcute("王莹，1*讯飞耳机，中通:73198854716123"));
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
        });
        ADScreenUtils.getInstance().register(MainActivity.this, isScreenRecord -> Log.d("录屏监听：", String.valueOf(isScreenRecord)));
        ADPicturePhotoUtils.getInstance().init(this).onCallBack(this);
        Log.i("Mac_Liu", "ip address " + ADNetworkUtils.getInstance().getIPAddress(true));
        findViewById(R.id.btn_4).setOnClickListener(v -> Observable.unsafeCreate((Observable.OnSubscribe<List<ADCursorManageUtils.ImageFolderModel>>) subscriber -> {
            List<ADCursorManageUtils.ImageFolderModel> contentProviderList = ADCursorManageUtils.getInstance(this).getImageFolder();
            subscriber.onNext(contentProviderList);
        })
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ADBaseRequestResult<List<ADCursorManageUtils.ImageFolderModel>>() {
                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(ADBaseExceptionManager.ApiException e) {

                    }

                    @Override
                    protected void onNextListener(List<ADCursorManageUtils.ImageFolderModel> adFileModels) {
                        Log.i("Mac_Liu", adFileModels.toString());
                        videoAdapter.setNewData(adFileModels);
                    }
                }));
        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ADCursorManageUtils.ImageFolderModel item = videoAdapter.getItem(position);
                Log.i("MAC_LIU", item.getMediaPath().toString());
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(v -> ADIntentManager.getInstance()
                .from(MainActivity.this)
                .startClass(NotificationActivity.class)
                .builder());
        Button button7 = (Button) findViewById(R.id.btn_7);
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADAnimationUtils.getInstance().startStretchWidthAnim(findViewById(R.id.btn_7), button7.getWidth(), button7.getWidth() + 300, 500);
            }
        });
        findViewById(R.id.btn_9).setOnClickListener(v -> ADIntentManager.getInstance()
                .from(MainActivity.this)
                .startClass(GreenDaoActivity.class)
                .builder());
        Button button = (Button) findViewById(R.id.btn_10);
        button.setText(ADFormatUtils.getInstance().decimalFormatNumber(10000000));
        findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADIntentManager.getInstance()
                        .from(MainActivity.this)
                        .startClass(HtmlTextActivity.class)
                        .builder();
            }
        });
        findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADCountDownUtils.getInstance()
                        .find((Button) findViewById(R.id.btn_12))
                        .setInterval(1000)
                        .setTime(60)
                        .setDesc("获取验证码")
                        .setPostfix("s")
                        .request(new ADCountDownUtils.Builder.OnADCountDownUtilsListener() {

                            @Override
                            public void onTick(String time, long countDown, int total, int interval) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        }).start();
            }
        });
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