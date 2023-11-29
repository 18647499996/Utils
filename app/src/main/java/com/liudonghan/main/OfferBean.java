package com.liudonghan.main;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/29/23
 */
public class OfferBean {

    /**
     * uk : n231124145618627
     * url : /nsa/n231124145618627.html
     * topic : 2006年，孙俪资助4年的“白眼狼”向海清，已经走上另一条不归路
     * type_py : yule
     * type_zh : 娱乐
     * type_en : ent
     * source : 峰哥正读
     * show_date : 2023-11-24 14:56
     * img169List : ["//minipc.eastday.com/ecms/thumbimg/20231124/918x815_656049170f4e0_mwpm_03201609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/1280x892_6560491705b24_mwpm_03201609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/960x686_656049170a8d0_mwpm_03201609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/1021x367_656049170797b_mwpm_03201609.jpeg"]
     * img169550List : ["//minipc.eastday.com/ecms/thumbimg/20231124/918x815_656049170f4e0_mwpm_05501609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/1280x892_6560491705b24_mwpm_05501609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/960x686_656049170a8d0_mwpm_05501609.jpeg","//minipc.eastday.com/ecms/thumbimg/20231124/1021x367_656049170797b_mwpm_05501609.jpeg"]
     * desc : 你敢想象，农夫与蛇的故事，会上演在现实生活中。
     * page_count : 11
     * leader_news : false
     * original_source :
     * isHot : 1
     * is_hot : 1
     */

    private String uk;
    private String url;
    private String topic;
    private String type_py;
    private String type_zh;
    private String type_en;
    private String source;
    private String show_date;
    private String desc;
    private int page_count;
    private boolean leader_news;
    private String original_source;
    private int isHot;
    private int is_hot;
    private List<String> img169List;
    private List<String> img169550List;

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType_py() {
        return type_py;
    }

    public void setType_py(String type_py) {
        this.type_py = type_py;
    }

    public String getType_zh() {
        return type_zh;
    }

    public void setType_zh(String type_zh) {
        this.type_zh = type_zh;
    }

    public String getType_en() {
        return type_en;
    }

    public void setType_en(String type_en) {
        this.type_en = type_en;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getShow_date() {
        return show_date;
    }

    public void setShow_date(String show_date) {
        this.show_date = show_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public boolean isLeader_news() {
        return leader_news;
    }

    public void setLeader_news(boolean leader_news) {
        this.leader_news = leader_news;
    }

    public String getOriginal_source() {
        return original_source;
    }

    public void setOriginal_source(String original_source) {
        this.original_source = original_source;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public List<String> getImg169List() {
        return img169List;
    }

    public void setImg169List(List<String> img169List) {
        this.img169List = img169List;
    }

    public List<String> getImg169550List() {
        return img169550List;
    }

    public void setImg169550List(List<String> img169550List) {
        this.img169550List = img169550List;
    }

    @Override
    public String toString() {
        return "OfferBean{" +
                "uk='" + uk + '\'' +
                ", url='" + url + '\'' +
                ", topic='" + topic + '\'' +
                ", type_py='" + type_py + '\'' +
                ", type_zh='" + type_zh + '\'' +
                ", type_en='" + type_en + '\'' +
                ", source='" + source + '\'' +
                ", show_date='" + show_date + '\'' +
                ", desc='" + desc + '\'' +
                ", page_count=" + page_count +
                ", leader_news=" + leader_news +
                ", original_source='" + original_source + '\'' +
                ", isHot=" + isHot +
                ", is_hot=" + is_hot +
                ", img169List=" + img169List +
                ", img169550List=" + img169550List +
                '}';
    }
}
