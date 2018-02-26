package com.afuktju.oona.ui.playlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afuktju.oona.R;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class TabVideoActivity extends AppCompatActivity {

    TabVideoPagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    String arrays[][] = {{"alpha 0", "alpha 1", "alpha 2"}, {"beta 0", "beta 1", "beta 2"}};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_video);
        pagerAdapter = new TabVideoPagerAdapter(this, arrays);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
