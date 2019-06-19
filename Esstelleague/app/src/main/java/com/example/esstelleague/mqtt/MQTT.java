package com.example.esstelleague.mqtt;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

/**
 * @author Dustin
 * The MQTT class enables connecting with a vehicle, it has a few public methods, which are used to connect, publish and check the connection.
 */

public class MQTT {
    private static final String TAG = "MqttHelper";

    private MqttAndroidClient mClient;
    private Context mAppContext;
    private boolean mConnected;

    /**
     * @param mAppContext appContext so a toast message can be made.
     */

    public MQTT(Context mAppContext) {
        this.mAppContext = mAppContext;
    }

    /**
     * Connect to a MQTT server using specified String located in MQTT_SettingsEnvironment.
     */

    public void connect() {
        String clientId = MqttClient.generateClientId();
        mClient = new MqttAndroidClient(mAppContext, "tcp://"+ MQTT_SettingsEnvironment.sMQTT_broker_IP, clientId);
        try {
            IMqttToken token = mClient.connect(getOptions());
            Toast.makeText(mAppContext, "Connecting", Toast.LENGTH_LONG).show();
            this.mConnected=false;
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(mAppContext, "Connected", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onSuccess: CONNECTED");
                    MQTT.this.mConnected=true;
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(mAppContext, "Failed to connect", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onFailure: FAILED TO CONNECT");
                    MQTT.this.mConnected=false;
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(TAG, "connect: ERROR", e);
        }
    }

    /**
     * Check the connection
     * @return Whether a connection is present.
     */

    public boolean checkConnected() {
        return this.mConnected;
    }

    /**
     * @return MQTT options which specify the username and password.
     */

    private MqttConnectOptions getOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(MQTT_SettingsEnvironment.sMQTT_username);
        mqttConnectOptions.setPassword(MQTT_SettingsEnvironment.sMQTT_passwd.toCharArray());
        return mqttConnectOptions;
    }

    /**
     * Publishes a message on the MQTT server on the selected topic.
     * @param payload (JSON) String you want to send.
     */

    public void publish(String payload) {
        byte[] mEncodedPayload = new byte[0];
        try {
            mEncodedPayload=payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(mEncodedPayload);
            mClient.publish(MQTT_SettingsEnvironment.sMQTT_topic,message);
            Log.d(TAG, "publish: SUCCESSFULLY PUBLISHED");
        } catch (UnsupportedEncodingException | MqttException e) {
            Log.e(TAG, "publish: ERROR", e);
        }
    }
}
