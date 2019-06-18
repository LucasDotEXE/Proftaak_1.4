package com.example.esstelleague;

import android.content.Context;
import android.view.animation.AnimationUtils;

public class Animation {
    public static android.view.animation.Animation getRotateAnimation(Context appContext) {
        return AnimationUtils.loadAnimation(appContext, R.anim.rotate);
    }

    public static android.view.animation.Animation getFeedbackAnimation(Context appContext) {
        return AnimationUtils.loadAnimation(appContext, R.anim.btn_feedback);
    }

    public static android.view.animation.Animation getFadeOut(Context app) {
        return AnimationUtils.loadAnimation(app, R.anim.fade_out);
    }
}
