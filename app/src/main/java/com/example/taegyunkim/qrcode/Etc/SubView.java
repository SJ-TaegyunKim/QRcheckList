package com.example.taegyunkim.qrcode.Etc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.taegyunkim.qrcode.R;

public class SubView extends LinearLayout {

    public SubView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public SubView(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_sub_view,this,true);
    }
}
