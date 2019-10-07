package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ming.slowly.R;

import java.util.ArrayList;
import java.util.List;

public class main_fragment extends Fragment {
    TabLayout tablayout;

    ViewPager tabViewpager;

    private List<Fragment> mFragmentArrays = new ArrayList<>();
    private List<String> mTabs = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment_layout,container,false);
        tablayout=view.findViewById(R.id.tabs);
        tabViewpager=view.findViewById(R.id.tab_viewpager);
        initView(view);
        return view;
    }
    private void initView(View view) {


        tablayout.removeAllTabs();
        tabViewpager.removeAllViews();
        if (mFragmentArrays != null) {
            mFragmentArrays.clear();
            mTabs.clear();
        }
        //替换成从服务器接口请求数据就成动态了
        mTabs.add("专注");
        mTabs.add("睡眠");
        mTabs.add("呼吸");
        mFragmentArrays.add(new Fragment1());
        mFragmentArrays.add(new Fragment2());
        mFragmentArrays.add(new Fragment3());
//        //动态添加Fragment
//        for (int i = 0; i < mTabs.size(); i++) {
//
//            Fragment fragment = new Fragment1();
//            Bundle bundle = new Bundle();
//            bundle.putInt("position", i);
//            fragment.setArguments(bundle);
//            mFragmentArrays.add(fragment);
//        }

        tabViewpager.setAdapter(new FragAdapter(getFragmentManager(), mFragmentArrays, mTabs));
        tablayout.setupWithViewPager(tabViewpager);

    }

}
