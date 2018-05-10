package com.example.taegyunkim.qrcode.GenerateQR;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taegyunkim.qrcode.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


// QR코드 생성을 위한 클래스
public class GenerateQRcode extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    String content; // 입력 된 문자열 읽기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);
        imageView = (ImageView) findViewById(R.id.iv_generated_qrcode);
        findViewById(R.id.btn_generatorQR).setOnClickListener(new View.OnClickListener() { // generate 버튼 클릭 시
            @Override
            public void onClick(View v) {
                content = ((EditText) findViewById(R.id.edit_QRtext)).getText().toString();
                // UTF-8 변환
                try {
                    content = URLEncoder.encode(content, "UTF-8");
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
                if (content.isEmpty()) {
                    Toast.makeText(GenerateQRcode.this, "문자를 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    generateQRcode(content);
                }
            }
        });
        Button btnSaveQR = (Button)findViewById(R.id.btn_saveQR); // save 버튼 클릭 시
        btnSaveQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (bitmap != null) {
                        String exStoragePath = "/sdcard/";
                        String fileName = content+".jpg";
                        Toast.makeText(getApplicationContext(),exStoragePath,Toast.LENGTH_LONG).show();
                        //경로 잘못됐다.경로 다시 설정할 것.

                        File file = new File(exStoragePath);
                        if(!file.exists()) // 폴더 없으면 생성
                            file.mkdir();

                        try{
                            FileOutputStream out = new FileOutputStream(exStoragePath+fileName);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            out.close();

                            Toast.makeText(getApplicationContext(),"File Save OK",Toast.LENGTH_LONG).show();

                        }catch (FileNotFoundException exception){
                            Log.e("FileNotFoundException", exception.getMessage());
                        }catch (IOException e){
                            Log.e("IOException", e.getMessage());
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Not save BMP",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "file error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void generateQRcode(String contents) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 500, 500));
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }
}
