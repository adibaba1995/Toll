package com.apsit.toll.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.apsit.toll.R;
import com.apsit.toll.presentation.view.adapter.MainViewPagerAdapter;
import com.apsit.toll.presentation.view.fragment.DisplayMapFragment;
import com.apsit.toll.presentation.view.fragment.OnTheGoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by adityathanekar on 16/03/17.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_FRAGMENT_POSITION = "com.apsit.toll.EXTRA_FRAGMENT_POSITION";

    public static final int ON_THE_GO = 0;
    public static final int TOLLS = 1;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation)
    NavigationView navigationView;

    private Unbinder unbinder;

    private ActionBarDrawerToggle drawerToggle;

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new DisplayMapFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        navigationView.inflateHeaderView(R.layout.navigation_header);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        if(intent != null) {
            showFragment(intent.getIntExtra(EXTRA_FRAGMENT_POSITION, -1));
        }
    }

    public boolean isOptionItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void syncToolbar() {
        drawerToggle.syncState();
    }

    public void lockNavigationView() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockNavigationView() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void onBackPressed() {
        if(callback != null) {
            callback.backPressed();
        }
    }

    private final void showFragment(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case ON_THE_GO:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new OnTheGoFragment()).commit();
                drawerLayout.closeDrawers();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.tolls:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new DisplayMapFragment()).commit();
                drawerLayout.closeDrawers();
                return true;
            case R.id.account:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                return true;
            case R.id.onthego:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new OnTheGoFragment()).commit();
                drawerLayout.closeDrawers();
                return true;
        }
        return false;
    }

    public interface Callback {
        void backPressed();
    }
}
