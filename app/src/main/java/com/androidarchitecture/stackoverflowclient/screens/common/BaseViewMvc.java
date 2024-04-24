package com.androidarchitecture.stackoverflowclient.screens.common;

import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc implements ViewMvc {
    private View mRootView;
    protected void setRootView(View mRootView) {
        this.mRootView = mRootView;
    }

    protected Context getContext() {
        return getRootView().getContext();
    }
    protected <T extends View> T findViewById(int resId) {
        return getRootView().findViewById(resId);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }
}
