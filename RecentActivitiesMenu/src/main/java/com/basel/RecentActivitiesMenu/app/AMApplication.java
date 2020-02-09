package com.basel.RecentActivitiesMenu.app;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import com.basel.RecentActivitiesMenu.model.activity;
import java.util.ArrayList;
import java.util.List;

public class AMApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private int mTopActivityCode;
    private ArrayList<Integer> recentActivitiesCodes = new ArrayList<>();
    private SparseArray<ComponentName> recentActivitiesComponents = new SparseArray<>();
    private SparseArray<Bitmap> recentActivitiesViews = new SparseArray<>();
    private SparseArray<Bundle> recentActivitiesBundles = new SparseArray<>();

    private static AMApplication mInstance;
    public static synchronized AMApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        registerActivityLifecycleCallbacks(AMApplication.this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mTopActivityCode = activity.hashCode();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        String mCallingPackage = activity.getCallingPackage();
        if(mCallingPackage!=null && !mCallingPackage.equals(getPackageName())) {
            return;
        }
        int mActivityCode = activity.hashCode();
        boolean cached = proccessActivityView(activity);
        if(cached&&!recentActivitiesCodes.contains(mActivityCode)){
            Bundle bundle = activity.getIntent().getExtras();
            recentActivitiesCodes.add(mActivityCode);
            recentActivitiesBundles.put(mActivityCode, bundle);
            recentActivitiesComponents.put(mActivityCode, activity.getComponentName());
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private boolean proccessActivityView(Activity activity) {
        View windowView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if(windowView==null)return false;
        View screenView = windowView.getRootView();
        if(screenView==null)return false;
        screenView.setDrawingCacheEnabled(true);
        screenView.buildDrawingCache(true);
        Bitmap cashBitmap = screenView.getDrawingCache();
        if(cashBitmap==null)return false;
        Bitmap screenBitmap = Bitmap.createBitmap(cashBitmap);
        screenView.setDrawingCacheEnabled(false);
        Bitmap mLastBitmap =  Bitmap.createScaledBitmap(screenBitmap,
                (screenBitmap.getWidth()/4), (screenBitmap.getHeight()/4), false);
        recentActivitiesViews.put(activity.hashCode(), mLastBitmap);
        return true;
    }

    public List<activity> getActivitiesView() {
        List<activity> list = new ArrayList<>();
        for(int code : recentActivitiesCodes){
            if(code!=mTopActivityCode){
                list.add(new activity(
                        recentActivitiesComponents.get(code),
                        recentActivitiesViews.get(code),
                        recentActivitiesBundles.get(code),
                        code
                ));
            }
        }
        return list;
    }


    //for swipe to remove later
    public void removeFromRecent(int mActivityCode) {
        recentActivitiesCodes.remove(Integer.valueOf(mActivityCode));//box to remove integer object not index
        recentActivitiesComponents.remove(mActivityCode);
        recentActivitiesViews.remove(mActivityCode);
        recentActivitiesBundles.remove(mActivityCode);
    }

    public void clearRecent() {
        mTopActivityCode = -1;
        recentActivitiesCodes.clear();
        recentActivitiesComponents.clear();
        recentActivitiesViews.clear();
    }

}
