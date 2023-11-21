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
}
