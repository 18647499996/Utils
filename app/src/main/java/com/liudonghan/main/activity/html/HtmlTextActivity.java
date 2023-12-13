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
            " <div class=\"medium_layout\" contenteditable=\"false\">\n" +
            "  <div contenteditable=\"true\">\n" +
            " <p>用七个故事，透视体面生活之下的荒诞底色——《体面人生》由读客文化出版，犀利透视现代生活光彩焕然下的暗面，敏锐察觉种种精致伪装下，不易察觉的情感失落。小说从暗流涌动的同学聚会开始，一路写到选秀造星、人工智能、虚拟伴侣等热点，描摹当代图景的同时，细腻呈现并反思现代都市生活种种精神困局。</p>\n" +
            "<img class=\"ait_item_box img_default\" style=\"max-width: 600px; height: auto; display: block; margin: 0 auto;\" src=\"https://image.whb.cn/image/a71e490a85874651bdc6272789996101.jpg\" alt=\"\" width=\"1225\" height=\"787\" data-id=\"915974\" data-src=\"https://image.whb.cn/image/a71e490a85874651bdc6272789996101.jpg\" data-width=\"1225\" data-height=\"787\"><img class=\"ait_item_box img_default\" style=\"max-width: 600px; height: auto; display: block; margin: 0 auto;\" src=\"https://image.whb.cn/image/13d10339de93429e9ea94ec1283f74a5.jpg\" alt=\"\" width=\"779\" height=\"649\" data-id=\"915973\" data-src=\"https://image.whb.cn/image/13d10339de93429e9ea94ec1283f74a5.jpg\" data-width=\"779\" data-height=\"649\">\n" +
            "  </div>\n" +
            " </div>\n" +
            " <p>“我关注那些微妙而迅疾地走向决定性的时刻，希望能在小说里抓住它们。”近期，黄昱宁短篇小说新作《体面人生》在朵云书院·旗舰店发布，和鲁迅文学奖得主小白、作家毛尖、“忽左忽右”主播程衍樑分享创作理念时，黄昱宁谈到，很多时候“体面”只不过是你最希望昭示于他人的那一面而已。</p>\n" +
            " <p>当这一面被撕开、击破，既无法说服他人也无法说服自己的时候，问题就在瞬间涌现，乃至爆发。正是体面坍塌的过程，构成极具戏剧张力的“决定性时刻”。</p>\n" +
            " <p>用七个故事，透视体面生活之下的荒诞底色——《体面人生》由读客文化出版，犀利透视现代生活光彩焕然下的暗面，敏锐察觉种种精致伪装下，不易察觉的情感失落。小说从暗流涌动的同学聚会开始，一路写到选秀造星、人工智能、虚拟伴侣等热点，描摹当代图景的同时，细腻呈现并反思现代都市生活种种精神困局。</p>\n" +
            " <div class=\"medium_layout\" contenteditable=\"false\">\n" +
            "  <div contenteditable=\"true\"><img class=\"ait_item_box img_default\" style=\"max-width: 600px; height: auto; display: block; margin: 0 auto;\" src=\"https://image.whb.cn/image/13d10339de93429e9ea94ec1283f74a5.jpg\" alt=\"\" width=\"779\" height=\"649\" data-id=\"915973\" data-src=\"https://image.whb.cn/image/13d10339de93429e9ea94ec1283f74a5.jpg\" data-width=\"779\" data-height=\"649\">\n" +
            "  </div>\n" +
            " </div>\n" +
            " <p>《体面人生》融合现实和轻科幻，游走于不同时间空间，让“历史、现实与未来彼此对望，科学与文学通过人物促膝夜谈”。故事彼此独立却时时互文，跨越百年的时空被往复登场的人物巧妙衔接。</p>\n" +
            " <p>毛尖读完后深有感触：有时很难说哪种身份一定会更快乐，“体面”有时代表着一种大家向往的生活状态，有时却成为难以忍受的束缚，《体面人生》恰是当下生活关键词的集合。“像黄昱宁这样完全用进行时态来写作的当代作家是很少的，这种写作特别需要勇气和才能，当然也离不开对现实生活的密切观察。 ”</p>\n" +
            " <p>“我们对不熟知的人往往有刻板印象，实际上每个人都有自己的世界和爱恨情仇，不会是贫乏的。”黄昱宁坦言，一直想写一瞬间推翻一切的饭局，“经常吃饭时看到人们抢着买单甚至抢到急眼，我在想他们到底在吵什么，有些是临时偶然的，有些可能掺杂着不便言说的恩怨。”</p>\n" +
            " <p>“她在故事中前所未有地抓住了某些不易觉察的真实世界碎片，让叙事质地变得更加坚实有力。”在小白看来，《体面人生》内含讽刺，和故事里塑造的某些人物一样，所谓“成功人士”有种双重自满，这种自满往往导致责任感的缺失。而一个称职的作家会带着独特视角去观察，尽可能去理解他人的努力和背后动机，“这种叙述本身就是一种责任感。”</p>\n" +
            " <div class=\"content_sign\">\n" +
            "  文：许旸<br>\n" +
            "   图：出版方<br>\n" +
            "   编辑：王彦<br>\n" +
            "   责任编辑：李婷\n" +
            " </div>\n" +
            "</body>";

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
        Log.i("Mac_Liu", "集合数据：" + ADGsonUtils.toJson(ADHtmlUtils.getInstance().parseHtml(string)));
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
