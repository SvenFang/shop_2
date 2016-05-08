package com.cqgk.clerk.bean.dbbean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;

/**
 * 数据库记录请求回调字符串
 * Created by duke on 16/4/13.
 */
@Table(name = "responrc")
public class ResponBean {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "url")
    public String url;

    @Column(name = "responstr")
    public String responstr;


    @Column(name = "time")
    private Date time;

    @Column(name = "date")
    private java.sql.Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponstr() {
        return responstr;
    }

    public void setResponstr(String responstr) {
        this.responstr = responstr;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "ResponBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", responstr='" + responstr + '\'' +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
