package com.example.administrator.breatheview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BreatheView brv = (BreatheView) findViewById(R.id.brv);
        brv.setInterval(2000) //设置闪烁间隔时间
                .setCoreRadius(5f)//设置中心圆半径
                .setDiffusMaxWidth(300f)//设置闪烁圆的最大半径
                .setDiffusColor(Color.parseColor("#0cf465"))//设置闪烁圆的颜色
                .setCoreColor(Color.parseColor("#f40c3a"))//设置中心圆的颜色
                .onStart();
    }
}
