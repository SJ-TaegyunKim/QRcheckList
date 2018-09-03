package com.example.taegyunkim.qrcode.DetectQR;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.Etc.SubView;
import com.example.taegyunkim.qrcode.Etc.Singleton;
import com.example.taegyunkim.qrcode.R;
import com.example.taegyunkim.qrcode.SQLite.DBHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static com.example.taegyunkim.qrcode.MainActivity.DBNAME;

public class ClassifyMachine extends AppCompatActivity {
    private DBHelper helper;

    String machineName; // getIntent 에서 값을 받기 위한 변수
    boolean machineCheck;
    boolean checkNonPass; // NonPass 여부를 알기위한 변수 ( 싱글톤 사용하여 동적으로 계속 생성되지 않기위해 만듬)
    String reasonForNonPass; // NonPass 시 왜 논패스인지 알기위한 변수

    TextView textView; // QR 코드 인식 후 어떤 기기인지 알려주는 View
    EditText editText; // Non-Pass 시 적는 View
    RadioButton sRadio; // Success 버튼 클릭했을 때를 위한 변수
    RadioButton fRadio; // Fail 버튼 클릭했을 때를 위한 변수
    Button submit; // 제출 View


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_machine);
        Intent intent = getIntent();
        machineName = intent.getExtras().getString("result");
        try{
            machineName = URLDecoder.decode(machineName,"UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        textView = (TextView)findViewById(R.id.machineName);
        sRadio = (RadioButton)findViewById(R.id.checkO);
        fRadio = (RadioButton)findViewById(R.id.checkX);
        submit = (Button)findViewById(R.id.submit);

        textView.setText(machineName); // 인텐트에서 넘어온 값 전달

        helper = new DBHelper(this, DBNAME, null, 1);

        sRadio.setOnClickListener(new View.OnClickListener() { // Success 클릭 이벤트
            @Override
            public void onClick(View view) {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.classifyReLayout);
                LinearLayout con = (LinearLayout)findViewById(R.id.con);
                con.setBackgroundResource(0);
                con.removeAllViews(); // Linear 전부 삭제
                checkNonPass = false;
                Singleton.getInstance().setCheckFail(checkNonPass);
            }
        });

        fRadio.setOnClickListener(new View.OnClickListener() { // Fail 클릭 이벤트
            @Override
            public void onClick(View view) {
                checkNonPass = Singleton.getInstance().getCheckFail();
                if(checkNonPass == false){ // 싱글톤 사용하지 않으면 계속 동적으로 만듬
                    checkNonPass = true;
                    Singleton.getInstance().setCheckFail(checkNonPass);
                    SubView newLayout= new SubView(getApplicationContext());
                    LinearLayout con = (LinearLayout)findViewById(R.id.con);
                    con.setBackgroundResource(R.drawable.shape);
                    con.addView(newLayout);
                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener() { // submit 클릭 이벤트
            @Override
            public void onClick(View view) {
                if (sRadio.isChecked()) {                                 // sRadio 체크 되어있을 때
                    // 1. select 문을 통해 해당 컬럼 존재하는지 조사.
                    // 2. 없으면 Toast 메시지 띄울 것.
                    // 2-1. 있으면 update 진행하고 비고는 null 값으로 초기화 할 것.
                    // machineName                                        // 기계명  ex) 회화로(좌),회화로(우)
                    machineCheck = true;                                // 기계 합,불 체크 ex) PASS,Non-Pass
                    reasonForNonPass = "Null";                           // DB NotNULL 이면 아무 문자열이라도 바꿀 것.

                    helper.update(machineName, machineCheck, reasonForNonPass);

                    Toast.makeText(getApplicationContext(),"Pass로 제출되었습니다.", Toast.LENGTH_LONG).show();
                }
                else {                                                    // fRadio 체크 되어있을 때
                    // machineName                                        // 기계명  ex) 회화로(좌),회화로(우)
                    machineCheck = false;                                 // 기계 합,불 체크 ex) PASS,Non-Pass
                    editText = (EditText) findViewById(R.id.checkFailExplain); // 불합격 사유 editText 와 연결
                    reasonForNonPass = editText.getText().toString();     // Non-Pass 이유 넣기

                    helper.update(machineName, machineCheck, reasonForNonPass);

                    Toast.makeText(getApplicationContext(),"Non-Pass로 제출되었습니다.", Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
    }
}