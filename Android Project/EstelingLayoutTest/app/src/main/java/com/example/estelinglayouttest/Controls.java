package com.example.estelinglayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class  Controls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setButtonActions();
        setContentView(R.layout.activity_controls);
    }

    private void setButtonActions() {
        ImageButton forward = findViewById(R.id.forwards);
        ImageButton back = findViewById(R.id.back);
        ImageButton stop = findViewById(R.id.stop);
        ImageButton left = findViewById(R.id.left);
        ImageButton right = findViewById(R.id.right);

        forward.setOnClickListener(getClickListener("forward"));
        back.setOnClickListener(getClickListener("back"));
        stop.setOnClickListener(getClickListener("stop"));
        left.setOnClickListener(getClickListener("left"));
        right.setOnClickListener(getClickListener("right"));
    }

    private View.OnClickListener getClickListener(final String action) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar speed = findViewById(R.id.speed);

                exampleAction(action, speed.getProgress());
            }
        };
    }

    private void exampleAction(String action, int speed) {
        Log.d("Example", "I am going: " + action + " at a speed of: " + speed);
    }


}
