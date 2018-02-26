package com.afuktju.oona.ui.main;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.afuktju.oona.R;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class VideoFragment extends Fragment {
    String videoUrl;
    private int progress = 0;
    public MediaPlayer.OnCompletionListener mListener;
    private static final String KEY_VIDEO_URL = "VIDEO_URL";
    private static final String KEY_PROGRESS = "PROGRESS";

    static VideoFragment init(String videoUrl) {
        VideoFragment videoFragment = new VideoFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putString(KEY_VIDEO_URL, videoUrl);
        videoFragment.setArguments(args);
        return videoFragment;
    }

    public void setListener(MediaPlayer.OnCompletionListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoUrl = getArguments().getString(KEY_VIDEO_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final VideoView videoView = (VideoView) inflater.inflate(R.layout.video_ch, container,
                false);
        if (videoView.hasFocus()) {
            initializeVideo(videoView);
        }
        return videoView;
    }

    @Override
    public void onPause() {
        super.onPause();
        VideoView videoView = (VideoView) getView();
        if (videoView != null && videoView.hasFocus()) {
            progress = videoView.getCurrentPosition();
        }

    }

    private void initializeVideo(final VideoView videoView) {
        videoView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {
                    videoView.setVideoPath(videoUrl);
                    videoView.seekTo(progress);
                    videoView.start();
                    videoView.setOnCompletionListener(mListener);
                    progress = 0;
                } else {
//                    progress = videoView.getCurrentPosition();
                    videoView.setOnCompletionListener(null);
                    videoView.stopPlayback();
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PROGRESS, progress);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            progress = savedInstanceState.getInt(KEY_PROGRESS);
        }

    }
}