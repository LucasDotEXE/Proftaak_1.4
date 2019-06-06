package com.example.estelinglayouttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Controls extends AppCompatActivity {

    private int carID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_controls);
        setButtonActions();
    }

    private void setButtonActions() {
        ImageButton forward = this.findViewById(R.id.voorwaards);
        ImageButton back = this.findViewById(R.id.back);
        ImageButton stop = this.findViewById(R.id.stop);
        ImageButton left = this.findViewById(R.id.left);
        ImageButton right = this.findViewById(R.id.right);

        forward.setOnClickListener(getClickListener(direction.FORWARD));
        back.setOnClickListener(getClickListener(direction.BACKWARDS));
        stop.setOnClickListener(getClickListener(direction.STOP));
        left.setOnClickListener(getClickListener(direction.LEFT));
        right.setOnClickListener(getClickListener(direction.RIGHT));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                subscribe();
            }
        });
    }

    private View.OnClickListener getClickListener(final direction action) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar speed = findViewById(R.id.speed);
                exampleAction(action, speed.getProgress());
            }
        };
    }

    private void exampleAction(direction action, int speed) {
        switch (action) {
            case FORWARD: {
//                publishMessage(getJsonFormat(10, 10, speed));
                break;
            }
            case BACKWARDS: {
//                publishMessage(getJsonFormat(-10, -10, speed));
                break;
            }
            case STOP: {
//                publishMessage(getJsonFormat(0, 0, 0));
                break;
            }
            case LEFT: {
//                publishMessage(getJsonFormat(-5, 5, speed));
                break;
            }
            case RIGHT: {
//                publishMessage(getJsonFormat(5, -5, speed));
                break;
            }
        }
    }

    private String getJsonFormat(int left, int right, int speed) {
        return "{\"JSONFILE\": " +
                      "{\"Vehicle\":" + this.carID + ", " +
                       "\"DutyCycleL\":"+ (left*speed+150) + ", " +
                       "\"DutyCycleR\":"+ (right*speed+150) +"} " +
                "}";
    }




    private enum direction {
        FORWARD, BACKWARDS, STOP, LEFT, RIGHT
    }
}
