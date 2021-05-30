package com.example.zendi_application.searchfragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.zendi_application.searchfragment.men_fragment.MenFragment;
import com.example.zendi_application.searchfragment.women_fragment.WomenFragment;

public class ViewPageAdapterForTablayout extends FragmentStatePagerAdapter {
    private final int SO_PAGE = 2;
    public ViewPageAdapterForTablayout(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0: return new MenFragment();
            default: return new WomenFragment();
        }
    }

    @Override
    public int getCount() {
        return SO_PAGE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0: return "MEN";
            default:return "WOMEN";
        }
    }
}
