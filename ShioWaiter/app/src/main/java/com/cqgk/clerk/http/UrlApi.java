package com.cqgk.clerk.http;


import com.cqgk.clerk.BuildConfig;

/**
 * 接口url地址定义
 *
 * @author duke
 */
public class UrlApi {

    /**
     * E扫盈: mall.mobo2o.com/api/v1/
     * 云指: m2.nnwhy.com/api/v1/
     * http://test.mobo2o.com
     */
    private static String Service = "test";
    /**  */
    private static String Domain = "/api/v1/";

    public static String getService() {
        return Service;
    }

    public static void setService(String service, String port) {
        Service = service + ":" + port;
    }

    public static void setService(String service) {
        Service = service;
    }

    public static String getDomain() {
        return Domain;
    }

    public static void setDomain(String domain) {
        Domain = domain;
    }

    public static String getCompleteUrl(String filePath) {
        return String.format("https://%s/%s", Service, filePath);
    }

    public static String getUrl(String action) {
        return String.format("https://%s%s%s", Service, Domain, action);
    }

    public static String getApiUrl(String action) {
        return String.format("%s%s%s", Service, Domain, action);
    }



    /**
     * 无域url自动加上前域
     *
     * @param url
     * @return
     */
    public static String getRealUrl(String url) {
        if (url == null)
            return "";

        if (url.startsWith("http://")) {
            return url;
        } else {
            return String.format(Service + "%s", url);
        }
    }

    /**
     * 获取url中page的id参数
     *
     * @param url
     * @return
     */
    public static String getUrlLastID(String url) {
        try {
            return url.substring(url.lastIndexOf("/") + 1);
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        return "";
    }


    /**
     * token请求
     */
    public static String url_gettoken = "apps/auth";




}
