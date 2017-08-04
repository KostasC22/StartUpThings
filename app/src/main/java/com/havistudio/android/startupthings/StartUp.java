package com.havistudio.android.startupthings;

import io.realm.RealmObject;

/**
 * Created by kostas on 04/08/2017.
 */

public class StartUp extends RealmObject {

    private long id;
    private String type;
    private String packageName;
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
