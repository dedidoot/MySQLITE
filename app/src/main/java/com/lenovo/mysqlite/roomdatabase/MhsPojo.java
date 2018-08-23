package com.lenovo.mysqlite.roomdatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Lenovo on 8/23/2018.
 * https://developer.android.com/training/data-storage/room/
 */

@Entity
public class MhsPojo {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "nickname")
    public String nickName;

    @ColumnInfo(name = "age")
    public String age;

    @ColumnInfo(name = "majors")
    public String majors;

    @ColumnInfo(name = "is_married")
    public String isMarried;


}
