package com.lenovo.mysqlite;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.lenovo.mysqlite.roomdatabase.AppDatabase;

/**
 * Created by Lenovo on 8/23/2018.
 */

public class AppInstance extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-campus")
                .allowMainThreadQueries()
                .build();
    }
}
