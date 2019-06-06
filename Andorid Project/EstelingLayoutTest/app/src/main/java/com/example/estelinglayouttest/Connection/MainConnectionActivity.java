package com.example.estelinglayouttest.Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.estelinglayouttest.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

public class MainConnectionActivity extends AppCompatActivity {

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    private MyBroadcastReceiver myBroadCastReceiver;
    static final String BROADCAST_ACTION = "com.appsfromholland.mqttpayloadavailabe";


    Button submitBtn, subscribeBtn, unsubscribeBtn;
    TextView tv;
    ImageView colorBlock;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conection_activity_main);

        pahoMqttClient = new PahoMqttClient();

        client = pahoMqttClient.getMqttClient(
                getApplicationContext(),
                MQTTConfig.getInstance().MQTT_BROKER_URL(),
                MQTTConfig.getInstance().CLIENT_ID());

        tv = findViewById(R.id.mqttTextId);

        layout = findViewById(R.id.mainLayoutId);


        submitBtn = findViewById(R.id.publish);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "stuff"; //Rewrite this to send the jason structure
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
        });

        // Unsubscribe to TOPIC
        subscribeBtn = findViewById(R.id.subscribe);
        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pahoMqttClient.subscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC(), 0);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        // Unsubscribe to topic
        unsubscribeBtn = findViewById(R.id.unsubscribe);
        unsubscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pahoMqttClient.unSubscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });

        // Setup Broadcast receiver
        myBroadCastReceiver = new MyBroadcastReceiver();

        // Start Broadcast receiver
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
            Intent intent = new Intent(MainConnectionActivity.this, MqttMessageService.class);
            startService(intent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Defineer een eigen broadcast receiver, deze vangt alles op voor
    public class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                String payload = intent.getStringExtra("payload");
                Log.i(TAG,  payload);

                try {

                    // TODO: 5/16/2019 rewrite code to receive correct the data
//                    JSONObject jsonObject = new JSONObject(payload);
//                    int red = jsonObject.getJSONObject("ledColor").getInt("r");
//                    int green = jsonObject.getJSONObject("ledColor").getInt("g");
//                    int blue = jsonObject.getJSONObject("ledColor").getInt("b");
//
//                    layout.setBackgroundColor( Color.rgb(red, green, blue) );

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method called when this Activity finished
     * Override this method to unregister MyBroadCastReceiver
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // make sure to unregister your receiver after finishing of this activity
        unregisterReceiver(myBroadCastReceiver);
    }
}
