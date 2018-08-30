package com.example.taegyunkim.qrcode.SQLite;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.taegyunkim.qrcode.Etc.Singleton;
import java.util.Map;
import static android.content.Context.MODE_PRIVATE;


public class DBHelper extends SQLiteOpenHelper
{
    private String tag = "TEST";
    private Context mContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name , factory, version);
        mContext = context;
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        // 이름은 ingredion

        // date 만들어주기
        String date = Singleton.getInstance().getDate();

        db.execSQL("CREATE TABLE if not exists Ingredion (date text primary key, Auto_Clave text,Auto_Clave_explain text,AAS text,AAS_explain text,Water_bath_가공전분_explain text,Water_bath_Advantec_explain text,Water_bath_가공전분 text,Water_bath_청신_explain text,Water_bath_Advantec text,Hotplate_전분6구_explain text,Water_bath_청신 text,Hotplate_제당우_explain text,Hotplate_전분6구 text,회화로_좌 text,회화로_좌_explain text,Hotplate_제당우 text,회화로_우 text,회화로_우_explain text,회화로_킬달용 text,회화로_킬달용_explain text,Hotplate_회화로옆 text,Hotplate_회화로옆_explain text,Hotplate_제당좌 text,Hotplate_제당좌_explain text,인화성물질보관 text,인화성물질보관_explain text, 점검자 text)");

        Log.d(tag,"onCreate");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert() {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("date",Singleton.getInstance().getDate());
        db.insert("Ingredion",null, values);
        //db.execSQL("INSERT INTO Ingredion VALUES (date,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)");
        db.close();
    }

    public void update(String column, String value, boolean checkValue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //db.update("Ingredion",values)

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

    // Override select(String input)
    public void select(String columns){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT date FROM Ingredion",null); // columns 가 아니라

        String dateCheck; // date 값 정정을 위한 String 변수

        while (cursor.moveToNext()){
            dateCheck = cursor.getString(cursor.getColumnIndex("date"));
            if (dateCheck.equals(columns)){
                Singleton.getInstance().setDateCheck(true);
            }
        }
    }

    // addAlter 시 컬럼이 존재하는지 먼저 파악할 것
    public void addAlter(String item){
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제

        // Alter Error
        db.execSQL("ALTER TABLE Ingredion ADD "+item+" TEXT");
        Log.d(tag,"addAlter");
        db.close();
    }

    public void alter(String oldColumn, String newColumn)
    {
        SharedPreferences prefs = mContext.getSharedPreferences("columnName", 0);
        SharedPreferences.Editor editor = prefs.edit();
        SQLiteDatabase db = getWritableDatabase();
        String createSql = "CREATE TABLE if not exists Ingredion (date text primary key, ";
        String copySql = "INSERT INTO Ingredion(";

        db.execSQL("ALTER TABLE Ingredion RENAME TO Ingredion2");

        for(int i=0; i<Singleton.getInstance().getColumnNameList().size(); i++)
        {
            if(Singleton.getInstance().getColumnNameList().get(i).toString().equals(oldColumn)==true)
            {
                createSql += newColumn + " text,";
            }
            else
            {
                createSql += Singleton.getInstance().getColumnNameList().get(i).toString() + " text,";
            }
        }
        createSql += " 점검자 text)";
        db.execSQL(createSql);

        for(int i=0; i<Singleton.getInstance().getColumnNameList().size(); i++)
        {
            if(i==Singleton.getInstance().getColumnNameList().size()-1)
            {
                if(Singleton.getInstance().getColumnNameList().get(i).toString().equals(oldColumn)==false)
                {
                    copySql += Singleton.getInstance().getColumnNameList().get(i).toString() + ") ";
                }
            }
            else
            {
                if(Singleton.getInstance().getColumnNameList().get(i).toString().equals(oldColumn)==false)
                {
                    copySql += Singleton.getInstance().getColumnNameList().get(i).toString() + ", ";
                }
            }
        }
        copySql += " SELECT ";

        for(int i=0; i<Singleton.getInstance().getColumnNameList().size(); i++)
        {
            if(i==Singleton.getInstance().getColumnNameList().size()-1)
            {
                if(Singleton.getInstance().getColumnNameList().get(i).toString().equals(oldColumn)==false)
                {
                    copySql += Singleton.getInstance().getColumnNameList().get(i).toString() + " FROM Ingredion2";
                }
            }
            else
            {
                if(Singleton.getInstance().getColumnNameList().get(i).toString().equals(oldColumn)==false)
                {
                    copySql += Singleton.getInstance().getColumnNameList().get(i).toString() + ", ";
                }
            }
        }

        db.execSQL(copySql);
        db.execSQL("DROP TABLE Ingredion2");
        editor.putString(findKey(oldColumn),newColumn);
        editor.apply();
        db.close();
    }

    String findKey(String value)
    {
        SharedPreferences prefs = mContext.getSharedPreferences("columnName", 0);
        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            if(value.equals(entry.getValue().toString()))
            {
                return entry.getKey();
            }
        }
        return null;
    }
}
