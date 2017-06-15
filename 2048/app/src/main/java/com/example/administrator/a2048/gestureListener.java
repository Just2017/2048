package com.example.administrator.a2048;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/5/31.
 */


public class gestureListener implements GestureDetector.OnGestureListener, View.OnTouchListener{

    private  GestureDetector mGestureDetector = new GestureDetector(this);

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    //
    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("touch","onDown");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("touch","onFling");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("touch","onLongPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("touch","onScroll");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("touch","onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("touch","onSingleTapUp");
        return false;
    }
}
