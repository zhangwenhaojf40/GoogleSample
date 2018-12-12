package sample.google.pictrueinpictrue;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;

/***
 *  Create By ZWH  On  2018/12/12 0012
 **/
public class MovieView extends RelativeLayout{
    private static final String TAG = "MovieView";
    private boolean mAdjustViewBounds;
    @RawRes private int mVideoResourceId;
    private SurfaceView mSurfaceView;
    MediaPlayer mMediaPlayer;

    MovieListener mMovieListener;
    private int mSavedCurrentPosition;
    public MovieView(Context context) {
        this(context,null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.layout_movie, this);
        mSurfaceView = findViewById(R.id.surf);

        final TypedArray attributes =
                context.obtainStyledAttributes(
                        attrs,
                        R.styleable.MovieView,
                        defStyleAttr,
                        R.style.Widget_PictureInPicture_MovieView);

        mVideoResourceId = attributes.getResourceId(R.styleable.MovieView_android_src, 0);
        mAdjustViewBounds = attributes.getBoolean(R.styleable.MovieView_android_adjustViewBounds, false);
        attributes.recycle();

        mSurfaceView
                .getHolder()
                .addCallback(
                        new SurfaceHolder.Callback() {
                            @Override
                            public void surfaceCreated(SurfaceHolder holder) {
                                openVideo(holder.getSurface());
                            }

                            @Override
                            public void surfaceChanged(
                                    SurfaceHolder holder, int format, int width, int height) {
                                // Do nothing
                            }

                            @Override
                            public void surfaceDestroyed(SurfaceHolder holder) {
                                if (mMediaPlayer != null) {
                                    mSavedCurrentPosition = mMediaPlayer.getCurrentPosition();
                                }
                                closeVideo();
                            }
                        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMediaPlayer != null) {
            final int videoWidth = mMediaPlayer.getVideoWidth();
            final int videoHeight = mMediaPlayer.getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0) {
                final float aspectRatio = (float) videoHeight / videoWidth;
                final int width = MeasureSpec.getSize(widthMeasureSpec);
                final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                final int height = MeasureSpec.getSize(heightMeasureSpec);
                final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                if (mAdjustViewBounds) {
                    if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
                        super.onMeasure(
                                widthMeasureSpec,
                                MeasureSpec.makeMeasureSpec(
                                        (int) (width * aspectRatio), MeasureSpec.EXACTLY));
                    } else if (widthMode != MeasureSpec.EXACTLY
                            && heightMode == MeasureSpec.EXACTLY) {
                        super.onMeasure(
                                MeasureSpec.makeMeasureSpec(
                                        (int) (height / aspectRatio), MeasureSpec.EXACTLY),
                                heightMeasureSpec);
                    } else {
                        super.onMeasure(
                                widthMeasureSpec,
                                MeasureSpec.makeMeasureSpec(
                                        (int) (width * aspectRatio), MeasureSpec.EXACTLY));
                    }
                } else {
                    final float viewRatio = (float) height / width;
                    if (aspectRatio > viewRatio) {
                        int padding = (int) ((width - height / aspectRatio) / 2);
                        setPadding(padding, 0, padding, 0);
                    } else {
                        int padding = (int) ((height - width * aspectRatio) / 2);
                        setPadding(0, padding, 0, padding);
                    }
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
                return;
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }




    void closeVideo() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    void openVideo(Surface surface) {
        if (mVideoResourceId == 0) {
            return;
        }
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setSurface(surface);
        startVideo();
    }
    public void startVideo() {
        mMediaPlayer.reset();
        try (AssetFileDescriptor fd = getResources().openRawResourceFd(mVideoResourceId)) {
            mMediaPlayer.setDataSource(fd);
            mMediaPlayer.setOnPreparedListener(
                    new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            // Adjust the aspect ratio of this view
                            requestLayout();
                            if (mSavedCurrentPosition > 0) {
                                mediaPlayer.seekTo(mSavedCurrentPosition);
                                mSavedCurrentPosition = 0;
                            } else {
                                play();
                            }
                        }
                    });

            mMediaPlayer.prepare();
        } catch (IOException e) {
            Log.e(TAG, "Failed to open video", e);
        }
    }

    public void play() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.start();
        setKeepScreenOn(true);
        if (mMovieListener != null) {
            mMovieListener.onMovieStarted();
        }
    }

    public abstract static class MovieListener {

        /** Called when the video is started or resumed. */
        public void onMovieStarted() {}

        /** Called when the video is paused or finished. */
        public void onMovieStopped() {}

        /** Called when this view should be minimized. */
        public void onMovieMinimized() {}
    }









}
