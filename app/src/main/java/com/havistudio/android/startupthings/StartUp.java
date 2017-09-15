package com.havistudio.android.startupthings;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kostas on 04/08/2017.
 */

public class StartUp extends RealmObject {

    @PrimaryKey
    private String id;
    private String data;
    private String type;
    private String category;
    private String packageName;
    private String fileName;
    private Long delay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "StartUp{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", packageName='" + packageName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", delay=" + delay +
                '}';
    }
}
