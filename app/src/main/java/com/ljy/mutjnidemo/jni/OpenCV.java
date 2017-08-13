package com.ljy.mutjnidemo.jni;

/**
 * Created by Administrator on 2017/8/13.
 */

public class OpenCV {
    static {
        System.loadLibrary("opencv_java3");
        System.loadLibrary("opencv_java");
        System.loadLibrary("opencv");
    }

    public native void nativeProcessFrame(long matAddrGr, long matAddrRgba);
}
