package com.example.taegyunkim.qrcode.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private String tag = "TEST";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,  name , factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 ingredion이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        inspector 문자열 컬럼*/

        db.execSQL("CREATE TABLE if not exists Ingredion (date text primary key, 점검자 text)"); // 이름 바꿀것
        Log.d(tag,"onCreate");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String date) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        Log.d(tag,"insertSQL");
        ContentValues values = new ContentValues();
        values.put("date",date);
        db.insert("Ingredion",null,values);
        //db.execSQL("INSERT INTO Ingredion VALUES (date,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)");
        db.close();
    }

    public void update(String column, String value, boolean checkValue) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        ContentValues values = new ContentValues();
       // db.update("Ingredion",values)

        db.close();
    }

    public void select(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("Ingredion",null,null,null,null,null,null);

        while(c.moveToNext()){
            String date = c.getString(c.getColumnIndex("date"));
            String checkPerson = c.getString(c.getColumnIndex("점검자"));
            String a1 = c.getString(c.getColumnIndex("회화로좌"));

            Log.d(tag,"date : "+date+", 점검자 : "+checkPerson+", 회화로좌 : "+a1);
        }
    }

    public void addAlter(String item){
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제

        db.execSQL("ALTER TABLE Ingredion ADD COLUMN"+item+"TEXT");
        Log.d(tag,"addAlter");
        db.close();
    }

    public void alter(String oldColumn, String newColumn) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제

        db.execSQL("ALTER TABLE Ingredion RENAME COLUMN"+oldColumn+"TO"+newColumn);
        db.close();
    }
    //public void select(String )
}
