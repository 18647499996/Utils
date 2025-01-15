package com.liudonghan.main.bean;

public class Seism {
    private String region;
    private String post_time;
    private String lat;
    private String lon;
    private String magnitude;
    private String depth;
    private String url;

    public Seism(String region, String post_time, String lat, String lon, String magnitude, String depth, String url) {
        this.region = region;
        this.post_time = post_time;
        this.lat = lat;
        this.lon = lon;
        this.magnitude = magnitude;
        this.depth = depth;
        this.url = url;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Seism{" +
                "region='" + region + '\'' +
                ", post_time='" + post_time + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", magnitude='" + magnitude + '\'' +
                ", depth='" + depth + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
