package com.example.estelinglayouttest.MQTT_Connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.estelinglayouttest.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

public class MQTTConnectionHelper {

    private MyBroadcastReceiver myBroadCastReceiver;
    static final String BROADCAST_ACTION = "com.appsfromholland.mqttpayloadavailabe";

    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    private AppCompatActivity parent

    public MQTTConnectionHelper() {


        pahoMqttClient = new PahoMqttClient();

        myBroadCastReceiver = new MyBroadcastReceiver();

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
            Intent intent = new Intent(MQTTConnectionHelper.this, MqttMessageService.class);
            startService(intent);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void subscribe() {
        try {
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

    public class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                String payload = intent.getStringExtra("payload");
                JSONObject jsonObject = new JSONObject(payload);

                JSONObject message = jsonObject.getJSONObject("message");
                messages = message.getString("mes") + "\n" + messages;
                messageBox.setText(messages);
                Log.i(TAG,  payload);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
