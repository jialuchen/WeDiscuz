package com.example.zootopia.ws.remote;

import com.example.zootopia.ws.DatabaseActionIntf;

import org.json.JSONObject;

/**
 * Created by ychu1 on 16/4/15.
 */
public interface RemoteDatabaseActionIntf extends DatabaseActionIntf {
    // apart from the 5 actions related to write, there are "read" remote db actions
    JSONObject login(String email,String password); //login, and get all after verified
}
