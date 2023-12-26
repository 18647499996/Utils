package com.liudonghan.main.activity.html;

import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liudonghan.main.R;
import com.liudonghan.main.bean.ImageBean;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBaseLoadingDialog;
import com.liudonghan.utils.ADGsonUtils;
import com.liudonghan.utils.ADHtmlUtils;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class HtmlTextActivity extends ADBaseActivity<HtmlTextPresenter> implements HtmlTextContract.View, ADHtmlUtils.OnADHtmlUtilsListener {

    @BindView(R.id.html)
    TextView html;
    @BindView(R.id.activity_html_img_cover)
    ImageView activityHtmlImgCover;

    private String htmlStr = "<body>\n" +
            "     <p style=\"text-align:justify;display:block;\">\n" +
            "      <video class=\"video-js  edui-upload-video  vjs-default-skin\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"https://wenhui.whb.cn/u/cms/www/202310/28074345tbwq.mp4\" data-setup=\"{}\" poster=\"https://image.whb.cn/image/default/commonCover.png\">\n" +
            "       <source src=\"https://wenhui.whb.cn/u/cms/www/202310/28074345tbwq.mp4\" type=\"video/mp4\">\n" +
            "      </video></p>\n" +
            "     <p style=\"text-align:justify;display:block;\">当地时间10月27日，第十届联合国大会紧急特别会议就巴以相关决议草案进行投票，最终草案以120票赞成、14票反对、45票弃权，同意票达到三分之二的多数票获得通过。美国、以色列等投票反对。</p>\n" +
            "     <p style=\"text-align:justify;display:block;\">该决议草案由约旦等超过48国共提。草案呼吁立即实行持久和持续的人道主义休战，立即向整个加沙地带的平民提供基本物资和服务，保护平民及国际机构，呼吁撤销以色列关于撤离北加沙的命令，谴责所有针对巴勒斯坦和以色列平民的暴力行为。</p>\n" +
            "     <p style=\"text-align:justify;display:block;\">根据议事规则，联大重要决议草案必须得到参与投票会员国（不含弃权）三分之二以上多数才可以通过。</p>\n" +
            "     <p style=\"text-align: justify; display: block;\">由于等待发言者过多，联大主席决定将决议草案投票时间提前到27日15时。据了解，第十届联合国大会紧急特别会议续会将在10月31日继续进行。</p>\n" +
            "     <p><br></p>\n" +
            "     <section class=\"whb_ueditor custom_block\" data-ignore=\"true\" style=\"width: 100%; height: auto;\">\n" +
            "      <section data-ignore=\"true\" style=\"margin: 0px auto; width: 100%; opacity: 1; transform: rotateZ(0deg);\" data-width=\"100%\" data-opacity=\"1\" data-rotate=\"0\"></section>\n" +
            "      <div data-ignore=\"true\" class=\"custom_block_bd\" style=\"text-align:center;padding: 10px 0;\">\n" +
            "       <p data-ignore=\"true\" style=\"margin: 0;line-height:1.5;font-family: PingFangSC-Regular;font-size: 14px;color: #9B9B9B;letter-spacing: 0;text-align: left;\"><span style=\"letter-spacing: 0px;\">编辑：施薇</span><br></p>\n" +
            "       <p data-ignore=\"true\" style=\"margin: 0;line-height:1.5;font-family: PingFangSC-Regular;font-size: 14px;color: #9B9B9B;letter-spacing: 0;text-align: left;\">责任编辑：孙欣祺</p>\n" +
            "       <p data-ignore=\"true\" style=\"margin: 0;line-height:1.5;font-family: PingFangSC-Regular;font-size: 14px;color: #9B9B9B;letter-spacing: 0;text-align: left;\">来源：央视新闻</p>\n" +
            "      </div>\n" +
            "      <section data-ignore=\"true\" style=\"margin: 0px auto; width: 100%; opacity: 1; transform: rotateZ(0deg);\" data-width=\"100%\" data-opacity=\"1\" data-rotate=\"0\"></section>\n" +
            "     </section>\n" +
            "    </body>";

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_html_text;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(this).setMiddleTitleBgRes("Html");
    }

    @Override
    protected HtmlTextPresenter createPresenter() throws RuntimeException {
        return (HtmlTextPresenter) new HtmlTextPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        Glide.with(this)
                .load("https://image.whb.cn/image/8918e16c892f49f3a86f4e8087e870e8.jpg")
                .into(activityHtmlImgCover);
//        ADHtmlUtils.getInstance().parseHtml(htmlStr);
        Log.i("Mac_Liu", "集合数据：" + ADGsonUtils.toJson(ADHtmlUtils.getInstance().parseHtml(htmlStr)));
        ADHtmlUtils.getInstance().parseHtml(this, string, this);

        String parser = ADHtmlUtils.getInstance()
                .from(this)
                .html(string)
                .cssQuery("p")
                .attrs("original", "alt")
                .parser();
        Log.i("Mac_Liu", "Html网页同步分享：" + parser);
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://www.lssjt.com/11/27/")
                .cssQuery("div", "ul.oh", "li", "div.pic")
                .attrs("data-original")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Log.i("Maps集合：", json);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }).get();
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://www.lssjt.com/11/27/8778.html")
                .cssQuery("div.content", "div.body")
                .attrs("src")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Log.i("历史详情：", json);

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }).get();
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://mini.eastday.com/nsa/n231124144547653.html?positionxy=242,27")
                .cssQuery("body", "div", "div.main_content", "div.article", "div.detail_left.clear-fix",
                        "div.detail_left_cnt", "div.J-contain_detail_cnt.contain_detail_cnt")
                .attrs("data-url")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Log.i("新闻详情：", json);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }).get();
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://mini.eastday.com/nsa/n231129135758235.html")
                .cssQuery("body", "div", "div.main_content", "div.article",
                        "div.detail_left.clear-fix", "div.pagination")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Log.i("Mac_Liu", "分页标签：" + json);
//                        for (int i = 0; i < selectElements.size(); i++) {
//                            Elements elements = selectElements.get(i).children();
//                            for (int j = 0; j < elements.size(); j++) {
//                                String attr = elements.get(j).select("a").attr("href");
//                                Log.i("Mac_Liu", "标签：" + attr);
//                            }
//                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }).get();
        // todo 搜狐详情数据
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://m.sohu.com/a/740434259_121119289?scm=1101.topic:55103:5.0.9.a2_3X357-0806_917")
                .cssQuery("body", "div.article-page", "div.article-content-wrapper", "div.article-main")
                .attrs("data-src", "width", "height")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Element head = document.head();
                        Elements children = head.children().select("script");
                        List<ImageBean> imageBeans = new ArrayList<>();
                        List<ADHtmlUtils.ElementBean> elementBeanList = ADGsonUtils.jsonArrayList(json, ADHtmlUtils.ElementBean.class);
                        for (int i = 0; i < children.size(); i++) {
                            String data = children.get(i).data();
                            if (data.contains("cfgs")) {
                                String replace = data.replace("var cfgs = ", "").replace(";", "").replace("window.deployEnv = 'prod'", "").replaceAll("\n", "").replaceAll(",[}]", "}");
                                String[] split = replace.split("imageList:");
                                String imgList = split[1].trim();
                                String substring = imgList.substring(0, imgList.length() - 1);
                                imageBeans = ADGsonUtils.jsonArrayList(substring, ImageBean.class);
                                Log.i("Mac_Liu", "搜狐Data：" + imageBeans.toString());
                            }
                        }
                        for (int i = 0; i < imageBeans.size(); i++) {
                            for (int j = 0; i < elementBeanList.size(); j++) {
                                if (elementBeanList.get(j).getMode() == ADHtmlUtils.HtmlMode.Image) {
                                    if (!elementBeanList.get(j).getContent().contains("http")) {
                                        elementBeanList.get(j).setContent("https:" + imageBeans.get(i).getUrl());
                                        elementBeanList.get(j).setWidth(imageBeans.get(i).getWidth());
                                        elementBeanList.get(j).setHeight(imageBeans.get(i).getHeight());
                                        break;
                                    }
                                }
                            }
                        }
                        Iterator<ADHtmlUtils.ElementBean> iterator = elementBeanList.iterator();
                        while (iterator.hasNext()){
                            ADHtmlUtils.ElementBean next = iterator.next();
                            if (next.getContent().equals("展开剩余")) {
                                iterator.remove();
                            }
                            if (next.getContent().equals("%")){
                                iterator.remove();
                            }
                            if (next.getContent().length() == 2 && ADRegexUtils.getInstance().isNumber(next.getContent())) {
                                iterator.remove();
                            }
                        }
                        String data = ADGsonUtils.toJson(elementBeanList);
                        Log.i("Mac_Liu", "搜狐网页分析：" + data);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }).get();

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
    public void setPresenter(HtmlTextContract.Presenter presenter) {
        mPresenter = (HtmlTextPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onReady() {
        ADBaseLoadingDialog.getInstance().init(HtmlTextActivity.this, "加载中..");
    }

    @Override
    public void onSucceed(Spanned spanned) {
        ADBaseLoadingDialog.getInstance().dismiss();
        html.setText(spanned);
    }

    private String string = "<p >本文转自：检察日报</p>\n" +
            "<p >先交钱再入职的是诈骗</p>\n" +
            "<p >上海松江：引导公安机关调查取证又发现一名被害人</p>\n" +
            "<p >\n" +
            "            <img referrerpolicy='no-referrer' width='100%' src='https://dfzximg01.dftoutiao.com/news/20230926/20230926071034_329909622a2c8b6ecdef757e5092fcf7_1.jpeg' data-weight='500' data-width='500' data-height='334'></p>\n" +
            "<p >办案检察官讨论案情。</p>\n" +
            "<p >2022年10月26日，在上海松江打工的小刘来到当地派出所报案，称自己于2021年底被老乡张某以介绍去非洲打工为名骗走了8346元，目前张某已失联。警方调查发现，另有一名被害人小詹在河南省许昌市报案。</p>\n" +
            "<p >今年8月14日，上海市松江区检察院依法以涉嫌诈骗罪对张某提起公诉。8月21日，法院依法判处张某有期徒刑十个月，缓刑一年，并处罚金3000元。</p>\n" +
            "<p >据小刘回忆，2021年底，他在上海认识了张某。一天，张某和众人闲聊时说起他以前在非洲打工，虽然背井离乡，但每个月可以赚5万多元，这让几个工友听了都很羡慕。张某说，他计划于2022年3月出国去打工，目前已经报名了。众人听说后，都提出想一起去赚钱，便拜托张某介绍。张某表示，他联系的公司会为大家办好护照、买好机票，但是大家要先向公司缴纳8000元保证金，以免机票买好后又反悔。</p>\n" +
            "<p >考虑到有丰厚的报酬，加之有一群老乡相互照应，小刘等人决定和张某一起出国，并陆续向张某支付了保证金。</p>\n" +
            "<p >2022年2月，出发的日子临近，张某在微信群里说，在国外买药比较贵，根据他的经验，最好在国内买好常用药，到时候带过去，他认识医院的人，可以帮忙买药，每人需要向他转账346元的药品费，大家纷纷转账。然而，到了约定出发的日子，张某却说，因为疫情，出国务工取消了，公司会在15个工作日内退回大家前期支付的8346元。眼看15个工作日的期限一天天临近，退款却一直没到账，张某同样表现得很着急，还说带大家到该公司讨要说法。</p>\n" +
            "<p >15个工作日过去了，小刘再次联系张某，却发现张某失联了。</p>\n" +
            "<p >小刘报案后，上海松江公安机关于2022年11月4日立案，并联系河南省许昌市公安机关移送小詹的报案材料。今年1月，松江公安机关查实案情后，将张某列为网上逃犯。5月，张某在老家河南省长葛市附近出现，经当地派出所民警电话联系，张某自行到当地派出所投案自首。</p>\n" +
            "<p >张某到案后，向警方如实供述了自己的犯罪事实。他说早年确实有过出国打工的经历，但赚的钱都被他赌博输光了。2021年底，仍然沉迷赌博但没了积蓄的张某，发现几个老乡特别渴望赚钱，就想以介绍他们去非洲打工为名骗他们的钱。实际上，他根本没有能力安排老乡出国打工。2022年2月，他将老乡转给他的“保证金”都赌输了，就又以“药品费”的名义骗了他们一笔钱。2022年3月底，眼看谎言即将被戳破，他便赶紧辞职，断绝了与几名老乡的联系。</p><p >今年6月16日，该案提请松江区检察院批准逮捕。案件办理过程中，承办检察官发现，在张某组建的出国务工微信群里，除张某外还有4个人，但案件卷宗里的报案材料却仅有小刘、小詹两人。而根据小刘、小詹的证言，群内的其他二人也都是被害人。</p>\n" +
            "<p >为进一步查明张某的犯罪金额，承办检察官重点就此对张某展开讯问。张某交代，群友小陈确实是被害人，而另一名群友小厉是张某当时的领导，尽管小厉对出国打工也很感兴趣，但张某不忍心骗小厉，最终在付款阶段拒绝了小厉与他们同去。据此，承办检察官引导公安机关进一步调查取证，最终找到小厉、小陈二人核实了情况，查明该案被害人确实是3人。</p>\n" +
            "<p >到案后，张某将诈骗所得退还给3名被害人，并获得了被害人的谅解。经审查，张某以非法占有为目的，虚构可以去非洲打工的事实，以收取保证金、药品费为由，使被害人陷入错误认识而自愿交付财物，涉案金额为25038元，其行为应当认定为诈骗罪。</p>";
}
