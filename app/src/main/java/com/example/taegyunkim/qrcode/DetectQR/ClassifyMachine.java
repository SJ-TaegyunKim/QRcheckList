package com.example.taegyunkim.qrcode.DetectQR;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.CheckTable;
import com.example.taegyunkim.qrcode.Etc.SubView;
import com.example.taegyunkim.qrcode.Etc.Singleton;
import com.example.taegyunkim.qrcode.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ClassifyMachine extends AppCompatActivity
{
    // Azure 변수
    public MobileServiceClient mClient;
    public MobileServiceTable<CheckTable> mCheckTable;

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
        textView = (TextView)findViewById(R.id.machineName);
        sRadio = (RadioButton)findViewById(R.id.checkO);
        fRadio = (RadioButton)findViewById(R.id.checkX);
        submit = (Button)findViewById(R.id.submit);

        textView.setText(machineName); // 인텐트에서 넘어온 값 전달

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
                if(checkNonPass == false){ // 싱글톤 사용하지 않으면
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

                if (sRadio.isChecked()) { // sRadio 체크 되어있을 때

                    reasonForNonPass = null;                           // DB NotNULL 이면 아무 문자열이라도 바꿀 것.
                    updateItem(machineName, 1, reasonForNonPass);

                } else { // fRadio 체크 되어있을 때
                    editText = (EditText) findViewById(R.id.checkFailExplain);
                    reasonForNonPass = editText.getText().toString();; // Non-Pass 이유 넣기
                    updateItem(machineName, 0, reasonForNonPass);
                }
            }
        });
    }

    public void updateItem(String type, int value, String remarks)
    {
        final CheckTable item = new CheckTable();

        if (mClient == null) {
            return;
        }

        item.setId(Singleton.getInstance().getId());

        // Set the item and update it in the table
        switch(type)
        {
            case "hwaehwa_left" :
                item.setHwaehwa_left(value);
                item.setHwaehwa_left_marks(remarks);
                break;
            case "hwaehwa_right" :
                item.setHwaehwa_right(value);
                item.setHwaehwa_right_marks(remarks);
                break;
            case "hwaehwa_kildal" :
                item.setHwaehwa_kildal(value);
                item.setHwaehwa_kildal_marks(remarks);
                 break;
            case "hotplate_hwaehwaside" :
                item.setHotplate_hwaehwaside(value);
                item.setHotplate_hwaehwaside_marks(remarks);
               break;
            case "hotplate_jaedang_left" :
                item.setHotplate_jaedang_left(value);
                item.setHotplate_jaedang_left_marks(remarks);
                break;
            case "hotplate_jaedang_right" :
                item.setHotplate_jaedang_right(value);
                item.setHotplate_jaedang_right_marks(remarks);
                break;
            case "hotplate_jeonboon_6" :
                item.setHotplate_jeonboon_6(value);
                item.setHotplate_jeonboon_6_marks(remarks);
                break;
            case "waterbath_chungsin" :
                item.setWaterbath_chungsin(value);
                item.setWaterbath_chungsin_marks(remarks);
                break;
            case "waterbath_advantec" :
                item.setWaterbath_advantec(value);
                item.setWaterbath_advantec_marks(remarks);
                break;
            case "waterbath_gagong" :
                item.setWaterbath_gagong(value);
                item.setWaterbath_gagong_marks(remarks);
                break;
            case "aas" :
                item.setAas(value);
                item.setAas_marks(remarks);
                break;
            case "autoclave" :
                item.setAutoclave(value);
                item.setAutoclave_marks(remarks);
                break;
            case "flammable" :
                item.setFlammable(value);
                item.setFlammable_marks(remarks);
                break;
            default:
                break;
        }

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    checkItemInTable(item);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 데이터 수정 후 UI 작업
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

    /**
     * Mark an item as completed in the Mobile Service Table
     *
     * @param item
     *            The item to mark
     */
    public void checkItemInTable(CheckTable item) throws ExecutionException, InterruptedException {
        mCheckTable.update(item).get();
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
