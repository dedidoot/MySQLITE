package com.lenovo.mysqlite.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Lenovo on 8/23/2018.
 * https://developer.android.com/reference/android/arch/persistence/room/Database
 */

@Database(entities = {MhsPojo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MhsDao mhsDao();

}