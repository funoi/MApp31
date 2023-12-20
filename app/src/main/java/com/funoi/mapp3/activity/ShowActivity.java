package com.funoi.mapp3.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;

import com.funoi.mapp3.R;
import com.funoi.mapp3.service.StuService;
import com.funoi.mapp3.vo.Stu;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        List<HashMap<String, Object>> data = getHashMaps();

        ListView listView=findViewById(R.id.listview);
        SimpleAdapter adapter=new SimpleAdapter(this,data,
                R.layout.list_item,new String[]{"name","sex","love","edu","intro"},
                new int[]{R.id.name,R.id.sex,R.id.love,R.id.edu,R.id.intro});
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            StuService service=new StuService(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("确认删除？").setTitle("提示");
            builder.setPositiveButton("确认", (dialog, id1) -> {
                String name=((TextView)view.findViewById(R.id.name)).getText().toString().substring(6);
                service.delStu(name);
                Snackbar.make(view,"删除成功",Snackbar.LENGTH_LONG).show();
            });
            builder.setNegativeButton("取消", (dialog, id12) -> {
                // User cancelled the dialog
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        });
    }

    private List<HashMap<String, Object>> getHashMaps() {
        StuService service=new StuService(this);
        List<Stu> stus=service.findAll(0,service.getCount());
        List<HashMap<String,Object>> data=new ArrayList<>();

        for (Stu s:stus){
            HashMap<String,Object> item=new HashMap<>();
            item.put("name","name: "+s.getName());
            item.put("sex","sex: "+s.getSex());
            item.put("love","love: "+s.getLove());
            item.put("edu","edu: "+s.getEdu());
            item.put("intro","intro: "+s.getIntro());
            data.add(item);
        }
        return data;
    }
}
