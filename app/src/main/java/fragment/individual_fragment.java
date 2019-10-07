package fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.ming.slowly.Login;
import com.ming.slowly.MainActivity;
import com.ming.slowly.Play2Activity;
import com.ming.slowly.R;
import com.ming.slowly.SharedPreferencesUtil;
import com.ming.slowly.Statistics_huxi;
import com.ming.slowly.Statistics_shuimian;
import com.ming.slowly.Statistics_zhuanzhu;
import com.ming.slowly.islogin;

import java.util.Calendar;


public class individual_fragment extends Fragment {
    private TextView paccount;
    private LinearLayout zhuanzhu;
    private LinearLayout huxi;
    private LinearLayout sleep;
    private CardView zhuxiao;
    private Button tixing;
    private AlarmManager alarmManager;
    private PendingIntent pi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.individual_fragment_layout,container,false);
        SharedPreferencesUtil spu = new SharedPreferencesUtil(getContext());
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        tixing=view.findViewById(R.id.btn_tixing);
        Intent intent = new Intent(getContext(), Login.class);
        pi = PendingIntent.getActivity(getContext(), 0, intent, 0);

        tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                new TimePickerDialog(getContext(), 0,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                //设置当前时间
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                // 根据用户选择的时间来设置Calendar对象
                                c.set(Calendar.HOUR, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                // ②设置AlarmManager在Calendar对应的时间启动Activity
                                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);

                                // 提示闹钟设置完毕:
                                Toast.makeText(getContext(), "提醒设置完毕~",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                        .get(Calendar.MINUTE), false).show();

            }
        });
        zhuxiao=view.findViewById(R.id.exit);
        if(islogin.getA()==1){
            zhuxiao.setVisibility(View.VISIBLE);
        }

        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil sharedPreferencesUtil=new SharedPreferencesUtil(getContext());
                sharedPreferencesUtil.clear();
                islogin.setA(0);
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);

            }
        });
        // SharedPreference获取用户账号密码，存在则填充
        String account = (String) spu.getParam("account","");
        paccount=view.findViewById(R.id.paccount);
        if(account.equals("")){
            paccount.setText("未登录");
        }else
            paccount.setText("欢迎您,用户"+account);

        zhuanzhu=view.findViewById(R.id.btn_zhuanzhutongji);
        zhuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islogin.getA()==1){
                    Intent intent=new Intent(getActivity(),Statistics_zhuanzhu.class);
                    startActivity(intent);
                }else {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                    builder.setTitle("尚未登陆");
                    builder.setMessage("请登陆后再试！");
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }

            }
        });
        huxi=view.findViewById(R.id.btn_huxitongji);
        huxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islogin.getA()==1){
                    Intent intent=new Intent(getActivity(),Statistics_huxi.class);
                    startActivity(intent);
                }else {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                    builder.setTitle("尚未登陆");
                    builder.setMessage("请登陆后再试！");
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }

            }
        });
        sleep=view.findViewById(R.id.btn_shuimiantongji);
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islogin.getA()==1){
                    Intent intent=new Intent(getActivity(),Statistics_shuimian.class);
                    startActivity(intent);
                }else {
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());
                    builder.setTitle("尚未登陆");
                    builder.setMessage("请登陆后再试！");
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }

            }
        });
        return view;
    }
}
