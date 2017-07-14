package com.heaven.test;

import android.app.Activity;
import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MyApplication extends Application{
    private String userSessionId;

    private String systemUserId;

    private List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;

    public MyApplication() { }
    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if(null == instance){
            instance = new MyApplication();
        }
        return instance;
    }
    //添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
//        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                if (hostname.equalsIgnoreCase("www.z2systems.com") ||
//                        hostname.equalsIgnoreCase("api.neoncrm.com")) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        CrashHandler mCustomCrashHandler = CrashHandler.getInstance();
//        mCustomCrashHandler.setCustomCrashHanler(getApplicationContext());
        CreateDB();
    }

    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    public void CreateDB(){
        daoConfig = new DbManager.DaoConfig()
                .setDbName("nenocrm")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        if(oldVersion < newVersion){

                        }
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getSystemUserId() {
        return systemUserId;
    }
    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

}
