package com.ming.slowly;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class Statistics_huxi extends AppCompatActivity {
    ImageView close;
    TextView shiyong_cishu;
    TextView zongji_huxicishu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breathstatistics);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
        close=findViewById(R.id.close13);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView shiyong_tianshu =(TextView)this.findViewById(R.id.text_days);
        shiyong_cishu=(TextView)this.findViewById(R.id.text_times);

        zongji_huxicishu=(TextView)this.findViewById(R.id.zongjihuxicishu2);
        register();
//        Button button=findViewById(R.id.test3);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                register();
//
//
//            }
//        });

    }
    private void register() {
        // 创建请求体对象
        CommonRequest request = new CommonRequest();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(getApplicationContext());
        String id = (String) spu.getParam("userid","1");

        // 填充参数
        request.addRequestParam("uid",id);

        // POST请求
        HttpUtil.sendPost(Consts.URL_getBreath, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonResponse res = new CommonResponse(response.body().string());
                SharedPreferencesUtil spu = new SharedPreferencesUtil(getApplicationContext());
//                showResponse(res.getResbreAll());

                shiyong_cishu.setText(res.getResAll());
                zongji_huxicishu.setText(res.getResbreAll());


            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                showResponse("Network ERROR");
            }
        });
    }
    private void showResponse(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
