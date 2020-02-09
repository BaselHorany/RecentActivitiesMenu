package com.basel.RecentActivitiesMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.basel.RecentActivitiesMenu.adapters.ActivitiesAdapter;
import com.basel.RecentActivitiesMenu.app.AMApplication;
import com.basel.RecentActivitiesMenu.model.activity;
import com.basel.RecentActivitiesMenu.utils.CarouselLinearLayoutManager;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivitiesMenuDrawer extends DrawerLayout {

    private RecyclerView rv;
    private List<activity> list = new ArrayList<>();
    private boolean isRecentBelowHeader,recentMenuAdded;
    private ActivitiesAdapter adapter;
    private NavigationView mDrawerView;

    public ActivitiesMenuDrawer(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ActivitiesMenuDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ActivitiesMenuDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        NestedScrollView.LayoutParams rvParams = new NestedScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (metrics.heightPixels)/4);
        rv = new RecyclerView(context);
        rv.setLayoutParams(rvParams);
        CarouselLinearLayoutManager mCarouselLinearLayoutManager = new CarouselLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                true);
        rv.setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING);
        rv.setLayoutManager(mCarouselLinearLayoutManager);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        rv.setVerticalFadingEdgeEnabled(false);
        rv.setHorizontalFadingEdgeEnabled(false);
        rv.setOverScrollMode(OVER_SCROLL_NEVER);

        adapter = new ActivitiesAdapter(context, list);
        rv.setAdapter(adapter);

        addDrawerListener(new SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                recentMenuAdded = false;
                List<activity> actlist = AMApplication.getInstance().getActivitiesView();
                if(actlist.isEmpty())return;
                mDrawerView = (NavigationView)drawerView;
                if(!isRecentBelowHeader) {
                    View mView = mDrawerView.getHeaderView(0);
                    if (mView != null) mDrawerView.removeHeaderView(mView);
                    mDrawerView.addHeaderView(rv);
                    if (mView != null) mDrawerView.addHeaderView(mView);
                }else{
                    mDrawerView.addHeaderView(rv);
                }
                recentMenuAdded = true;
                for(activity act : actlist){
                    list.add(act);
                    adapter.notifyDataSetChanged();
                }
                actlist.clear();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                list.clear();
                adapter.notifyDataSetChanged();
                if(recentMenuAdded)mDrawerView.removeHeaderView(rv);
            }

        });

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setRecentBelowHeader(boolean recentBelowHeader) {
        isRecentBelowHeader = recentBelowHeader;
    }

}
