package com.letianpai.robot.components.charging

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Bundle
import android.os.PowerManager
import kotlin.math.max
import kotlin.math.min

/**
 * @author liujunbin
 */
class BatteryReceiver() : BroadcastReceiver() {
    //    public static final String ACTION_BATTERY_UPDATE = "com.renhejia.robot.action.battery_update";
    private var currentPercent: Int = 0
    private var chargingMode: Boolean = false
    private var mContext: Context? = null

    override fun onReceive(context: Context, intent: Intent) {
        this.mContext = context
        when (intent.action) {
            Intent.ACTION_BATTERY_CHANGED -> handleBatteryChanged(context, intent)
            Intent.ACTION_POWER_DISCONNECTED -> responseDisconnect()
            Intent.ACTION_POWER_CONNECTED -> responseConnect()
        }
    }

    private fun responseDisconnect() {
        ChargingUpdateCallback.instance.setChargingStatus(false, currentPercent)
    }

    private fun responseConnect() {
//        GeeUILogUtils.logi("letianpai","responseConnect_responseCharging"+ currentPercent);
        ChargingUpdateCallback.instance.setChargingStatus(true, currentPercent)
    }

    private fun handleBatteryChanged(context: Context, intent: Intent) {
        val bundle: Bundle? = intent.getExtras()

        val chargePlug: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        //        GeeUILogUtils.logi("letianpai_1213","chargePlug: "+ chargePlug);
        if (null == bundle) {
            return
        }

        // 获取当前电量
        val current: Int = bundle.getInt(BatteryManager.EXTRA_LEVEL, 0)
        // 获取总电量
        val total: Int = bundle.getInt(BatteryManager.EXTRA_SCALE, 100)

        if (total == 0) {
            return
        }
        var percent: Int = current * 100 / total
        percent = max(0.0, min(percent.toDouble(), 100.0)).toInt()
        this.currentPercent = percent

        val status: Int =
            intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
        if (percent <= 15) {
            showBatteryLowDialog(context)
        }

        when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> responseCharging(context, percent)
            BatteryManager.BATTERY_STATUS_FULL -> if (isCharging(chargePlug)) {
                responseCharging(context, percent)
            } else {
                responseDisCharging(context, percent)
            }

            BatteryManager.BATTERY_STATUS_DISCHARGING -> {
                responseDisCharging(context, percent)
                responseDisCharging(context, percent, chargePlug)
            }

            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> {
                responseDisCharging(context, percent)
                responseDisCharging(context, percent, chargePlug)
            }

            BatteryManager.BATTERY_STATUS_UNKNOWN -> {
                responseDisCharging(context, percent)
                responseDisCharging(context, percent, chargePlug)
            }
        }//        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_BATTERY_UPDATE));
    }

    /**
     * Response discharging
     * @param context
     * @param percent
     * @param chargePlug
     */
    private fun responseDisCharging(context: Context, percent: Int, chargePlug: Int) {
        //TODO Need to add repeat pop-up protection
        //TODO to be check and removed

        chargingMode = false
        ChargingUpdateCallback.instance.setChargingStatus(false, percent, chargePlug)
        hideChargingView()
    }

    /**
     * Response discharging
     * @param context
     * @param percent
     */
    private fun responseDisCharging(context: Context, percent: Int) {
        //TODO Need to add repeat pop-up protection
        //TODO to be check and removed

        chargingMode = false
        ChargingUpdateCallback.instance.setChargingStatus(chargingMode, percent)
        hideChargingView()
    }

    /**
     * 响应充电
     *
     * @param context
     * @param percent
     */
    private fun responseCharging(context: Context, percent: Int) {
        sendShowChargingDialog(context, percent)
        chargingMode = true
        ChargingUpdateCallback.instance.setChargingStatus(chargingMode, percent)
        showChargingView()
        killApps()
    }

    /**
     * Show charging pop-up
     * @param context
     * @param percent
     */
    private fun sendShowChargingDialog(context: Context, percent: Int) {
        //TODO Show the power-up pop-up window, here the channel interface,
        // TODO do not do UI display, only do the message passing, display layer to do this logic
    }

    /**
     * Show Low Power Popup
     *
     * @param mContext
     */
    private fun showBatteryLowDialog(mContext: Context) {
        // TODO needs to do low point alerts
      // TODO Optimist needs to do segmented power alert logic (20%, 10%, 5%)
    }

    /**
     *
     * @param context
     * @return
     */
    private fun getScreenStatus(context: Context): Boolean {
        val powerManager: PowerManager =
            context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.isScreenOn
    }

    private fun isCharging(chargePlug: Int): Boolean {
        return (chargePlug == BatteryManager.BATTERY_PLUGGED_AC
                    ) || (chargePlug == BatteryManager.BATTERY_PLUGGED_USB
                    ) || (chargePlug == BatteryManager.BATTERY_PLUGGED_WIRELESS)
    }

    private fun showChargingView() {
        //TODO display is popping page
        //TODO here channel interface, do not do UI display, only do message passing, display layer do this logic
    }

    private fun hideChargingView() {
        //TODO Here the channel interface, do not do UI display, only do the message passing, display layer to do this logic
    }

    /**
     * 倒计时关机接口
     */
    fun showCountdownDialog() {
        //TODO Here the channel interface, do not do UI display, only do the message passing, display layer to do this logic
    }

    /**
     * Services and Apps to close when killing charging
     */
    private fun killApps() {
        //TODO Send a message to the framework layer to kill all power-consuming operations when charging.
    }
}
