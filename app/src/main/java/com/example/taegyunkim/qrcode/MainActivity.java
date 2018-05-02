package com.example.taegyunkim.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.GenerateQR.GenerateQRcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Intent classifyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGenerateClick = (Button)findViewById(R.id.btn_generateQR);
        btnGenerateClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), GenerateQRcode.class);
                startActivity(intent); // GenerateQRcode 로 이동
            }
        });
    }
    public void detectClick(View v){
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                classifyString = new Intent(getApplicationContext(), ClassifyMachine.class);
                classifyString.putExtra("result",result.getContents());
                startActivity(classifyString);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
