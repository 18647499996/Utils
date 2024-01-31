package com.liudonghan.main.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.main.OfferBean;
import com.liudonghan.main.R;
import com.liudonghan.main.activity.RegexActivity;
import com.liudonghan.main.activity.date.DateActivity;
import com.liudonghan.main.activity.encrypt.EncryptActivity;
import com.liudonghan.main.activity.html.HtmlTextActivity;
import com.liudonghan.main.activity.image.ImageViewActivity;
import com.liudonghan.main.activity.style.TextStyleActivity;
import com.liudonghan.main.adapter.MainMenuAdapter;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.utils.ADEncryptManager;
import com.liudonghan.utils.ADFormatUtils;
import com.liudonghan.utils.ADGsonUtils;
import com.liudonghan.utils.ADIntentManager;
import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADPermissionManager;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADScreenUtils;
import com.liudonghan.utils.ADSignatureManager;
import com.liudonghan.view.recycler.ADRecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends ADBaseActivity<MainPresenter> implements MainContract.View, ADPicturePhotoUtils.ADImageFileCallback, ADNetworkUtils.OnNetworkUtilsChangeListener, BaseQuickAdapter.OnItemClickListener, ADScreenUtils.OnContentObserverUtilsListener {


    @BindView(R.id.recycler_view)
    ADRecyclerView recyclerView;

    private ImageView imageView;
    private MainMenuAdapter mainMenuAdapter;

    private String[] stringArray = new String[]{"日期工具", "文本Style处理工具", "Intent管理器", "网络管理工具",
            "正则工具", "屏幕录制监听", "系统相册工具", "计时器工具", "加密工具", "系统设置", "异常捕获", "AES解密",
            "Html", "图片工具", "json", "应用签名","权限"};
    private String json = "typenews1([\n" +
            "    {\n" +
            "        \"uk\": \"n231124145618627\",\n" +
            "        \"url\": \"/nsa/n231124145618627.html\",\n" +
            "        \"topic\": \"2006年，孙俪资助4年的“白眼狼”向海清，已经走上另一条不归路\",\n" +
            "        \"type_py\": \"yule\",\n" +
            "        \"type_zh\": \"娱乐\",\n" +
            "        \"type_en\": \"ent\",\n" +
            "        \"source\": \"峰哥正读\",\n" +
            "        \"show_date\": \"2023-11-24 14:56\",\n" +
            "        \"img169List\": [\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/918x815_656049170f4e0_mwpm_03201609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/1280x892_6560491705b24_mwpm_03201609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/960x686_656049170a8d0_mwpm_03201609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/1021x367_656049170797b_mwpm_03201609.jpeg\"\n" +
            "        ],\n" +
            "        \"img169550List\": [\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/918x815_656049170f4e0_mwpm_05501609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/1280x892_6560491705b24_mwpm_05501609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/960x686_656049170a8d0_mwpm_05501609.jpeg\",\n" +
            "            \"//minipc.eastday.com/ecms/thumbimg/20231124/1021x367_656049170797b_mwpm_05501609.jpeg\"\n" +
            "        ],\n" +
            "        \"desc\": \"你敢想象，农夫与蛇的故事，会上演在现实生活中。\",\n" +
            "        \"page_count\": 11,\n" +
            "        \"leader_news\": false,\n" +
            "        \"original_source\": \"\",\n" +
            "        \"isHot\": 1,\n" +
            "        \"is_hot\": 1\n" +
            "    }\n" +
            " \n" +
            "])";

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
        ADPicturePhotoUtils.getInstance().init(this).onCallBack(this);
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
                String base64Encode = ADEncryptManager.getInstance().encryptBase64("https://www.bilibili.com/video/BV1HG411Y7W1/");
                Log.w("Mac_Liu", "Base64加密：" + base64Encode);
                String base64Decode = ADEncryptManager.getInstance().decryptBase64(base64Encode);
                Log.w("Mac_Liu", "Base64解密：" + base64Decode);
                break;
            case 12:
                HtmlTextActivity.startActivity(this, HtmlTextActivity.class);
                break;
            case 13:
                ImageViewActivity.startActivity(this, ImageViewActivity.class);
                break;
            case 14:
                String replace = json.replace("typenews1(", "").replace(")", "");
                Log.i("Mac_Liu", "JSON 字符串：" + replace);
                List<OfferBean> offerBeans = ADGsonUtils.jsonArrayList(replace, OfferBean.class);
                Log.i("Mac_Liu", "JSON 序列化：" + offerBeans.toString());
                Log.i("Mac_Liu", "列表数据：" + offerBeans.get(0).getDesc());
                Log.i("Mac_Liu", str.replaceAll("\\\\\"", ""));
                break;
            case 15:
                Log.i("Mac_Liu", ADSignatureManager.getInstance().getSHA256(this) + "\n" + ADSignatureManager.getInstance().getMD5(this) + "\n" + ADSignatureManager.getInstance().getSha1(this));
                break;
            case 16:
                ADPermissionManager.with(this)
                        .permission(ADPermissionManager.ACCESS_COARSE_LOCATION,
                                ADPermissionManager.READ_EXTERNAL_STORAGE,
                                ADPermissionManager.WRITE_EXTERNAL_STORAGE)
                        .request(new ADPermissionManager.OnPermission() {
                            @Override
                            public void hasPermission(List<String> mPermissions, boolean isAll) {
                                Log.i("Mac_Liu",mPermissions.toString() + " ----- " + isAll);
                            }

                            @Override
                            public void noPermission(List<String> failPermissions, boolean checkMorePermission) {

                            }
                        });
                break;
            default:
                break;
        }
    }

    @Override
    public void isScreenRecord(boolean isScreenRecord) {
        Log.d("录屏监听：", String.valueOf(isScreenRecord));
    }

    private String str = "\\\"https://image2.cqcb.com/2023/10/28/9aa830df0bac7513012e17307793e242.png\\\"";
}