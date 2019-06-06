package com.example.estelinglayouttest.MQTT_Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.estelinglayouttest.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MQTTConnectionHelper {

    private MyBroadcastReceiver myBroadCastReceiver;
    static final String BROADCAST_ACTION = "com.appsfromholland.mqttpayloadavailabe";

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    private AppCompatActivity parent;

    public MyBroadcastReceiver getMyBroadCastReceiver() {
        return myBroadCastReceiver;
    }

    public MQTTConnectionHelper(@NonNull AppCompatActivity parent) {
        this.parent = parent;

        pahoMqttClient = new PahoMqttClient();

        myBroadCastReceiver = new MyBroadcastReceiver();

        client = pahoMqttClient.getMqttClient(
                this.parent.getApplicationContext(),
                MQTTConfig.getInstance().MQTT_BROKER_URL(),
                MQTTConfig.getInstance().CLIENT_ID());

        try
        {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BROADCAST_ACTION);
            this.parent.registerReceiver(myBroadCastReceiver, intentFilter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        // Start services
        try {
            Intent intent = new Intent(this.parent, MqttMessageService.class);
            this.parent.startService(intent);
        } catch(Exception e) {
            e.printStackTrace();
        }

        this.subscribe();
    }


    public void subscribe() {
        try {
            Log.i("1234768", client.toString());
            pahoMqttClient.subscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC(), 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void unsubscribe() {
        try {
            pahoMqttClient.unSubscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC());
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
}
