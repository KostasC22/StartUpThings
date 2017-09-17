package com.havistudio.android.startupthings;

/**
 * Created by kostas on 12/08/2017.
 */

public class MyApp {

    private String appName;
    private String packageName;

    public MyApp() {
        appName = "";
        packageName = "";
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return packageName;
    }
}
