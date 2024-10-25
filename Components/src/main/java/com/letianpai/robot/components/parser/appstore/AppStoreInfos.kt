package com.letianpai.robot.components.parser.appstore

class AppStoreInfos(var packageName: String, var displayName: String) {
    override fun toString(): String {
        return "{" +
                "packageName:'" + packageName + '\'' +
                ", displayName:'" + displayName + '\'' +
                '}'
    }
}
