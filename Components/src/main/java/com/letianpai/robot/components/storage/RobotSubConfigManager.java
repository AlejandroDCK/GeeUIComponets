package com.letianpai.robot.components.storage;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * 机器人 偏好设置管理器
 * @author liujunbin
 */
public class RobotSubConfigManager implements RobotSubConfigConst {

    private static RobotSubConfigManager mRobotConfigManager;
    private RobotSubSharedPreference mRobotSharedPreference;
    private Context mContext;
    private Gson gson;


    private RobotSubConfigManager(Context context) {
        this.mContext = context;
        gson = new Gson();
        this.mRobotSharedPreference = new RobotSubSharedPreference(context,
                RobotSubSharedPreference.SHARE_PREFERENCE_NAME, RobotSubSharedPreference.ACTION_INTENT_CONFIG_CHANGE);
    }


    private void initKidSmartConfigState() {

    }

    public static RobotSubConfigManager getInstance(Context context) {
        if (mRobotConfigManager == null) {
            mRobotConfigManager = new RobotSubConfigManager(context);
            mRobotConfigManager.initKidSmartConfigState();
            mRobotConfigManager.commit();
        }
        return mRobotConfigManager;

    }

    public boolean commit() {
        return mRobotSharedPreference.commit();
    }

    public long getUpdateTime() {
        return mRobotSharedPreference.getLong(KEY_UPDATE_ROBOT_TIME,0l);
    }

    public void setUpdateTime(long time){
        mRobotSharedPreference.putLong(KEY_UPDATE_ROBOT_TIME,time);
    }

    public long getUploadFrequency() {
        return mRobotSharedPreference.getInt(KEY_UPLOAD_FREQUENCY,1);
    }

    public void setUploadFrequency(int frequency){
        mRobotSharedPreference.putInt(KEY_UPLOAD_FREQUENCY,frequency);
    }

    public long getUploadFrequencyInternalTime() {
        return mRobotSharedPreference.getLong(KEY_UPLOAD_FREQUENCY_INTERNAL_TIME,0l);
    }

    public void setUploadFrequencyInternalTime(long time){
        mRobotSharedPreference.putLong(KEY_UPLOAD_FREQUENCY_INTERNAL_TIME,time);
    }

    public long getUploadDataTime() {
        return mRobotSharedPreference.getLong(KEY_UPLOAD_DATA_TIME,0l);
    }

    public void setUploadDataTime(long time){
        mRobotSharedPreference.putLong(KEY_UPLOAD_DATA_TIME,time);
    }

    public String getAppList() {
        return mRobotSharedPreference.getString(KEY_SAVE_APP_LIST,VALUE_DEFAULT_APP_LIST);
    }

    public void setAppList(String appList){
        mRobotSharedPreference.putString(KEY_SAVE_APP_LIST,appList);
    }

    public boolean getSpeechCommandSwitch() {
        return true;
//        return mRobotSharedPreference.getBoolean(KEY_SPEECH_COMMAND_SWITCH,true);
    }

    public void setSpeechCommandSwitch(boolean speechCommandStatus){
        mRobotSharedPreference.putBoolean(KEY_SPEECH_COMMAND_SWITCH,speechCommandStatus);
    }

    public void setOpenMainViewTime(long currentTimeMillis) {
        mRobotSharedPreference.putLong(KEY_SAVE_OPEN_TIME,currentTimeMillis);
    }

    public long getOpenMainViewTime() {
        return mRobotSharedPreference.getLong(KEY_SAVE_OPEN_TIME,0);
    }

    public boolean isNeedRegisterWifi() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - getOpenMainViewTime())> 10 * 1000){
            return true;
        }else{
            return false;
        }
    }

    public void saveUserPackageList(List<String> list) {
        String json = gson.toJson(list);
        mRobotSharedPreference.putString(KEY_SAVE_PACKAGE_LIST, json);
    }

    public void addUserPackage(String packageName) {
        List<String> packageList = getUserPackageList();
        if (packageList == null){
            packageList = new ArrayList<>();
        }
        if (!packageList.contains(packageName)){
            packageList.add(packageName);
            saveUserPackageList(packageList);
        }

    }

    public void removeUserPackage(String packageName) {
        List<String> packageList = getUserPackageList();
        if (packageList == null){
            packageList = new ArrayList<>();
        }
        packageList.remove(packageName);
        saveUserPackageList(packageList);
    }

    public List<String> getUserPackageList() {
        String json = mRobotSharedPreference.getString(KEY_SAVE_PACKAGE_LIST, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public int getUserPackageListSize() {
        List<String> userList = getUserPackageList();
        if (userList == null){
            return 0;
        }else{
            return userList.size();
        }
    }

    public void resetUserPackageList() {
        HashSet<String> userAppList = new HashSet<>();
        List<String> getUserPackageList = getUserPackageList();
        if (getUserPackageList != null && getUserPackageList.size()> 0){
            for (int i = 0; i< getUserPackageList.size(); i++){
                userAppList.add(getUserPackageList.get(i));
            }
            List<String> appList = new ArrayList<>();

            for (String value: userAppList){
                appList.add(value);
            }
            saveUserPackageList(appList);
            commit();
        }

    }


}
