package com.example.zendi_application.searchfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.zendi_application.searchfragment.kids_fragmet.KidsFragment;
import com.example.zendi_application.searchfragment.men_fragment.MenFragment;
import com.example.zendi_application.searchfragment.women_fragment.WomenFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapterForTablayout extends FragmentStatePagerAdapter {
//    private List<Fragment> FragmentList = new ArrayList<Fragment>();
//    private List<String> TitleList = new ArrayList<>();
    public ViewPageAdapterForTablayout(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0: return new MenFragment();
            case 1: return new WomenFragment();
            default: return new KidsFragment();
        }
    }

    @Override
    public int getCount() {
        //return  FragmentList.size();
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return TitleList.get(position);
        switch (position)
        {
            case 0: return "MEN";
            case 1: return  "WOMEN";
            default:return "KIDS";
        }
    }

    public void addFragment(Fragment fragment,String title)
    {
//        FragmentList.add(fragment);
//        TitleList.add(title);

    }
}
