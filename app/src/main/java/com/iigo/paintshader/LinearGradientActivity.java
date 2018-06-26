package com.iigo.paintshader;

import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 10:16
 */
public class LinearGradientActivity extends AppCompatActivity {
    private LGView lgViewClamp;
    private LGView lgViewRepeat;
    private LGView lgViewMirror;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lg);

        lgViewClamp = findViewById(R.id.lv_clamp);
        lgViewRepeat = findViewById(R.id.lv_repeat);
        lgViewMirror = findViewById(R.id.lv_mirror);

        lgViewClamp.setTileMode(Shader.TileMode.CLAMP);
        lgViewRepeat.setTileMode(Shader.TileMode.REPEAT);
        lgViewMirror.setTileMode(Shader.TileMode.MIRROR);
    }
}
