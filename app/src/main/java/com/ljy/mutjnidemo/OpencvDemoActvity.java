package com.ljy.mutjnidemo;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ljy.mutjnidemo.jni.OpenCV;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class OpencvDemoActvity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG = OpencvDemoActvity.class.getCanonicalName();
    private Mat mRgba;
    private Mat mIntermediateMat;
    private Mat mGray;
    private CameraBridgeViewBase mOpenCvCameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opencv_demo_actvity);
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setClickable(true);
        mOpenCvCameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camera camera = ((JavaCameraView) mOpenCvCameraView).getCamera();
                if (camera != null) camera.autoFocus(null);
            }
        });
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mOpenCvCameraView.enableView();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();
        new OpenCV().nativeProcessFrame(mGray.getNativeObjAddr(), mRgba.getNativeObjAddr());
        return mRgba;
    }
}
