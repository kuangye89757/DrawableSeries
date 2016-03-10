package com.jikexueyuan.drawableseries;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView test_transition;
    private TextView test_scale;
    private ImageView test_clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_transition = (TextView) findViewById(R.id.test_transition);
        TransitionDrawable drawable = (TransitionDrawable) test_transition.getBackground();
        drawable.startTransition(1000);

        test_scale = (TextView) findViewById(R.id.test_scale);
        ScaleDrawable scaleDrawable = (ScaleDrawable) test_scale.getBackground();
        scaleDrawable.setLevel(1);

        test_clip = (ImageView) findViewById(R.id.test_clip);
        ClipDrawable clipDrawable = (ClipDrawable) test_clip.getBackground();
        clipDrawable.setLevel(8000);

    }
}
