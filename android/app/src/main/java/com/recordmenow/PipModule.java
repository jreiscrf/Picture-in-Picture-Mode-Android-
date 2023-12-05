package com.recordmenow;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.util.Rational;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.util.DisplayMetrics;
import android.os.Build;

public class PipModule extends ReactContextBaseJavaModule {
    public PipModule(ReactApplicationContext reactContext) {
      super(reactContext);
    }
  
    @Override
    public String getName() {
      return "PipModule";
    }
  
    @ReactMethod
    public void enterPipMode() {
      if (getCurrentActivity() != null) {
        getCurrentActivity().enterPictureInPictureMode();
      }
    }
  
    @ReactMethod
    public void exitPipMode() {
      if (getCurrentActivity() != null) {
        getCurrentActivity().finish();
      }
    }
  
  
  @ReactMethod
  public void setAspectRatio(float heightFactor) {
    if (getCurrentActivity() != null && getCurrentActivity().isInPictureInPictureMode()) {
      DisplayMetrics displayMetrics = new DisplayMetrics();
      getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
      int screenWidth = displayMetrics.widthPixels;
      int screenHeight = displayMetrics.heightPixels;
  
      float aspectRatio = 32f / 16f;
  
      int pipWidth;
      int pipHeight;
      if (screenWidth > screenHeight) {
        pipWidth = screenHeight;
        pipHeight = (int) (screenHeight / aspectRatio);
      } else {
        pipWidth = screenWidth;
        pipHeight = (int) (screenWidth / aspectRatio);
      }
  
      // Increase the height by the specified factor
      pipHeight = (int) (pipHeight * heightFactor);
  
      PictureInPictureParams.Builder pipBuilder = new PictureInPictureParams.Builder();
  
      // Check if the setAspectRatio method is available (added in API level 31)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Rational aspectRatioRational = new Rational(pipWidth, pipHeight);
        pipBuilder.setAspectRatio(aspectRatioRational);
      } else {
        // Handle earlier Android versions
      }
  
      PictureInPictureParams params = pipBuilder.build();
  
      getCurrentActivity().setPictureInPictureParams(params);
    }
  }
  
  
  
    @ReactMethod
    public boolean isInPipMode() {
      return getCurrentActivity() != null && getCurrentActivity().isInPictureInPictureMode();
    }
  
    public void onConfigurationChanged(Configuration newConfig) {
      // Handle configuration changes if necessary
    }
  }