package com.example.estelinglayouttest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Spinner languages;
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

        this.languages = view.findViewById(R.id.languageSelecterSpinner);
        this.languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("Nederlands")) {
                    Configuration configuration = new Configuration();
                    configuration.locale = Locale.US;
                    getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());
                } else if (item.equals("English")) {
                    Configuration configuration = new Configuration();
                    configuration.locale = Locale.UK;
                    getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());
                } else if (item.equals("Deutsch")) {
                    Configuration configuration = new Configuration();
                    configuration.locale = Locale.GERMANY;
                    getContext().getResources().updateConfiguration(configuration, getContext().getResources().getDisplayMetrics());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> languageList = new ArrayList<>();
        languageList.add("Nederlands");
        languageList.add("English");
        languageList.add("Deutsch");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, languageList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        this.languages.setAdapter(adapter);

        return view;
    }

    public void refresh() {
    }
}
