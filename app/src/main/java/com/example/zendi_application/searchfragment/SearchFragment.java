package com.example.zendi_application.searchfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zendi_application.HomeScreen;
import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.kids_fragmet.KidsFragment;
import com.example.zendi_application.searchfragment.men_fragment.MenFragment;
import com.example.zendi_application.searchfragment.women_fragment.WomenFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;

    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.Adapter mAdapter3;

    private RecyclerView.LayoutManager mLayoutManager2;
    private RecyclerView.LayoutManager mLayoutManager3;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);



        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        ViewPageAdapterForTablayout viewPageAdapterForTablayout = new ViewPageAdapterForTablayout(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPageAdapterForTablayout.addFragment(MenFragment.getInstance(),"Men");
        viewPageAdapterForTablayout.addFragment(WomenFragment.getInstance(),"Women");
        viewPageAdapterForTablayout.addFragment(KidsFragment.getInstance(),"Kids");
        viewPager.setAdapter(viewPageAdapterForTablayout);
        tabLayout.setupWithViewPager(viewPager);


        //((HomeScreen) getActivity()).mViewPager
        

        return view;
    }

}
