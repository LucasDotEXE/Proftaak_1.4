package com.example.esstelleague;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsFragment extends Fragment {

    private Switch mDarkTheme;
    private Switch mHints;
    private Switch mSound;
    private Button mAboutUs;
    private boolean mDarkThemeBool = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        this.mSound = view.findViewById(R.id.soundSwitchI);
        this.mSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.SOUND =isChecked;
            }
        });
        this.mHints = view.findViewById(R.id.hintsSwitchI);
        this.mHints.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.HINTS =isChecked;
            }
        });
        this.mDarkTheme = view.findViewById(R.id.darkThemeSwitchI);
        this.mDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getActivity().setTheme(R.style.AppThemeDark);
                    mDarkThemeBool = true;
                } else {
                    getActivity().setTheme(R.style.AppTheme);
                    mDarkThemeBool = false;
                }
                refresh();
            }
        });

        this.mAboutUs = view.findViewById(R.id.aboutUsButtonI);
        this.mAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
                if (mDarkThemeBool) {
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