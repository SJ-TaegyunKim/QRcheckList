package com.example.taegyunkim.qrcode;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.Etc.Singleton;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;
import com.example.taegyunkim.qrcode.DetectQR.ClassifyMachine;
import com.example.taegyunkim.qrcode.GenerateQR.GenerateQRcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    Intent classifyString;
    Button btnGenerateClick;
    public MobileServiceClient mClient;
    public MobileServiceTable<CheckTable> mCheckTable;
    public String stringDate;
    public Date date;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public long now;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenerateClick = (Button)findViewById(R.id.btn_generateQR);
    }

    public void detectClick(View v){
        new IntentIntegrator(this).initiateScan();

        now = System.currentTimeMillis();
        date = new Date(now);
        stringDate = dateFormat.format(date);

        try
        {
            mClient = new MobileServiceClient("https://qrchecklist.azurewebsites.net", this);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(15, TimeUnit.SECONDS);
                    client.setWriteTimeout(15, TimeUnit.SECONDS);
                    return client;
                }
            });

            mCheckTable = mClient.getTable(CheckTable.class);

        } catch(IOException e) { Log.e("MobileServiceClient","error");}

        getAllData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && resultCode == RESULT_OK){
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                btnGenerateClick.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(v.getContext(), GenerateQRcode.class);
                        startActivity(intent); // GenerateQRcode 로 이동
                    }
                });
                classifyString = new Intent(getApplicationContext(), ClassifyMachine.class);
                classifyString.putExtra("result",result.getContents());
                startActivity(classifyString);
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getAllData()
    {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<CheckTable> results = mCheckTable.where().field("date").eq(stringDate).execute().get();
                    if(results.size()>0)
                    {
                        Log.e("Results count ", results.size()+"");
                        for (CheckTable item : results)
                        {
                            // 검사 비고란 정보 받아오기
                            Singleton.getInstance().setHwaehwa_left_remarks(item.getHwaehwa_left_marks());
                            Singleton.getInstance().setHwaehwa_right_remarks(item.getHwaehwa_right_marks());
                            Singleton.getInstance().setHwaehwa_kildal_remarks(item.getHwaehwa_kildal_marks());
                            Singleton.getInstance().setHotplate_hwaehwaside_remarks(item.getHotplate_hwaehwaside_marks());
                            Singleton.getInstance().setHotplate_jaedang_left_remarks(item.getHotplate_jaedang_left_marks());
                            Singleton.getInstance().setHotplate_jaedang_right_remarks(item.getHotplate_jaedang_right_marks());
                            Singleton.getInstance().setHotplate_jeonboon_6_remarks(item.getHotplate_jeonboon_6_marks());
                            Singleton.getInstance().setWaterbath_chungsin_remarks(item.getWaterbath_chungsin_marks());
                            Singleton.getInstance().setWaterbath_advantec_remarks(item.getWaterbath_advantec_marks());
                            Singleton.getInstance().setWaterbath_gagong_remarks(item.getWaterbath_gagong_marks());
                            Singleton.getInstance().setAas_remarks(item.getAas_marks());
                            Singleton.getInstance().setAutoclave_remarks(item.getAutoclave_marks());
                            Singleton.getInstance().setFlammable_remarks(item.getFlammable_marks());

                            // 검사 여부 받아오기
                            Singleton.getInstance().setHwaehwa_left(item.getHwaehwa_left());
                            Singleton.getInstance().setHwaehwa_right(item.getHwaehwa_right());
                            Singleton.getInstance().setHwaehwa_kildal(item.getHwaehwa_kildal());
                            Singleton.getInstance().setHotplate_hwaehwaside(item.getHotplate_hwaehwaside());
                            Singleton.getInstance().setHotplate_jaedang_left(item.getHotplate_jaedang_left());
                            Singleton.getInstance().setHotplate_jaedang_right(item.getHotplate_jaedang_right());
                            Singleton.getInstance().setHotplate_jeonboon_6(item.getHotplate_jeonboon_6());
                            Singleton.getInstance().setWaterbath_chungsin(item.getWaterbath_chungsin());
                            Singleton.getInstance().setwaterbath_advantec(item.getWaterbath_advantec());
                            Singleton.getInstance().setWaterbath_gagong(item.getWaterbath_gagong());
                            Singleton.getInstance().setAas(item.getAas());
                            Singleton.getInstance().setAutoclave(item.getAutoclave());
                            Singleton.getInstance().setFlammable(item.getFlammable());
                        }
                    }
                    else
                    {
                        // 오늘 일정으로 인스턴스 생성
                        CheckTable item = new CheckTable();
                        item.setDate(stringDate);

                        Log.e("insert try", "insert trying");
                        addItem();

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() { // UI 작업
                         }
                    });
                } catch (final Exception e)
                {
                    Log.e("Async", "Get error");
                }
                return null;
            }
        };

        runAsyncTask(task);
    }

    public void addItem()
    {
        if (mClient == null) {
            return;
        }

        // Create a new item
        final CheckTable item = new CheckTable();

        item.setDate(stringDate);

        // Insert the new item
        AsyncTask<Void, Void, Void> task =
                new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final CheckTable entity = addItemInTable(item);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // UI 작업
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };

        runAsyncTask(task);

    }

    public CheckTable addItemInTable(CheckTable item) throws ExecutionException, InterruptedException
    {
        CheckTable entity = mCheckTable.insert(item).get();
        return entity;
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }

    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }
}
