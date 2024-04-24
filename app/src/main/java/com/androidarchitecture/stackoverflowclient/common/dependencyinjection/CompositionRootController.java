package com.androidarchitecture.stackoverflowclient.common.dependencyinjection;

import android.app.Activity;
import android.view.LayoutInflater;

import com.androidarchitecture.stackoverflowclient.networking.StackoverflowApi;
import com.androidarchitecture.stackoverflowclient.screens.common.MvcViewFactory;

public class CompositionRootController {
    private final CompositionRoot mCompositionRoot;
    private final Activity mActivity;
    public CompositionRootController(CompositionRoot compositionRoot, Activity activity) {
        this.mCompositionRoot = compositionRoot;
        this.mActivity = activity;
    }

    public StackoverflowApi getStackOverService() {
        return mCompositionRoot.getStackOverService();
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    public MvcViewFactory getMvcViewFactory() {
        return new MvcViewFactory(getLayoutInflater());
    }
}
