package com.androidarchitecture.stackoverflowclient.screens.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import com.androidarchitecture.stackoverflowclient.common.dependencyinjection.CompositionRoot;
import com.androidarchitecture.stackoverflowclient.common.dependencyinjection.CompositionRootController;
import com.androidarchitecture.stackoverflowclient.screens.CustomApplication;

public class BaseActivity extends AppCompatActivity {
    private CompositionRootController mCompositionRootController;
    protected CompositionRootController getCompositionRoot() {
        if (mCompositionRootController == null) {
            mCompositionRootController = new CompositionRootController(new CompositionRoot(), this);
        }
        return mCompositionRootController;
    }
}
