package com.example.esstelleague;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.esstelleague.mqtt.MQTT;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Lucas
 * @author Dustin
 * The ControlActivity class enables controlling a car using MQTT.
 */

public class ControlActivity extends AppCompatActivity {
    private static final String TAG = "ControlActivity";

    private MQTT mMQTT_Client;
    private int mCarID = -1;
    private Direction mLastDir =Direction.STOP;

    private ArrayList<CircleImageView> cars;

    /**
     * OnCreate method of interface.
     * @param savedInstanceState Bundle obj.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String theme = this.getIntent().getStringExtra("theme");

        if (theme.equals("dark")) {
            this.setTheme(R.style.AppThemeDark);
        } else {
            this.setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_controlscreen);
        setSeekBar();
        mMQTT_Client=new MQTT(getApplicationContext());
        setButtonActions();
        mMQTT_Client.connect();

    }

    /**
     * Rotate the speed slider so it is vertical and not horizontal.
     */

    private void setSeekBar() {
        ConstraintLayout activityControls = (ConstraintLayout) findViewById(R.id.sb_speedslider);
        activityControls.setRotation(-90f);
        activityControls.setTranslationX(-250);
        activityControls.requestLayout();
    }

    /**
     * Set all button actions
     */

    private void setButtonActions() {
        ImageButton forward = this.findViewById(R.id.forward);
        ImageButton back = this.findViewById(R.id.back);
        ImageButton stop = this.findViewById(R.id.stop);
        ImageButton left = this.findViewById(R.id.left);
        ImageButton right = this.findViewById(R.id.right);

        ImageView info = findViewById(R.id.Info);
        info.setOnClickListener(v -> Toast.makeText(getApplicationContext(), R.string.ca_fastHint, Toast.LENGTH_LONG).show());

        forward.setOnClickListener(getClickListener(Direction.FORWARD, R.id.forward));
        back.setOnClickListener(getClickListener(Direction.BACKWARDS, R.id.back));
        stop.setOnClickListener(getClickListener(Direction.STOP, R.id.stop));
        left.setOnClickListener(getClickListener(Direction.LEFT, R.id.left));
        right.setOnClickListener(getClickListener(Direction.RIGHT, R.id.right));

        CircleImageView civ_car1 = this.findViewById(R.id.civ_car1);
        CircleImageView civ_car2 = this.findViewById(R.id.civ_car2);
        CircleImageView civ_car3 = this.findViewById(R.id.civ_car3);
        CircleImageView civ_car4 = this.findViewById(R.id.civ_car4);

        this.cars = new ArrayList<>();
        this.cars.add(civ_car1);
        this.cars.add(civ_car2);
        this.cars.add(civ_car3);
        this.cars.add(civ_car4);


        civ_car1.setOnClickListener(event -> {
            if (mCarID != 1) {
                mCarID = 1;
                civ_car1.startAnimation(Animation.getRotateAnimation(getApplicationContext()));
                civ_car1.postOnAnimationDelayed(setSelectedbackground(civ_car1), 750);
            }
        });
        civ_car2.setOnClickListener(event -> {
            if (mCarID != 2) {
                mCarID = 2;
                civ_car2.startAnimation(Animation.getRotateAnimation(getApplicationContext()));
                civ_car2.postOnAnimationDelayed(setSelectedbackground(civ_car2), 750);
            }
        });
        civ_car3.setOnClickListener(event -> {
            if (mCarID != 3) {
                mCarID = 3;
                civ_car3.startAnimation(Animation.getRotateAnimation(getApplicationContext()));
                civ_car3.postOnAnimationDelayed(setSelectedbackground(civ_car3), 750);
            }
        });
        civ_car4.setOnClickListener(event -> {
            if (mCarID != 4) {
                mCarID = 4;
                civ_car4.startAnimation(Animation.getRotateAnimation(getApplicationContext()));
                civ_car4.postOnAnimationDelayed(setSelectedbackground(civ_car4), 750);
            }
        });
    }

    /**
     * Make background of image EsstelingRed (which is a dedicated color).
     * @param car Specifies the selection window.
     * @return Runnable for new Thread.
     */

    private Runnable setSelectedbackground(CircleImageView car) {
        return () -> {
            for (CircleImageView view : this.cars) {
                if (view == car) {
                    view.setBackgroundColor(getColor(R.color.EsstelingRed));
                } else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        };
    }

    /**
     * Track control actions of the user.
     * @param action Specifies the direction.
     * @param id Specifies the id of the image button.
     * @return ClickListener.
     */

    private View.OnClickListener getClickListener(final Direction action, int id) {
        return v -> {
            SeekBar mSB_Speed = findViewById(R.id.seekBarSlider);
            mSB_Speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    action(mLastDir, mSB_Speed.getProgress());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            if (findViewById(id) instanceof ImageButton) {
                ImageButton button = (ImageButton)findViewById(id);
                button.startAnimation(Animation.getFeedbackAnimation(getApplicationContext()));
            }
            action(action, mSB_Speed.getProgress());
        };
    }

    /**
     * @param action Defines the direction the vehicle should move.
     * @param speed Defines the speed of the vehicle.
     */

    private void action(Direction action, int speed) {

        switch (action) {
            case FORWARD: {
                publishMessage(getJsonFormat(25.5, 25.5, speed));
                mLastDir =Direction.FORWARD;
                break;
            }
            case BACKWARDS: {
                publishMessage(getJsonFormat(-25.5, -25.5, speed));
                mLastDir =Direction.BACKWARDS;
                break;
            }
            case STOP: {
                publishMessage(getJsonFormat(0, 0, 0));
                mLastDir =Direction.STOP;
                break;
            }
            case LEFT: {
                publishMessage(getJsonFormat(-5.5, 25.5, speed));
                mLastDir =Direction.LEFT;
                break;
            }
            case RIGHT: {
                publishMessage(getJsonFormat(25.5, -5.5, speed));
                mLastDir =Direction.RIGHT;
                break;
            }
        }
    }

    /**
     * JSON format to send to vehicle.
     * @param left Left wheel speed.
     * @param right Right wheel speed.
     * @param speed Speed multiplier.
     * @return JSON "payload" string.
     */

    private String getJsonFormat(double left, double right, int speed) {
        return "{\"JSONFILE\": " +
                "{\"Vehicle\":" + mCarID + ", " +
                "\"DutyCycleL\":" + (left * speed) + ", " +
                "\"DutyCycleR\":" + (right * speed) + "} " +
                "}";
    }

    /**
     * Publish a MQTT message.
     * @param msg publishMessage for MQTT using the MQTT class.
     */

    public void publishMessage(String msg) {
        if (!msg.isEmpty() && mMQTT_Client.checkConnected() && mCarID!=-1)
            mMQTT_Client.publish(msg);
        else if (!mMQTT_Client.checkConnected())
            mMQTT_Client.connect();
        else if (mCarID==-1) Toast.makeText(this, getResources().getString(R.string.ca_selectvehicle), Toast.LENGTH_LONG).show();
    }

    /**
     * Specifies a direction for the vehicle.
     */

    private enum Direction {
        FORWARD, BACKWARDS, STOP, LEFT, RIGHT
    }
}
