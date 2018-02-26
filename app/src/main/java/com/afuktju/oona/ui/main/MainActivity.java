package com.afuktju.oona.ui.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.afuktju.oona.R;
import com.afuktju.oona.Utils;
import com.afuktju.oona.ui.playlist.TabVideoActivity;
import com.afuktju.oona.ui.advertisement.AdvertisementActivity;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    public final static int DELAY_ADVERTISEMENT = 30 * 1000;
    public final static int DELAY_LOGO = 10 * 1000;
    Handler mHandler;
    public int logos[] = {R.drawable.logo_facebook, 0, R.drawable.logo_linkedin, 0, R.drawable.logo_twitter, 0};
    public int videos[] = {R.raw.video_example_one, R.raw.video_example_two};
    private int counter_logo;
    ImageView ivLogo;
    private ViewPager viewpager;
    private VideoPagerAdapter mPagerAdapter;

    public static final String KEY_ADVERTISEMENT_URL = "ADVERTISEMENT_URL";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mHandler = new Handler();
        viewpager = findViewById(R.id.viewpager);
        ivLogo = findViewById(R.id.ivLogo);
        findViewById(R.id.ivPlayList).setOnClickListener(this);
        initializedLogo();
        initializeVideo();

    }

    public void initializeVideo() {
        viewpager = findViewById(R.id.viewpager);
        mPagerAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setup(this, videos);
        mPagerAdapter.setOnCompletionListener(this);
        viewpager.setAdapter(mPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showAdvertisement();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        showAdvertisement();

    }


    public void showAdvertisement() {
        mHandler.removeCallbacks(mRunAdvertisement);
        mHandler.postDelayed(mRunAdvertisement, DELAY_ADVERTISEMENT);
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

    Runnable mRunAdvertisement = new Runnable() {
        @Override
        public void run() {
            Intent openAdvertisement = new Intent(MainActivity.this, AdvertisementActivity.class);
            openAdvertisement.putExtra(KEY_ADVERTISEMENT_URL, "" + Utils.getVideoPathByRaw(MainActivity.this, R.raw.video_ten_second));
            startActivity(openAdvertisement);
            overridePendingTransition(0, 0);

        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPlayList:
                startActivity(new Intent(this, TabVideoActivity.class));
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        viewpager.setCurrentItem(viewpager.getCurrentItem() == 0 ? 1 : 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPagerAdapter.notifyDataSetChanged();
    }


}
