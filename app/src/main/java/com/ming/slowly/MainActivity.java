package com.ming.slowly;

import android.content.Intent;
import android.graphics.Color;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.ryan.rv_gallery.AnimManager;
import com.ryan.rv_gallery.GalleryRecyclerView;


import java.lang.*;

import java.util.ArrayList;
import java.util.List;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;
import fragment.FragAdapter;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.ViewPagerAdapter;
import fragment.individual_fragment;
import fragment.main_fragment;
import fragment.news_fragment;


/**
 * Created by 鸣 on 2018/1/22.
 */

public class MainActivity extends AppCompatActivity  {
    private EditText editText;
    public MainActivity mainActivity;
    private DrawerLayout mDrawerLayout;

    private GalleryRecyclerView recyclerView;

    int nowp=0;


    private TextView mCustomTextView;

    private ViewPager mViewPager;
    private TabLayout mTab;
    private List<Fragment> fragments;
    private Fragment [] fragments_bottom;
    private List<String> titleList;
    private Fragment []mFragments;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment_news;
    private Fragment fragment_individual;
    private MenuItem menuItem;
    protected void onCreate(Bundle savedInstanceStace){
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_main);//homepage



        initView();



//        new MaterialIntroView.Builder(this)
//                .enableDotAnimation(true)
//                .enableIcon(false)
//                .setFocusGravity(FocusGravity.CENTER)
//                .setFocusType(Focus.MINIMUM)
//                .setTargetPadding(1)
//                .setInfoTextSize(20)
//                .setDelayMillis(500)
//                .enableFadeAnimation(true)
//                .performClick(true)
//                .setInfoText("選擇你想要進行的項目.")
//                .setShape(ShapeType.CIRCLE)
//                .setTarget(recyclerView)
//                .setUsageId("intro_item") //THIS SHOULD BE UNIQUE ID
//                .show();
//
//        new MaterialIntroView.Builder(this)
//                .enableDotAnimation(true)
//                .enableIcon(false)
//                .setTargetPadding(1)
//                .setInfoTextSize(20)
//                .setFocusGravity(FocusGravity.CENTER)
//                .setFocusType(Focus.MINIMUM)
//                .setDelayMillis(500)
//                .enableFadeAnimation(true)
//                .performClick(true)
//                .setInfoText("Hi There! 點擊條目選擇你喜歡的一種白噪聲.")
//                .setShape(ShapeType.CIRCLE)
//                .setTarget(recyclerView1)
//                .setUsageId("intro_baizaosheng") //THIS SHOULD BE UNIQUE ID
//                .show();
//
//        recyclerView
//                // 设置滑动速度（像素/s）
//                .initFlingSpeed(9000)
//                // 设置页边距和左右图片的可见宽度，单位dp
//                .initPageParams(-10, 40)
//                // 设置切换动画的参数因子
//                .setAnimFactor(0.1f)
//                // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
//                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
//                .setOnItemClickListener(new GalleryRecyclerView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int i) {
//                        switch (i){
//                            case 0:
//                                Intent intent= new Intent(MainActivity_login.this,PlayActivity.class);
//                                intent.putExtra("music",nowp);
//                                startActivity(intent);
//                                break;
//                            case 1:
//                                Intent intent1= new Intent(MainActivity_login.this,Play2Activity.class);
//                                intent1.putExtra("music",nowp);
//                                startActivity(intent1);
//                                break;
//                            case 2:
//                                Intent intent2= new Intent(MainActivity_login.this,Play3Activity.class);
//                                startActivity(intent2);
//                                break;
//                        }
//                    }
//                })
//                // 设置初始化的位置
//                .initPosition(0)
//                // 在设置完成之后，必须调用setUp()方法
//                .setUp();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = getWindow().getDecorView();
            //重点：SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }//状态栏透明

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);    //Toolbar

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.cata);
        }// R.id.home按钮逻辑



//        NavigationView navigationView=findViewById(R.id.nav_view);
//        navigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_menu_text_color, null));
//        navigationView.setItemIconTintList(getResources().getColorStateList(R.color.nav_menu_text_color, null));
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.people:
//
//                        startActivity(intent);
//                        break;
//                    case R.id.language:
//                        Intent intent1=new Intent(MainActivity_login.this,changeLanguage.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.nav_setting:
//                        Toast.makeText(MainActivity_login.this,"Sorry!no setting.",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.rili:
//                        break;
//                    default:
//                        mDrawerLayout.closeDrawers();
//                }
//
//                return true;
//            }
//        });//nav menu逻辑










    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (recyclerView != null) {
            // 释放资源
            recyclerView.release();
        }
    }

    private int getPosition(int mConsumeX, int shouldConsumeX) {
        float offset = (float) mConsumeX / (float) shouldConsumeX;
        int position = Math.round(offset);        // 四捨五入獲取位置
        return position;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }//toolbar menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                break;

            case R.id.down:
//                Toast.makeText(getApplicationContext(),"You Clicked Down",Toast.LENGTH_SHORT).
//                        show();
                break;
            default:
        }
        return true;
    }


    private void initView() {

//        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
//        BottomNavigationViewHelper.disableShiftMode(navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager=findViewById(R.id.pager);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        adapter.addFragment(new main_fragment());
        adapter.addFragment(new news_fragment());
        adapter.addFragment(new individual_fragment());

        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);


        mViewPager.setOffscreenPageLimit(3);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item1:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.item2:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.item3:
                        mViewPager.setCurrentItem(2);
                        return true;

                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        mViewPager.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }



}
