package com.liudonghan.main.bean;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/30/23
 */
public class ImageBean {

    /**
     * url : //p9.itc.cn/images01/20231129/4b9916a07f194822b94a1f29d4d0b582.jpeg
     * width : 1024
     * height : 1485
     */

    private String url;
    private String width;
    private String height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "url='" + url + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
