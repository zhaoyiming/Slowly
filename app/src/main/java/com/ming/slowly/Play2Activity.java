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


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.freedom.lauzy.playpauseviewlib.PlayPauseView;


import info.abdolahi.CircularMusicProgressBar;
import okhttp3.Call;
import okhttp3.Response;

public class Play2Activity extends AppCompatActivity implements View.OnClickListener {
    Calendar curDate=Calendar.getInstance();
    private AlarmManager alarmManager;
    private MediaPlayer mediaPlayer1;
    CountDownTime countDown1;
    CircularMusicProgressBar circularMusicProgressBar1;
    TextView textView1;
    PlayPauseView pauseView1;
    ImageView imageView1;
    boolean isPause=false;
    long curtime=0;
    int nowp;
    int allSecond1=1500;

    private TextView recycletext;
    private ImageView recycleimage;
    private RecyclerView recyclerView1;
    private List<Recycle_item> list1=new ArrayList<>();
    private List<Recycle_item> listslide=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);
        Intent intent=getIntent();
        nowp=intent.getIntExtra("music",0);
        textView1=findViewById(R.id.stime1);
        imageView1=findViewById(R.id.close1);
        pauseView1=findViewById(R.id.pausebutton1);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        initdata();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
        pauseView1.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                if(mediaPlayer1!=null){
                    mediaPlayer1.start();
                }
                if(curtime!=0 && isPause){
                    CountDown((int)curtime);
                    countDown1.start();
                    isPause=false;
                }
            }

            @Override
            public void pause() {
                if(mediaPlayer1!=null){
                    mediaPlayer1.pause();
                }
                if(!isPause){
                    isPause=true;
                    countDown1.cancel();
                }
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Play2Activity.this);
                builder.setTitle("确定要退出睡眠嗎？");
                builder.setMessage("退出后本次睡眠即将失败！");
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
        circularMusicProgressBar1=findViewById(R.id.album_art1);
        CardView cardView1=findViewById(R.id.playmusic1);
        cardView1.setOnClickListener(this);
        CountDown(4500000);


    }
    public  void  alarmOne(View view){
        //获取当前系统的时间
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        //弹出时间对话框
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Calendar c=Calendar.getInstance();
                curDate=c;
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);

                //时间一到，发送广播（闹钟响了）
                Intent intent=new Intent();
                intent.setAction("com.ming.slowly.RING");
                //将来时态的跳转
                PendingIntent pendingIntent=PendingIntent.getBroadcast(Play2Activity.this,0x101,intent,0);
                //设置闹钟
                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playmusic1:
                if(mediaPlayer1==null){
                    switch (nowp){
                        case 1:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.chongming);
                            break;
                        case 2:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.sea);
                            break;
                        case 3:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.mingxiang);
                            break;
                        case 4:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.shenlin);
                            break;
                        case 5:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.taikong);
                            break;
                        default:
                            mediaPlayer1 = MediaPlayer.create(this, R.raw.rain);
                            break;
                    }
                    register();
                    alarmOne(pauseView1);
                    mediaPlayer1.setLooping(true);
                    countDown1.start();
                    mediaPlayer1.start();
                    pauseView1.callOnClick();
//                    Calendar now=Calendar.getInstance();
//                    long miao = (curDate.getTimeInMillis() - now.getTimeInMillis())/1000;
//                    int day = (int)(miao / (60*60*24));             //天
//                    int hour = (int)(miao % (60*60*24) / (60*60));  //时
//                    int mm = (int)(miao % (60*60) / 60);            //分
//                    int sec = (int)(miao % 60);                     //秒
//                    textView1.setText(day+"天"+hour+"时"+mm+"分"+sec+"秒");

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

            // 进度条实现更新操作
            second = (allSecond1 - second) / allSecond1 * 100;
            //
            circularMusicProgressBar1.setValue((int) second);
            // 歌词更新操作
            second = allSecond1 * 1000 - millisUntilFinished;

            curtime=millisUntilFinished;
            // Log.d("MaskMusic", "geci  : "+(long)second);
            // lrcplaytoend.playToPause((long)
            // (allSecond*1000-millisUntilFinished));
        }

        @Override
        public void onFinish() {
            circularMusicProgressBar1.setValue(0);
            textView1.setText(getMinuteBySecond((int) allSecond1));
            allSecond1 = 0;
            if(mediaPlayer1!=null){
                mediaPlayer1.stop();
            }
            finish();
        }

    }
    private void CountDown(int allTime) {
        countDown1 = new CountDownTime(allTime, 1000);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer1!=null){
            mediaPlayer1.stop();
        }
        countDown1.cancel();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Play2Activity.this);
        builder.setTitle("确定要退出本次睡眠吗？");
        builder.setMessage("退出后本次睡眠即将失败！");
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
        if(mediaPlayer1!=null){
            mediaPlayer1.start();
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

        recyclerView1=findViewById(R.id.recycle_2);
        recycletext=findViewById(R.id.recycle_view_text);
        recycleimage=findViewById(R.id.recycle_view_image);

        LinearLayoutManager m=new LinearLayoutManager(this);
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(m);

        RecycleAdapter1 recycleAdapter1=new RecycleAdapter1(list1);
        recycleAdapter1.setOnItemClickListener(new RecycleAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mediaPlayer1==null){
                    nowp = position;
                }
                else {
                    nowp = position;
                    if (mediaPlayer1 != null) {
                        mediaPlayer1.stop();
                        mediaPlayer1 = null;

                    }
                    if (countDown1 != null) {
                        countDown1.cancel();
                        allSecond1 = 0;
                    }
                    if(mediaPlayer1==null){
                        switch (nowp){
                            case 1:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.chongming);
                                break;
                            case 2:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.sea);
                                break;
                            case 3:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.mingxiang);
                                break;
                            case 4:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.shenlin);
                                break;
                            case 5:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.taikong);
                                break;
                            default:
                                mediaPlayer1 = MediaPlayer.create(Play2Activity.this, R.raw.rain);
                                break;
                        }

                        mediaPlayer1.setLooping(true);
                        if(curtime!=0){
                            CountDown((int)curtime);
                            countDown1.start();
                        }
                        mediaPlayer1.start();

                    }
                }
            }
        });
        recyclerView1.setAdapter(recycleAdapter1);




    }
    private void register() {
        // 创建请求体对象
        CommonRequest request = new CommonRequest();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(getApplicationContext());
        String id = (String) spu.getParam("userid","1");
        String starttime="2019-05-19 11:43";
        String endtime="2019-05-20 6:55";
        String sleeptime="7h12min";
        // 填充参数
        request.addRequestParam("uid",id);
        request.addRequestParam("sleeptime",sleeptime);
        request.addRequestParam("test5",starttime);
        request.addRequestParam("test6",endtime);


        // POST请求
        HttpUtil.sendPost(Consts.URL_sleep, request.getJsonStr(), new okhttp3.Callback() {
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
