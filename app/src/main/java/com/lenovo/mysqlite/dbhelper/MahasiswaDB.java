package com.lenovo.mysqlite.dbhelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.lenovo.mysqlite.GlobalConst;
import com.lenovo.mysqlite.TableName;

public class MahasiswaDB extends SQLiteOpenHelper {

    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String NICKNAME = "nickname";
    public static final String AGE = "age";
    public static final String MAJORS = "majors";
    public static final String IS_MARRIED = "is_married";

    public MahasiswaDB(Context context) {
        super(context, GlobalConst.DATABASE_NAME, null, GlobalConst.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TableName.MAHASISWA + " " +
                        "(" + ID + " integer primary key, " +
                        FULL_NAME + " text, " +
                        NICKNAME + " text, " +
                        AGE + " text," +
                        MAJORS + " text," +
                        IS_MARRIED + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TableName.MAHASISWA);
        onCreate(db);
    }

    public boolean insertMhs(String fullName, String nickName, String age, String majors, String isMarried) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FULL_NAME, fullName);
        contentValues.put(NICKNAME, nickName);
        contentValues.put(AGE, age);
        contentValues.put(MAJORS, majors);
        contentValues.put(IS_MARRIED, isMarried);
        db.insert(TableName.MAHASISWA, null, contentValues);
        return true;
    }

    public Cursor getMhs(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName.MAHASISWA + " where " + ID + "=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TableName.MAHASISWA);
        return numRows;
    }

    public boolean updateMhs(Integer id, String fullName, String nickName, String age, String majors, String isMarried) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FULL_NAME, fullName);
        contentValues.put(NICKNAME, nickName);
        contentValues.put(AGE, age);
        contentValues.put(MAJORS, majors);
        contentValues.put(IS_MARRIED, isMarried);
        db.update(TableName.MAHASISWA, contentValues, ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMhs(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName.MAHASISWA,
                ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllNickName() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName.MAHASISWA, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(NICKNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}