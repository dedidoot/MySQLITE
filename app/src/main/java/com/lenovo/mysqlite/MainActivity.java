package com.lenovo.mysqlite;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.lenovo.mysqlite.databinding.ActivityMainInject;
import com.lenovo.mysqlite.dbhelper.MahasiswaDB;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private ActivityMainInject inject;
    private MahasiswaDB mahasiswaDB;
    private Subscriber<String> mySubscriber;
    private Observable<String> createObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mahasiswaDB = new MahasiswaDB(this);

        inject = DataBindingUtil.setContentView(this, R.layout.activity_main);
        inject.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.wtf("onNext", "onNext " + s);
            }

            @Override
            public void onCompleted() {
                Log.wtf("onCompleted", "onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                Log.wtf("onError", "onError " + e.getMessage());
            }
        };

        createObserver = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 1000; i++) {
                    mahasiswaDB.insertMhs(inject.edFullName.getText().toString(),
                            inject.edNickname.getText().toString() + " " + i,
                            inject.edAge.getText().toString(),
                            inject.edMajors.getText().toString(),
                            inject.rbYes.isChecked() ? "Yes" : "No");
                }
                subscriber.onNext("Inserting...");
                subscriber.onCompleted();
            }
        });

        inject.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validation();

                createObserver.subscribe(mySubscriber);
            }
        });


    }

    private void validation() {
        if (inject.edFullName.getText().toString().isEmpty()) {
            inject.edFullName.setError("harus diisi");
            return;
        }
        if (inject.edNickname.getText().toString().isEmpty()) {
            inject.edNickname.setError("harus diisi");
            return;
        }
        if (inject.edMajors.getText().toString().isEmpty()) {
            inject.edMajors.setError("harus diisi");
            return;
        }
        if (inject.edAge.getText().toString().isEmpty()) {
            inject.edAge.setError("harus diisi");
            return;
        }
        if (!inject.rbNo.isChecked() && !inject.rbYes.isChecked()) {
            Snackbar.make(inject.getRoot(), "Status nikah harus diisi", Snackbar.LENGTH_SHORT).show();
            return;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    inject.scrollForm.setVisibility(View.GONE);
                    inject.recyclerView.setVisibility(View.VISIBLE);
                    if (mahasiswaDB.getAllNickName().size() > 0) {
                        for (int i = 0; i < mahasiswaDB.getAllNickName().size(); i++) {
                            Log.wtf("cek", "List: " + mahasiswaDB.getAllNickName().get(i));
                        }
                    }
                    return true;
                case R.id.nav_mhs:
                    inject.scrollForm.setVisibility(View.VISIBLE);
                    inject.recyclerView.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

}
