package com.liudonghan.utils;

import android.annotation.NonNull;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/10/23
 */
public class ADHtmlUtils {
    private static volatile ADHtmlUtils instance = null;

    private ADHtmlUtils() {
    }

    public static ADHtmlUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADHtmlUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADHtmlUtils();
                }
            }
        }
        return instance;
    }

    /**
     * todo 解析Html标签
     *
     * @param htmlBody Html内容
     * @return List<ElementBean>
     */
    public List<ElementBean> parseHtml(String htmlBody) {
        List<ElementBean> elementBeans = new ArrayList<>();
        Document parse = Jsoup.parse(htmlBody);
        Element body = parse.body();
        Elements allElements = body.children();
        for (Element tag : allElements) {
            if (tag.hasText()) {
                if (!ADArrayUtils.isEmpty(tag.getElementsByTag("img"))) {
                    getContent(tag.children(), elementBeans);
                } else {
                    elementBeans.add(new ElementBean(HtmlMode.Text, tag.text()));
                }
            } else {
                Elements src = tag.getElementsByTag("img");
                for (int i = 0; i < src.size(); i++) {
                    elementBeans.add(new ElementBean(HtmlMode.Image, src.get(i).attr("src")));
                }
            }
        }
        return elementBeans;
    }


    private void getContent(Elements elements, List<ElementBean> elementBeans) {
        for (int i = 0; i < elements.size(); i++) {
            if (!ADArrayUtils.isEmpty(elements.get(i).children())) {
                getContent(elements.get(i).children(), elementBeans);
            } else {
                if (!TextUtils.isEmpty(elements.get(i).text())) {
                    elementBeans.add(new ElementBean(HtmlMode.Text, elements.get(i).text()));
                } else {
                    Elements img = elements.get(i).getElementsByTag("img");
                    if (!ADArrayUtils.isEmpty(img)) {
                        for (int j = 0; j < img.size(); j++) {
                            elementBeans.add(new ElementBean(HtmlMode.Image, img.get(j).attr("src")));
                        }
                    }
                    Elements video = elements.get(i).getElementsByTag("video");
                    if (!ADArrayUtils.isEmpty(video)) {
                        for (int j = 0; j < video.size(); j++) {
                            elementBeans.add(new ElementBean(HtmlMode.Video, video.get(j).attr("src")));
                        }
                    }
                }
            }
        }
    }

    /**
     * todo 解析Html标签
     *
     * @param activity activity引用
     * @param htmlBody Html内容
     * @param textView TextView组件
     */
    public void parseHtml(Activity activity, String htmlBody, TextView textView) {
        Point screenSize = ADScreenUtils.getInstance().getScreenSize(activity);
        new Thread(() -> {
            Spanned spanned = Html.fromHtml(htmlBody, source -> {
                Drawable drawable = null;
                try {
                    drawable = Drawable.createFromStream(new URL(source).openStream(), "image.jpg");//下载图片
                    drawable.setBounds(0, 0, screenSize.x, 720);//设置图片显示范围
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }, null);

            //切换到主线程设置内容
            activity.runOnUiThread(() -> textView.setText(spanned));
        }).start();
    }

    /**
     * todo 解析Html标签
     *
     * @param activity              activity引用
     * @param htmlBody              Html标签
     * @param onADHtmlUtilsListener 解析回调
     */
    public void parseHtml(Activity activity, String htmlBody, OnADHtmlUtilsListener onADHtmlUtilsListener) {
        onADHtmlUtilsListener.onStart();
        Point screenSize = ADScreenUtils.getInstance().getScreenSize(activity);
        new Thread(() -> {
            Spanned spanned = Html.fromHtml(htmlBody, source -> {
                Drawable drawable = null;
                try {
                    drawable = Drawable.createFromStream(new URL(source).openStream(), "image.jpg");//下载图片
                    drawable.setBounds(0, 0, screenSize.x, 720);//设置图片显示范围
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }, null);

            //切换到主线程设置内容
            activity.runOnUiThread(() -> onADHtmlUtilsListener.onSucceed(spanned));
        }).start();
    }

    public interface OnADHtmlUtilsListener {
        void onStart();

        void onSucceed(Spanned spanned);
    }

    public static class ElementBean {

        private HtmlMode mode;
        private String content;

        public ElementBean(HtmlMode mode, String content) {
            this.mode = mode;
            this.content = content;
        }

        public HtmlMode getMode() {
            return mode;
        }

        public void setMode(HtmlMode mode) {
            this.mode = mode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public enum HtmlMode {
        Text,
        Image,
        Video
    }
}
