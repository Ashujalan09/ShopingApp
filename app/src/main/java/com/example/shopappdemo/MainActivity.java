package com.example.shopappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shopappdemo.navItems.CategoryFragment;
import com.example.shopappdemo.navItems.HomeFragment;
import com.example.shopappdemo.navItems.OrderFragment;
import com.example.shopappdemo.navItems.SellerUploadFragment;
import com.example.shopappdemo.navItems.WishlistFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.main_drawer);

        NavigationView navigationView = findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.CloseDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.category);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.category:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new CategoryFragment()).commit();
                break;


            case R.id.seller:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new SellerUploadFragment()).commit();
                break;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new HomeFragment()).commit();
                break;

            case R.id.wishlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new WishlistFragment()).commit();
                break;
            case R.id.order:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_holder,new OrderFragment()).commit();
            break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}