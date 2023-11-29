package com.appdet.quizhub;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

public class SystemBars {
    private final Activity activity;
    private final View decorView;
    private final int hideFlags;
    private boolean areSystemBarsVisible;

    public SystemBars(Activity activity) {
        this.activity = activity;
        decorView = activity.getWindow().getDecorView();
        hideFlags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        areSystemBarsVisible = true;
    }

    public void enableSwipeToToggleSystemBars() {
        hideSystemBars();

        decorView.setOnTouchListener(new View.OnTouchListener() {
            private float swipeStartY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        swipeStartY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float swipeEndY = event.getRawY();
                        if (swipeEndY - swipeStartY > getSwipeThreshold()) {
                            showSystemBars();
                        } else if (swipeStartY - swipeEndY > getSwipeThreshold()) {
                            hideSystemBars();
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void hideSystemBars() {
        decorView.setSystemUiVisibility(hideFlags);
        areSystemBarsVisible = false;
    }

    private void showSystemBars() {
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        areSystemBarsVisible = true;
    }

    private float getSwipeThreshold() {
        return 200; // Adjust this value as needed
    }

    public boolean areSystemBarsVisible() {
        return areSystemBarsVisible;
    }
}
