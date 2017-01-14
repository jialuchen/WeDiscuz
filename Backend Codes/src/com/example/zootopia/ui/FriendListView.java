package com.example.zootopia.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.zootopia.wediscuz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zootopia on 4/12/16.
 * Linpeng Lyu (linpengl)
 * Yilei Chu (ychu1)
 * Jialu Chen (jialuc)
 */
public class FriendListView extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Yilei Chu");
        map.put("email", "ychu1@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Jialu Chen");
        map.put("email", "jialuc@andrew.cmu.edu");
        map.put("image",R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Linpeng Lyu");
        map.put("email", "linpengl@andrew.cmu.edu");
        map.put("image", R.mipmap.ic_launcher);
        list.add(map);

        return list;
    }
}
