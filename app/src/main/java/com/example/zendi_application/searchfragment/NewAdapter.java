package com.example.zendi_application.searchfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.zendi_application.searchfragment.men_fragment.MenFragment;
import com.example.zendi_application.searchfragment.women_fragment.WomenFragment;

import org.jetbrains.annotations.NotNull;

public class NewAdapter extends FragmentStateAdapter {
    public NewAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0: return new MenFragment();
            default:return new WomenFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
