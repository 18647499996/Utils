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
import com.liudonghan.utils.ADFormatUtils;
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

    private String htmlStr = "<p></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-bc31c90d5acc0cd8b2c5b35d851b429e_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"634\\\" data-original-token=\\\"v2-df197129eb062d4b67ad11c5386f453f\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pica.zhimg.com/v2-bc31c90d5acc0cd8b2c5b35d851b429e_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"KG_-xQTo\\\">上周的文章中我评价《繁花》是一个喷满香水的裹尸布，专门去掩盖“先富阶级”臭不可闻的发家史。有些朋友看了之后很不满意，说你应该看完再评价。我终于耗费了长达一周的时间捏着鼻子看完了，现在我对于《繁花》的评价更低了：我认为这是一部艺术成分很高，但思想性仅能对标《小时代》的作品。</p><p data-pid=\\\"Xq50u8Uq\\\">尤其是近年来，国产影视剧制作水准、思想境界泥沙俱下，广大观众会被《繁花》这种裹尸布欺骗也不怪大家，今天就给大家看一些好东西。最能对标《繁花》的一部好莱坞作品是《了不起的盖茨比》：一个拍了正处在蓬勃上升期的1990年的上海，一个拍了正处在蓬勃上升期的1920年的纽约。</p><p data-pid=\\\"SRX5nE9x\\\">只不过区别在于《了不起的盖茨比》开宗明义，繁华的纽约城股市天天创新高、一座座高楼大厦接连拔地而起、一个个百万富翁相继诞生，但是——是道德堕落的。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-fe29704408910227890908959525154e_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"1369\\\" data-original-token=\\\"v2-81b82eff66f9d2825550ce0d1a8342f4\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-fe29704408910227890908959525154e_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"M0KNdMWe\\\">紧接着电影就通过对话展示了两个鲜明对立的两个阶级：new money和old money。虽然翻译成了“上流阶层”和“暴发户”，我觉得还是new money和old money这两个又直白又赤裸裸的形容词更能方便大家理解。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-052bd80e2fdaf21edadcfe12a337c8d7_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-16d54c4f098c376cb843b1f31f9b6f14\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-052bd80e2fdaf21edadcfe12a337c8d7_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-b37711f19f1f6ba3f0884a407b6f5d66_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"453\\\" data-qrcode-action=\\\"none\\\" data-original-token=\\\"v2-a1946bd68fccd927a16fa3353869ab21\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic1.zhimg.com/v2-b37711f19f1f6ba3f0884a407b6f5d66_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"jdPmuUDe\\\">仅有这两个阶层么？不，还有一个与之鲜明对立的劳工阶层，即无产阶级。这是电影中劳工们生活的贫民窟“纽约的垃圾场”——</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic2.zhimg.com/v2-11a84c07bce76778eb6e27a4782351d3_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"1364\\\" data-original-token=\\\"v2-cbe51f022b7d70841c6b8deeb462a340\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic2.zhimg.com/v2-11a84c07bce76778eb6e27a4782351d3_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"CxcXqY1E\\\">“这是纽约的‘垃圾场’，为飞速发展的黄金城市提供‘资源’”，这里的资源是一句双关，既包括煤炭这种自然资源，也包括活生生的劳动力即“人力资源”。</p><p data-pid=\\\"arWI44UH\\\">《了不起的盖茨比》电影开头短短几分钟的内容，其信息量超过了整部三十集之长的《繁花》，这就是鲜明的差距。在《繁花》中我们可曾看到哪怕一点真实与反思的镜头，给到了“供上海城市运转的资源们”？没有，有的仅仅是一个浮光掠影、纸醉金迷、属于少数人虚伪繁华的空中楼阁。</p><p data-pid=\\\"Q_pecvqg\\\">而《了不起的盖茨比》虽然主视角也是上流社会浮光掠影、纸醉金迷的生活，但电影把足够的关注度赋予了“社会的背面”，运用了大量镜头语言，来展现纽约城巨大的贫富差距甚至阶级矛盾。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-f2082247ca05a74aa6d4e98643bccfd0_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"454\\\" data-original-token=\\\"v2-646ba6bfee0073f2c8464022e7261d35\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pica.zhimg.com/v2-f2082247ca05a74aa6d4e98643bccfd0_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"QgVYoN0L\\\">比如上图是镜头从资本家酒池肉林的淫趴中拉升，展示了整个1920s纽约城的繁华，但是在镜头的右上角里不忘出镜两个深夜凌晨还在施工的工人。这个镜头语言非常明显，这个繁华的纽约城是他们建设的，但是纽约城的繁华不属于他们，甚至深夜还要加班赶工期。</p><p data-pid=\\\"2ZkNZH6s\\\">《繁花》打着“重现历史、高度还原”的噱头，吹嘘自己请来各路专家，这也还原、那也还原，唯独不见还原历史的“另一面”——广大劳工阶级的真实生活。难不成真是王大老爷心善，上海滩十里洋场花花世界，见不得一个穷人？</p><p data-pid=\\\"7AC7pR9W\\\">《了不起的盖茨比》还有这个经典画面，从富人区开往穷人区的道路上，一条泾渭分明的分界线——</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-4f9ec30e156a2289d4d102457ee6e4cc_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-8c1744e55b9c45cabdbdee997d454fb3\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic1.zhimg.com/v2-4f9ec30e156a2289d4d102457ee6e4cc_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"B8-SjrIC\\\">《繁花》重建了1990的上海，《了不起的盖茨比》重建了1920的纽约。但是导演不仅仅沉迷于纽约作为世界第一城市的荣耀与光环，无时无刻不在镜头语言中体现着对于繁荣和发达的批判性与反思性。</p><p data-pid=\\\"Mzh6Mmez\\\">所以说《繁花》是虚的、是伪的，是没有任何思想性的华丽空壳，是一部卑颜屈膝上贡给“先富”们马屁诗。</p><p data-pid=\\\"ErZSaY5L\\\">《了不起的盖茨比》中“爱情”和“初恋”是一条明线，而“阶级”“贫富”则是一条暗线：“这个世界是不属于穷小子的”这句话不仅仅是对记叙者尼克说的，更是盖茨比悲剧的核心根源。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-a9a9404afb58b4006efa675d14e7283c_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-2540a225b6003eb8bf555465a4917256\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-a9a9404afb58b4006efa675d14e7283c_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"-E48eYjQ\\\">黛西的形象第一位的当然是“爱情”的象征，但还有一个更深层次的表达——是“美国梦”具象化的体现。</p><p data-pid=\\\"9wPbjgd6\\\">一个穷小子不认自己的农民爹妈，一门心思做奋斗逼往上爬，难道仅仅是为了一丝“初恋情怀”吗？当然不是，否则《了不起的盖茨比》就不能称之为美国最伟大的小说之一了，也不会登上中国初高中学生们课外阅读的推荐书目了。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-2be5efc6bb0e18a00e2ff0cf606b6c5d_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"449\\\" data-original-token=\\\"v2-16c64451c068782940cfa2c6de74fb93\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-2be5efc6bb0e18a00e2ff0cf606b6c5d_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"Zyo3YZ6k\\\">这部作品的立意必然很高，其实最根本讲的就是一个奋斗逼“美国梦”的破碎，以及美国上流社会的虚伪、贪婪、腐朽与堕落。</p><p data-pid=\\\"zskZDDpD\\\">有钱又如何，天天开大party又如何，你是一个穷小子出身，就永远跨越不过血缘与身份的阶级壁垒。“黛西”也就是“美国梦”，永远不会真正属于你。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-e5e6ea940216641728d15b9824ea54b0_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"646\\\" data-original-token=\\\"v2-c77ed06517485b41a53cc4de4036ebd0\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic1.zhimg.com/v2-e5e6ea940216641728d15b9824ea54b0_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"cv87MP2h\\\">更进一步，真正的“美国梦”又如何，不管是new money和old money又如何？还不是腐朽、堕落、罪恶包裹上一张华丽的外皮吗？区别在于《了不起的盖茨比》戳破了这张画皮，而《繁花》就是画皮本身——</p><p data-pid=\\\"rzJSUn1S\\\">把先富阶级的财富积累道德化、优美化、正义化、个人奋斗化、能力归因化、商战智慧化，是对真实历史的遮掩、罪恶与剥削的粉饰、人民记忆扭曲。</p><p data-pid=\\\"TIq5Kctq\\\">你但凡拍出了《了不起的盖茨比》中十分之一的上流社会的批判性与反思性，我绝对会说墨镜王最牛逼，谁反对墨镜王我就反对谁。但偏偏把《繁花》拍成了一条华丽的、手工的、匠人精神的、精织细秀的、有超高审美价值的——裹尸布。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-2ac26b400745777277826e81eca6d268_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-fe0466334185ff9781ddc855222edd24\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-2ac26b400745777277826e81eca6d268_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"TndevLY6\\\">我也是在抱怨什么，因为早就对这种现象习以为常了，现在几乎所有的文艺作品只要拍一些现实题材，都离不开对财富和权力的上位阶级的美化，以及对平民阶层庸俗的想象与恶意的揣测。老读者肯定知道，我对相关的话题是天天骂、月月骂、年年骂，所以已经毫无情感上的波澜，已经可以剥离这种尸臭味去单纯享受视听影像带来的享受。</p><p data-pid=\\\"LvuoJmDs\\\">不过优秀的作品还是很多的，我们就来看看《了不起的盖茨比》是怎样把《繁花》挂上的画皮撕下来的：首先是new money的画皮：电影中提出的这个问题——“钱（真正）从哪里来”，是《繁花》三十集都没有讲明白、或是有意掩盖的。</p><p data-pid=\\\"biUpahFN\\\">其实右上角的歌词，就已经回答了这个问题——<br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-81b51beb24f1a11870a7bb2b71a61760_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-c0023b91260a6c3010426a49ce4bcb55\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-81b51beb24f1a11870a7bb2b71a61760_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"EqxIAt41\\\">盖茨比作为一个穷小子，想要跨越阶层的天花板，是一件非常小概率的事件，然而他却非常不易地集齐了：</p><p data-pid=\\\"nF10QzID\\\">第一，自身的努力，化身为奋斗逼；第二，奇遇，救起了一位酗酒的百万富翁；第三，也就是最根本的一点，他的财富积累具有非常擦边球的性质——道德的擦边球和法律的擦边球。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic4.zhimg.com/v2-7aadff342016aa33ebd0eed0fbd59823_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"452\\\" data-original-token=\\\"v2-b437bda92b1aee8383a5f5dcccd3b3ae\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic4.zhimg.com/v2-7aadff342016aa33ebd0eed0fbd59823_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"HTJETOOQ\\\">电影中反复提及的一个名字迈耶·沃夫希姆，是一个犹太黑帮大佬，靠贩卖私酒和金融投机获取财富，盖茨比本质来讲是他的白手套，帮助他获取信息、勾兑政商关系、对其他富人进行金融欺诈——这就是一个穷小子所能到达的天花板了。</p><p data-pid=\\\"mNk2uzrA\\\">其次是old money的画皮：盖茨比想要跨越积极的努力不具备合法性，那么布坎南继承祖祖辈辈的财富就具有合法性吗？当然更不具有了，从作者对这两个人物的褒贬就一目了然。</p><p data-pid=\\\"WitNDS-s\\\">布坎南是一个愚蠢、自大、暴躁、出轨、种族歧视与阶级歧视的人渣，但偏偏这种人渣能继承傲视全美的财富。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-1ffb8dcd9712faa757a3246762eea6d1_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"449\\\" data-original-token=\\\"v2-9c5af7b069c2fde9c8623ca741265afb\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-1ffb8dcd9712faa757a3246762eea6d1_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"ODzmZrpb\\\">这就是反思性和批判性的体现，不像《繁花》一样，把道德、正义、积极与美的光环全都谄媚地一股脑加给了“先富”阶层。《了不起的盖茨比》用镜头语言和剧情让我们更深刻地思考：百万富翁又如何，资本家和旧贵族又如何，凭什么不能对他们进行道德的审视？</p><p data-pid=\\\"08m2gKrd\\\">进一步思考，盖茨比的悲剧是一个更深的隐喻：布坎南出轨穷人的妻子，黛西撞死了穷人的妻子，而这一切误会与谎言，最终造成了穷人把仇恨矛头对准了盖茨比——这就是最顶层阶级制造的矛盾，转嫁给了现在的穷人和曾经的穷人，贵族们永远是最后的赢家。</p><p data-pid=\\\"GaVXCF2j\\\">《繁花》美则美矣，但是《了不起的盖茨比》也美啊，美跟思想性又不是冲突的事情，我随便放两张剧照：</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-e1d7753fcaf0f50be75f10a4988f9912_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"449\\\" data-original-token=\\\"v2-3295b1a93d2a4cf55c6e68a02872e8fe\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic1.zhimg.com/v2-e1d7753fcaf0f50be75f10a4988f9912_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-cea8c06fa7ae7f178891a14ad903ad7b_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"720\\\" data-original-token=\\\"v2-2fe11f4465e96ade4a5297ec53e1befb\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-cea8c06fa7ae7f178891a14ad903ad7b_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-d26203beb1b38635e1bd3eb426fc4de1_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"599\\\" data-original-token=\\\"v2-19267ed541c500fbf3646e98117d8a20\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-d26203beb1b38635e1bd3eb426fc4de1_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"E59BfrgP\\\">在《繁花》中，你能看到这样对于社会肌理如此深入、细腻且立体的剖析吗？一点都没有。《繁花》里不是没有普通人，但都是一群无血无肉的普通人，是“先富阶级”的衬托、附属物、挂件，没有任何主观能动性的表达和个人情感欲望的诉求，是最最标准的“工具人”形象。</p><p data-pid=\\\"vSQcQ1Ec\\\">比如《繁花》中的一个情节：资本家拿到了一笔大订单，血汗工厂的工人们集体欢呼。这个情节也就是没工作过的蠢学生和十指不沾阳春水的小布尔乔亚，现在的年轻人都经历过资本主义的暴打，都清楚得很——大订单虽然可以让董事长再买一辆法拉利，但也给了我们宝贵的加班机会啊！</p><p data-pid=\\\"IgEhnr0U\\\">所以哪有什么阶级对立，哪有什么贫富分化，哪有什么血汗工厂，哪有什么劳务派遣……看见没，工人和资本家是一起欢呼的，一起高唱着“我们是相亲相爱的一家人”，人民对历史的记忆就这样被改写了。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic2.zhimg.com/v2-d8ef250b77c03381910d1d745a0592ff_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"586\\\" data-original-token=\\\"v2-98edfb68797af5146e4d1a57294690ac\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic2.zhimg.com/v2-d8ef250b77c03381910d1d745a0592ff_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"-iBmP_87\\\">（配图：电影《悲惨世界》）</p><p data-pid=\\\"Itb4FYXD\\\">《花样年华》《重庆森林》《春光乍泄》《阿飞正传》等一系列作品中，王家卫也展现出了他对底层、对小人物、对边缘群体的人文关怀：普通人也有欲望，也有情与爱，也在挣扎中努力地拼凑自己完整的灵魂，他们也值得“被看见”。</p><p data-pid=\\\"wzFqR7zm\\\">然而《繁花》则将这种温情的俯视和细腻的探究一扫而空，变成了对上位者的讨好与献媚，为先富阶级织出了这样一条唯美、优雅、温情脉脉的裹尸布，好掩盖那个腐朽堕落的阶层不断散发的尸臭味，再继续用这种虚假的画皮欺骗一批又一批年轻的无产阶级。</p><p data-pid=\\\"_kd4tn2g\\\">我气，是因为他是王家卫，他是审美的教员，他是光影的魔术师，他是镜头美学的探路者，他是缪斯送给华语电影的珍宝。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-13ec218701707c7d8c8be96e205a6b3e_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"723\\\" data-original-token=\\\"v2-2adbc97bf1d5631e41f4ff8e9e4c85eb\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-13ec218701707c7d8c8be96e205a6b3e_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"XMsLJmyf\\\">要不是因为他是王家卫，我还真不至于这么气。不然跪舔先富、对标“小时代”价值观的影视作品在当今时代可谓比比皆是，甚至是主流、是潮流，但是你王家卫拍出这种献媚的作品……</p><p data-pid=\\\"3CEdq-jZ\\\">就好像武侠小说里一位大侠苦练数十年武功，终于成为了数一数二的绝世高手，结果自宫当太监去做皇帝的大内侍卫了。然后在皇帝和贵妃把酒言欢的宴席上，舞出了一套“九天玄女繁花剑”，获得了皇帝贵妃还有周围一众大小太监们的啧啧称奇。</p><p data-pid=\\\"ETvAYnP1\\\">吹《繁华》的人不仅仅是阅片量浅薄，没有看过真正有深度的好作品，更是政治上的幼稚，对“先富起来”阶层的非法性、非道德性、堕落性缺乏警惕，才容易被这样一部“金玉其外，败絮其中”的作品牵着鼻子走。</p><p data-pid=\\\"t94ecAyQ\\\">基于此，我再给大家推荐几部好作品，比如同类型题材、同样是小李子主演的《华尔街之狼》。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-1001d27e5cc6a14bf16bff3db7702c75_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"450\\\" data-original-token=\\\"v2-40c2ef391f1170b0ea34b55fff141e57\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-1001d27e5cc6a14bf16bff3db7702c75_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"f8rS96a4\\\">这部同样展现华尔街富豪阶级的作品，同样充满了批判性与反思性，用大量非常黄暴的限制级镜头向我们展示了一个奢靡堕落、酒池肉林、声色犬马、醉生梦死的上流社会。</p><p data-pid=\\\"TIT2QpCN\\\">我详细截图一帧一帧分析了《了不起的盖茨比》，《华尔街之狼》大家可以用同样的方法论去自己评析，学会这种阶级视角是最重要的，本文就简单一笔带过了。</p><p data-pid=\\\"onm1ZVAC\\\">两部电影里的小李子财富积累的手段是相同的——金融投机行为，或者再直白一点说就是坑蒙拐骗，把一些垃圾股票包装包装卖给有大笔退休金又不懂金融的中老年人，这跟缅北诈骗有什么区别？</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-b17b8cb52ae21bb3947beaf7e7b1650a_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"400\\\" data-rawheight=\\\"276\\\" data-original-token=\\\"v2-10f5ba46068a48ba9a9e8e48185565e2\\\" class=\\\"content_image\\\" width=\\\"400\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"pMPHVS2b\\\">无论是《了不起的盖茨比》还是《华尔街之狼》，都给我们展示了不同的视角去审视上流社会，既有奋斗的、搏命的、超人才智的，也有腐朽的、堕落的、吃人与剥削的，这就是好作品的全面性、思想性与批判性。</p><p data-pid=\\\"-kCDw_-m\\\">然而《繁花》只有一个视角，一个单薄而虚伪的“先富”的视角，把纸醉金迷变成了光影之美，把声色犬马变成了风度翩翩，把剥削和欺骗用道德与能力掩盖，最终为观众献上了一份雕花的臭狗屎。</p><p data-pid=\\\"JLV3QVjg\\\">我怕有人不服气，我再举一个香港的例子，经典电视剧《大时代》和《创世纪》中，同样是讲上流社会，同样充满了对先富阶级的审视、批判与反思。比如《大时代》中最经典的镜头之一，就是用白纸黑字的视觉冲击力把“阶级视角”四个字写给观众：</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-a65b7c5c5a7cd436e85ee9464447f966_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"608\\\" data-original-token=\\\"v2-4d4cab76ba281ab2cb789d3e8399171a\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pica.zhimg.com/v2-a65b7c5c5a7cd436e85ee9464447f966_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-7774ff0fa7eabce2c0d1c759b3d943a2_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"608\\\" data-original-token=\\\"v2-1735c6ec70b26d3027f0ae77da9f83c1\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pica.zhimg.com/v2-7774ff0fa7eabce2c0d1c759b3d943a2_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"AKf-LnZg\\\">而《创世纪》中的经典“反思”——这个世界笑贫不笑娼！</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-9fa6e13934ebcec4daafd0b637017bec_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"595\\\" data-rawheight=\\\"489\\\" data-original-token=\\\"v2-0fe6a0b799fc3d0838b2437ecd63b40a\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"595\\\" data-original=\\\"https://pic1.zhimg.com/v2-9fa6e13934ebcec4daafd0b637017bec_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"clIw31W2\\\">《大时代》和《创世纪》这两部作品，一部讲股市投机，一部讲地产投机，堪称“港剧双壁”。看了这两部作品，你对社会的认知会更上一个台阶，而看了《繁花》之后有什么？</p><p data-pid=\\\"cxeawgTk\\\">我们看跟《繁花》相关的讨论，都是宝总吃了什么餐厅，宝总住了什么总统套房；而《小时代》上映之后，大家都在热议杨幂背的是爱马仕的那款包，郭采洁穿的是巴宝莉的定制高跟鞋——这不一模一样吗，说你是（艺术成分很高的）《小时代》很委屈吗？</p><p data-pid=\\\"KUmnLWjB\\\">再来看看这两部老港剧的深度。老读者都知道，我只要一讲房地产问题，都会配上以下截图，这就是出自《创世纪》：</p><figure data-size=\\\"normal\\\"><img src=\\\"https://picx.zhimg.com/v2-363d09cd1b4662087520d25f23c8a209_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"1647\\\" data-original-token=\\\"v2-b71311c691ebf39fbb26e72958b254d4\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://picx.zhimg.com/v2-363d09cd1b4662087520d25f23c8a209_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"6Ip803DN\\\">前后剧情中还有更深入的讨论，直指投机行为的合法性或道德性：</p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic1.zhimg.com/v2-ee27de61d33d6188672ce274383a669e_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"389\\\" data-rawheight=\\\"676\\\" data-original-token=\\\"v2-15c9ceb3a15f76b4971ec432bd0e1abd\\\" class=\\\"content_image\\\" width=\\\"389\\\"/></figure><figure data-size=\\\"normal\\\"><img src=\\\"https://pica.zhimg.com/v2-7d29e5f8d028001900cf3cdd687ec8f6_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"385\\\" data-rawheight=\\\"632\\\" data-original-token=\\\"v2-43030dcc813044bbaac26759050d64d1\\\" class=\\\"content_image\\\" width=\\\"385\\\"/></figure><p data-pid=\\\"sD_pVkAJ\\\">《大时代》中也充满了广为人知的名场面，比如这个“技术性调整”——</p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-d799498aa11f2aaa7723641c962a4c72_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"712\\\" data-original-token=\\\"v2-716e31bfca9e9e2db3f4b842d36c2f03\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-d799498aa11f2aaa7723641c962a4c72_r.jpg\\\"/></figure><p data-pid=\\\"_IXa7b-j\\\">我们看同样是跳楼的戏份，《大时代》也比《繁花》要高出一档。《大时代》中的跳楼，就是“宿命的惩罚”：你搞投机、玩弄股票、割大多数人韭菜，那其实你也不过是韭菜的一部分；而《繁花》的跳楼则是一种哀叹，一种美好的消逝，一种悲伤于时代的落幕。</p><p data-pid=\\\"cJlHWDIf\\\">这种感觉就跟《罗曼蒂克消亡史》一样，从名字就看得出来——民国的上海滩是罗曼蒂克的，太可惜了，罗曼蒂克是消亡的。</p><p data-pid=\\\"sckbS3nu\\\">导演在采访里说，葛优（原型就是黑帮大佬杜月笙）回国被安检的那个镜头，就是典型意味着“罗曼蒂克的消亡”，你看一个这样叱咤风云的大佬，最后被一个海关小兵，让伸手伸手，让脱帽子脱帽子，这一仗打完，他们的地位全没了。</p><p data-pid=\\\"6XYmQzQK\\\">当时我就想，海关安检让你脱个帽子有啥问题啊，凭什么你就有特权啊，非得要国家当你家就满意了啊，就罗曼蒂克了啊。</p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-8e5db37895ea86c5fdb2f0ceaccc1028_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"403\\\" data-original-token=\\\"v2-6c2f2798ef95c0d75ac9b44700ab7f20\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-8e5db37895ea86c5fdb2f0ceaccc1028_r.jpg\\\"/></figure><p data-pid=\\\"A6dA1X1X\\\">所以说这些文人、小资产阶级文艺从业者，屁股从来坐不正的，他们从来不会把自己摆在一个与绝大多数人平等的地位上，天然的瞧不起劳动人民，他们潜意识中还是认为自己应该像旧社会那样做老爷，像民国那样才子佳人风花雪月，像国外贵族那样罗曼蒂克。</p><p data-pid=\\\"Ct1_pTL8\\\">一旦他们的特权被新生代的进步势力所摧毁后，也自然会如丧考妣地去怀念那一段“消亡的罗曼蒂克”，本质上讲是怀念他们永不可回的特权。</p><p data-pid=\\\"31383N5q\\\">《罗曼蒂克消亡史》开头部分就是杜月笙活埋了一个人，当初看电影的时候我还以为被坑杀的是另外一个黑帮大佬，后来越想越不对，经过多方确认，那个形象的原型是我党早期工人运动领袖、革命烈士汪寿华。</p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic2.zhimg.com/v2-04cc960e4cb3eb1247e90fe74a54327f_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"409\\\" data-original-token=\\\"v2-52010480c35a57b96b1f9266a87610e7\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic2.zhimg.com/v2-04cc960e4cb3eb1247e90fe74a54327f_r.jpg\\\"/></figure><p data-pid=\\\"foH7TsX1\\\">这样的污名化可以说非常过分了，汪寿华是五卅反帝爱国斗争的主要领导人之一，先后参与指挥了上海工人三次武装起义，1927年4月11日深夜被青帮头目杜月笙指使打手活埋于上海城西枫林桥，是四一二大屠杀中第一位牺牲的共产党员。</p><p data-pid=\\\"Q7MaOZZG\\\">而在《罗曼蒂克消亡史》中，汪寿华被丑化成了一个猥琐的中年男子，在上海养小老婆、畏首畏尾、贪生怕死；而历史中的汪寿华，无论是罢工游行还是起义战斗，永远是冲在第一线的。即便在真正的历史中，最后的鸿门宴上杜月笙也对汪寿华表达了由衷的赞赏，甚至说出你跟我干保你不死的话来。</p><p data-pid=\\\"2jkLTZW7\\\">这部电影往小了说是历史虚无主义，往大了说就是违反《英雄烈士保护法》。</p><p data-pid=\\\"ZFmrK_eh\\\">在《罗曼蒂克消亡史》中葛优对汪寿华说：“（罢工者）有些人就不想让上海好”——这里的“好”要加一个限定词，是上层社会的“好”。上海滩十里洋场花花世界，葛优代表的这个黑道大家族可以优雅，可以腔调，可以罗曼蒂克，想喝粥喝粥、想吃点心吃点心、想跳舞跳舞、想拍电影拍电影、想砍谁手砍谁手、想活埋谁活埋谁……</p><p data-pid=\\\"1o3YeVyL\\\">他们自然怀念这个花花世界，自然怀念这个罗曼蒂克。但是这跟上海最广大的底层人民没关系，跟码头扛活的没关系、跟街上拉车的没关系、跟工厂劳工没关系，他们是罗曼蒂克下的蝼蚁，他们用自己的血汗扛起了这个花花世界，然而在这个花花世界里他们跟牲口没有两样。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic4.zhimg.com/v2-d14a3221162f18f4f5d7193d0c1b722b_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"540\\\" data-original-token=\\\"v2-2611baac533c31fec62acafb96f076ad\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic4.zhimg.com/v2-d14a3221162f18f4f5d7193d0c1b722b_r.jpg\\\"/></figure><p data-pid=\\\"RA6IJdvT\\\">所以我们看，本文从横向同类题材分析了四部排上流社会的作品——《了不起的盖茨比》《华尔街之狼》《大时代》《创世纪》，上一篇文章中从纵向的对比题材中分析了《漫长的季节》《风中有朵雨做的云》《暴裂无声》。</p><p data-pid=\\\"d6Pv_nay\\\">对比这些作品，我们就可以很容易看出《繁花》从思想光谱和传达理念上最相似的作品是什么——就是《小时代》。但是，一部艺术成分很高的《小时代》，反而是危害性更强的《小时代》。</p><p data-pid=\\\"Xbxot7oF\\\">因为它更容易迷惑人民，让真实的历史颠倒了过来，让本就获取财富的先富阶级在美与道德的光环中继续夜夜笙歌，我们劳动人民不能这样放任，让改写历史的两根柱子就这样轻易被他们完成了。</p><p class=\\\"ztext-empty-paragraph\\\"><br/></p><figure data-size=\\\"normal\\\"><img src=\\\"https://pic3.zhimg.com/v2-2c0550b886717acd624b88db6630d046_b.jpg\\\" data-caption=\\\"\\\" data-size=\\\"normal\\\" data-rawwidth=\\\"1080\\\" data-rawheight=\\\"451\\\" data-original-token=\\\"v2-c01953d2c11b6877349e4b332f31ffcd\\\" class=\\\"origin_image zh-lightbox-thumb\\\" width=\\\"1080\\\" data-original=\\\"https://pic3.zhimg.com/v2-2c0550b886717acd624b88db6630d046_r.jpg\\\"/></figure><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p data-pid=\\\"F4RUPuy9\\\">首发于公众号大浪淘沙，欢迎关注：</p><a href=\\\"https://link.zhihu.com/?target=https%3A//mp.weixin.qq.com/s%3F__biz%3DMjM5NzE2NTY0Ng%3D%3D%26mid%3D2650694677%26idx%3D1%26sn%3D2a25f1286356207b5812ceec1cdf3127%26chksm%3Dbed4eb3489a36222c0cba5f455372c4d57c65bf57eddb7eac5d1e2f50daa4c71e16c56b02d24%26token%3D70473275%26lang%3Dzh_CN%23rd\\\" data-draft-node=\\\"block\\\" data-draft-type=\\\"link-card\\\" data-image=\\\"https://pic3.zhimg.com/v2-7b409d79856fc0dcddc9aba290a8f8ca_180x120.jpg\\\" data-image-width=\\\"1280\\\" data-image-height=\\\"545\\\" class=\\\" wrap external\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\">《繁花》：王家卫自甘堕落，拍了一部艺术成分很高的《小时代》</a><p></p>";

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
                .load("https://image.whb.cn/video/1708131903084_r6ixb1_5566668999.mp4?x-oss-process=video/snapshot,t_0,f_jpg,w_640,h_360")
                .into(activityHtmlImgCover);
//        ADHtmlUtils.getInstance().parseHtml(htmlStr);
        List<ADHtmlUtils.ElementBean> elementBeanList = ADHtmlUtils.getInstance().parseHtml(htmlStr);
        for (int i = 0; i < elementBeanList.size(); i++) {
            if (elementBeanList.get(i).getMode() == ADHtmlUtils.HtmlMode.Image) {
                elementBeanList.get(i).setContent(elementBeanList.get(i).getContent().replaceAll("\\\\\"", ""));
            }
        }
        Log.i("Mac_Liu", "数据内容：" + ADGsonUtils.toJson(elementBeanList));
        ADHtmlUtils.getInstance().parseHtml(this, htmlStr, this);

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
        ADHtmlUtils.getInstance()
                .from(this)
                .url("https://mini.eastday.com/mobile/231022221539857396924.html")
                .cssQuery("body","article.J-article.article","div.J-article-content.article-content")
                .attrs("src","data-width","data-height")
                .listener(new ADHtmlUtils.Builder.OnConnectListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onDocument(Document document, Elements selectElements, String json) {
                        Log.i("Mac_Liu","东方资讯：" + json);
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
                        while (iterator.hasNext()) {
                            ADHtmlUtils.ElementBean next = iterator.next();
                            if (next.getContent().equals("展开剩余")) {
                                iterator.remove();
                            }
                            if (next.getContent().equals("%")) {
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
