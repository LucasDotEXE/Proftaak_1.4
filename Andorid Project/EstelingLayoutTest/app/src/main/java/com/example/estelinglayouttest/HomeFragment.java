package com.example.estelinglayouttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estelinglayouttest.JoyStick.JoyStickView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        JoyStickView joyStick = new JoyStickView(inflater.inflate(R.layout.fragment_home, container, false).getContext());

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
