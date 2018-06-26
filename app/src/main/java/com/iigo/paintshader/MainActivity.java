package com.iigo.paintshader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartLinearGradient(View view) {
        startActivity(new Intent(this, LinearGradientActivity.class));
    }

    public void onStartNeonLights(View view) {
        startActivity(new Intent(this, NeonLightActivity.class));
    }

    public void onStartBitmapShader1(View view) {
        startActivity(new Intent(this, BitmapShaderActivity.class));
    }

    public void onStartBitmapShader2(View view) {
        startActivity(new Intent(this, BitmapShaderInvertActivity.class));
    }

    public void onStartComposeShader(View view) {
        startActivity(new Intent(this, ComposeShaderActivity.class));
    }

    public void onStartRadialGradient(View view) {
        startActivity(new Intent(this, RadialGradientActivity.class));
    }

    public void onStartSweepGradient(View view) {
        startActivity(new Intent(this, SweepGradientActivity.class));
    }

    public void onStartSweepGradient2(View view) {
        startActivity(new Intent(this, SweepGradientRadarActivity.class));
    }
}
