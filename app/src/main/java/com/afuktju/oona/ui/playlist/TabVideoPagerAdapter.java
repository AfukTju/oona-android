package com.afuktju.oona.ui.playlist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afuktju.oona.R;
import com.afuktju.oona.SpacesItemDecoration;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class TabVideoPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String values[][];
    public final static int ALPHA_STATE = 0;
    public final static int BETA_STATE = 1;

    public TabVideoPagerAdapter(Context context, String values[][]) {
        mContext = context;
        this.values = values;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.tab_video_ch, collection, false);
        RecyclerView rvItems = layout.findViewById(R.id.rvItems);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rvItems.setLayoutManager(layoutManager);
        TabVideoItemAdapter adapter = new TabVideoItemAdapter(values[position]);
        rvItems.addItemDecoration(new SpacesItemDecoration((int) mContext.getResources().getDimension(R.dimen.line_space_5dp)));
        rvItems.setAdapter(adapter);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ALPHA_STATE:
                return "Alpha";
            case BETA_STATE:
                return "Beta";

            default:
                return super.getPageTitle(position);

        }

    }
}
