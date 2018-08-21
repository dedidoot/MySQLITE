package com.lenovo.mysqlite;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.lenovo.mysqlite.databinding.ActivityMainInject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainInject inject;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    inject.scrollForm.setVisibility(View.GONE);
                    inject.recyclerView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.nav_mhs:
                    inject.scrollForm.setVisibility(View.VISIBLE);
                    inject.recyclerView.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject = DataBindingUtil.setContentView(this, R.layout.activity_main);
        inject.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
