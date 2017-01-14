package com.example.zootopia.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Provide basic func for all video-and-voice related activities used in App
 *
 * Created by apple on 9/12/15.
 * Modified by Zootopia on 4/29/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class BaseActivity extends FragmentActivity{


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }


    // Global view click listener
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onUserInteraction(view);
        }
    };


    public View.OnClickListener getViewClickListener(){
        return onClickListener;
    }

    /**
     * Central point of handling all view click events
     * @param view
     */
    public void onUserInteraction(View view){

    }
//
//    public void log(Object obj) {
//
//        // You can use filter *** to filter out message
//        LoggingUtils.error(getClass().getName(),
//                String.format("*** %s ***",
//                        obj == null ? "--!--"
//                                : obj.toString()));
//    }
}
