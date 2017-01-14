package com.example.zootopia.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zootopia.application.AgoraApplication;
import com.example.zootopia.application.AgoraConfiguration;
import com.example.zootopia.util.NetworkConnectivityUtils;
import com.example.zootopia.wediscuz.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;

/**
 * Created by Linpeng Lyu on 4/29/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class ChannelActivity extends BaseEngineEventHandlerActivity {
    // initialize constant variable
    public final static int CALLING_TYPE_VIDEO = 0x100;
    public final static int CALLING_TYPE_VOICE = 0x101;

    public final static String EXTRA_CALLING_TYPE = "EXTRA_CALLING_TYPE";
    public final static String EXTRA_CHANNEL_ID = "EXTRA_CHANNEL_ID";
    public final static String EXTRA_ROOM_NAME = "EXTRA_ROOM_NAME";

    // initialize view and some variable
    private int mCallingType;
    private SurfaceView mLocalView;
    private String vendorKey = "";
    private String channelId = "";
    private String channelName = "";
    private TextView mDuration;
    private TextView mByteCounts;
    private LinearLayout mRemoteUserContainer;
    private AlertDialog alertDialog;
    private Button hangupButton;
    private Button videoButton;
    private Button voiceButton;
    private Button muteButton;
    private Button unmuteButton;
    private Button switchButton;
    private int time = 0;

    // initialize status variables
    private int mLastRxBytes = 0;
    private int mLastTxBytes = 0;
    private int mLastDuration = 0;

    private int mRemoteUserViewWidth = 0;

    RtcEngine rtcEngine;


    @Override
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.ui_groupchat_activity);


        // keep screen on - turned on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mRemoteUserViewWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
        mCallingType = getIntent().getIntExtra(EXTRA_CALLING_TYPE, CALLING_TYPE_VOICE /*default is voice call*/);

        initialize();
        setupRtcEngine();
        initViews();
        setupTime();

        if (mCallingType == CALLING_TYPE_VIDEO) {
            // video call
            View simulateClick = new View(getApplicationContext());
            simulateClick.setId(R.id.groupchat_button_video);
            this.onUserInteraction(simulateClick);
            voiceButton.setVisibility(View.VISIBLE);
            videoButton.setVisibility(View.GONE);
        } else if (mCallingType == CALLING_TYPE_VOICE) {
            // voice call
            View simulateClick = new View(getApplicationContext());
            simulateClick.setId(R.id.groupchat_button_voice);
            this.onUserInteraction(simulateClick);
            voiceButton.setVisibility(View.GONE);
            videoButton.setVisibility(View.VISIBLE);
        }

        // check network
        if (!NetworkConnectivityUtils.isConnectedToNetwork(getApplicationContext())) {
            onError(104);
        }
    }

    /**
     * initializeKey - initialize vendor key
     */
    void initialize() {
        vendorKey = AgoraConfiguration.vendorKey;
        channelId = getIntent().getStringExtra(EXTRA_CHANNEL_ID);
        channelName = getIntent().getStringExtra(EXTRA_ROOM_NAME);
    }

    void setupChannel() {
        this.rtcEngine.joinChannel(
                this.vendorKey,
                this.channelId,
                "" /*optionalInfo*/,
                new Random().nextInt(Math.abs((int) System.currentTimeMillis()))/*optionalUid*/);

        ((TextView) findViewById(R.id.groupchat_roomID)).setText(String.format(getString(R.string.title_channel), channelName));

    }

    void setupRtcEngine() {
        // setup engine
        ((AgoraApplication) getApplication()).setRtcEngine(vendorKey);
        rtcEngine = ((AgoraApplication) getApplication()).getRtcEngine();
//        LogUtil.log.d(getApplicationContext().getExternalFilesDir(null).toString() + "/agorasdk.log");
        rtcEngine.setLogFile(getApplicationContext().getExternalFilesDir(null).toString() + "/agorasdk.log");


        // setup engine event activity
        ((AgoraApplication) getApplication()).setEngineEventHandlerActivity(this);

        rtcEngine.enableVideo();

    }


    void ensureLocalViewIsCreated() {

        if (this.mLocalView == null) {

            // local view has not been added before
            FrameLayout localViewContainer = (FrameLayout) findViewById(R.id.groupchat_local_view);
            SurfaceView localView = rtcEngine.CreateRendererView(getApplicationContext());
            this.mLocalView = localView;
            localViewContainer.addView(localView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

            rtcEngine.enableVideo();
            rtcEngine.setupLocalVideo(new VideoCanvas(this.mLocalView));
        }

    }

    /**
     * initViews - Initialize all views and the listeners
     */
    void initViews() {
        // mute button
        muteButton = (Button) findViewById(R.id.groupchat_button_mute);
        muteButton.setOnClickListener(getViewClickListener());

        // unmute button
        unmuteButton = (Button) findViewById(R.id.groupchat_button_unmute);
        unmuteButton.setVisibility(View.GONE);
        unmuteButton.setOnClickListener(getViewClickListener());

        // camera switcher
        switchButton = (Button) findViewById(R.id.groupchat_button_switch);
        switchButton.setOnClickListener(getViewClickListener());

        // status view
        mDuration = (TextView) findViewById(R.id.groupchat_stat_time);
        mByteCounts = (TextView) findViewById(R.id.groupchat_stat_bytes);

        // remote user views
        mRemoteUserContainer = (LinearLayout) findViewById(R.id.groupchat_remote_views);

        // hangup button
        hangupButton = (Button) findViewById(R.id.groupchat_button_hungup);
        hangupButton.setOnClickListener(getViewClickListener());

        // video button
        videoButton = (Button) findViewById(R.id.groupchat_button_video);
        videoButton.setOnClickListener(getViewClickListener());

        // voice button
        voiceButton = (Button) findViewById(R.id.groupchat_button_voice);
        voiceButton.setOnClickListener(getViewClickListener());

        setRemoteUserViewVisibility(true);
    }

    void setRemoteUserViewVisibility(boolean isVisible) {
        try {
            mRemoteUserContainer.getLayoutParams().height =
                    isVisible ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics())
                            : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setupTime() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        time++;

                        if (time >= 3600) {
                            mDuration.setText(String.format("%d:%02d:%02d", time / 3600, (time % 3600) / 60, (time % 60)));
                        } else {
                            mDuration.setText(String.format("%02d:%02d", (time % 3600) / 60, (time % 60)));
                        }
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1000, 1000);
    }


    /**
     * updateRemoteUserViews - when we switch the chat mode between video and voice, we
     * update the view's visibility. Do not add new one.
     *
     * @param callingType
     */
    void updateRemoteUserViews(int callingType) {

        int visibility = View.GONE;

        if (callingType == CALLING_TYPE_VIDEO) {
            visibility = View.GONE;

        } else if (callingType == CALLING_TYPE_VOICE) {
            visibility = View.VISIBLE;
        }

        for (int i = 0, size = mRemoteUserContainer.getChildCount(); i < size; i++) {

            View singleRemoteView = mRemoteUserContainer.getChildAt(i);
            singleRemoteView.findViewById(R.id.remote_user_voice_container).setVisibility(visibility);

            if (CALLING_TYPE_VIDEO == callingType) {
                // re-setup remote video

                FrameLayout remoteVideoUser = (FrameLayout) singleRemoteView.findViewById(R.id.viewlet_remote_video_user);
                // ensure remote video view setup
                if (remoteVideoUser.getChildCount() > 0) {
                    final SurfaceView remoteView = (SurfaceView) remoteVideoUser.getChildAt(0);
                    if (remoteView != null) {
                        remoteView.setZOrderOnTop(true);
                        remoteView.setZOrderMediaOverlay(true);
                        int savedUid = (Integer) remoteVideoUser.getTag();
                        //log("saved uid: " + savedUid);
                        rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_ADAPTIVE, savedUid));
                    }
                }

            }
        }
    }

    @Override
    public void onUserInteraction(View view) {
        switch (view.getId()) {
            default:
                super.onUserInteraction(view);
                break;
            case R.id.groupchat_button_video: {

                mCallingType = CALLING_TYPE_VIDEO;

                findViewById(R.id.groupchat_local_voice_bg).setVisibility(View.GONE);

                // set button visibility
                voiceButton.setVisibility(View.VISIBLE);
                videoButton.setVisibility(View.GONE);

                // enable video call
                ensureLocalViewIsCreated();

                rtcEngine.enableVideo();
                rtcEngine.muteLocalVideoStream(false);
                rtcEngine.muteLocalAudioStream(false);
                rtcEngine.muteAllRemoteVideoStreams(false);

                // join video call
                if (mRemoteUserContainer.getChildCount() == 0) {
                    this.setupChannel();
                }

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateRemoteUserViews(CALLING_TYPE_VIDEO);
                    }
                }, 500);
            }
            break;
            case R.id.groupchat_button_voice: {

                mCallingType = CALLING_TYPE_VOICE;

                // show background for voice call
                findViewById(R.id.groupchat_local_voice_bg).setVisibility(View.VISIBLE);

                // set button visibility
                voiceButton.setVisibility(View.GONE);
                videoButton.setVisibility(View.VISIBLE);

                ensureLocalViewIsCreated();

                // disable video call when necessary
                rtcEngine.disableVideo();
                rtcEngine.muteLocalVideoStream(true);
                rtcEngine.muteAllRemoteVideoStreams(true);

                // join voice call
                if (mRemoteUserContainer.getChildCount() == 0) {
                    this.setupChannel();
                }

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateRemoteUserViews(CALLING_TYPE_VOICE);
                    }
                }, 500);

            }
            break;
            case R.id.groupchat_button_mute:
                rtcEngine.muteLocalAudioStream(true);
                muteButton.setVisibility(View.GONE);
                unmuteButton.setVisibility(View.VISIBLE);
                break;
            case R.id.groupchat_button_unmute:
                rtcEngine.muteLocalAudioStream(false);
                muteButton.setVisibility(View.VISIBLE);
                unmuteButton.setVisibility(View.GONE);
                break;
            case R.id.groupchat_button_switch:
                rtcEngine.switchCamera();
                break;
            case R.id.groupchat_button_hungup:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rtcEngine.leaveChannel();
                    }
                }).run();

                // keep screen on - turned off
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                rtcEngine.leaveChannel();
            }
        }).run();

        // keep screen on - turned off
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    public void onUpdateSessionStats(final IRtcEngineEventHandler.RtcStats stats) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // bytes
                mByteCounts.setText(((stats.txBytes + stats.rxBytes - mLastTxBytes - mLastRxBytes) / 1024 / (stats.totalDuration - mLastDuration + 1)) + "KB/s");

                // remember data from this call back
                mLastRxBytes = stats.rxBytes;
                mLastTxBytes = stats.txBytes;
                mLastDuration = stats.totalDuration;

            }
        });


    }

    public synchronized void onFirstRemoteVideoDecoded(final int uid, int width, int height, final int elapsed) {

        //log("onFirstRemoteVideoDecoded: uid: " + uid + ", width: " + width + ", height: " + height);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View remoteUserView = mRemoteUserContainer.findViewById(Math.abs(uid));

                // ensure container is added
                if (remoteUserView == null) {

                    LayoutInflater layoutInflater = getLayoutInflater();

                    View singleRemoteUser = layoutInflater.inflate(R.layout.ui_voice_remote_users, null);
                    singleRemoteUser.setId(Math.abs(uid));

                    TextView username = (TextView) singleRemoteUser.findViewById(R.id.remote_user_name);
                    username.setText(String.valueOf(uid));

                    mRemoteUserContainer.addView(singleRemoteUser, new LinearLayout.LayoutParams(mRemoteUserViewWidth, mRemoteUserViewWidth));

                    remoteUserView = singleRemoteUser;
                }


                FrameLayout remoteVideoUser = (FrameLayout) remoteUserView.findViewById(R.id.viewlet_remote_video_user);
                remoteVideoUser.removeAllViews();
                remoteVideoUser.setTag(uid);

                // ensure remote video view setup
                final SurfaceView remoteView = RtcEngine.CreateRendererView(getApplicationContext());
                remoteVideoUser.addView(remoteView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                remoteView.setZOrderOnTop(true);
                remoteView.setZOrderMediaOverlay(true);

                rtcEngine.enableVideo();
                int successCode = rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_ADAPTIVE, uid));

                if (successCode < 0) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rtcEngine.setupRemoteVideo(new VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_ADAPTIVE, uid));
                            remoteView.invalidate();
                        }
                    }, 500);
                }


                if (remoteUserView != null && CALLING_TYPE_VIDEO == mCallingType) {
                    remoteUserView.findViewById(R.id.remote_user_voice_container).setVisibility(View.GONE);
                } else {
                    remoteUserView.findViewById(R.id.remote_user_voice_container).setVisibility(View.VISIBLE);
                }

                // app hints before you join
                TextView appNotification = (TextView) findViewById(R.id.groupchat_app_notification);
                appNotification.setText("");
                setRemoteUserViewVisibility(true);
            }
        });

    }

    public synchronized void onUserJoined(final int uid, int elapsed) {

        //log("onUserJoined: uid: " + uid);

        View existedUser = mRemoteUserContainer.findViewById(Math.abs(uid));
        if (existedUser != null) {
            // user view already added
            return;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                // Handle the case onFirstRemoteVideoDecoded() is called before onUserJoined()
                View singleRemoteUser = mRemoteUserContainer.findViewById(Math.abs(uid));
                if (singleRemoteUser != null) {
                    return;
                }

                LayoutInflater layoutInflater = getLayoutInflater();
                singleRemoteUser = layoutInflater.inflate(R.layout.ui_voice_remote_users, null);
                singleRemoteUser.setId(Math.abs(uid));

                TextView username = (TextView) singleRemoteUser.findViewById(R.id.remote_user_name);
                username.setText(String.valueOf(uid));

                mRemoteUserContainer.addView(singleRemoteUser, new LinearLayout.LayoutParams(mRemoteUserViewWidth, mRemoteUserViewWidth));


                // app hints before you join
                TextView appNotification = (TextView) findViewById(R.id.groupchat_app_notification);
                appNotification.setText("");
                setRemoteUserViewVisibility(true);

            }
        });


    }

    public void onUserOffline(final int uid) {

        //log("onUserOffline: uid: " + uid);

        if (isFinishing()) {
            return;
        }

        if (mRemoteUserContainer == null) {
            return;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View userViewToRemove = mRemoteUserContainer.findViewById(Math.abs(uid));
                mRemoteUserContainer.removeView(userViewToRemove);

                // no joined users any more
                if (mRemoteUserContainer.getChildCount() == 0) {
                    setRemoteUserViewVisibility(false);
                    TextView appNotification = (TextView) findViewById(R.id.groupchat_app_notification);
                    appNotification.setText(R.string.room_prepare);
                }
            }
        });


    }


    @Override
    public void finish() {

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        super.finish();
    }

    @Override
    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats) {
        try {
            super.onLeaveChannel(stats);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUserMuteVideo(final int uid, final boolean muted) {

        //log("onUserMuteVideo uid: " + uid + ", muted: " + muted);

        if (isFinishing()) {
            return;
        }

        if (mRemoteUserContainer == null) {
            return;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                View remoteView = mRemoteUserContainer.findViewById(Math.abs(uid));
                remoteView.findViewById(R.id.remote_user_voice_container).setVisibility(
                        (CALLING_TYPE_VOICE == mCallingType || (CALLING_TYPE_VIDEO == mCallingType && muted))
                                ? View.VISIBLE
                                : View.GONE);
                remoteView.invalidate();
            }
        });

    }

    @Override
    public synchronized void onError(int err) {


        if (isFinishing()) {
            return;
        }


        // incorrect vendor key
        if (101 == err) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (alertDialog != null) {
                        return;
                    }

                    alertDialog = new AlertDialog.Builder(ChannelActivity.this).setCancelable(false)
                            .setMessage(getString(R.string.error_101))
                            .setPositiveButton(getString(R.string.error_confirm), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // Go to login
                                    Intent toLogin = new Intent(ChannelActivity.this, LoginActivity.class);
                                    toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(toLogin);

                                    rtcEngine.leaveChannel();

                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialogInterface) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .create();

                    alertDialog.show();
                }
            });


        }

        // no network connection
        if (104 == err) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView appNotification = (TextView) findViewById(R.id.groupchat_app_notification);
                    appNotification.setText(R.string.network_error);
                }
            });
        }


    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
