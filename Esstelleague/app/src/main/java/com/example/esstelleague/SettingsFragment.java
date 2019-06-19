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
import android.widget.Switch;

/**
 * @author Tom
 * The SettingsFragment class introduces a few settings such as dark theme.
 */

public class SettingsFragment extends Fragment {
    private Switch mDarkTheme;
    private Switch mHints;
    private Switch mSound;
    private Button mAboutUs;


    /**
     * onCreateView method is created by extending fragment.
     * @param inflater LayoutInflater object.
     * @param container ViewGroup container object.
     * @param savedInstanceState Bundle object (savedInstanceState).
     * @return View object.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        this.mSound = view.findViewById(R.id.soundSwitchI);
        this.mSound.setOnCheckedChangeListener((buttonView, isChecked) -> MainActivity.SOUND =isChecked);
        this.mHints = view.findViewById(R.id.hintsSwitchI);
        this.mHints.setOnCheckedChangeListener((buttonView, isChecked) -> MainActivity.HINTS =isChecked);
        this.mDarkTheme = view.findViewById(R.id.darkThemeSwitchI);
        this.mDarkTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //functionality for darktheme, themes are set here, boolean in MainActivity is used to decide themes elsewhere using intents
            if (isChecked) {
                getActivity().setTheme(R.style.AppThemeDark);
                MainActivity.DARKTHEME = true;
            } else {
                getActivity().setTheme(R.style.AppTheme);
                MainActivity.DARKTHEME = false;
            }
            refresh();
        });

        this.mAboutUs = view.findViewById(R.id.aboutUsButtonI);
        this.mAboutUs.setOnClickListener(v -> {
            //Intent is used to send along theme
            Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
            if (MainActivity.DARKTHEME) {
                intent.putExtra("theme", "dark");
            } else {
                intent.putExtra("theme", "light");
            }
            v.getContext().startActivity(intent);
        });

        return view;
    }

    /**
     * method for forcing a refresh of the fragment, used to activate darktheme.
     */
    public void refresh() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}
