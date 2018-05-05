package com.example.taegyunkim.qrcode;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
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
        //test commit

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

        getTableFromAzure();
    }

    private void getTableFromAzure()
    {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<CheckTable> results = mCheckTable.where().field("date").eq(stringDate).select( "date", "hwaehwa_left", "hwaehwa_right").execute().get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() { // UI 작업
                            Log.e("Results count ", results.size()+"");
                            for (CheckTable item : results)
                            {
                                Log.e("hwaehwa left", item.getHwaehwa_left()+"");
                                Log.e("hwaehwa right ", item.getHwaehwa_right()+"");
                                /*
                                if (currentWeather.contains("rain") || currentWeather.contains("drizzle") || currentWeather.contains("storm")) { breakAcceleration = item.getWetAcceleration(); }
                                else if(currentWeather.contains("snow")) { breakAcceleration = item.getDryAcceleration()/2;}
                                else { breakAcceleration = item.getDryAcceleration(); }

                                Singleton.getInstance().setAcceleration(breakAcceleration);
                                */
                            }
                        }
                    });
                } catch (final Exception e){
                    Log.e("Async", "error");
                }
                return null;
            }
        };

        runAsyncTask(task);
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

}
