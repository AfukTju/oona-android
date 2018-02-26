package com.afuktju.oona.ui.advertisement;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.afuktju.oona.R;
import com.afuktju.oona.ui.main.MainActivity;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class AdvertisementActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    VideoView videoAdvertisement;
    private int progress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        videoAdvertisement = findViewById(R.id.videoAdvertisement);
        videoAdvertisement.setOnCompletionListener(this);

    }

    private void initializeVideo() {
        videoAdvertisement.setVideoPath(getIntent().getStringExtra(MainActivity.KEY_ADVERTISEMENT_URL));
        videoAdvertisement.seekTo(progress);
        videoAdvertisement.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        progress = videoAdvertisement.getCurrentPosition();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        finish();
    }

    @Override
    public void onBackPressed() {

    }


}