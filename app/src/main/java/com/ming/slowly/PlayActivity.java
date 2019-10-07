package com.ming.slowly;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.freedom.lauzy.playpauseviewlib.PlayPauseView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.abdolahi.CircularMusicProgressBar;
import okhttp3.Call;
import okhttp3.Response;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    CountDownTime countDown;
    CircularMusicProgressBar circularMusicProgressBar;
    RelativeLayout relativeLayout;
    TextView textView;
    PlayPauseView pauseView;
    ImageView imageView;
    boolean isPause=false;
    CardView cardView;
    private TextView recycletext;
    private ImageView recycleimage;
    private RecyclerView recyclerView1;
    private List<Recycle_item> list1=new ArrayList<>();
    private List<Recycle_item> listslide=new ArrayList<>();

    long curtime=0;

    int allSecond=1500;
    int nowp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent=getIntent();
        nowp=intent.getIntExtra("music",0);
        textView=findViewById(R.id.stime);
        imageView=findViewById(R.id.close);
        pauseView=findViewById(R.id.pausebutton);
        cardView=findViewById(R.id.playmusic);
         initdata();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }


       pauseView.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
           @Override
           public void play() {
               if(mediaPlayer!=null){
                   mediaPlayer.start();
               }
               if(curtime!=0 && isPause){
                   CountDown((int)curtime);
                   countDown.start();
                   isPause=false;
               }
           }

           @Override
           public void pause() {
               if(mediaPlayer!=null){
                   mediaPlayer.pause();
               }
               if(!isPause){
                   isPause=true;
                   countDown.cancel();
               }
           }
       });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(PlayActivity.this);
                builder.setTitle("确定退出本次专注嗎？");
                builder.setMessage("退出后本次专注即将失败！");
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
        circularMusicProgressBar=findViewById(R.id.album_art);
        CardView cardView=findViewById(R.id.playmusic);
        cardView.setOnClickListener(this);
        CountDown(1500000);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playmusic:
                register();

                if(mediaPlayer==null){
                    switch (nowp){
                        case 1:
                            mediaPlayer = MediaPlayer.create(this, R.raw.chongming);
                            break;
                        case 2:
                            mediaPlayer = MediaPlayer.create(this, R.raw.sea);
                            break;
                        case 3:
                            mediaPlayer = MediaPlayer.create(this, R.raw.mingxiang);
                            break;
                        case 4:
                            mediaPlayer = MediaPlayer.create(this, R.raw.shenlin);
                            break;
                        case 5:
                            mediaPlayer = MediaPlayer.create(this, R.raw.taikong);
                            break;
                        default:
                            mediaPlayer = MediaPlayer.create(this, R.raw.rain);
                            break;
                    }

                    mediaPlayer.setLooping(true);
                    countDown.start();
                    mediaPlayer.start();
                    pauseView.callOnClick();
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

        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //倒计时显示操作
            second = millisUntilFinished / 1000;
            textView.setText(getMinuteBySecond((int) second));
            // 进度条实现更新操作
            second = (allSecond - second) / allSecond * 100;
            //
            circularMusicProgressBar.setValue((int) second);
            // 歌词更新操作
            second = allSecond * 1000 - millisUntilFinished;

            curtime=millisUntilFinished;
            // Log.d("MaskMusic", "geci  : "+(long)second);
            // lrcplaytoend.playToPause((long)
            // (allSecond*1000-millisUntilFinished));
        }

        @Override
        public void onFinish() {
            circularMusicProgressBar.setValue(0);
            textView.setText(getMinuteBySecond((int) allSecond));
            allSecond = 0;
            if(mediaPlayer!=null){
                mediaPlayer.stop();
            }
            finish();
        }

    }
    private void CountDown(int allTime) {
        countDown = new CountDownTime(allTime, 1000);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        countDown.cancel();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(PlayActivity.this);
        builder.setTitle("确定退出本次专注嗎？");
        builder.setMessage("退出后本次专注即将失败！");
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
        if(mediaPlayer!=null){
            mediaPlayer.start();
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

        recyclerView1=findViewById(R.id.recycle_1);
        recycletext=findViewById(R.id.recycle_view_text);
        recycleimage=findViewById(R.id.recycle_view_image);

        LinearLayoutManager m=new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(m);

        RecycleAdapter1 recycleAdapter1=new RecycleAdapter1(list1);
        recycleAdapter1.setOnItemClickListener(new RecycleAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mediaPlayer==null){
                    nowp = position;
                }else {
                    nowp = position;
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer = null;

                    }
                    if (countDown != null) {
                        countDown.cancel();
                        allSecond = 0;
                    }
                    if(mediaPlayer==null){
                        switch (nowp){
                            case 1:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.chongming);
                                break;
                            case 2:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.sea);
                                break;
                            case 3:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.mingxiang);
                                break;
                            case 4:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.shenlin);
                                break;
                            case 5:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.taikong);
                                break;
                            default:
                                mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.rain);
                                break;
                        }

                        mediaPlayer.setLooping(true);
                        if(curtime!=0){
                            CountDown((int)curtime);
                            countDown.start();
                        }
                circularMusicProgressBar.setValue(0);
                        mediaPlayer.start();

                    }
                }

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
        HttpUtil.sendPost(Consts.URL_Absorb, request.getJsonStr(), new okhttp3.Callback() {
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
