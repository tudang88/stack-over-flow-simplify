package com.androidarchitecture.stackoverflowclient.screens.common.controllers;

import androidx.appcompat.app.AppCompatActivity;

import com.androidarchitecture.stackoverflowclient.common.dependencyinjection.CompositionRoot;
import com.androidarchitecture.stackoverflowclient.screens.CustomApplication;

public class BaseActivity extends AppCompatActivity {
    protected CompositionRoot getCompositionRoot() {
        return ((CustomApplication) getApplication()).getCompositionRoot();
    }
}
