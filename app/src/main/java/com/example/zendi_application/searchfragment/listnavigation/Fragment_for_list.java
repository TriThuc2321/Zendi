package com.example.zendi_application.searchfragment.listnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.ViewPageAdapterForTablayout;
import com.example.zendi_application.searchfragment.kids_fragmet.KidsFragment;
import com.example.zendi_application.searchfragment.men_fragment.MenFragment;
import com.example.zendi_application.searchfragment.women_fragment.WomenFragment;

public class Fragment_for_list extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_listnavigation, container, true);
        return view;
    }
}
