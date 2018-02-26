package com.afuktju.oona.ui.main;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.afuktju.oona.Utils;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class VideoPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private int videos[] = {};
    MediaPlayer.OnCompletionListener mListener;

    public VideoPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    public void setup(Context context,int videos[]) {
        this.mContext=context;
        this.videos=videos;

    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener mListener){
        this.mListener=mListener;
    }


    @Override
    public int getCount() {
        return videos.length;
    }

    @Override
    public VideoFragment getItem(int position) {
        VideoFragment videoFragment = VideoFragment.init(Utils.getVideoPathByRaw(mContext, videos[position]));
        videoFragment.setListener(mListener);
        return videoFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}