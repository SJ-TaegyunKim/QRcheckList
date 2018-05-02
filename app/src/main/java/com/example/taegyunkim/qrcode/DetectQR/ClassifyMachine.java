package com.example.taegyunkim.qrcode.DetectQR;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.taegyunkim.qrcode.Etc.SubView;
import com.example.taegyunkim.qrcode.Etc.DataManager;
import com.example.taegyunkim.qrcode.R;

public class ClassifyMachine extends AppCompatActivity {
    String result; // getIntent 에서 값을 받기 위한 변수
    boolean checkFail; // 두번 EditView를 생성하지 않기 위한 변수
    TextView textView;
    RadioButton sRadio; // Success 버튼 클릭했을 때를 위한 변수
    RadioButton fRadio; // Fail 버튼 클릭했을 때를 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_machine);
        Intent intent = getIntent();
        result = intent.getExtras().getString("result");
        textView = (TextView)findViewById(R.id.machineName);
        textView.setText(result);
        sRadio = (RadioButton)findViewById(R.id.checkO);
        fRadio = (RadioButton)findViewById(R.id.checkX);
        sRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.classifyReLayout);
                LinearLayout con = (LinearLayout)findViewById(R.id.con);
                con.setBackgroundResource(0);
                con.removeAllViews();
                checkFail = false;
                DataManager.getInstance().setCheckFail(checkFail);
            }
        });
        fRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFail = DataManager.getInstance().getCheckFail();
                if(checkFail == false){
                    checkFail = true;
                    DataManager.getInstance().setCheckFail(checkFail);
                    SubView newLayout= new SubView(getApplicationContext());
                    LinearLayout con = (LinearLayout)findViewById(R.id.con);
                    con.setBackgroundResource(R.drawable.shape);
                    con.addView(newLayout);
                }
            }
        });
    }


}
