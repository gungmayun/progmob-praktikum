package com.example.adiputra.sewain;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Sewain.db";
    public static final String TABLE_NAME = "tabel_motor";
    public static final String TABLE_LOGIN = "tabel_login";
    private static final int DATABASE_VERSION = 1;
    public static final String COL_1 = "id";
    public static final String COL_2 = "nopol";
    public static final String COL_3 = "jenis";
    public static final String COL_4 = "tahun";
    public static final String COL_5 = "harga";
    public static final String KEY_NAMA = "name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_REPASSWORD = "repassword";
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists tabel_motor(id integer primary key autoincrement," +
                "nopol text NOT NULL," +

                "jenis text NOT NULL," +

                "tahun text NOT NULL," +

                "harga integer NOT NULL);");
        Log.i("tag","mantap");

        db.execSQL("create table if not exists tabel_login(id integer primary key autoincrement," +
                "name text NOT NULL," +
                "username text NOT NULL," +
                "password text NOT NULL," +
                "repassword text NOT NULL);");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN);
        onCreate(db);

    }


    public boolean insertData(String nopol, String jenis, String tahun, String harga) {

        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nopol);
        contentValues.put(COL_3,jenis);
        contentValues.put(COL_4,tahun);
        contentValues.put(COL_5,harga);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertUsername(String username, String password, String nama, String repassword) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        onCreate(db);
        contentValues.put(KEY_NAMA,nama);
        contentValues.put(KEY_USERNAME,username);
        contentValues.put(KEY_PASSWORD,password);
        contentValues.put(KEY_REPASSWORD,repassword);
        long result = db.insert(TABLE_LOGIN, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from tabel_motor", null);

        return res;

    }



    //metode untuk merubah data

    public boolean updateData(String id, String nopol, String jenis, String tahun, String harga) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);

        contentValues.put(COL_2,nopol);

        contentValues.put(COL_3,jenis);

        contentValues.put(COL_4,tahun);

        contentValues.put(COL_5,harga);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});

        return true;

    }


    public int deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    }

}