package fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> titlelist;
    public FragAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titlelist1){
        super(fm);
        this.titlelist=titlelist1;
        mFragments=fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }


    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }
}
