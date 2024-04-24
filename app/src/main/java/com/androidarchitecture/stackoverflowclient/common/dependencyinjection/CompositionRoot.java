package com.androidarchitecture.stackoverflowclient.common.dependencyinjection;

import com.androidarchitecture.stackoverflowclient.networking.StackoverflowApi;
import com.androidarchitecture.stackoverflowclient.networking.StackoverflowApiService;

public class CompositionRoot {
    public StackoverflowApi mStackOverService;

    public StackoverflowApi getStackOverService() {
        if (mStackOverService == null) {
            mStackOverService = StackoverflowApiService.getInstance().getService();
        }
        return mStackOverService;
    }
}
