package com.bgp.cnpc.fourreport;

import android.app.Application;
import android.os.Handler;

import com.bgp.cnpc.fourreport.common.exception.CrashHandler;


/**
 * Created by liuheng on 2017/4/17.
 * 自定义Application
 */
public class App extends Application {

    private static App mContext;

    private static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mMainThreadHandler = new Handler();

        /**异常捕获 日志记录 开启 begain*/
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        /**异常捕获 日志记录 开启  end*/
    }

    public static App getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
}
