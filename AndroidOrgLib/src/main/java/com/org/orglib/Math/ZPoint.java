package com.org.orglib.Math;

/**
 * Created by xiuer on 16/12/7.
 */

public class ZPoint {


    private float mX = 0;
    private float mY = 0;
    //x-Axis distance
    private float mDx = 0;
    //y-Axis  distance
    private float mDy = 0;


    public ZPoint() {
    }

    public ZPoint(float mX, float mY) {
        this.mX = mX;
        this.mY = mY;
    }

    public ZPoint(float mX, float mY, float mDx, float mDy) {
        this.mX = mX;
        this.mY = mY;
        this.mDx = mDx;
        this.mDy = mDy;
    }

    public float getmX() {
        return mX;
    }

    public void setmX(float mX) {
        this.mX = mX;
    }

    public float getmY() {
        return mY;
    }

    public void setmY(float mY) {
        this.mY = mY;
    }

    public float getmDy() {
        return mDy;
    }

    public void setmDy(float mDy) {
        this.mDy = mDy;
    }

    public float getmDx() {
        return mDx;
    }

    public void setmDx(float mDx) {
        this.mDx = mDx;
    }

    @Override
    public String toString() {
        return "ZPoint{" +
                "mX=" + mX +
                ", mY=" + mY +
                ", mDx=" + mDx +
                ", mDy=" + mDy +
                '}';
    }
}
