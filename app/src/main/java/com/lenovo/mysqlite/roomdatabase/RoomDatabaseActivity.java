package com.lenovo.mysqlite.roomdatabase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.lenovo.mysqlite.AppInstance;
import com.lenovo.mysqlite.R;
import com.lenovo.mysqlite.databinding.RdaInject;

import java.util.List;

/**
 * Created by Lenovo on 8/23/2018.
 */

public class RoomDatabaseActivity extends AppCompatActivity {

    private RdaInject inject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject = DataBindingUtil.setContentView(this, R.layout.room_database_activity);

        inject.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10000; i++) {
                    MhsPojo mhsPojo = new MhsPojo();
                    mhsPojo.fullName = "yaya";
                    mhsPojo.nickName = "yaya";
                    mhsPojo.age = "yaya";
                    mhsPojo.majors = "yaya";
                    mhsPojo.isMarried = "yaya";
                    AppInstance.db.mhsDao().insertAll(mhsPojo);
                }
            }
        });

        inject.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        inject.scrollForm.setVisibility(View.GONE);
                        inject.recyclerView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < mhsPojos().size(); i++) {
                            Log.wtf("cek", "data " + mhsPojos().get(i).nickName);
                        }
                        return true;
                    case R.id.nav_mhs:
                        inject.scrollForm.setVisibility(View.VISIBLE);
                        inject.recyclerView.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });

    }

    public synchronized List<MhsPojo> mhsPojos() {
        return AppInstance.db.mhsDao().getAll();
    }
}
