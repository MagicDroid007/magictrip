package com.magicdroid.magictrip.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicdroid.magictrip.R;

public class WalkThroughAdapter extends PagerAdapter {

    private Activity mContext;

    public WalkThroughAdapter(Activity context) {
        mContext = context;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_walkthrough, container, false);

        TextView walkthroughMessageFirstLine = (TextView) view.findViewById(R.id.txtFirstMessage);
        TextView walkthroughMessageSecondLine = (TextView) view.findViewById(R.id.txtSecondMessage);

        switch (position) {
            case 1:
                walkthroughMessageFirstLine.setText("All your trip details\nin one spot.");
                walkthroughMessageSecondLine.setText("Get personalized activity ideas based\non the time, place and weather.");
                break;
            case 2:
                walkthroughMessageFirstLine.setText("Stress-free planning");
                walkthroughMessageSecondLine.setText("Easily find your reservations from\nfrom our huge database");
                break;
            case 3:
                walkthroughMessageFirstLine.setText("No Internet? No Problem");
                walkthroughMessageSecondLine.setText("Browse your trip info even\nwith no internet connection");
                break;
        }




        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        ((ViewPager) container).removeView(view);
    }

}