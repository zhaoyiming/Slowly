package com.ming.slowly;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Statistics_shuimian extends AppCompatActivity {
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleepstatistics);
        close=findViewById(R.id.close12);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
        TextView shiyong_tianshu =(TextView)this.findViewById(R.id.text_days);
        TextView shiyong_cishu =(TextView)this.findViewById(R.id.text_times);
        TextView rushui_shijian =(TextView)this.findViewById(R.id.shuimianshijian1);
        TextView xinglai_shijian =(TextView)this.findViewById(R.id.shuimiancishu1);
        TextView shuimian_shijian =(TextView)this.findViewById(R.id.shuimianshijian2);
        TextView zongji_shuimian_cishu =(TextView)this.findViewById(R.id.zongjishuimiancishu2);


    }
}
