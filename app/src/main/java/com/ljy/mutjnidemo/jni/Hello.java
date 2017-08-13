package com.ljy.mutjnidemo.jni;

/**
 * Created by Administrator on 2017/8/13.
 */

public class Hello {
    static {
        System.loadLibrary("hello");
    }

    public native String stringFromJNI();
}
