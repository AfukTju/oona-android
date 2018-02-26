package com.afuktju.oona.ui.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afuktju.oona.R;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class TabVideoItemAdapter extends RecyclerView.Adapter<TabVideoItemAdapter.TabItemHolder> {


    private String arrays[];

    public TabVideoItemAdapter(String arrays[]) {
        this.arrays = arrays;
    }

    @Override
    public TabItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_video_item_ch, parent, false);
        return new TabItemHolder(v);
    }


    @Override
    public void onBindViewHolder(TabItemHolder holder, int position) {
        String value =arrays[position];
        holder.textView.setText(value);

    }

    @Override
    public int getItemCount() {
        return arrays.length;
    }


    public class TabItemHolder extends RecyclerView.ViewHolder {
        private TextView textView;


        public TabItemHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

    }


}