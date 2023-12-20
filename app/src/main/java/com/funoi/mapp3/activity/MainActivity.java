package com.funoi.mapp3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.funoi.mapp3.R;
import com.funoi.mapp3.service.StuService;
import com.funoi.mapp3.vo.Stu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // edu
        setSpinner();

        // save
        Button save=findViewById(R.id.save);
        setSave(save);

        // read
        Button read=findViewById(R.id.read);
        setRead(read);

    }

    public void setRead(Button read){
        read.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);//为Intent设置要激活的组件
            startActivity(intent);

        });
    }

    public void setSave(Button save){
        save.setOnClickListener(v -> {
            Stu stu=getStu();
            StuService service=new StuService(this);
            String result=service.addStu(stu);
            Snackbar.make(v,result,Snackbar.LENGTH_LONG).show();
        });
    }

    public void setSpinner() {
        Spinner edu = findViewById(R.id.edu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.edu_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        edu.setAdapter(adapter);
    }

    public Stu getStu(){
        // name
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        // sex
        RadioGroup rg = findViewById(R.id.sex);
        String sex = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        // love
        StringBuilder sb = new StringBuilder();
        CheckBox a = findViewById(R.id.a);
        CheckBox c = findViewById(R.id.c);
        CheckBox g = findViewById(R.id.g);
        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(a);
        checkBoxes.add(c);
        checkBoxes.add(g);
        for (CheckBox x : checkBoxes) {
            if (x.isChecked()) {
                sb.append(x.getText().toString()).append(" ");
            }
        }
        String love = sb.toString().trim();
        // edu
        String edu = ((Spinner) findViewById(R.id.edu)).getSelectedItem().toString();
        // intro
        String intro = Objects.requireNonNull(((TextInputEditText) findViewById(R.id.intro)).getText()).toString();

        return new Stu(name, sex, love, edu, intro);
    }
}