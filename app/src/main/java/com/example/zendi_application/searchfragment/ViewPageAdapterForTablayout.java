package com.example.zendi_application.searchfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapterForTablayout extends FragmentStatePagerAdapter {
    private List<Fragment> FragmentList = new ArrayList<Fragment>();
    private List<String> TitleList = new ArrayList<>();
    public ViewPageAdapterForTablayout(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public ViewPageAdapterForTablayout(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getCount() {
        return  FragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TitleList.get(position);
    }

    public void addFragment(Fragment fragment,String title)
    {
        FragmentList.add(fragment);
        TitleList.add(title);
    }
}
