package com.letianpai.robot.components.broadcast

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.letianpai.robot.components.charging.BatteryReceiver

/**
 * 广播管理器
 *
 * @author liujunbin
 */
class BroadcastReceiverManager(var mContext: Context) {
    init {
        init()
    }

    private fun init() {
        // TODO add the initialisation of the broadcasts to be listened to.
        // TODO This is the unified entry point for the state to be listened to, the only listening position,
        //  TODO the subsequent need for state, unified here to listen to the state after distribution
        setBatteryListener()
    }

    //电池监听
    private fun setBatteryListener() {
        val batteryReceiver: BatteryReceiver = BatteryReceiver()
        val intentFilter: IntentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        mContext.registerReceiver(batteryReceiver, intentFilter)
    } //    private void setNetWorkChangeListener() {
    //        NetWorkChangeReceiver netChangeReceiver = new NetWorkChangeReceiver();
    //        IntentFilter intentFilter = new IntentFilter();
    //        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
    //        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
    ////        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
    //        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
    //        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    //        mContext.registerReceiver(netChangeReceiver, intentFilter);
    //    }
    //
    //    private void setWifiChangeListener() {
    //        WifiReceiver wifiReceiver = new WifiReceiver();
    //        IntentFilter intentFilter = new IntentFilter();
    //        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
    //        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
    //        mContext.registerReceiver(wifiReceiver, intentFilter);
    //    }
    //
    //    private void setTimeListener() {
    //        TimerReceiver mTimeReceiver = new TimerReceiver();
    //        IntentFilter timeFilter = new IntentFilter();
    //        timeFilter.addAction(Intent.ACTION_TIME_TICK);
    //        mContext.registerReceiver(mTimeReceiver, timeFilter);
    //    }


    companion object {
        private var instance: BroadcastReceiverManager? = null

        fun getInstance(context: Context): BroadcastReceiverManager? {
            synchronized(BroadcastReceiverManager::class.java, {
                if (instance == null) {
                    instance = BroadcastReceiverManager(context.getApplicationContext())
                }
                return instance
            })
        }
    }
}
