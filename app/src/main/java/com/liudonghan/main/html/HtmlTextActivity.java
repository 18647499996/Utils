package com.liudonghan.main.html;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liudonghan.main.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class HtmlTextActivity extends ADBaseActivity<HtmlTextPresenter> implements HtmlTextContract.View {

    @BindView(R.id.html)
    TextView html;

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
        html.setText(Html.fromHtml("<h2>Dixie原本是只流浪狗，后来被主人捡到，这才有了个家...</h2><h2>主人很爱它，丝毫不觉得它哪里怪，然而每个看到Dixie的人，都会最先注意到它的耳朵！</h2><p class=\"clear imgbox\"><!--{img:0}--></p><p class=\"clear\">这双耳朵不仅太大了，也没能竖起来，反而像两边伸出，像一对飞机翅膀！</p><p class=\"clear imgbox\"><!--{img:1}--></p><p class=\"clear\">主人就打趣的为它拍了个视频——</p><p class=\"clear\">大家都说作为一个小狗狗，我耳朵最终会长正常的...</p><p class=\"clear\">可尴尬的是，12年后的我...</p><p class=\"clear imgbox\"><!--{img:2}--></p><p class=\"clear\">结果，这个普普通通的12岁老姑娘，快速引起了很多网友的注意：</p><p class=\"clear\">这不就是汪版的尤达宝宝？？？</p><p class=\"clear imgbox\"><!--{img:3}--></p><p class=\"clear\">于是，凭借这双努力了一辈子都没能站起来的耳朵，Dixie很快就在网上火了起来，还在TikTok上拥有了十万多粉丝。</p><p class=\"clear imgbox\"><!--{img:4}--></p><p class=\"clear\">因为看习惯了， 主人并不觉得Dixie的耳朵有什么，但还是会在某些时刻被惊到：</p><p class=\"clear\">跟旁边的狗子对比起来看的话，Dixie的耳朵是真的很大很突出！</p><p class=\"clear imgbox\"><!--{img:5}--></p><p class=\"clear\">虽然是个红汪了，但Dixie的生活倒没有什么变化，它依旧和它最爱的主人，以及同是被救的同伴幸福的生活在一起...</p><p class=\"clear\">每天都在享受爱和付出爱，过着它前半生流浪时最梦寐以求的日常。</p><p class=\"clear imgbox\"><!--{img:6}--></p><p class=\"clear\">分享Dixie的故事，是想跟大家说，狗狗身上都有亮点，但是有时候，这些亮点，可能看起来是有点怪的存在...</p><p class=\"clear\">是奇怪还是亮点，就看你能否突破审美限制，每个有着小亮点的狗狗，也都值得被爱哦。</p><p class=\"clear imgbox\"><!--{img:7}--></p><p class=\"clear\"><strong>-END-</strong></p>"));
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
}
