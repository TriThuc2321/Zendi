package com.example.zendi_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeScreen extends AppCompatActivity {
    AppBarLayout appBarLayout;
    MaterialToolbar mAppBarTop;
    BottomNavigationView mNavigationView;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
        // test
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World222!");
        // Hooks
        mAppBarTop = findViewById(R.id.topAppBar);
        appBarLayout = findViewById(R.id.appbarlayout);
        mAppBarTop.setTitle("DROPS"+ "");
        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_paper);
        mNavigationView.setBackgroundColor(Color.WHITE);

        setUpViewPager();

        mAppBarTop.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.account) {
                    Intent intent = new Intent(HomeScreen.this, Account_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
                }
                return true;
            }
        });
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_drops:
                        mViewPager.setCurrentItem(0);

                        break;
                    case R.id.action_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_wishlist:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_shop:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }
    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_drops).setChecked(true);
                        mAppBarTop.setTitle("DROPS"+ "");
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                        mAppBarTop.setTitle("SHOP"+ "");
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_wishlist).setChecked(true);
                        mAppBarTop.setTitle("WISHLIST"+ "");
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.action_shop).setChecked(true);
                        mAppBarTop.setTitle("SHOPPING BAG" + "");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}