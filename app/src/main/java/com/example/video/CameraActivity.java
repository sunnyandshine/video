package com.example.video;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class CameraActivity extends Activity implements SurfaceHolder.Callback {
    private int cameraId=1;
    private Camera mcamera;
    private Button capture;
    private SurfaceView msurfaceView;
    private SurfaceHolder msurfaceHolder;
    private int screenWidth;
    private int screenheight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        msurfaceView=findViewById(R.id.surfaceview);
        msurfaceHolder=msurfaceView.getHolder();
        msurfaceHolder.addCallback(this);
    }
    private void open()
    {
        try
        {
            mcamera=Camera.open(cameraId);
            mcamera.setDisplayOrientation(90);
            mcamera.setPreviewDisplay(msurfaceHolder);
            mcamera.startPreview();
        }catch (IOException e)
        {
            mcamera.release();
            mcamera=null;
            Toast.makeText(CameraActivity.this, "surface created failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){}
        else
        {
            open();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters=mcamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

       // mcamera.setParameters(parameters);
        mcamera.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}

