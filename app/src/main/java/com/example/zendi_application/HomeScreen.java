package com.example.zendi_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.zendi_application.ActivityAccount.Account_Activity;
import com.example.zendi_application.ActivityAccount.LoginRegisterActivity;
import com.example.zendi_application.dropFragment.DetailDropFragment;
import com.example.zendi_application.dropFragment.drop.drop;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeScreen extends AppCompatActivity {
    private static final int REQUEST_EXIT = 1;
    public AppBarLayout appBarLayout;
    public MaterialToolbar mAppBarTop;
    public BottomNavigationView mNavigationView;
    public ViewPager mViewPager;
    public DataManager dataManager;

    @Override
    protected void onResume() {
        super.onResume();
        this.appBarLayout.setVisibility(View.VISIBLE);
        this.mNavigationView.setVisibility(View.VISIBLE);
        Log.d("MainActivity Lifecycle", "===== onResume =====");
    }
    protected void onStart() {
        super.onStart();
        this.appBarLayout.setVisibility(View.VISIBLE);
        Log.d("MainActivity Lifecycle", "===== onStart =====");
    }
    protected void onRestart() {
        super.onRestart();
        this.appBarLayout.setVisibility(View.VISIBLE);
        Log.d("MainActivity Lifecycle", "===== onRestart =====");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);

        // Hooks
        mAppBarTop = findViewById(R.id.topAppBar);
        appBarLayout = findViewById(R.id.appbarlayout);
        this.appBarLayout.setVisibility(View.VISIBLE);
        mAppBarTop.setTitle("DROPS"+ "");
        mNavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.view_paper);
        mNavigationView.setBackgroundColor(Color.WHITE);

        setUpViewPager();

        mAppBarTop.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.account) {

                    Intent intent = new Intent(HomeScreen.this, LoginRegisterActivity.class);
                    startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }
}