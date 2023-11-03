package com.liudonghan.main.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.R;
import com.liudonghan.main.activity.RegexActivity;
import com.liudonghan.main.activity.date.DateActivity;
import com.liudonghan.main.activity.encrypt.EncryptActivity;
import com.liudonghan.main.activity.style.TextStyleActivity;
import com.liudonghan.main.adapter.MainMenuAdapter;
import com.liudonghan.main.adapter.VideoAdapter;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADEncryptManager;
import com.liudonghan.utils.ADFormatUtils;
import com.liudonghan.utils.ADGsonUtils;
import com.liudonghan.utils.ADIntentManager;
import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADScreenUtils;
import com.liudonghan.view.recycler.ADRecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;


public class MainActivity extends ADBaseActivity<MainPresenter> implements MainContract.View, ADPicturePhotoUtils.ADImageFileCallback, ADNetworkUtils.OnNetworkUtilsChangeListener, BaseQuickAdapter.OnItemClickListener, ADScreenUtils.OnContentObserverUtilsListener {


    @BindView(R.id.recycler_view)
    ADRecyclerView recyclerView;
    private Button textView;
    private ADNetworkUtils.NetworkReceive networkReceive;
    //    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private TextView ep;
    private ImageView imageView;
    private MainMenuAdapter mainMenuAdapter;

    private String[] stringArray = new String[]{"日期工具", "文本Style处理工具", "Intent管理器", "网络管理工具",
            "正则工具", "屏幕录制监听", "系统相册工具", "计时器工具", "加密工具", "系统设置", "异常捕获", "AES解密"};

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
        immersionBar.statusBarColor(R.color.black).statusBarDarkFont(false).titleBar(recyclerView).init();
        mainMenuAdapter = new MainMenuAdapter(R.layout.item_menu);
        recyclerView.setAdapter(mainMenuAdapter);
        mainMenuAdapter.setNewData(Arrays.asList(stringArray));
        Log.e("Mac_Liu", "activity路径：" + getLocalClassName());
        Log.i("Mac_Liu", "7天：" + ADFormatUtils.getInstance().getBeforeDay(7));

//        videoAdapter = new VideoAdapter(R.layout.item_video);
//        recyclerView = (RecyclerView) findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        recyclerView.setAdapter(videoAdapter);
//        textView = (Button) findViewById(R.id.btn_3);
//        imageView = (ImageView) findViewById(R.id.img1);
//        ep = (TextView) findViewById(R.id.textview);
//        imageView.setImageResource(ADApplicationUtils.getAppIcon());
//        findViewById(R.id.btn_1).setOnClickListener(v -> networkReceive = ADNetworkUtils.getInstance().setNetworkListener(MainActivity.this, MainActivity.this));
//        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });
//        findViewById(R.id.btn_2).setOnClickListener(v -> ADNetworkUtils.getInstance().unregisterReceiver(MainActivity.this, networkReceive));
//        textView.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, TestActivity.class);
//            this.startActivity(intent);

//        });
        ADPicturePhotoUtils.getInstance().init(this).onCallBack(this);
//        Log.i("Mac_Liu", "ip address " + ADNetworkUtils.getInstance().getIPAddress(true));
//        findViewById(R.id.btn_4).setOnClickListener(v -> Observable.unsafeCreate((Observable.OnSubscribe<List<ADCursorManageUtils.ImageFolderModel>>) subscriber -> {
//            List<ADCursorManageUtils.ImageFolderModel> contentProviderList = ADCursorManageUtils.getInstance(this).getImageFolder();
//            subscriber.onNext(contentProviderList);
//        })
//                .subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ADBaseRequestResult<List<ADCursorManageUtils.ImageFolderModel>>() {
//                    @Override
//                    protected void onCompletedListener() {
//
//                    }
//
//                    @Override
//                    protected void onErrorListener(ADBaseExceptionManager.ApiException e) {
//
//                    }
//
//                    @Override
//                    protected void onNextListener(List<ADCursorManageUtils.ImageFolderModel> adFileModels) {
//                        Log.i("Mac_Liu", adFileModels.toString());
//                        videoAdapter.setNewData(adFileModels);
//                    }
//                }));
//        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ADCursorManageUtils.ImageFolderModel item = videoAdapter.getItem(position);
//                Log.i("MAC_LIU", item.getMediaPath().toString());
//            }
//        });
//        findViewById(R.id.btn_6).setOnClickListener(v -> ADIntentManager.getInstance()
//                .from(MainActivity.this)
//                .startClass(NotificationActivity.class)
//                .builder());
//        Button button7 = (Button) findViewById(R.id.btn_7);
//        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ADAnimationUtils.getInstance().startStretchWidthAnim(findViewById(R.id.btn_7), button7.getWidth(), button7.getWidth() + 300, 500);
//            }
//        });
//        findViewById(R.id.btn_9).setOnClickListener(v -> ADIntentManager.getInstance()
//                .from(MainActivity.this)
//                .startClass(GreenDaoActivity.class)
//                .builder());
//        Button button = (Button) findViewById(R.id.btn_10);
//        Button button13 = (Button) findViewById(R.id.btn_13);
//        findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ADIntentManager.getInstance()
//                        .from(MainActivity.this)
//                        .startClass(HtmlTextActivity.class)
//                        .builder();
//            }
//        });
//        findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ADCountDownUtils.getInstance()
//                        .find((Button) findViewById(R.id.btn_12))
//                        .setInterval(1000)
//                        .setTime(60)
//                        .setDesc("获取验证码")
//                        .setPostfix("s")
//                        .request(new ADCountDownUtils.Builder.OnADCountDownUtilsListener() {
//
//                            @Override
//                            public void onTick(String time, long countDown, int total, int interval) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//
//                            }
//                        }).start();
//            }
//        });
//        findViewById(R.id.btn_14).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PetActivity.startActivity(MainActivity.this, PetActivity.class);
//            }
//        });
    }

    @Override
    protected void addListener() throws RuntimeException {
        mainMenuAdapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                // 日期工具
                DateActivity.startActivity(this, DateActivity.class);
                break;
            case 1:
                // 文本Style工具
                TextStyleActivity.startActivity(this, TextStyleActivity.class);
                break;
            case 2:
                // intent管理器
                ADIntentManager.getInstance()
                        .from(this)
                        .startAction("test")
                        .putExt("int", 99)
                        .builder();
                break;
            case 3:
                // 网络管理工具
                ADNetworkUtils.getInstance().setNetworkListener(this, this);
                break;
            case 4:
                // 正则工具
                RegexActivity.startActivity(this, RegexActivity.class);
                break;
            case 5:
                // 屏幕录制工具
                ADScreenUtils.getInstance().register(this, this);
                break;
            case 6:
                // 系统相册工具
                ADPicturePhotoUtils.getInstance().takePicture();
                break;
            case 7:
                // 计时器工具
                break;
            case 8:
                // 加密工具
                EncryptActivity.startActivity(this, EncryptActivity.class);
                break;
            case 9:
                // 系统设置
                break;
            case 10:
                // 异常捕获
                imageView.getDrawable().setBounds(0, 0, 0, 0);
                break;
            case 11:
                String encrypt = ADEncryptManager.getInstance().encryptECB("cretinzp**273846", "{targetId=30}");
                Log.w("Mac_Liu", "AES加密：" + encrypt);
                String decrypt = ADEncryptManager.getInstance().decryptECB("2oxS7wXkFk0oAx/nG7NCare3A9I7VMIubu+tDyiB8r2mjJkUxtFFgsi8wAqhBxx8NOfl4wyyMSBotxlNJDg6A9uKf8cu000NswYzMgNqy2w=", "cretinzp**273846");
                Log.w("Mac_Liu", "AES解密：" + decrypt);
                break;
            default:
                break;
        }
    }

    @Override
    public void isScreenRecord(boolean isScreenRecord) {
        Log.d("录屏监听：", String.valueOf(isScreenRecord));
    }
}