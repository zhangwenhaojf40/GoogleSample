package sample.google.interpolator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/***
 *  Create By ZWH  On  2018/12/19 0019
 **/
public class AnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        View view = findViewById(R.id.view);

        findViewById(R.id.btn_rotation).setOnClickListener(v -> {
            ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
            anim.setDuration(500);
            // 设置动画运行的时长
            anim.setStartDelay(500);
            // 设置动画延迟播放时间
            anim.setRepeatCount(0);
            // 设置动画重复播放次数 = 重放次数+1
            anim.setRepeatMode(ValueAnimator.RESTART);
            // 设置重复播放动画模式
            anim.start();
        });

    }
}


/*
 * alpha 透明
 * translationX  直线
 * scaleX       缩放
 * Animator animator = AnimatorInflater.loadAnimator(context, R.animator.set_animation);  xml动画
 * animator.setTarget(view);
 * animator.start();
 *
 * */