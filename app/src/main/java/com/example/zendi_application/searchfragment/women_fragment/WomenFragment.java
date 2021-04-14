package com.example.zendi_application.searchfragment.women_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.men_fragment.MenFragment;

public class WomenFragment extends Fragment {
    public static WomenFragment getInstance()
    {
        WomenFragment womenFragment = new WomenFragment();
        return womenFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_women, container, false);
        return view;
    }
}
