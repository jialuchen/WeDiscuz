package com.example.zootopia.application;

import android.app.Application;
import android.util.Log;

import com.example.zootopia.handler.MessageHandler;
import com.example.zootopia.ui.BaseEngineEventHandlerActivity;
import com.xsj.crasheye.Crasheye;

import io.agora.rtc.RtcEngine;

/**
 * Created by apple on 9/9/15.
 * Modified by Zootopia Lyu on 4/29/16
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class AgoraApplication extends Application {

    private RtcEngine rtcEngine;
    private MessageHandler messageHandler;

    @Override
    public void onCreate() {

        super.onCreate();

        Crasheye.initWithNativeHandle(this, "06798b00");


        Log.d("Crasheye","0");

        messageHandler = new MessageHandler();
    }

    /**
     * Vendor key for Wediscuz:  3f53e87f7b004d77852b1da049dba64
     *
     * @param vendorKey
     */
    public void setRtcEngine(String vendorKey) {

        if (rtcEngine == null) {
            rtcEngine = RtcEngine.create(getApplicationContext(), vendorKey, messageHandler);
        }
    }

    /**
     * getRtcEngine - get RtcEngine object
     * @return
     */
    public RtcEngine getRtcEngine() {

        return rtcEngine;
    }

    /**
     * setEngineEventHandlerActivity - set activity for EngineEventHandler object
     * specify which activity the MessageHandler object control
     * @param engineEventHandlerActivity
     */
    public void setEngineEventHandlerActivity(BaseEngineEventHandlerActivity engineEventHandlerActivity) {
        messageHandler.setActivity(engineEventHandlerActivity);
    }
}
