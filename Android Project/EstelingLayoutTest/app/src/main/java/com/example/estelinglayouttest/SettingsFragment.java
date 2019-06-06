package com.example.estelinglayouttest;

import android.content.Intent;
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

    private Switch darkTheme;
    private Switch hints;
    private Switch sound;
    private Button aboutUs;

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
                } else {
                    getActivity().setTheme(R.style.AppTheme);
                }
            }
        });

        this.aboutUs = view.findViewById(R.id.aboutUsButton);
        this.aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
