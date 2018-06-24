package com.example.taegyunkim.qrcode.SQLite;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.taegyunkim.qrcode.R;

public class ChangeColumn extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_column);
        context = getApplicationContext();
        String[] columnInfo = loadArray("columnName",context);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerinChangeColumn);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.custom_simple_dropdown_item_1line,columnInfo);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    // Device File Explorer -> data/data/com.example.taegyunkim.qrcode/databases/IngrediDBfile.db
    // 오른 클릭 -> Save as -> 바탕화면
    // 바꿀 컬럼명 들을 가져옴.
    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("columnName", 0);
        int size = prefs.getInt(arrayName + "_size", 0);

        String array[] = new String[size/2];
        for(int i=0;i<size/2;i++) {
            Log.e("count",String.valueOf(i).toString());
            int n;
            n = i*2; // 컬럼명만 가져오기위해 작성
            array[i] = prefs.getString(arrayName + "_" + n, null);
        }
        return array;
    }
    // 현재 DB
}
