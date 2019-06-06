package com.example.estelinglayouttest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static Fragment currentFragment = new HomeFragment();
    public static Fragment homeFragment = new HomeFragment();
    public static Fragment mapFragment = new MapFragment();
    public static Fragment playFragment = new PlayFragment();
    public static Fragment settingsFragment = new SettingsFragment();
    public static boolean hints = true;
    public static boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                currentFragment).commit();
    }

    public void reload() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                currentFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                        selectedFragment = homeFragment;
                            break;
                            case R.id.nav_map:
                        selectedFragment = mapFragment;
                            break;
                            case R.id.nav_play:
                        selectedFragment = playFragment;
                            break;
                            case R.id.nav_settings:
                        selectedFragment = settingsFragment;
                            break;
                    }
                    currentFragment = selectedFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
