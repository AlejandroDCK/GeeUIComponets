package com.letianpai.robot.components.parser.appstore;

public class AppStoreInfos {

    private String packageName;
    private String displayName;

    public AppStoreInfos(String packageName, String appName) {
        this.packageName = packageName;
        this.displayName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "{" +
                "packageName:'" + packageName + '\'' +
                ", displayName:'" + displayName + '\'' +
                '}';
    }
}
