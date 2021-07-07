package com.example.zendi_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.zendi_application.ActivityAccount.Admin.AdminActivity;
import com.example.zendi_application.ActivityAccount.LoginRegisterActivity;
import com.example.zendi_application.ActivityAccount.User;
import com.example.zendi_application.addProductPackage.uploadData;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.zendi_application.DataManager.GetUser;
import static com.example.zendi_application.DataManager.listUsers;

public class HomeScreen extends AppCompatActivity {
    private static final int REQUEST_EXIT = 99;
    public AppBarLayout appBarLayout;
    public MaterialToolbar mAppBarTop;
    public BottomNavigationView mNavigationView;
    public ViewPager mViewPager;
    public DataManager dataManager;


    @Override
    protected void onResume() {
        super.onResume();
        setShopOwner();
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
        //set chủ shop
        dataBase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setShopOwner();
        //set chủ shop

        mAppBarTop.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.account) {

                    Intent intent = new Intent(HomeScreen.this, LoginRegisterActivity.class);
                    startActivityForResult(intent, REQUEST_EXIT);

                    /*Intent intent = new Intent(HomeScreen.this, Location.class);
                    startActivityForResult(intent, REQUEST_EXIT);*/
                    
                    overridePendingTransition(R.anim.slide_from_right_account,R.anim.slide_to_left_account);
                }
                else if(item.getItemId() == R.id.staff_manager_item){
                    listUsers.clear();
                    DataManager.loadUser();
                    Intent intent = new Intent(HomeScreen.this, AdminActivity.class);
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
        //thắng
        if(DataManager.host != null && DataManager.host.getId() != null)
        {
            DataManager.shoeInWish.clear();
            DataManager.getShoeInWishFromFirestone("InWish/"+DataManager.host.getId()+"/ShoeinWish",DataManager.shoeInWish);
        }
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
            //if (resultCode == RESULT_OK) {
                this.finish();
            //}
        }
    }

    private long pressedTime;
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference dataBase;

    void setShopOwner(){
        if(currentUser == null){
            mAppBarTop.getMenu().findItem(R.id.staff_manager_item).setVisible(false);
        }
        else {
            dataBase.child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);

                    if(user.getShopOwner() == 2 || user.getShopOwner() == 1){
                        mAppBarTop.getMenu().findItem(R.id.staff_manager_item).setVisible(true);
                    }
                    else{
                        mAppBarTop.getMenu().findItem(R.id.staff_manager_item).setVisible(false);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}