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

import com.example.estelinglayouttest.MQTT_Connection.MQTTConfig;
import com.example.estelinglayouttest.MQTT_Connection.MQTTConnectionHelper;
import com.example.estelinglayouttest.MQTT_Connection.MqttMessageService;
import com.example.estelinglayouttest.MQTT_Connection.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Controls extends AppCompatActivity {

    private MyBroadcastReceiver myBroadCastReceiver;
    static final String BROADCAST_ACTION = "com.appsfromholland.mqttpayloadavailabe";

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    private boolean isSubscribed = false;

    private int carID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pahoMqttClient = new PahoMqttClient();

        myBroadCastReceiver = new MyBroadcastReceiver();

        client = pahoMqttClient.getMqttClient(
                getApplicationContext(),
                MQTTConfig.getInstance().MQTT_BROKER_URL(),
                MQTTConfig.getInstance().CLIENT_ID());

        try
        {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BROADCAST_ACTION);
            registerReceiver(myBroadCastReceiver, intentFilter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        // Start services
        try {
            Intent intent = new Intent(Controls.this, MqttMessageService.class);
            startService(intent);
        } catch(Exception e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_controls);
        setButtonActions();


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });
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
        if (!isSubscribed) {
            subscribe();
        }

        switch (action) {
            case FORWARD: {
                publishMessage(getJsonFormat(10, 10, speed));
                break;
            }
            case BACKWARDS: {
                publishMessage(getJsonFormat(-10, -10, speed));
                break;
            }
            case STOP: {
                publishMessage(getJsonFormat(0, 0, 0));
                break;
            }
            case LEFT: {
                publishMessage(getJsonFormat(-5, 5, speed));
                break;
            }
            case RIGHT: {
                publishMessage(getJsonFormat(5, -5, speed));
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

    public void subscribe() {
        try {
            pahoMqttClient.subscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC(), 0);
            this.isSubscribed = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void unsubscribe() {
        try {
            pahoMqttClient.unSubscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC());
            this.isSubscribed = false;
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void publishMessage(String msg) {
        if( !msg.isEmpty()) {
            try {
                // TODO: 5/16/2019 rewrite this to send instructions for movement to a specific car
                pahoMqttClient.publishMessage(client, msg, 0, MQTTConfig.getInstance().PUBLISH_TOPIC());
            } catch (MqttException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }



    private enum direction {
        FORWARD, BACKWARDS, STOP, LEFT, RIGHT
    }
    public class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                String payload = intent.getStringExtra("payload");
                JSONObject jsonObject = new JSONObject(payload);

                Log.i(TAG,  payload);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unsubscribe();
        // make sure to unregister your receiver after finishing of this activity
        unregisterReceiver(this.myBroadCastReceiver);

    }
}
