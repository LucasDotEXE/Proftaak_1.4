package com.example.estelinglayouttest.JoyStick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class JoyStick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoyStickView joyStick = new JoyStickView(this);
        setContentView(joyStick);


    }
}
