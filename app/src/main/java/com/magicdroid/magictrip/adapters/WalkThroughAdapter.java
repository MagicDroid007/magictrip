package com.magicdroid.magictrip.adapters;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
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
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_walkthrough, container, false);

        TextView firstTV = (TextView) view.findViewById(R.id.txtFirstMessage);
        TextView secondTV = (TextView) view.findViewById(R.id.txtSecondMessage);

        switch (position) {
            case 1:
                firstTV.setText("All your trip details\nin one spot.");
                secondTV.setText("Get personalized activity ideas based\non the time, place and weather.");
                break;
            case 2:
                firstTV.setText("Stress-free planning");
                secondTV.setText("Easily find your reservations from\nfrom our huge database");
                break;
            case 3:
                firstTV.setText("No Internet? No Problem");
                secondTV.setText("Browse your trip info even\nwith no internet connection");
                break;
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}