package com.liudonghan.main.activity.html;

import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBaseLoadingDialog;
import com.liudonghan.utils.ADGsonUtils;
import com.liudonghan.utils.ADHtmlUtils;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
        Log.i("Mac_Liu", "集合数据：" + ADGsonUtils.toJson(ADHtmlUtils.getInstance().parseHtml(htmlStr)));
        ADHtmlUtils.getInstance().parseHtml(this, htmlStr, this);

        String parser = ADHtmlUtils.getInstance()
                .from(this)
                .html(string)
                .cssQuery("div", "ul.oh", "li", "div.pic")
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

    private String string = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\" />\n" +
            "\t<title>历史上今天11月25日是什么日子_11月25日今日新鲜事今天发生的重大新闻及历史大事件记录-历史上今天官网</title>\n" +
            "\t<meta name=\"viewport\"\n" +
            "\t\tcontent=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\n" +
            "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
            "\t<meta name=\"renderer\" content=\"webkit\">\n" +
            "\t<meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\n" +
            "\t<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\n" +
            "\t<meta name=\"applicable-device\" content=\"pc,mobile\">\n" +
            "\t<meta name=\"keywords\" content=\"历史上的11月25日,历史上的今天11月25日,11月25日是什么日子,11月25日历史大事件,今日新鲜事\">\n" +
            "\t<meta name=\"description\" content=\"分类整理每年历史上的11月25日今天发生的重大新闻、今日新鲜事及历史大事件记录，用历史大事件告诉你历史上今天11月25日是什么日子，今日新鲜事有哪些？\">\n" +
            "\t<link rel=\"stylesheet\" href=\"https://www.lssjt.com/style/css/base.css?v=1.11\">\n" +
            "\t<script type=\"text/javascript\" src=\"https://www.lssjt.com/style/js/jquery1.8.2.min.js?v=1.2\"></script>\n" +
            "\t<!-- <script type=\"text/javascript\" src=\"https://libs.baidu.com/jquery/1.8.2/jquery.min.js?v=1.2\"></script> -->\n" +
            "\t<script type=\"text/javascript\" src=\"https://www.lssjt.com/style/js/common.js?v=1.3\"></script>\n" +
            "\t<!--[if IE]> \n" +
            "<script src=\"https://www.lssjt.com/style/js/html5.js\"></script>\n" +
            " <![endif]-->\n" +
            "\t<!--[if lt IE 9]> \n" +
            "<script src=\"https://www.lssjt.com/style/js/respond.min.js\"></script>\n" +
            "<![endif]-->\n" +
            "\t<link rel=\"stylesheet\" href=\"https://www.lssjt.com/style/css/index.css\">\n" +
            "\t<!-- <script type=\"text/javascript\" name=\"baidu-tc-cerfication\" data-appid=\"7029157\" src=\"https://apps.bdimg.com/cloudaapi/lightapp.js\"></script> -->\n" +
            "\t<script async src=\"https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-2558005650187672\"\n" +
            "\t\tcrossorigin=\"anonymous\"></script>\n" +
            "</head>\n" +
            "\n" +
            "<body class=\"w960 index\">\n" +
            "\t<script type=\"text/javascript\" src=\"https://www.lssjt.com/style/js/dates.js\"></script>\n" +
            "\t<script>\n" +
            "\t\tif($(window).width()<640){\n" +
            "\t\t\tjssrc = \"https://www.lssjt.com/style/js/dates.js\";\n" +
            "\t\t\tvar head= document.getElementsByTagName('head')[0]; \n" +
            "\t\t\tvar script= document.createElement('script'); \n" +
            "\t\t\tscript.type= 'text/javascript'; \n" +
            "\t\t\tscript.onload = script.onreadystatechange = function() { \n" +
            "\t\t\t\tif (!this.readyState || this.readyState === \"loaded\" || this.readyState === \"complete\" ) {\n" +
            "\t\t\t\t\t$('#beginTime').date('',Ycallback);\n" +
            "\t\t\t\t\tscript.onload = script.onreadystatechange = null; \n" +
            "\t\t\t\t} \n" +
            "\t\t\t}; \n" +
            "\t\t\tscript.src = jssrc; \n" +
            "\t\t\thead.appendChild(script); \n" +
            "\t\t\tfunction Ycallback(date){\n" +
            "\t\t\t\tdates=date.split(\"-\");\n" +
            "\t\t\t\twindow.location.href=\"https://www.lssjt.com/\"+dates[1].replace(/\\b(0+)/gi,\"\")+\"/\"+dates[2].replace(/\\b(0+)/gi,\"\")+\"/\";\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t</script>\n" +
            "\t<div id=\"datePlugin\"></div>\n" +
            "\t<div class=\"header\">\n" +
            "\t\t<div class=\"wrap boxsing pr\">\n" +
            "\t\t\t<div class=\"mtop\">\n" +
            "\t\t\t\t<a href=\"https://www.lssjt.com/sitemap.html\" class=\"mmenu smap l\"></a>\n" +
            "\t\t\t\t<span class=\"mpos\">\n" +
            "\t\t\t\t历史上今天\n" +
            "\t\t\t</span><input id=\"beginTime\" class=\"datebtn kbtn r\">\n" +
            "\n" +
            "\t\t</div>\n" +
            "\t\t\t\t<div class=\"mnav poh oh\">\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/year/\">年度事件</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/\">世界历史</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/jieri/\">节日大全</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/people/\">历史人物</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/guoxue/\">国学文化</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/lszz/\">历史战争</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/test/\">心理测试</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/qwys/\">奇闻异事</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/wjzm/\">末解之迷</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/sjzz/\">世界之最</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/wnl/\">万年历</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/shengrimima/\">生日密码</a>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"ptop moh oh\">\n" +
            "\t\t\t\t\t<div class=\"logo\">\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com\"><img src=\"https://www.lssjt.com/style/images/logo.png\" alt=\"\"></a>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<div class=\"search l pr\">\n" +
            "\t\t\t\t\t\t<form target=\"_blank\" action=\"https://so.lssjt.com/cse/search\">\n" +
            "\t\t\t\t\t\t\t<input type=\"hidden\" value=\"13760229540400167492\" name=\"s\">\n" +
            "\t\t\t\t\t\t\t<input type=\"text\" class=\"tkey\" value=\"林允宋威龙日本过七夕疑似复合？女方回应：假新闻\" onfocus=\"this.value=''\" name=\"q\"><input style=\"cursor:pointer;\" type=\"button\" class=\"tbt\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</form>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<div class=\"r ad\" id=\"45\">\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div class=\"navbar white\">\n" +
            "\t\t\t\t<div class=\"wrap boxsing oh pr\">\n" +
            "\t\t\t\t\t<nav>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/year/\">年度事件</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/\">世界历史</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/jieri/\">节日大全</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/people/\">历史人物</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/guoxue/\">国学文化</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/qwys/\">奇闻异事</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/wjzm/\">末解之迷</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/sjzz/\">世界之最</a>\n" +
            "\t\t\t\t\t</nav>\n" +
            "\t\t\t\t\t<div class=\"menubtn\">\n" +
            "\t\t\t\t\t\t<b></b>\n" +
            "\t\t\t\t\t\t<p class=\"m_list\">\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/day/\">每日历史事件</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/chaodai/1.html\">朝代历史事件</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/jiaoyu/\">教育史的今天</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/keji/\">科技史上今天</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/dssdjt/\">党史上今天</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/tiyu/\">体育史上今天</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/che/\">车史上今天</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/huangdi/\">历代皇帝</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/zgls/\">中国历史</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/wgls/\">外国历史</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/lszz/\">历史战争</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/pandian/\">历史盘点</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/test/\">心理测试</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/wnl/\">万年历</a>\n" +
            "\t\t\t\t\t\t\t<a target=\"_blank\" rel=\"nofollow\" href=\"https://www.lssjt.com/sitemap.html\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"font-weight:600;\">网站导航</a>\n" +
            "\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\n" +
            "\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t$(function(){\n" +
            "\t\t$(\".tkey\").on('blur', function(){\n" +
            "\t\t\tif($(this).val() == '')\n" +
            "\t\t\t\t$(this).val('请输入搜索关键词');\n" +
            "\t\t}).on('focus', function(){\n" +
            "\t\t\tif($(this).val() == '请输入搜索关键词')\n" +
            "\t\t\t\t$(this).val('');\n" +
            "\t\t});\n" +
            "\t\t$(\".tbt\").click(function () {\n" +
            "\t\t\tvar q = this.form.q.value;\n" +
            "\t\t\tif(q != '' && q != '请输入搜索关键词')\n" +
            "\t\t\t\tthis.form.submit();\n" +
            "\t\t});\n" +
            "\t\t$(\".menubtn\").hover(function () {\n" +
            "\t\t\t$('.m_list').show();\n" +
            "\t\t},function () {\n" +
            "\t\t\t$('.m_list').hide();\n" +
            "\t\t}\n" +
            "\t\t);\n" +
            "\t});\n" +
            "\t\t</script>\n" +
            "\t\t<div class=\"w100 oh\">\n" +
            "\t\t\t<div class=\"banner tpic wimg\">\n" +
            "\t\t\t\t<script language=\"javascript\" src=\"https://www.lssjt.com/caches/poster_js/46.js\"></script>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"wrap main oh mt18\">\n" +
            "\t\t\t<div class=\"ml experience pr\">\n" +
            "\t\t\t\t<p class=\"etype\">\n" +
            "\t\t\t\t\t<span class=\"cur type0\" tdata=\"0\" onclick=\"c_event(this,0)\"><b>全部</b></span><span class=\"type1\" tdata=\"57\" onclick=\"c_event(this,'typeid_57')\"><b>事件</b></span><span class=\"type2\" tdata=\"56\" onclick=\"c_event(this,'typeid_56')\"><b>出生</b></span><span class=\"type3\" tdata=\"55\" onclick=\"c_event(this,'typeid_55')\"><b>逝世</b></span><span class=\"type4\" tdata=\"54\" onclick=\"c_event(this,'typeid_54')\"><b>节假日</b></span><span class=\"type5\" tdata=\"53\" onclick=\"c_event(this,'typeid_53')\">纪念</span>\n" +
            "\t\t\t\t</p>\n" +
            "\t\t\t\t<h1>\n" +
            "\t\t\t\t\t历史上11月25日都发生了什么<br/>\n" +
            "\t\t\t    今日历史上的11月25日今天发生的重大新闻及历史大事件记录<br/>\n" +
            "\t\t\t    历史上的今天11月25日是什么日子\n" +
            "\t\t\t</h1>\n" +
            "\t\t\t\t\t<div class=\"linediv\">\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<ul class=\"oh\" id=\"container\">\n" +
            "\t\t\t\t\t\t<li class=\"circlel poh\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2017</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic pr\">\n" +
            "\t\t\t\t\t\t\t\t<div id=\"58\"></div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel lif typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2016</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2016</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19495.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"米格-29战斗机的设计师和研发者瓦诺·伊万·米高扬去世\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2017/0209/20170209055536635.jpg\" alt=\"米格-29战斗机的设计师和研发者瓦诺·伊万·米高扬去世\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2016年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19495.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">米格-29战斗机的设计师和研发者瓦诺·伊万·米高扬去世</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2016</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2016</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19303.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"古巴政治家、军事家菲德尔·卡斯特罗逝世\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2016/1128/20161128102159682.jpg\" alt=\"古巴政治家、军事家菲德尔·卡斯特罗逝世\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2016年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19303.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">古巴政治家、军事家菲德尔·卡斯特罗逝世</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2012</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2012</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15183.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"我国首艘航母“辽宁舰”成功起降歼-15舰载机\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120114859114.jpg\" alt=\"我国首艘航母“辽宁舰”成功起降歼-15舰载机\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2012年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15183.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">我国首艘航母“辽宁舰”成功起降歼-15舰载机</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2012</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2012</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/13382.html\" class=\"pica\" title=\"熊朝忠成中国首位世界职业拳王\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201211/25/D7123918762.jpg\" alt=\"熊朝忠成中国首位世界职业拳王\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2012年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/13382.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">熊朝忠成中国首位世界职业拳王</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2011</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2011</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15169.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"苏联著名举重运动员瓦西里·阿列克谢耶夫病逝\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120101939580.jpg\" alt=\"苏联著名举重运动员瓦西里·阿列克谢耶夫病逝\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2011年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15169.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">苏联著名举重运动员瓦西里·阿列克谢耶夫病逝</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2009</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2009</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/12830.html\" class=\"pica\" title=\"英研制世界最快超音速汽车\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/E8123514166.jpg\" alt=\"英研制世界最快超音速汽车\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2009年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/12830.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">英研制世界最快超音速汽车</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2005</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2005</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15182.html\" class=\"pica\" title=\"韩国将端午祭申请为人类口头和无形遗产\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120114213976.jpg\" alt=\"韩国将端午祭申请为人类口头和无形遗产\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2005年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15182.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">韩国将端午祭申请为人类口头和无形遗产</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2005</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2005</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15168.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"英格兰车手、前世界拉力锦标赛冠军理查德·伯恩斯病逝\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120101621654.jpg\" alt=\"英格兰车手、前世界拉力锦标赛冠军理查德·伯恩斯病逝\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2005年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15168.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">英格兰车手、前世界拉力锦标赛冠军理查德·伯恩斯病逝</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2001</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2001</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15181.html\" class=\"pica\" title=\"第九届全运会在广州闭幕\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120113422878.jpg\" alt=\"第九届全运会在广州闭幕\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>2001年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15181.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">第九届全运会在广州闭幕</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>2000</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>2000</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>2000年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3889.html\" title=\"海牙气候会议未达成协议\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">海牙气候会议未达成协议</a>\n" +
            "\t\t\t\t\t\t\t\t<p>2000年11月25日，联合国《气候变化框架...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1998</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1998</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8599.html\" class=\"pica\" title=\"北京市正式宣布申办2008年奥运会\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/200905/17/3723117313.jpg\" alt=\"北京市正式宣布申办2008年奥运会\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1998年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8599.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">北京市正式宣布申办2008年奥运会</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1997</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1997</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1997年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8618.html\" title=\"广西第二次移交二战美军遗骸\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">广西第二次移交二战美军遗骸</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1997年11月25日 广西第二次移交二战美...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1992</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1992</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15180.html\" class=\"pica\" title=\"捷克斯洛伐克联邦议会通过《解体法》\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120112912498.jpg\" alt=\"捷克斯洛伐克联邦议会通过《解体法》\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1992年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15180.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">捷克斯洛伐克联邦议会通过《解体法》</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1992</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1992</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1992年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8670.html\" title=\"流氓集团首犯段氏四兄弟被处决\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">流氓集团首犯段氏四兄弟被处决</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1992年11月25日，横行一方、无恶不作的...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1991</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1991</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/12831.html\" class=\"pica\" title=\"中共十三届八中全会在北京开幕\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/DE123926692.jpg\" alt=\"中共十三届八中全会在北京开幕\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1991年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/12831.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中共十三届八中全会在北京开幕</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1990</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1990</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8695.html\" class=\"pica\" title=\"我国科学家直接发现脱氧核糖核酸新结构\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/5912629244.jpg\" alt=\"我国科学家直接发现脱氧核糖核酸新结构\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1990年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8695.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">我国科学家直接发现脱氧核糖核酸新结构</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1986</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1986</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19302.html\" class=\"pica\" title=\"第一个国际素食日\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2016/1124/20161124053642426.jpg\" alt=\"第一个国际素食日\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1986年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/19302.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">第一个国际素食日</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1986</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1986</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15178.html\" class=\"pica\" title=\"法赫德国王大桥正式通车\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120111801995.jpg\" alt=\"法赫德国王大桥正式通车\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1986年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15178.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">法赫德国王大桥正式通车</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1986</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1986</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3888.html\" class=\"pica\" title=\"中国长江科考漂流队征服长江\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/3712415415.jpg\" alt=\"中国长江科考漂流队征服长江\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1986年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3888.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中国长江科考漂流队征服长江</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1983</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1983</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15177.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"第六届全国人大常委会第三次会议在京举行\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120111521339.jpg\" alt=\"第六届全国人大常委会第三次会议在京举行\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1983年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15177.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">第六届全国人大常委会第三次会议在京举行</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1983</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1983</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1983年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3887.html\" title=\"中日同意把关系三原则扩大为四原则\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中日同意把关系三原则扩大为四原则</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1983年11月25日，中日两国政府一致同意...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1982</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1982</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1982年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8770.html\" title=\"中央军委授予张华荣誉称号\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中央军委授予张华荣誉称号</a>\n" +
            "\t\t\t\t\t\t\t\t<p>中央军委于1982年11月25日发布命令，授...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_56\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1981</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1981</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15170.html\" class=\"pica\" title=\"西班牙职业足球员哈维·阿隆索出生\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120103047660.jpg\" alt=\"西班牙职业足球员哈维·阿隆索出生\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1981年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15170.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">西班牙职业足球员哈维·阿隆索出生</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1981</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1981</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1981年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8779.html\" title=\"西德发现二百具木乃伊\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">西德发现二百具木乃伊</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1981年11月，在西德提宾根大学的地下室...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1980</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1980</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1980年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/4450.html\" title=\"上沃尔特发生军事政变\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">上沃尔特发生军事政变</a>\n" +
            "\t\t\t\t\t\t\t\t<p>１９８０年１１月２５日，非洲西部的内...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1979</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1979</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3886.html\" class=\"pica\" title=\"“渤海2号”钻井船沉没事件\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/DD12340443.jpg\" alt=\"“渤海2号”钻井船沉没事件\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1979年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3886.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">“渤海2号”钻井船沉没事件</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_56\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1976</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1976</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15171.html\" class=\"pica\" title=\"中国男演员黄海波出生\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120103550182.jpg\" alt=\"中国男演员黄海波出生\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1976年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15171.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中国男演员黄海波出生</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1975</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1975</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3885.html\" class=\"pica\" title=\"苏里南共和国宣告独立\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/FE12259685.jpg\" alt=\"苏里南共和国宣告独立\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1975年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3885.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">苏里南共和国宣告独立</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1974</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1974</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15167.html\" class=\"pica\" title=\"联合国第三任秘书长吴丹病逝于纽约\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120101105458.jpg\" alt=\"联合国第三任秘书长吴丹病逝于纽约\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1974年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15167.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">联合国第三任秘书长吴丹病逝于纽约</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1970</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1970</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8812.html\" class=\"pica\" title=\"日本作家三岛由纪夫剖腹自杀\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/200905/17/62231137104.jpg\" alt=\"日本作家三岛由纪夫剖腹自杀\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1970年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8812.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">日本作家三岛由纪夫剖腹自杀</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1958</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1958</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1958年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8851.html\" title=\"三门峡截流成功\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">三门峡截流成功</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1958年11月25日，黄河三门峡工程截流指...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_55\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1950</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1950</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8868.html\" class=\"pica\" title=\"毛岸英在抗美援朝战争中牺牲\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/200905/17/67231347905.jpg\" alt=\"毛岸英在抗美援朝战争中牺牲\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1950年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8868.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">毛岸英在抗美援朝战争中牺牲</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1947</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1947</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8880.html\" class=\"pica\" title=\"好莱坞10名专业人员被列入黑名单\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/200905/17/EE231415715.jpg\" alt=\"好莱坞10名专业人员被列入黑名单\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1947年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8880.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">好莱坞10名专业人员被列入黑名单</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_57\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1937</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1937</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15176.html\" class=\"pica\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"日军攻陷无锡 国民党最后最后防线被攻破\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120110945707.jpg\" alt=\"日军攻陷无锡 国民党最后最后防线被攻破\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1937年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15176.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">日军攻陷无锡 国民党最后最后防线被攻破</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1936</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1936</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1936年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3884.html\" title=\"日德签订“反共产国际协定”\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">日德签订“反共产国际协定”</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1936年11月25日，日本和德国在柏林签订...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1935</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1935</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3883.html\" class=\"pica\" title=\"施剑翘刺杀孙传芳\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201111/25/49121340950.jpg\" alt=\"施剑翘刺杀孙传芳\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1935年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3883.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">施剑翘刺杀孙传芳</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_0\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1928</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1928</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"text pr\">\n" +
            "\t\t\t\t\t\t\t\t<span>1928年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/3882.html\" title=\"毛泽东总结井冈山武装斗争经验\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\">毛泽东总结井冈山武装斗争经验</a>\n" +
            "\t\t\t\t\t\t\t\t<p>1928年11月25日，中共红军第四军前委书...</p>\n" +
            "\t\t\t\t\t\t\t\t<i></i>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_56\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1926</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1926</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8949.html\" class=\"pica\" title=\"物理学家李政道诞生\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/200905/17/73231645393.jpg\" alt=\"物理学家李政道诞生\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1926年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/8949.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">物理学家李政道诞生</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circlel typeid_56\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1921</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1921</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/13743.html\" class=\"pica\" title=\"澳门赌王何鸿燊出生\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"/upic/201311/25/05204148600.jpg\" alt=\"澳门赌王何鸿燊出生\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1921年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/13743.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">澳门赌王何鸿燊出生</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li class=\"circler typeid_56\">\n" +
            "\t\t\t\t\t\t\t<p class=\"time\">\n" +
            "\t\t\t\t\t\t\t\t<span class=\"moh\"><b>1915</b><i>&nbsp;—&nbsp;●&nbsp;</i></span>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"poh\"><b>1915</b></span>\n" +
            "\t\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t\t<div class=\"pic\">\n" +
            "\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15172.html\" class=\"pica\" title=\"中国语言学家许国璋出生\"\n" +
            "\t\t\t\t\t\t\t\t\ttarget=\"_blank\"><img src=\"/style/images/grey.gif\" data-original=\"https://www.lssjt.com/uploadfile/2015/1120/20151120104437549.jpg\" alt=\"中国语言学家许国璋出生\" class=\"roundies\"></a>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"t\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<span>1915年11月25日</span>\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"https://www.lssjt.com/11/25/15172.html\" class=\"txt\"\n" +
            "\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\">中国语言学家许国璋出生</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t<div class=\"clear\"></div>\n" +
            "\t\t\t\t\t<div class=\"loading\"></div>\n" +
            "\t\t\t\t\t<div class=\"viewmore\" id=\"more\">\n" +
            "\t\t\t\t\t\t<a href=\"javascript:;\" class=\"get_more\">点击查看更多</a>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<script type=\"text/javascript\" src=\"https://www.lssjt.com/style/js/jquery.lazyload.min.js\"></script>\n" +
            "\t\t\t<script type=\"text/javascript\" charset=\"utf-8\">\n" +
            "\t\t\t\t$(function() {\n" +
            "\t\t\t\t$(\".pic img\").lazyload({effect: \"fadeIn\"});\n" +
            "\t\t\t});\n" +
            "\t\t\t</script>\n" +
            "\t\t\t<div class=\"mr box\">\n" +
            "\t\t\t\t<div class=\"date moh adroll pr\">\n" +
            "\t\t\t\t\t<a class=\"pdguide\" target=\"_blank\" href=\"https://www.lssjt.com/lishi/dsj/\">\n" +
            "\t\t\t\t\t\t<span class=\"pdpic animated\"></span>\n" +
            "\t\t\t\t\t\t<span class=\"pdtxt animated\"></span>\n" +
            "\t\t\t\t\t</a>\n" +
            "\t\t\t\t\t<p class=\"dt\">历史上今天</p>\n" +
            "\t\t\t\t\t<div class=\"d_select\">\n" +
            "\t\t\t\t\t\t<span></span>\n" +
            "\t\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t\t<b>选择月份</b>\n" +
            "\t\t\t\t\t\t\t<a href=\"/1/1/\">1</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/2/1/\">2</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/3/1/\">3</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/4/3/\">4</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/5/1/\">5</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/6/1/\">6</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/7/1/\">7</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/8/1/\">8</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/9/1/\">9</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/10/1/\">10</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/11/1/\">11</a>\n" +
            "\t\t\t\t\t\t\t<a href=\"/12/1/\">12</a>\n" +
            "\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<div class=\"dm\" style=\"padding:0.7em 0\">\n" +
            "\t\t\t\t\t\t<p>2023/11 星期六</p>\n" +
            "\t\t\t\t\t\t<p class=\"fbw\">25</p>\n" +
            "\t\t\t\t\t\t<p id='lunar'></p>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t<div class=\"db\">\n" +
            "\t\t\t\t\t\t<p class=\"oh\">\n" +
            "\t\t\t\t\t\t\t<span>日</span><span>一</span><span>二</span><span>三</span><span>四</span><span>五</span><span>六</span>\n" +
            "\t\t\t\t\t\t</p>\n" +
            "\t\t\t\t\t\t<p class=\"oh\">\n" +
            "\t\t\t\t\t\t\t<a href=''></a><a href=''></a><a href=''></a> <a href='/11/1/'> 1</a> <a href='/11/2/'>\n" +
            "\t\t\t\t\t\t\t\t2</a> <a href='/11/3/'> 3</a> <a href='/11/4/'> 4</a> <a href='/11/5/'> 5</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/6/'> 6</a> <a href='/11/7/'> 7</a> <a href='/11/8/'> 8</a> <a href='/11/9/'>\n" +
            "\t\t\t\t\t\t\t\t9</a> <a href='/11/10/'> 10</a> <a href='/11/11/'> 11</a> <a href='/11/12/'> 12</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/13/'> 13</a> <a href='/11/14/'> 14</a> <a href='/11/15/'> 15</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/16/'> 16</a> <a href='/11/17/'> 17</a> <a href='/11/18/'> 18</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/19/'> 19</a> <a href='/11/20/'> 20</a> <a href='/11/21/'> 21</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/22/'> 22</a> <a href='/11/23/'> 23</a> <a href='/11/24/'> 24</a> <a\n" +
            "\t\t\t\t\t\t\t\thref='/11/25/' class='cur'> 25</a> <a href='/11/26/'> 26</a> <a href='/11/27/'> 27</a>\n" +
            "\t\t\t\t\t\t\t<a href='/11/28/'> 28</a> <a href='/11/29/'> 29</a> <a href='/11/30/'> 30</a> </p>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"hot mt18 moh\">\n" +
            "\t\t\t\t\t<ul>\n" +
            "\t\t\t\t\t\t<li><span>02-25</span><a href=\"https://www.lssjt.com/2/16/20775.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"冬奥会金牌榜:中国第一枚冬奥会金牌获得者是？\" target=\"_blank\">冬奥会金牌榜:中国第一枚冬奥</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-23</span><a href=\"https://www.lssjt.com/2/23/20774.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"支付宝微信收款码个人不能用？官方回复:假的\" target=\"_blank\">支付宝微信收款码个人不能用？</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-23</span><a href=\"https://www.lssjt.com/2/22/20773.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"全球最大的墨西哥毒枭古兹曼在墨西哥落网\" target=\"_blank\">全球最大的墨西哥毒枭古兹曼在</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-22</span><a href=\"https://www.lssjt.com/2/21/20772.html\" title=\"北京冬残奥会中国体育代表团成立\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\">北京冬残奥会中国体育代表团成</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-21</span><a href=\"https://www.lssjt.com/2/21/20771.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"2022最强寒流来袭，台湾一夜猝死41人，疑受寒流影响\" target=\"_blank\">2022最强寒流来袭，台湾一夜猝</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-21</span><a href=\"https://www.lssjt.com/2/21/20770.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"勒布朗队夺nba全明星赛5连胜，库里夺全明星MVP\" target=\"_blank\">勒布朗队夺nba全明星赛5连胜，</a></li>\n" +
            "\t\t\t\t\t\t<li><span>02-21</span><a href=\"https://www.lssjt.com/2/20/20769.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"北京冬季奥运会在北京首都体育馆鸟巢正式闭幕\" target=\"_blank\">北京冬季奥运会在北京首都体育</a></li>\n" +
            "\t\t\t\t\t\t<li><span>06-01</span><a href=\"https://www.lssjt.com/5/31/20768.html\" title=\"感动中国的邮递员王顺友去世\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\">感动中国的邮递员王顺友去世</a></li>\n" +
            "\t\t\t\t\t\t<li><span>05-31</span><a href=\"https://www.lssjt.com/5/28/20767.html\" title=\"著名滑稽表演艺术家童双春逝世\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\">著名滑稽表演艺术家童双春逝世</a></li>\n" +
            "\t\t\t\t\t\t<li><span>05-26</span><a href=\"https://www.lssjt.com/5/20/20766.html\"\n" +
            "\t\t\t\t\t\t\t\ttitle=\"中国眼科医学界泰斗夏德昭逝世，享年104岁\" target=\"_blank\">中国眼科医学界泰斗夏德昭逝世</a></li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"figure moh mt18 moh\">\n" +
            "\t\t\t\t\t<h4><a href=\"https://www.lssjt.com/people/\" target=\"_blank\">人物专栏</a></h4>\n" +
            "\t\t\t\t\t<ul>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/9603.html\" title=\"【宋庆龄】宋庆龄简介_宋庆龄三姐妹\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2015/0514/20150514063402676.jpg\" height=\"80\" width=\"80\" alt=\"【宋庆龄】宋庆龄简介_宋庆龄三姐妹\"/><br><span>宋庆龄</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/9605.html\" title=\"【蒋介石】蒋介石简介_蒋介石的五虎上将\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2015/0609/20150609025426977.jpg\" height=\"80\" width=\"80\" alt=\"【蒋介石】蒋介石简介_蒋介石的五虎上将\"/><br><span>蒋介石</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/11904.html\" title=\"【李先念】李先念简介_李先念子女\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2015/1216/20151216040943582.jpg\" height=\"80\" width=\"80\" alt=\"【李先念】李先念简介_李先念子女\"/><br><span>李先念</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/11101.html\" title=\"【刘少奇】刘少奇简介_刘少奇怎么死的\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2015/0914/20150914053849350.jpg\" height=\"80\" width=\"80\" alt=\"【刘少奇】刘少奇简介_刘少奇怎么死的\"/><br><span>刘少奇</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/11997.html\" title=\"【陆定一】陆定一简介_陆昊是陆定一的儿子\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2015/1221/20151221111616503.jpg\" height=\"80\" width=\"80\" alt=\"【陆定一】陆定一简介_陆昊是陆定一的儿子\"/><br><span>陆定一</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t<li><a href=\"https://www.lssjt.com/people/jindai/20514.html\" title=\"【姚依林】姚依林简历_姚依林女婿\"\n" +
            "\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
            "\t\t\t\t\t\t\t\tstyle=\"\"><img src=\"https://www.lssjt.com/uploadfile/2016/1128/20161128093803695.jpg\" height=\"80\" width=\"80\" alt=\"【姚依林】姚依林简历_姚依林女婿\"/><br><span>姚依林</span></a>\n" +
            "\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t</ul>\n" +
            "\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"story mt18 moh\">\n" +
            "\t\t\t\t\t<div class=\"c_tab\"><span class=\"cur\">最新</span><span>最热</span></div>\n" +
            "\t\t\t\t\t<h4><a href=\"https://www.lssjt.com/people/gushi/\" target=\"_blank\">野史秘闻</a></h4>\n" +
            "\t\t\t\t\t<div class=\"contentb\">\n" +
            "\t\t\t\t\t\t<ul class=\"cur\">\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36198.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"徐霞客的个人简介 徐霞客的主要成就\" target=\"_blank\">徐霞客的个人简介 徐霞客的主</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36197.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"李东阳的人物评价 李东阳的主要成就\" target=\"_blank\">李东阳的人物评价 李东阳的主</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36196.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"杜陵阳生平简介与历史典故\" target=\"_blank\">杜陵阳生平简介与历史典故</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36195.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"杜预生平简介与个人作品简介\" target=\"_blank\">杜预生平简介与个人作品简介</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36194.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"宋义生平经历与历史典故\" target=\"_blank\">宋义生平经历与历史典故</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36193.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"张耳生平简介与历史典故\" target=\"_blank\">张耳生平简介与历史典故</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36192.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"周亚夫生平简介与历史典故\" target=\"_blank\">周亚夫生平简介与历史典故</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36191.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"王翰的人物生平 王翰的人物评价\" target=\"_blank\">王翰的人物生平 王翰的人物评</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36190.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"王之涣生平简介与历史成就\" target=\"_blank\">王之涣生平简介与历史成就</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-06</span><a href=\"https://www.lssjt.com/people/gushi/36189.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"\u200B周邦彦生平经历与文学成就及历史贡献\" target=\"_blank\">\u200B周邦彦生平经历与文学成就及</a></li>\n" +
            "\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t<ul>\n" +
            "\t\t\t\t\t\t\t<li><span>11-09</span><a href=\"https://www.lssjt.com/people/gushi/19804.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"章含之换肾 章含之用了聂树斌的肾？\" target=\"_blank\">章含之换肾 章含之用了聂树斌</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>01-19</span><a href=\"https://www.lssjt.com/people/gushi/13315.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"叶剑英有几个老婆？叶剑英家族势力庞大\" target=\"_blank\">叶剑英有几个老婆？叶剑英家族</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>01-19</span><a href=\"https://www.lssjt.com/people/gushi/13312.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"叶剑英为什么叫花帅 叶剑英晚节不保是怎么回事？\" target=\"_blank\">叶剑英为什么叫花帅 叶剑英晚</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>10-21</span><a href=\"https://www.lssjt.com/people/gushi/19100.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"邓小平当过国家主席吗？邓小平为什么不当国家主席？\" target=\"_blank\">邓小平当过国家主席吗？邓小平</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>05-09</span><a href=\"https://www.lssjt.com/people/gushi/26185.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"徐斌洲简介 徐斌洲中将子女\" target=\"_blank\">徐斌洲简介 徐斌洲中将子女</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>09-19</span><a href=\"https://www.lssjt.com/people/gushi/17634.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"成吉思汗十大勇士 成吉思汗打下的版图\" target=\"_blank\">成吉思汗十大勇士 成吉思汗打</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>06-23</span><a href=\"https://www.lssjt.com/people/gushi/15208.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"为何不提杨尚昆李先念两位国家主席 杨子是杨尚昆的孙子吗\" target=\"_blank\">为何不提杨尚昆李先念两位国家</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>08-09</span><a href=\"https://www.lssjt.com/people/gushi/16222.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"胡耀邦死后谁骂“该死的没有死，不该死的死了”？\" target=\"_blank\">胡耀邦死后谁骂&ldquo;该死的没有死</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>12-21</span><a href=\"https://www.lssjt.com/people/gushi/21621.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"阿沛阿旺晋美几个老婆 阿沛阿旺晋美12个子女\" target=\"_blank\">阿沛阿旺晋美几个老婆 阿沛阿</a></li>\n" +
            "\t\t\t\t\t\t\t<li><span>03-31</span><a href=\"https://www.lssjt.com/people/gushi/24915.html\"\n" +
            "\t\t\t\t\t\t\t\t\ttitle=\"滕海清将军杀蒙古人 滕海清与“内人党”事件\" target=\"_blank\">滕海清将军杀蒙古人 滕海清与</a></li>\n" +
            "\t\t\t\t\t\t</ul>\n" +
            "\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"ad mt18\" id=\"12\"></div>\n" +
            "\t\t\t</div>\n" +
            "\t\t\t<div class=\"clear\"></div>\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"recom moh box mt18\">\n" +
            "\t\t\t<div class=\"wrap\">\n" +
            "\t\t\t\t<div class=\"l\">\n" +
            "\t\t\t\t\t<span>历史总结</span>\n" +
            "\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/day/\">每日历史事件</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/year/\">年度历史事件</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/chaodai/1.html\">朝代历史事件</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/chinawar/\">中国历代战争年表</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/pandian/\">历史盘点</a>\n" +
            "\t\t\t\t\t\t<a target=\"_blank\" href=\"https://www.lssjt.com/lishi/dsj/\">历史盛世</a>\n" +
            "\t\t\t\t\t</p>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"l\">\n" +
            "\t\t\t\t\t<span>历史文化</span>\n" +
            "\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/zh5000/\" target=\"_blank\">中华上下五千年</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/sj5000/\" target=\"_blank\">世界上下五千年</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/huaxia/\" target=\"_blank\">华夏文明</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/sjhm/\" target=\"_blank\">世界回眸</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/lsyy/\" target=\"_blank\">历史寓言</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/baijiaxing/\" target=\"_blank\">百家姓</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/ershisixiao/\" target=\"_blank\">二十四孝</a>\n" +
            "\n" +
            "\t\t\t\t\t</p>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"l nobr\">\n" +
            "\t\t\t\t\t<span>趣味历史</span>\n" +
            "\t\t\t\t\t<p>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/wjzm/\" target=\"_blank\">未解之谜</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/qwys/\" target=\"_blank\">奇闻异事</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/shengrihua/\" target=\"_blank\">生日花</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/shengrimima/\" target=\"_blank\">生日密码</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/city/\" target=\"_blank\">历史名城</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/lishi/sjzz/\" target=\"_blank\">世界之最</a>\n" +
            "\t\t\t\t\t\t<a href=\"https://www.lssjt.com/test/\" target=\"_blank\">心里测试</a>\n" +
            "\t\t\t\t\t</p>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"clear\"></div>\n" +
            "\t\t\t\t<div class=\"dynasty\">\n" +
            "\t\t\t\t\t<span>朝代历史:</span>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/shanggu/\" target=\"_blank\">上古</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/xiachao/\" target=\"_blank\">夏朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/shangchao/\" target=\"_blank\">商朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/zhouchao/\" target=\"_blank\">周朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/chunqiuzhanguo/\" target=\"_blank\">春秋战国</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/qinchao/\" target=\"_blank\">秦朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/hanchao/\" target=\"_blank\">汉朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/sanguo/\" target=\"_blank\">三国</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/jinchao/\" target=\"_blank\">晋朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/nanbeichao/\" target=\"_blank\">南北朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/suichao/\" target=\"_blank\">隋朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/tangchao/\" target=\"_blank\">唐朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/wudaishiguo/\" target=\"_blank\">五代十国</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/songchao/\" target=\"_blank\">宋朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/yuanchao/\" target=\"_blank\">元朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/mingchao/\" target=\"_blank\">明朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/qingchao/\" target=\"_blank\">清朝</a>\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/zgls/jindaishi/\" target=\"_blank\">近代</a>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"clear\"></div>\n" +
            "\t\t\t\t<div class=\"dynasty\">\n" +
            "\t\t\t\t\t<span>行业事件:</span>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/jiaoyu/\" target=\"_blank\">教育史上的今天</a>\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/keji/\" target=\"_blank\">科技史上的今天</a>\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/dssdjt/\" target=\"_blank\">党史上的今天</a>\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/tiyu/\" target=\"_blank\">体育上的今天</a>\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/che/\" target=\"_blank\">车史上的今天</a>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t</div>\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"poh mt18\" id=\"gg30\"></div>\n" +
            "\t\t<div class=\"footer mt18\">\n" +
            "\t\t\t<div class=\"wrap\">\n" +
            "\t\t\t\t<div class=\"moh white\">\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/webinfo.html\" rel=\"nofollow\">网站简介</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/couse.html\" rel=\"nofollow\">发展历程</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/readme.html\" rel=\"nofollow\">使用必读</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/gongyi.html\" rel=\"nofollow\">公益活动</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/guanggao.html\" rel=\"nofollow\">赞助广告</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/mianze.html\" rel=\"nofollow\">免责声明</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/me.html\" rel=\"nofollow\">联系我们</a> -\n" +
            "\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/about/link.html\" rel=\"nofollow\">友情链接</a> -\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/code/\" rel=\"nofollow\">插件申请</a> -\n" +
            "\t\t\t\t\t<a href=\"https://www.lssjt.com/sitemap.html\">网站地图</a><br/>Copyright©2004-2023 <a\n" +
            "\t\t\t\t\t\thref=\"https://www.lssjt.com\">历史上今天</a> <a href=\"https://beian.miit.gov.cn\"\n" +
            "\t\t\t\t\t\ttarget=\"_blank\">闽ICP备20014412号-4</a><br/>\n" +
            "\t\t\t历史上今天是个公益性的史料网站,梳理历史上的今天发生的重大新闻及历史事件记录！\n" +
            "\t\t\t\t\t<script language=\"javascript\" src=\"https://www.lssjt.com/style/js/tj.js\"></script><br/>\n" +
            "\t\t</div>\n" +
            "\t\t\t\t\t<div class=\"poh white\">\n" +
            "\t\t\t\t\t\tCopyright©2004-2023<br/> <a href=\"https://beian.miit.gov.cn\"\n" +
            "\t\t\t\t\t\t\ttarget=\"_blank\">闽ICP备20014412号-4</a><br/>\n" +
            "\t\t</div>\n" +
            "\t\t\t\t\t</div>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div class=\"state\"></div>\n" +
            "\t\t\t\t<div id=\"totop\"></div>\n" +
            "\t\t\t\t<script>\n" +
            "\t\t\t\t\ttab('.c_tab span', '.contentb', 'cur');\n" +
            "\t\tfunction tab(name, content, cur, n) {\n" +
            "\t\t\t$(name).mouseover(function() {\n" +
            "\t\t\t\t$(this).addClass(cur).siblings().removeClass(cur);\n" +
            "\t\t\t\t$(this).parent().parent().find(content).children(\"ul\").eq($(this).index()).addClass(\"cur\").siblings().removeClass(\"cur\");\n" +
            "\t\t\t});\n" +
            "\t\t}\n" +
            "\t\t$(function(){\n" +
            "\t\t\tvar lunar = GetLunarDay(2023, 11, 25);\n" +
            "\t\t\t$('#lunar').html(lunar);\n" +
            "\t\t\tchange_height();\n" +
            "\t\t\t$(\".date .d_select\").hover(\n" +
            "\t\t\t\tfunction () {\n" +
            "\t\t\t\t\t$('.date .d_select p').show();\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\tfunction () {\n" +
            "\t\t\t\t\t$('.date .d_select p').hide();\n" +
            "\t\t\t\t}\n" +
            "\t\t\t\t);\n" +
            "\t\t\t$(window).resize(function() {\n" +
            "\t\t\t\tclernH();\n" +
            "\t\t\t\tchange_height();\n" +
            "\n" +
            "\t\t\t});\n" +
            "\t\t});\n" +
            "\t\tfunction change_height () {\n" +
            "\t\t\tif($(window).width()<480){\n" +
            "\t\t\t\tnum = 0;\n" +
            "\t\t\t\t$(\".experience li\").each(function(i){\n" +
            "\t\t\t\t\tif(!$(this).is(\":hidden\")){\n" +
            "\t\t\t\t\t\tprenum = i-1;\n" +
            "\t\t\t\t\t\tpreobj = $(\".experience li\").eq(prenum);\n" +
            "\t\t\t\t\t\timgobj = preobj.children('div')\n" +
            "\t\t\t\t\t\theight = -25*num;\n" +
            "\t\t\t\t\t\t$(this).css(\"top\",height);\n" +
            "\t\t\t\t\t\tnum++;\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t});\n" +
            "\t\t\t\tpreheight = $('.experience ul').height();\n" +
            "\t\t\t\t$('.experience ul').height(preheight-(25*(num-1)));\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t}\n" +
            "\t\tfunction clernH () {\n" +
            "\t\t\t$('.experience li').css(\"top\",0);\n" +
            "\t\t\t$('.experience ul').height('auto');\n" +
            "\t\t}\n" +
            "\t\tfunction c_event(v,a){\n" +
            "\t\t\tclernH();\n" +
            "\t\t\t$v=$(v);\n" +
            "\t\t\t$v.addClass('cur').siblings().removeClass('cur');\n" +
            "\t\t\tif(!a){\n" +
            "\t\t\t\t$('.experience li').removeClass('dpn');\n" +
            "\t\t\t}\n" +
            "\t\t\telse{\n" +
            "\t\t\t\t$('.experience li').addClass('dpn');\n" +
            "\t\t\t\t$('.experience li.'+a).removeClass('dpn');\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\t$(\".etype\").removeClass('fixed');\n" +
            "\n" +
            "\t\t\t$(\".experience li\").removeClass('circler');\n" +
            "\t\t\t$(\".experience li\").removeClass('circlel');\n" +
            "\t\t\ttindex = 1;\n" +
            "\t\t\t$(\".experience li\").each(function(i){\n" +
            "\t\t\t\tif(!$(this).hasClass('dpn')){\n" +
            "\t\t\t\t\tif(tindex%2 == 1){\n" +
            "\t\t\t\t\t\t$(this).addClass('circlel');\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\telse{\n" +
            "\t\t\t\t\t\t$(this).addClass('circler');\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t\ttindex++;\n" +
            "\t\t\t\t}\n" +
            "\t\t\t});\n" +
            "\n" +
            "\t\t\tvar scroH = $(this).scrollTop();\n" +
            "\t\t\tif(scroH>240)\n" +
            "\t\t\t\t$('body,html').animate({scrollTop:0},1);\n" +
            "\t\t\tchange_height();\n" +
            "\t\t}\n" +
            "\t\tvar navH = $(\".etype\").offset().top;\n" +
            "\t\t$(window).scroll(function(){\n" +
            "\t\t//获取滚动条的滑动距离\n" +
            "\t\tvar scroH = $(this).scrollTop();\n" +
            "\t\t//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定\n" +
            "\t\tif(scroH>=navH){\n" +
            "\t\t\t$(\".etype\").addClass('fixed');\n" +
            "\t\t}else if(scroH<navH){\n" +
            "\t\t\t$(\".etype\").removeClass('fixed');\n" +
            "\t\t}\n" +
            "\t\telse{\n" +
            "\n" +
            "\t\t}\n" +
            "\t});\n" +
            "\n" +
            "\t\tvar pg = 1;\n" +
            "\t\t$(\"#more\").click(function(){\n" +
            "\t\t\t$('.loading').show();\n" +
            "\t\t\t$.getJSON(\"https://www.lssjt.com/index.php?m=content&c=index&a=json_event\",{page:pg, pagesize:40, month:11, day:25},function(json){ \n" +
            "\t\t\t\t$('.loading').hide();\n" +
            "\t\t\t\tif(json){ \n" +
            "\t\t\t\t\tvar str = \"\"; \n" +
            "\t\t\t\t\tcurid = $('.etype span.cur').attr('tdata');\n" +
            "\t\t\t\t\t$.each(json,function(index,list){\n" +
            "\t\t\t\t\t\tif((list.id != curid) && (curid !=0))\n" +
            "\t\t\t\t\t\t\ttclass = \" dpn\";\n" +
            "\t\t\t\t\t\telse\n" +
            "\t\t\t\t\t\t\ttclass = \"\";\n" +
            "\t\t\t\t\t\t$('.etype span.cur').attr('tdata')\n" +
            "\t\t\t\t\t\tif(index%2 == 1){\n" +
            "\t\t\t\t\t\t\tstr += \"<li class=\\\"circler typeid_\"+list.id+tclass+\"\\\">\"; \n" +
            "\t\t\t\t\t\t}else{\n" +
            "\t\t\t\t\t\t\tstr += \"<li class=\\\"circlel typeid_\"+list.id+tclass+\"\\\">\"; \n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\tstr += \"<p class=\\\"time\\\"><span class=\\\"moh\\\"><b>\"+ list.solaryear +\"</b><i>&nbsp;—&nbsp;●&nbsp;</i></span><span class=\\\"poh\\\"><b>\"+ list.solaryear +\"</b></span></p>\";\t\n" +
            "\t\t\t\t\t\tif(list.thumb){\n" +
            "\t\t\t\t\t\t\tstr += \"<div class=\\\"pic\\\"><a href='\"+list.url+\"' title='\"+list.title+\"' class=\\\"pica\\\" target=\\\"_blank\\\"><img src='\"+list.thumb+\"' alt='\"+list.title+\"' class=\\\"roundies\\\"></a><div class=\\\"t\\\"><span>\"+ list.solaryear +\"年11月25日</span><a href='\"+list.url+\"' title='\"+list.title+\"' target=\\\"_blank\\\" class=\\\"txt\\\">\"+list.title+\"</a></div></div>\"; \n" +
            "\t\t\t\t\t\t}else{\n" +
            "\t\t\t\t\t\t\tstr +=\"<div class=\\\"text pr\\\"><span>\"+ list.solaryear +\"年11月25日</span><a href='\"+list.url+\"' title='\"+list.title+\"' target=\\\"_blank\\\">\"+list.title+\"</a><p>\"+list.description+\"</p><i></i></div>\";\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\tstr += \"</li>\";\n" +
            "\t\t\t\t\t});\n" +
            "$(\"#container\").append(str); \n" +
            "pg++;\n" +
            "clernH();\n" +
            "change_height();\n" +
            "}else{\n" +
            "\t$('.viewmore').css('background','none');\n" +
            "\t$('.get_more').html('没有更多信息');\n" +
            "\treturn false;\n" +
            "}\n" +
            "});\n" +
            "\n" +
            "}); \n" +
            "\t\t\t\t</script>\n" +
            "\t\t\t\t<script language=\"javascript\">\n" +
            "\t\t\t\t\tvar adobj = $(\".adroll\");\n" +
            "\tif(adobj.length > 0)\n" +
            "\t{\n" +
            "\t\tadheight = adobj.position().top+1480;\n" +
            "\t\trollheight = $(document).scrollTop();\n" +
            "\t\tadroll(rollheight,adheight);\n" +
            "\t\t$(window).scroll(function()\n" +
            "\t\t{\n" +
            "\t\t\trollheight1 = $(document).scrollTop();\n" +
            "\t\t\tadroll(rollheight1,adheight);\n" +
            "\t\t});\n" +
            "\t}\n" +
            "\tfunction adroll(rollheight,adheight){\n" +
            "\t\tzindex = $('.state').css('z-index');\n" +
            "\t\tif(zindex>=4)\n" +
            "\t\t\treturn;\n" +
            "\t\tif(rollheight>=adheight){\n" +
            "\t\t\t$(\".adroll\").addClass(\"adfixed\");\n" +
            "\t\t}\n" +
            "\t\tif(rollheight<=adheight){\n" +
            "\t\t\t$(\".adroll\").removeClass(\"adfixed\");\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\t\t\t\t</script>\n" +
            "\n" +
            "\t\t\t\t<div id=\"gg_57\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\tif(isMobile()){m_index_rand1();}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div id=\"gg_58\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\tif(isMobile()){m_index_rand2();}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div id=\"gg_59\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\tif(isMobile()){m_index_rand3();}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div id=\"gg_30\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\tif(isMobile()){m_index_bt();}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div id=\"gg_45\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\t\tif(!isMobile()){document.write(\"<script src=\\\"https://www.lssjt.com/caches/poster_js/45.js\\\" type=\\\"text/javascript\\\"><\\/script>\");}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<div id=\"gg_12\" style=\"display:none\">\n" +
            "\t\t\t\t\t<script language=\"javascript\">\n" +
            "\t\t\t\t\t\tif(!isMobile()){document.write(\"<script src=\\\"https://www.lssjt.com/caches/poster_js/12.js\\\" type=\\\"text/javascript\\\"><\\/script>\");}\n" +
            "\t\t\t\t\t</script>\n" +
            "\t\t\t\t</div>\n" +
            "\t\t\t\t<script type=\"text/javascript\">\n" +
            "\t\t\t\t\tif(isMobile()){\n" +
            "\t\tdocument.getElementById(\"58\").innerHTML=document.getElementById(\"gg_58\").innerHTML;\n" +
            "\t\tdocument.getElementById(\"gg30\").innerHTML=document.getElementById(\"gg_30\").innerHTML;\n" +
            "\t}\n" +
            "\telse{\n" +
            "\t\tdocument.getElementById(\"45\").innerHTML=document.getElementById(\"gg_45\").innerHTML;\n" +
            "\t\tdocument.getElementById(\"12\").innerHTML=document.getElementById(\"gg_12\").innerHTML;\n" +
            "\n" +
            "\t}\n" +
            "\t\t\t\t</script>\n" +
            "\t\t\t\t<div class=\"fix-index-bt moh\"\n" +
            "\t\t\t\t\tstyle=\"display:none;height: 120px;width: 100%;position: fixed;bottom: 0;z-index: 1000;\"><a\n" +
            "\t\t\t\t\t\ttarget=\"_blank\" href=\"javascript:void(0);\"\n" +
            "\t\t\t\t\t\tstyle=\"display: block;width: 100%;height: 100%;\"></a><span style=\"position: absolute;left: 50%;display: block;width: 50px;height: 50px;margin-left: 455px;z-index: 10000;top: 35px;cursor: pointer;\" onclick=\"$(this).parent().hide();\"></span>\n" +
            "\t\t\t\t</div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
