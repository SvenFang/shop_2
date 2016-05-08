package com.cqgk.clerk.helper;

/**
 * 创建人:Sven Fang
 * 创建时间:2012-5-7
 */

import android.content.Context;

import com.cqgk.clerk.bean.dbbean.ResponBean;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Sven SQLite工具类
 */
public class DBHelper implements DbManager.DbUpgradeListener {

    private final static String DATABASE_NAME = "shopwaiter_db";
    /**
     * 数据库版本
     */
    private final static int DATABASE_VERSION = 1;

    private static DBHelper dbHelper;

    private DbManager db;


    private DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(DATABASE_NAME)
            .setDbVersion(DATABASE_VERSION)
            .setDbUpgradeListener(this);

    /**
     * 获取该类的操作实例
     **/
    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }


        return dbHelper;
    }// end of getInstance method

    private DBHelper(Context context) {
        try {
            db = x.getDb(daoConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(DbManager dbManager, int i, int i1) {

    }




    /**
     * @param bean
     * @return
     */
    public boolean insertRespond(ResponBean bean) {
        try {
            bean.setTime(new Date());
            bean.setDate(new java.sql.Date(new Date().getTime()));
            db.save(bean);
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param bean
     * @return
     */
    public boolean updateRespond(ResponBean bean) {
        try {
            db.update(ResponBean.class, WhereBuilder.b("url", "=", bean.getUrl()),
                    new KeyValue("responstr", bean.getResponstr()),
                    new KeyValue("time", bean.getTime()),
                    new KeyValue("date", bean.getDate()));
        } catch (DbException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResponBean getResond(String url) {
        try {
            List<ResponBean> list = db.selector(ResponBean.class)
                    .where("url", "=", url)
                    .limit(1).findAll();
            return list==null || list.size()==0 ? null : list.get(0);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanRespond() {
        try {
            List<ResponBean> lists = db.selector(ResponBean.class).findAll();
            db.delete(lists);
        } catch (DbException ex) {
            ex.printStackTrace();
        }
    }


}