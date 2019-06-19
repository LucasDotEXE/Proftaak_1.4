package com.example.esstelleague;

import android.content.Context;
import android.view.animation.AnimationUtils;

/**
 * @author Lucas
 * @author Dustin
 * Added animation effects for the application. Extra effects can be added in this list.
 */

class Animation {
    static android.view.animation.Animation getRotateAnimation(Context appContext) {
        return AnimationUtils.loadAnimation(appContext, R.anim.rotate);
    }

    static android.view.animation.Animation getFeedbackAnimation(Context appContext) {
        return AnimationUtils.loadAnimation(appContext, R.anim.btn_feedback);
    }

    static android.view.animation.Animation getFadeOut(Context app) {
        return AnimationUtils.loadAnimation(app, R.anim.fade_out);
    }
}
