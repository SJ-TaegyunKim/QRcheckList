package com.example.taegyunkim.qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.Etc.Singleton;
import com.example.taegyunkim.qrcode.DetectQR.ClassifyMachine;
import com.example.taegyunkim.qrcode.Etc.Singleton;
import com.example.taegyunkim.qrcode.GenerateQR.GenerateQRcode;
import com.example.taegyunkim.qrcode.SQLite.ChangeColumn;
import com.example.taegyunkim.qrcode.SQLite.DBHelper;
import com.facebook.stetho.Stetho;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DBHelper helper;
    String dbName = "IngrediDBfile.db";
    //public SQLiteDatabase db;

    Intent classifyString;
    Button btnGenerateClick;
    Button btnChangeColumns;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        btnGenerateClick = (Button) findViewById(R.id.btn_generateQR);
        btnGenerateClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GenerateQRcode.class);
                startActivity(intent); // GenerateQRcode 로 이동
            }
        });
        btnChangeColumns = (Button) findViewById(R.id.btn_changeColumn);
        btnChangeColumns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangeColumn.class);
                startActivity(intent);
            }
        });

        // sharedPreference로 array[0]부터 저장할것
        String[] column = {"회화로_좌", "회화로_좌_explain", "회화로_우", "회화로_우_explain", "회화로_킬달용", "회화로_킬달용_explain", "Hotplate_회화로옆", "Hotplate_회화로옆_explain", "Hotplate_제당좌", "Hotplate_제당좌_explain", "Hotplate_제당우", "Hotplate_제당우_explain", "Hotplate_전분6구", "Hotplate_전분6구_explain", "Water_bath_청신", "Water_bath_청신_explain", "Water_bath_Advantec", "Water_bath_Advantec_explain", "Water_bath_가공전분", "Water_bath_가공전분_explain", "AAS", "AAS_explain", "Auto_Clave", "Auto_Clave_explain", "인화성물질보관", "인화성물질보관_explain"};
        saveArray(column, "columnName", getApplicationContext());

        helper = new DBHelper(this, dbName, null, 1);
        //helper.insert();
        saveDB();
    }


    public void detectClick(View v) {

        helper.select(Singleton.getInstance().getDate()); // PRIMARY KEY Exist check

        // Singleton Datecheck 'True'일 시 그냥 QRcode Recorder 실행
        if (Singleton.getInstance().getDateCheck()) {
            new IntentIntegrator(this).initiateScan();
        } else {
            helper.insert();
            new IntentIntegrator(this).initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && resultCode == RESULT_OK) {
                try {
                    temp = result.getContents();
                    temp = URLDecoder.decode(temp, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Scanned: " + temp, Toast.LENGTH_LONG).show();

                btnGenerateClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), GenerateQRcode.class);
                        startActivity(intent); // GenerateQRcode 로 이동
                    }
                });
                classifyString = new Intent(getApplicationContext(), ClassifyMachine.class);
                classifyString.putExtra("result", temp);
                startActivity(classifyString);
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("columnName", 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    private void saveDB() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row row = sheet.createRow(0);
        Cell cell;

        cell = row.createCell(0);
        cell.setCellValue("한글");

        cell = row.createCell(1);
        cell.setCellValue("English");

        cell = row.createCell(2);
        cell.setCellValue("123");

        File xlsFile = new File(getExternalFilesDir(null), "text.xls");
        try {
            FileOutputStream os = new FileOutputStream(xlsFile);
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Toast.makeText(getApplicationContext(), xlsFile.getAbsolutePath() + "에 저장되었습니다.", Toast.LENGTH_SHORT).show();
        // for(int i=0; i)
        // DB (0,0 부터 끝까지)
        // for(int i=0; i<)
    }
}