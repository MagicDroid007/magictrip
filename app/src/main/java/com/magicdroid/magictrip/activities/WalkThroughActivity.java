package com.magicdroid.magictrip.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.magicdroid.magictrip.R;
import com.magicdroid.magictrip.adapters.WalkThroughAdapter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WalkThroughActivity extends BaseActivity {
    private ImageView[] mDots;
    private WalkThroughAdapter mWalkThroughAdapter;
    private Subscription mSubscribe;
    private int mDotsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        TextView getStartedText = (TextView) findViewById(R.id.walkthroughGetStarted);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.walkThroughViewPager);

        mWalkThroughAdapter = new WalkThroughAdapter(this);
        viewPager.setAdapter(mWalkThroughAdapter);
        setUiPageViewController();

        shimmerFrameLayout.setDuration(3000);
        shimmerFrameLayout.setRepeatMode(ObjectAnimator.REVERSE);
        shimmerFrameLayout.startShimmerAnimation();

        mSubscribe = Observable.interval(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {

                        int currentPos = viewPager.getCurrentItem() + 1;
                        viewPager.setCurrentItem(currentPos == 3 ? 0 : currentPos, true);

                    }
                });

        getStartedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mDotsCount; i++) {
                    mDots[i].setImageResource(R.drawable.gray_circle);
                }
                mDots[position].setImageResource(R.drawable.red_circle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setUiPageViewController() {
        if (mDots == null) {
            LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots1);
             mDotsCount = mWalkThroughAdapter.getCount();

            mDots = new ImageView[mDotsCount];

            for (int i = 0; i < mDotsCount; i++) {
                mDots[i] = new ImageView(this);
                mDots[i].setImageResource(R.drawable.gray_circle);
                dotsLayout.addView(mDots[i]);
                setMargins(mDots[i], 0, 0, 20, 0);

            }
            mDots[0].setImageResource(R.drawable.red_circle);
        }

    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }


    @Override
    protected void onDestroy() {
        if (mSubscribe != null && !mSubscribe.isUnsubscribed())
            mSubscribe.unsubscribe();
        super.onDestroy();
    }
}
