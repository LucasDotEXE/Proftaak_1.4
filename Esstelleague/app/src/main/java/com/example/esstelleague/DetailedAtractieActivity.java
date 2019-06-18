package com.example.esstelleague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedAtractieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String theme = getIntent().getStringExtra("theme");
        if (theme.equals("dark")) {
            this.setTheme(R.style.AppThemeDark);
        } else {
            this.setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_detailed_atractie);

        Attraction gwb = (Attraction) getIntent().getSerializableExtra("GWB_OBJECT");

        ImageView banner = findViewById(R.id.atractie_banner);
        TextView naam = findViewById(R.id.atractie_naam);
        TextView detail = findViewById(R.id.atractie_detail);
        naam.setText(gwb.getmName());
        detail.setText(getText(gwb.getmDetails()));

        Button play = findViewById(R.id.atractie_play);

        play.setText("Play at: \n" + gwb.getmName());
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ControlActivity.class);
                if (MainActivity.DARKTHEME) {
                    intent.putExtra("theme", "dark");
                } else {
                    intent.putExtra("theme", "light");
                }
                startActivity(intent);
            }
        });

        int resid = getResources().getIdentifier(
                gwb.getmImageUrl(),
                "drawable",
                getPackageName()
        );

        banner.setImageResource(resid);
    }
}
