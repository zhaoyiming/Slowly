package com.ming.slowly;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class Statistics_zhuanzhu extends AppCompatActivity {
    ImageView close;
    Button button222;
    String s1="";
    String s2="";
    String s3="";
    TextView shiyong_tianshu;
    TextView shiyong_cishu;
    TextView zhuanzhu_shijian;
    TextView zhuanzhu_cishu;
    TextView zongji_zhuanzhu_shijian;
    TextView zongji_zhuanzhu_cishu;
    Handler handler;
    CommonResponse res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concentratestatistics);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }


        close=findViewById(R.id.close11);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shiyong_tianshu =(TextView)this.findViewById(R.id.text_days);
        shiyong_cishu=(TextView)this.findViewById(R.id.text_times);
//        zhuanzhu_shijian=(TextView)this.findViewById(R.id.zhuanzhushijian1);
        zhuanzhu_cishu =(TextView)this.findViewById(R.id.zhuanzhucishu1);
//        zongji_zhuanzhu_shijian=(TextView)this.findViewById(R.id.zhuanzhushijian2);
        zongji_zhuanzhu_cishu =(TextView)this.findViewById(R.id.zongjizhuanzhucishu2);

        register();
//        shiyong_cishu.setText(res.getResAll());
//        zhuanzhu_shijian.setText(res.getResAbsdata()+" min");
//        zhuanzhu_cishu.setText(res.getResAbsdata());
//        zongji_zhuanzhu_shijian.setText(res.getResAbsAll()+" min");
//        zongji_zhuanzhu_cishu.setText(res.getResAbsAll());





    }
    private void register() {
        // 创建请求体对象
        CommonRequest request = new CommonRequest();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(getApplicationContext());
        String id = (String) spu.getParam("userid","1");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String t=format.format(new Date());
        // 填充参数
        request.addRequestParam("uid",id);
        request.addRequestParam("atime",t);
        // POST请求
        HttpUtil.sendPost(Consts.URL_getAbsorb, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = new CommonResponse(response.body().string());

//                showResponse(res.getResAbsdata());
                shiyong_cishu.setText(res.getResAll());
//                zhuanzhu_shijian.setText(res.getResAbsdata());
                zhuanzhu_cishu.setText(res.getResAbsdata());
//                zongji_zhuanzhu_shijian.setText(res.getResAbsAll());
                zongji_zhuanzhu_cishu.setText(res.getResAbsAll());


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
