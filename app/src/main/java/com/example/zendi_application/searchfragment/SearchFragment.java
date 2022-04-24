package com.example.zendi_application.searchfragment;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zendi_application.R;
import com.google.android.material.tabs.TabLayout;


public class SearchFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private RecyclerView rcvForSuggest;
    private AdapterForSuggestion adapterForSuggestion;
    private SearchView searchView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        ViewPageAdapterForTablayout viewPageAdapterForTablayout = new ViewPageAdapterForTablayout(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPageAdapterForTablayout);
        tabLayout.setupWithViewPager(viewPager);

        rcvForSuggest = view.findViewById(R.id.suggest_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvForSuggest.setLayoutManager(linearLayoutManager);

        adapterForSuggestion = new AdapterForSuggestion();
        rcvForSuggest.setAdapter(adapterForSuggestion);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvForSuggest.addItemDecoration(itemDecoration);

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterForSuggestion.filter(newText);
                return false;
            }
        });
        return view;
    }

}
