package com.example.zootopia.dblayout;

import java.util.List;

/**
 * Created by ychu1 on 16/4/15.
 */
public class DbRoomMembersHandler implements DbHandlerIntf {
    private static final String ROOM_ID = "room_id";
    private static final String USER_ID = "user_id";

    @Override
    public void createTable() {

    }

    /* ======================= CRUD ======================= */
    public void addRoomMember(int userID){      // insert

    }

    public List<Integer> gerRoomMemberList(){   // read
        return null;
    }

    public void deleteMember(int id){           // delete

    }
}
