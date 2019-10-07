package com.ming.slowly;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.freedom.lauzy.playpauseviewlib.PlayPauseView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.abdolahi.CircularMusicProgressBar;
import okhttp3.Call;
import okhttp3.Response;

public class Play3Activity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer2;
    CountDownTime countDown2;
    int nowp=0;
    CircularMusicProgressBar circularMusicProgressBar2;
    TextView textView2;
    TextView hu;
    PlayPauseView pauseView2;
    ImageView imageView2;
    boolean isPause=false;
    long curtime=0;
    int allSecond2=60;

    private TextView recycletext;
    private ImageView recycleimage;
    private RecyclerView recyclerView1;
    private List<Recycle_item> list1=new ArrayList<>();
    private List<Recycle_item> listslide=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play3);
        textView2=findViewById(R.id.stime2);
        hu=findViewById(R.id.huxistate);
        imageView2=findViewById(R.id.close2);
        pauseView2=findViewById(R.id.pausebutton2);
        initdata();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
        pauseView2.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                if(mediaPlayer2!=null){
                    mediaPlayer2.start();
                }
                if(curtime!=0 && isPause){
                    CountDown((int)curtime);
                    countDown2.start();
                    isPause=false;
                }
            }

            @Override
            public void pause() {
                if(mediaPlayer2!=null){
                    mediaPlayer2.pause();
                }
                if(!isPause){
                    isPause=true;
                    countDown2.cancel();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Play3Activity.this);
                builder.setTitle("确定要退出吗？");
                builder.setMessage("退出后本次呼吸即将失败！");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
        });
        circularMusicProgressBar2=findViewById(R.id.album_art2);
        CardView cardView2=findViewById(R.id.playmusic2);
        cardView2.setOnClickListener(this);
        CountDown(60000);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playmusic2:
                if(mediaPlayer2==null){
                    register();
                    mediaPlayer2 = MediaPlayer.create(this, R.raw.mingxiang);
                    mediaPlayer2.setLooping(true);
                    countDown2.start();
                    mediaPlayer2.start();
                    pauseView2.callOnClick();
                }


        }

    }
    public static String getMinuteBySecond(int seconds) {

        StringBuffer buffer = new StringBuffer();
        int second = seconds % 60;
        int minute = seconds / 60;

        if (minute <= 9) {
            buffer.append("0" + minute + ":");
        } else {
            buffer.append(minute + ":");
        }
        if (second <= 9) {
            buffer.append("0" + second);
        } else {
            buffer.append(second);
        }
        return buffer.toString();
    }

    private class CountDownTime extends CountDownTimer {

        private double second = 0;
        int huxinum=0;
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //倒计时显示操作

            second = millisUntilFinished / 1000;

            if(huxinum==0){
                hu.setText("吸气");
            }else if(huxinum==4){
                hu.setText("屏气");
            }else if(huxinum==11){
                hu.setText("呼气");
            }else if(huxinum==19){
                hu.setText("吸气");
                huxinum=0;
            }
            huxinum++;
            textView2.setText(getMinuteBySecond((int) second));
            // 进度条实现更新操作
            second = (allSecond2 - second) / allSecond2 * 100;
            //
            circularMusicProgressBar2.setValue((int) second);
            // 歌词更新操作
            second = allSecond2 * 1000 - millisUntilFinished;

            curtime=millisUntilFinished;
            // Log.d("MaskMusic", "geci  : "+(long)second);
            // lrcplaytoend.playToPause((long)
            // (allSecond*1000-millisUntilFinished));
        }

        @Override
        public void onFinish() {
            circularMusicProgressBar2.setValue(0);
            textView2.setText(getMinuteBySecond((int) allSecond2));
            allSecond2 = 0;
            if(mediaPlayer2!=null){
                mediaPlayer2.stop();
            }
            Intent intent=new Intent(Play3Activity.this,finishActivity.class);
            startActivity(intent);
            finish();
        }

    }
    private void CountDown(int allTime) {
        countDown2 = new CountDownTime(allTime, 1000);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer2!=null){
            mediaPlayer2.stop();
        }
        countDown2.cancel();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Play3Activity.this);
        builder.setTitle("确定要退出吗？");
        builder.setMessage("退出后本次专注即將失败！");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        if(mediaPlayer2!=null){
            mediaPlayer2.start();
        }
        super.onResume();
    }
    public void initdata(){
        list1=new ArrayList<>();
        listslide=new ArrayList<>();
        list1.add(new Recycle_item("海洋",R.drawable.sea));
        list1.add(new Recycle_item("虫鸣",R.drawable.chongming));
        list1.add(new Recycle_item("雨夜",R.drawable.yuye));
        list1.add(new Recycle_item("冥想",R.drawable.mingxiang));
        list1.add(new Recycle_item("森林",R.drawable.shenlin));
        list1.add(new Recycle_item("太空",R.drawable.taikong));

        recyclerView1=findViewById(R.id.recycle_3);
        recycletext=findViewById(R.id.recycle_view_text);
        recycleimage=findViewById(R.id.recycle_view_image);

        LinearLayoutManager m=new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(m);

        RecycleAdapter1 recycleAdapter1=new RecycleAdapter1(list1);
        recycleAdapter1.setOnItemClickListener(new RecycleAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                nowp=position;
            }
        });
        recyclerView1.setAdapter(recycleAdapter1);
//        int space = 1;
//        recyclerView1.addItemDecoration(new itemdeo(space));
//        recyclerView1.setNestedScrollingEnabled(false);




    }
    private void register() {
        // 创建请求体对象
        CommonRequest request = new CommonRequest();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(getApplicationContext());
        String id = (String) spu.getParam("userid","1");
        // 填充参数
        request.addRequestParam("uid",id);
        // POST请求
        HttpUtil.sendPost(Consts.URL_breath, request.getJsonStr(), new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
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
