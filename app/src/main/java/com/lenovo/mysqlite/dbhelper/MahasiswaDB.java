package com.lenovo.mysqlite.dbhelper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.lenovo.mysqlite.GlobalConst;
import com.lenovo.mysqlite.field.MahasiswaFieldName;
import com.lenovo.mysqlite.TableName;

public class MahasiswaDB extends SQLiteOpenHelper {

    public MahasiswaDB(Context context) {
        super(context, GlobalConst.DATABASE_NAME, null, GlobalConst.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TableName.MAHASISWA + " " +
                        "(" + MahasiswaFieldName.ID + " integer primary key, " +
                        MahasiswaFieldName.FULL_NAME + " text, " +
                        MahasiswaFieldName.NICKNAME + " text, " +
                        MahasiswaFieldName.AGE + " text," +
                        MahasiswaFieldName.MAJORS + " text," +
                        MahasiswaFieldName.IS_MARRIED + " text)"
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
        contentValues.put(MahasiswaFieldName.FULL_NAME, fullName);
        contentValues.put(MahasiswaFieldName.NICKNAME, nickName);
        contentValues.put(MahasiswaFieldName.AGE, age);
        contentValues.put(MahasiswaFieldName.MAJORS, majors);
        contentValues.put(MahasiswaFieldName.IS_MARRIED, isMarried);
        db.insert(TableName.MAHASISWA, null, contentValues);
        return true;
    }

    public Cursor getMhs(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName.MAHASISWA + " where " + MahasiswaFieldName.ID + "=" + id + "", null);
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
        contentValues.put(MahasiswaFieldName.FULL_NAME, fullName);
        contentValues.put(MahasiswaFieldName.NICKNAME, nickName);
        contentValues.put(MahasiswaFieldName.AGE, age);
        contentValues.put(MahasiswaFieldName.MAJORS, majors);
        contentValues.put(MahasiswaFieldName.IS_MARRIED, isMarried);
        db.update(TableName.MAHASISWA, contentValues, MahasiswaFieldName.ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMhs(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName.MAHASISWA,
                MahasiswaFieldName.ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllMhs() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableName.MAHASISWA, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(MahasiswaFieldName.NICKNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}