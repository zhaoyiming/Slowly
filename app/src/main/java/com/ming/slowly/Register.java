package com.ming.slowly;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    private Button registerBtn;
    private EditText cancel;
    private EditText accountText;
    private EditText pwdText;
    private EditText register_email;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        setListeners();
    }
    void initComponents(){
        registerBtn = (Button) findViewById(R.id.Register);
        cancel = (EditText) findViewById(R.id.back);
        accountText = (EditText) findViewById(R.id.register_account);
        pwdText = (EditText) findViewById(R.id.register_pwd);
        register_email = (EditText) findViewById(R.id.register_email);
        progressDialog = new ProgressDialog(Register.this);
    }

    void setListeners(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     *  POST方式Register
     */
    private void register() {
        // 创建请求体对象
        CommonRequest request = new CommonRequest();

        // 前端参数校验，防SQL注入
        String account = Util.StringHandle(accountText.getText().toString());
        String pwd = Util.StringHandle(pwdText.getText().toString());
        String email = Util.StringHandle(register_email.getText().toString());

        // 检查数据格式是否正确
        String resMsg = checkDataValid(account,pwd,email);
        if(!resMsg.equals("")){
            showResponse(resMsg);
            return;
        }

        // 填充参数
        request.addRequestParam("username",account);
        request.addRequestParam("password",pwd);
        request.addRequestParam("mail",email);

        // POST请求
        HttpUtil.sendPost(Consts.URL_Register, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                String  returndata=response.message();
//                if (returndata.equals("1")){
//                    showResponse("success");
//                    finish();
//                }else{
//                    showResponse("fail");
//                }
                CommonResponse res = new CommonResponse(response.body().string());
                String resCode = res.getResCode();
//                String resMsg = res.getResMsg();
                // 显示注册结果

                // 注册成功
//                if (resCode.equals(Consts.SUCCESSCODE_REGISTER)) {
//                            finish();
//
//                }
                if (resCode.equals("1")){
                    showResponse("注册成功");
                    finish();
                }
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
                Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String checkDataValid(String account,String pwd,String email){
        if(TextUtils.isEmpty(account) | TextUtils.isEmpty(pwd) | TextUtils.isEmpty(email))
            return getResources().getString(R.string.null_hint);
        if(!email.contains("@"))
            return getResources().getString(R.string.not_email);
//        if(!Pattern.compile("[1][358]\\d{9}") .matcher(account).matches())
//            return getResources().getString(R.string.account_invalid_hint);
        return "";
    }
}


