package com.afuktju.oona.ui.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.afuktju.oona.R;
import com.afuktju.oona.Utils;
import com.afuktju.oona.OnSwipeTouchListener;
import com.afuktju.oona.ui.advertisement.AdvertisementActivity;
import com.afuktju.oona.ui.playlist.TabVideoActivity;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    VideoView vvMain;
    public int logos[] = {R.drawable.logo_facebook, 0, R.drawable.logo_linkedin, 0, R.drawable.logo_twitter, 0};
    public int videos[] = {R.raw.video_example_one, R.raw.video_example_two};
    private int counter_logo;
    private ImageView ivLogo;
    public final static int DELAY_LOGO = 10 * 1000;
    public final static int DELAY_ADVERTISEMENT_IN_SECONDS = 30;

    private int index_video = 0;
    public static final String KEY_ADVERTISEMENT_URL = "ADVERTISEMENT_URL";
    private int progress = 0;
    private Handler handlerProgressVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_video);
        ivLogo = findViewById(R.id.ivLogo);
        findViewById(R.id.ivPlayList).setOnClickListener(this);
        initializedLogo();

    }

    private void initializeMainVideo() {
        vvMain = findViewById(R.id.vvMain);
        vvMain.setVideoPath(Utils.getVideoPathByRaw(this, videos[index_video]));
        OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                onChangeVideo();
            }

            @Override
            public void onSwipeLeft() {
                onChangeVideo();
            }
        };
        vvMain.setOnTouchListener(onSwipeTouchListener);
        vvMain.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });
        vvMain.seekTo(progress);
        vvMain.start();
    }


    private void onChangeVideo() {
        index_video = (index_video == 0 ? 1 : 0);
        vvMain.setVideoPath(Utils.getVideoPathByRaw(this, videos[index_video]));
        vvMain.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                onChangeVideo();
            }
        });
        vvMain.start();

    }

    public void initializedLogo() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivLogo.setImageResource(logos[counter_logo]);
                counter_logo++;
                if (counter_logo >= logos.length) {
                    counter_logo = 0;
                }
                initializedLogo();
            }
        }, DELAY_LOGO);
    }

    private void initializeHandlerAdvertisement() {
        if (handlerProgressVideo == null)
            handlerProgressVideo = new Handler();
        handlerProgressVideo.postDelayed(mRunUpdateProgressVideo, 1000);
    }


    public void showAdvertisement() {
        Intent openAdvertisement = new Intent(this, AdvertisementActivity.class);
        openAdvertisement.putExtra(KEY_ADVERTISEMENT_URL, "" + Utils.getVideoPathByRaw(this, R.raw.video_ten_second));
        startActivity(openAdvertisement);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPlayList:
                startActivity(new Intent(this, TabVideoActivity.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        progress = vvMain.getCurrentPosition();
        if (handlerProgressVideo != null)
            handlerProgressVideo.removeCallbacks(mRunUpdateProgressVideo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeMainVideo();
        initializeHandlerAdvertisement();
    }


    Runnable mRunUpdateProgressVideo = new Runnable() {
        @Override
        public void run() {
            double progressInSecond = Math.ceil(vvMain.getCurrentPosition() / 1000);
            if (progressInSecond == (DELAY_ADVERTISEMENT_IN_SECONDS - 1)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showAdvertisement();
                    }
                }, 1000);
            }
            initializeHandlerAdvertisement();
        }
    };
}
