package com.example.estelinglayouttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.OrientationHelper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsFragment extends Fragment {

    private Switch darkTheme;
    private Switch hints;
    private Switch sound;
    private Button aboutUs;
    private boolean darkThemeBool = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        this.sound = view.findViewById(R.id.soundSwitch);
        this.sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.sound = true;
                } else {
                    MainActivity.sound = false;
                }
            }
        });
        this.hints = view.findViewById(R.id.hintsSwitch);
        this.hints.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.hints = true;
                } else {
                    MainActivity.hints = false;
                }
            }
        });
        this.darkTheme = view.findViewById(R.id.darkThemeSwitch);
        this.darkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().setTheme(R.style.AppThemeDark);
                    darkThemeBool = true;
                } else {
                    getActivity().setTheme(R.style.AppTheme);
                    darkThemeBool = false;
                }
                refresh();
            }
        });

        this.aboutUs = view.findViewById(R.id.aboutUsButton);
        this.aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
                if (darkThemeBool) {
                    intent.putExtra("theme", "dark");
                } else {
                    intent.putExtra("theme", "light");
                }
                v.getContext().startActivity(intent);
            }
        });

        return view;
    }

    public void refresh() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}
