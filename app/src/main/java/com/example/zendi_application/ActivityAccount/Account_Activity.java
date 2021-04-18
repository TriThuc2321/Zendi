package com.example.zendi_application.ActivityAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.zendi_application.R;
import com.example.zendi_application.ViewPagerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;

public class Account_Activity extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_);

        mAuth = FirebaseAuth.getInstance();
        addFragment(new LoginRegisterFragment(this), false);
    }
    private void OnBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left_account,R.anim.slide_to_right_account);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){

        }
    }

    public void addFragment(Fragment fragment, boolean isBackStack) {

        FragmentManager fmgr = getSupportFragmentManager();

        FragmentTransaction ft = fmgr.beginTransaction();

        ft.add(R.id.mFragment, fragment);

        if(isBackStack) ft.addToBackStack(fragment.getClass().getSimpleName());

        ft.commit();

    }



}