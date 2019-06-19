package com.example.esstelleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Tom
 * The AboutUsActivity sets a theme to normal or dark mode depending on the selected setting.
 */

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String theme = this.getIntent().getStringExtra("theme");
        if (theme.equals("dark")) {
            this.setTheme(R.style.AppThemeDark);
        } else {
            this.setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_about_us);
    }
}
