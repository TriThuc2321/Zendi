package com.example.zendi_application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.zendi_application.searchfragment.SearchFragment;
import com.example.zendi_application.searchfragment.listnavigation.Fragment_for_list;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DropFragment();
            case 1: return new SearchFragment();
            case 2: return new WishlistFragment();
            default: return new ShopFragment();
            //default: return shopFragment;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
