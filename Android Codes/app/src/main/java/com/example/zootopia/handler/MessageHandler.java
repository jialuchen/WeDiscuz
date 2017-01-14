package com.example.zootopia.handler;

import com.example.zootopia.ui.BaseEngineEventHandlerActivity;

import io.agora.rtc.IRtcEngineEventHandler;

/**
 * Created by apple on 9/10/15.
 * Modified by Zootopia on 4/29/15
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class MessageHandler extends IRtcEngineEventHandler {

    private BaseEngineEventHandlerActivity mHandlerActivity;

    // show the screen of other user in the chatroom
    @Override
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {

        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onFirstRemoteVideoDecoded(uid, width, height, elapsed);
        }
    }

    // user enter the room
    @Override
    public void onUserJoined(int uid, int elapsed){

        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onUserJoined(uid, elapsed);
        }
    }

    // user exit the room
    @Override
    public void onUserOffline(int uid, int reason) {

        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onUserOffline(uid);
        }
    }

    // listen on whether other user close the video chat
    @Override
    public void onUserMuteVideo(int uid,boolean muted){

        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onUserMuteVideo(uid, muted);
        }
    }

    // update chatting data
    @Override
    public void onRtcStats(RtcStats stats){

        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onUpdateSessionStats(stats);
        }
    }

    // listen on user's exiting
    @Override
    public void onLeaveChannel(RtcStats stats) {
        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onLeaveChannel(stats);
        }
    }


    @Override
    public void onError(int err) {
        BaseEngineEventHandlerActivity activity = getActivity();

        if (activity != null) {
            activity.onError(err);
        }
    }

    public void setActivity(BaseEngineEventHandlerActivity activity) {

        this.mHandlerActivity = activity;
    }

    public BaseEngineEventHandlerActivity getActivity(){

        return mHandlerActivity;
    }
}
