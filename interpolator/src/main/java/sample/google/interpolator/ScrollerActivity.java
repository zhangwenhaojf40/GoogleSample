package sample.google.interpolator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;

/***
 *  Create By ZWH  On  2018/12/19 0019
 **/
public class ScrollerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        MyView myView = findViewById(R.id.my_view);
        findViewById(R.id.btn_test).setOnClickListener(v -> {

            myView.startScroll();
        });
        findViewById(R.id.btn_jump).setOnClickListener(v -> {
            startActivity(new Intent(this,AnimationActivity.class));
        });

    }
}
