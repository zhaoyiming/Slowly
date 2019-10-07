package com.ming.slowly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class Start_interface extends AppCompatActivity {
    private KenBurnsView kenBurnsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_interface);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.start);
        relativeLayout.setSystemUiVisibility(View.INVISIBLE);// 隐藏状态栏

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }//透明虚拟键

        kenBurnsView=(KenBurnsView)findViewById(R.id.start_animation);
        kenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener(){
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                startActivity(new Intent(Start_interface.this,MainActivity.class));
                finish();
            }
        });
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000,linearInterpolator);
        kenBurnsView.setTransitionGenerator(generator);
    }
}
