package com.example.esstelleague;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Pascal
 * HomeFragment is the container fragment for the home screen (which is a XML-file).
 */

public class HomeFragment extends Fragment {

    /**
     * @param inflater LayoutInflater object.
     * @param container ViewGroup container object.
     * @param savedInstanceState Bundle savedInstanceState object.
     * @return Return the view.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = view.findViewById(R.id.Play_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.CURRENT_FRAGMENT = MainActivity.PLAY_FRAGMENT;
                MainActivity.MAIN_ACTIVITY.reload();
            }
        });
        return view;
    }
}
