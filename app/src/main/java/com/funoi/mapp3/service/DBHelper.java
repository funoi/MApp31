package com.funoi.mapp3.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mapp3.db"; // 数据库名称
    private static final int DB_VERSION = 1; // 数据库版本

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "create table stu"
                        + "("
                        + "name varchar(255) primary key not null,"
                        + "sex varchar(255),"
                        + "love varchar(255),"
                        + "edu varchar(255),"
                        + "intro varchar(255)"
                        + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
