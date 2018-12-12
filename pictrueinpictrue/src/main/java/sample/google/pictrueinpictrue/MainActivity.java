package sample.google.pictrueinpictrue;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private MovieView mMovieView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMovieView = findViewById(R.id.movieView);

        findViewById(R.id.pip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureInPictureParams.Builder mPictureInPictureParamsBuilder =
                        new PictureInPictureParams.Builder();
                Rational aspectRatio = new Rational(mMovieView.getWidth(), mMovieView.getHeight());
                mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
                enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());

            }
        });
    }


}
