package com.ming.slowly;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.ming.slowly.CommonResponse;
import com.ming.slowly.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author: Lemon-XQ
 * @date: 2018/2/23
 */

public class Server {
    private Activity mActivity;
    private String resCode;
    private String resMsg;
    private HashMap<String,String> property;
    private ArrayList<HashMap<String,String>> dataList;
    private final long sleepTime = 700L;

    public Server(Activity activity){
        mActivity = activity;
    }

    // ----------------下为User各个属性的获取及设置（服务器端）---------------









    private void infoPost(String url, String json){
        HttpUtil.sendPost(url,json,new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonResponse res = new CommonResponse(response.body().string());
                resCode = res.getResCode();
                resMsg = res.getResMsg();
                property = res.getPropertyMap();
                dataList = res.getDataList();
//                showResponse(resMsg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                showResponse("Network ERROR");
            }
        });
    }

    private void showResponse(final String msg) {
        Log.e("Server",msg);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
