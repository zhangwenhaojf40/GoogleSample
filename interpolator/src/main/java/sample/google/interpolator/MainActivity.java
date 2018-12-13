package sample.google.interpolator;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Interpolator[] mInterpolators;
    private View mView;
    private SeekBar seekBar;
    private Path mPathIn;
    private Path mPathOut;
    boolean isIn=true;
    private Spinner spinner;
    private CheckBox checkBox;
    private int color=Color.parseColor("#9c27b0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        initPath();
        initInterpolator();
        initSpinner();
        initSeekBar();



    }

    private void initId() {
        mView = findViewById(R.id.square);
        checkBox = findViewById(R.id.check_box);
    }

    private void initPath() {
        mPathIn = new Path();
        mPathIn.moveTo(1f, 1f);
        mPathIn.lineTo(0.2f, 0.2f);
        mPathOut = new Path();
        mPathOut.moveTo(0.2f,0.2f);
        mPathOut.lineTo(1f, 1f);

    }

    private void initSeekBar() {
        final TextView duration = findViewById(R.id.tv_duration);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duration.setText(getResources().getString(R.string.duration,progress));
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(700);
    }

    private void initInterpolator() {
        mInterpolators  = new Interpolator[]{
                new AccelerateInterpolator(),
                new DecelerateInterpolator(),
                new AccelerateDecelerateInterpolator(),

                new AnticipateInterpolator(),
                new AnticipateOvershootInterpolator(),
                new BounceInterpolator(),
                new CycleInterpolator(2),
                new LinearInterpolator(),
                new OvershootInterpolator()
        };
    }

    private void initSpinner() {
        String[] array = getResources().getStringArray(R.array.interpolator_names);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,array);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test:
                startAnimtion();
                break;
        }
        return true;
    }

    private void startAnimtion() {
        int selectedItemPosition = spinner.getSelectedItemPosition();
        if (checkBox.isChecked()) {
            int startColor =color== Color.parseColor("#9c27b0")?Color.parseColor("#9c27b0"):Color.parseColor("#356678");
            int endColor =startColor==Color.parseColor("#9c27b0")?Color.parseColor("#356678"):Color.parseColor("#9c27b0");
            ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),startColor,endColor);
            colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //之后就可以得到动画的颜色了
                    color = (int) animation.getAnimatedValue();
                    mView.setBackgroundColor(color);//设置一下, 就可以看到效果.
                }
            });
            colorAnimator.setDuration(seekBar.getProgress());
            colorAnimator.start();
            return;
        }

        Path path = isIn ? mPathIn : mPathOut;
        ObjectAnimator animator = ObjectAnimator.ofFloat(mView,View.SCALE_X,View.SCALE_Y,path);
        animator.setInterpolator(mInterpolators[selectedItemPosition]);
        animator.setDuration(seekBar.getProgress());
        animator.start();
        isIn = !isIn;
    }
}




