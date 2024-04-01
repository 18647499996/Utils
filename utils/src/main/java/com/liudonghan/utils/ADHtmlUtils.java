package com.liudonghan.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
                    getContent(tag.children(), elementBeans, "src");
                } else {
                    elementBeans.add(new ElementBean(HtmlMode.Text, tag.text()));
                }
            } else {
                Elements img = tag.getElementsByTag("img");
                for (int i = 0; i < img.size(); i++) {
                    elementBeans.add(new ElementBean(HtmlMode.Image, img.get(i).attr("src")));
                }
                Elements video = tag.getElementsByTag("video");
                for (int i = 0; i < video.size(); i++) {
                    elementBeans.add(new ElementBean(HtmlMode.Video,video.get(i).attr("src")));
                }
            }
        }
        return elementBeans;
    }

    /**
     * todo 获取标签内容
     *
     * @param elements     html标签节点
     * @param elementBeans 内容列表
     * @param attrs        标签属性名称
     */
    private void getContent(Elements elements, List<ElementBean> elementBeans, String... attrs) {
        for (int i = 0; i < elements.size(); i++) {
            if (!ADArrayUtils.isEmpty(elements.get(i).children())) {
                getContent(elements.get(i).children(), elementBeans, attrs);
            } else {
                if (!TextUtils.isEmpty(elements.get(i).text())) {
                    elementBeans.add(new ElementBean(HtmlMode.Text, elements.get(i).text()));
                } else {
                    Elements img = elements.get(i).getElementsByTag("img");
                    if (!ADArrayUtils.isEmpty(img)) {
                        for (int j = 0; j < img.size(); j++) {
                            ElementBean elementBean = new ElementBean();
                            elementBean.setMode(HtmlMode.Image);
                            for (String str : attrs) {
                                String attr = img.get(j).attr(str);
                                if (!TextUtils.isEmpty(attr)) {
                                    if (str.contains("width")) {
                                        elementBean.setWidth(attr);
                                    } else if (str.contains("height")) {
                                        elementBean.setHeight(attr);
                                    } else {
                                        elementBean.setContent(attr);
                                    }
                                }
                            }
                            if (!TextUtils.isEmpty(elementBean.getContent())){
                                elementBeans.add(elementBean);
                            }
                        }
                    }
                    Elements video = elements.get(i).getElementsByTag("video");
                    if (!ADArrayUtils.isEmpty(video)) {
                        for (int j = 0; j < video.size(); j++) {
                            for (String str : attrs) {
                                String attr = video.get(j).attr(str);
                                if (!TextUtils.isEmpty(attr)) {
                                    elementBeans.add(new ElementBean(HtmlMode.Video, attr));
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * todo 根据标签查找内容
     *
     * @param html     html文本
     * @param cssQuery css标签
     * @return Elements
     */
    public Elements findCssByElements(String html, String... cssQuery) {
        Document parse = Jsoup.parse(html);
        Element body = parse.body();
        Elements elements = null;
        for (String css : cssQuery) {
            if (null != elements) {
                elements = elements.select(css);
            } else {
                elements = body.select(css);
            }
        }
        return elements;
    }

    /**
     * todo 根据标签查找内容
     *
     * @param document html文档内容
     * @param cssQuery css标签
     * @return Elements
     */
    public Elements findCssByElements(Document document, String... cssQuery) {
        Element body = document.body();
        Elements elements = null;
        for (String css : cssQuery) {
            if (null != elements) {
                elements = elements.select(css);
            } else {
                elements = body.select(css);
            }
        }
        return elements;
    }

    public Builder from(Context context) {
        return new Builder(context);
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
        onADHtmlUtilsListener.onReady();
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

        void onReady();

        void onSucceed(Spanned spanned);
    }

    public static class ElementBean {

        private HtmlMode mode;
        private String content;
        private String width;
        private String height;
        private String originImageUrl;

        public ElementBean() {

        }

        public ElementBean(HtmlMode mode, String content) {
            this.mode = mode;
            this.content = content;
        }

        public ElementBean(HtmlMode mode, String content, String width, String height) {
            this.mode = mode;
            this.content = content;
            this.width = width;
            this.height = height;
        }

        public ElementBean(HtmlMode mode, String content, String width, String height, String originImageUrl) {
            this.mode = mode;
            this.content = content;
            this.width = width;
            this.height = height;
            this.originImageUrl = originImageUrl;
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


        public void setWidth(String width) {
            this.width = width;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public String getHeight() {
            return height;
        }

        public String getOriginImageUrl() {
            return originImageUrl;
        }

        public void setOriginImageUrl(String originImageUrl) {
            this.originImageUrl = originImageUrl;
        }
    }

    public enum HtmlMode {
        Text,
        Image,
        Video
    }

    public static class Builder {

        private Context context;
        private String url;
        private String html;
        private int millis = 30 * 1000;
        private OnConnectListener listener;
        private String[] cssQuery;
        private String requestBody;
        private Map<String, String> headers = new HashMap<>();
        private String[] attrs = new String[]{};

        public Builder(Context context) {
            this.context = context;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder timeout(int millis) {
            this.millis = millis;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder requestBody(String body) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder listener(OnConnectListener listener) {
            this.listener = listener;
            return this;
        }

        public void get() {
            execute("get");

        }

        public void post() {
            execute("post");
        }

        private void execute(String methodType) {
            if (null != listener) {
                listener.onReady();
            }
            new Thread(() -> {
                try {
                    Document document = "get".equals(methodType) ? connect().get() : connect().post();
                    if (null != listener) {
                        if (null != cssQuery) {
                            listener.onDocument(document, getInstance().findCssByElements(document, cssQuery), getElementJson(document));
                        } else {
                            listener.onDocument(document, document.getAllElements(), "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (null != listener) {
                        listener.onError(e);
                    }
                }
            }).start();
        }

        /**
         * 获取格式化element节点Json数据
         *
         * @param document Document引用
         * @return String
         */
        private String getElementJson(Document document) {
            Elements elements = getInstance().findCssByElements(document, cssQuery);
            List<ElementBean> elementBeanList = new ArrayList<>();
            for (int i = 0; i < Objects.requireNonNull(elements).size(); i++) {
                Elements children = elements.get(i).children();
                getInstance().getContent(children, elementBeanList, attrs);
            }
            return ADGsonUtils.toJson(elementBeanList);
        }

        /**
         * 网络连接配置
         *
         * @return Connection
         */
        private Connection connect() {
            Connection connect = Jsoup.connect(url);
            if (!ADArrayUtils.isEmpty(headers.entrySet())) {
                Set<Map.Entry<String, String>> entries = headers.entrySet();
                for (Map.Entry<String, String> next : entries) {
                    connect.header(next.getKey(), next.getValue());
                }
            }
            connect.timeout(millis);
            connect.requestBody(requestBody);
            return connect;
        }

        /**
         * todo 查询css标签下元素
         *
         * @param cssQuery 标签
         * @return Builder
         */
        public Builder cssQuery(String... cssQuery) {
            this.cssQuery = cssQuery;
            return this;
        }

        /**
         * todo html标签属性名称
         *
         * @param attrs 标签属性数组
         *              注：如果attrsKey对应标签无属性，则value值=""，默认返回标签text文本
         * @return Builder
         */
        public Builder attrs(String... attrs) {
            this.attrs = attrs;
            return this;
        }

        /**
         * 设置Html网页
         *
         * @param html 网页信息
         * @return Builder
         */
        public Builder html(String html) {
            this.html = html;
            return this;
        }

        /**
         * 执行html网页分析
         *
         * @return String
         */
        public String parser() {
            if (TextUtils.isEmpty(html)) {
                Log.i("Mac_Liu", "Could not determine a form action HTML");
                return null;
            }
            Document parse = Jsoup.parse(html);
            return getElementJson(parse);
        }

        public interface OnConnectListener {

            /**
             * 准备
             */
            void onReady();

            /**
             * document回调
             *
             * @param document       document网页信息
             * @param selectElements 选中标签节点
             * @param json           json数据
             */
            void onDocument(Document document, Elements selectElements, String json);

            /**
             * 异常回调
             *
             * @param e 异常信息
             */
            void onError(Exception e);
        }
    }
}
