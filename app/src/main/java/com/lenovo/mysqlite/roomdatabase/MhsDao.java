package com.lenovo.mysqlite.roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Lenovo on 8/23/2018.
 */

@Dao
public  interface MhsDao {

    @Query("SELECT * FROM MhsPojo")
     List<MhsPojo> getAll();

    @Query("SELECT * FROM MhsPojo WHERE nickname" + " LIKE :nickname LIMIT 1")
    List<MhsPojo> getNickName(String nickname);

    @Insert
    void insertAll(MhsPojo... mhsPojos);

    @Delete
    void delete(MhsPojo mhsPojo);

}
