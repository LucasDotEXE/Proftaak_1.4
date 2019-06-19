package com.example.esstelleague;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author Tom
 * @author Lucas
 * MainActivity is the first activity that loads, and initializes the navigation and button actions.
 * Tom: Fragments are static so they can be used in another class.
 */

public class MainActivity extends AppCompatActivity {

    public static MainActivity MAIN_ACTIVITY;
    public static Fragment CURRENT_FRAGMENT = new HomeFragment();
    public static Fragment HOME_FRAGMENT = new HomeFragment();
    public static Fragment MAP_FRAGMENT = new MapFragment();
    public static Fragment PLAY_FRAGMENT = new PlayFragment();
    public static Fragment SETTINGS_FRAGMENT = new SettingsFragment();
    public static boolean HINTS = true;
    public static boolean SOUND = true;
    public static boolean DARKTHEME = false;

    /**
     * OnCreate method implemented by extending AppCompatActivity
     * @param savedInstanceState Bundle obj. savedInstanceState.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        MAIN_ACTIVITY = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                CURRENT_FRAGMENT).commit();
    }

    /**
     * Reload a fragment.
     */

    public void reload() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                CURRENT_FRAGMENT).commit();
    }

    /**
     * Init navigation actions.
     * Tom: No new fragments are made after the initial fragments to allow proper usage of themes.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                        selectedFragment = HOME_FRAGMENT;
                            break;
                            case R.id.nav_map:
                        selectedFragment = MAP_FRAGMENT;
                            break;
                            case R.id.nav_play:
                        selectedFragment = PLAY_FRAGMENT;
                            break;
                            case R.id.nav_settings:
                        selectedFragment = SETTINGS_FRAGMENT;
                            break;
                    }
                    CURRENT_FRAGMENT = selectedFragment;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
