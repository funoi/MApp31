package com.funoi.mapp3.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.funoi.mapp3.vo.Stu;

import java.util.ArrayList;
import java.util.List;

public class StuService {
    private final DBHelper dbHelper;

    public StuService(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public String addStu(Stu stu){
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        if(findStu(stu.getName())!=null){
            return updateStu(stu);
        }

        ContentValues s=new ContentValues();
        s.put("name",stu.getName());
        s.put("sex",stu.getSex());
        s.put("love",stu.getLove());
        s.put("edu",stu.getEdu());
        s.put("intro",stu.getIntro());

        long i=db.insert("stu",null,s);
        if(i>0)
            return "添加成功";
        else
            return "添加失败";
    }

    public Stu findStu(String name_) {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 只读的方式打开数据库

        Cursor cursor =
                db.query(
                        "stu",
                        null,
                        "name=?",
                        new String[]{name_},
                        null,
                        null,
                        null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
            String love = cursor.getString(cursor.getColumnIndexOrThrow("love"));
            String edu = cursor.getString(cursor.getColumnIndexOrThrow("edu"));
            String intro = cursor.getString(cursor.getColumnIndexOrThrow("intro"));

            cursor.close();
            return new Stu(name, sex, love, edu, intro);
        } else {
            cursor.close();
            return null;
        }
    }

    public String delStu(String name_){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int i=db.delete("stu","name=?",new String[]{name_});
        if(i>0)
            return "删除成功";
        else
            return "删除失败";
    }

    public String updateStu(Stu stu){
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues s = new ContentValues();
        if (stu.getName() != null) {
            s.put("name", stu.getName());
        }
        if (stu.getSex() != null) {
            s.put("sex", stu.getSex());
        }
        if (stu.getLove() != null) {
            s.put("love", stu.getLove());
        }
        if (stu.getEdu() != null) {
            s.put("edu", stu.getEdu());
        }
        if (stu.getIntro() != null) {
            s.put("intro", stu.getIntro());
        }

        int i=db.update("stu", s, "name=?", new String[]{stu.getName()});
        if(i>0)
            return "更新成功";
        else
            return "更新失败";
    }

    public List<Stu> findAll(Integer offset, Integer maxResult){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        List<Stu> stus=new ArrayList<>();
        Cursor cursor = db.query("stu", null, null, null,
                null, null, null, offset + " , " + maxResult);


        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String sex = cursor.getString(cursor.getColumnIndexOrThrow("sex"));
            String love = cursor.getString(cursor.getColumnIndexOrThrow("love"));
            String edu = cursor.getString(cursor.getColumnIndexOrThrow("edu"));
            String intro = cursor.getString(cursor.getColumnIndexOrThrow("intro"));

            stus.add(new Stu(name, sex, love, edu, intro));
        }


        cursor.close();
        if (!stus.isEmpty()) {
            return stus;
        } else {
            return null;
        }
    }

    public Integer getCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 只读的方式打开数据库
        Cursor cursor = db.query("stu", new String[]{"count(*)"}, null,
                null, null, null, null);

        cursor.moveToFirst();
        int sum = cursor.getInt(0);

        cursor.close();
        return sum;
    }
}
