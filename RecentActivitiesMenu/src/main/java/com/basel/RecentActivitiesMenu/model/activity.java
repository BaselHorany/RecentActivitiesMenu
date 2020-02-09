package com.basel.RecentActivitiesMenu.model;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Bundle;

public class activity {

    private ComponentName component;
    private Bitmap view;
    private Bundle bundle;
    private int code;

    public activity(ComponentName component, Bitmap view, Bundle bundle, int code) {
        this.component = component;
        this.view = view;
        this.bundle = bundle;
        this.code = code;
    }

    public ComponentName getComponent() {
        return component;
    }

    public void setComponent(ComponentName component) {
        this.component = component;
    }

    public Bitmap getView() {
        return view;
    }

    public void setView(Bitmap view) {
        this.view = view;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
