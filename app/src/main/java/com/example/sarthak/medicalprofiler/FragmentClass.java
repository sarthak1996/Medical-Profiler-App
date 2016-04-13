package com.example.sarthak.medicalprofiler;


/*
Class that sets up the tab layout using Fragment_One class
 */

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.sarthak.medicalprofiler.Fragments.Fragment_One;
import com.example.sarthak.medicalprofiler.Fragments.Fragment_Three;
import com.example.sarthak.medicalprofiler.Fragments.Fragment_Two;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak on 1/23/2016.
 */
public class FragmentClass{
    public FragmentClass(){

    }
    public void setupViewPager(ViewPager viewPager,FragmentManager fragmentManager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(new Fragment_One(), "Mood");
        adapter.addFragment(new Fragment_Two(), "Reminders for medication");
        adapter.addFragment(new Fragment_Three(), "Report Details");
        viewPager.setAdapter(adapter);
    }
    public void setTabIcons(TabLayout tabLayout,int position,int tabIcon){
        tabLayout.getTabAt(position).setIcon(tabIcon);

    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        /*
        return null for only icon
         */
        //return mFragmentTitleList.get(position);
        return null;
    }
}