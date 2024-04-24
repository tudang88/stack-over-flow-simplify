package com.androidarchitecture.stackoverflowclient.screens.common;

import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionItemViewMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseObservableViewMvc<ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {
    private Set<ListenerType> mObservers = new HashSet<>();


    @Override
    public void registerListener(ListenerType listener) {
        mObservers.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        mObservers.remove(listener);
    }

    public Set<ListenerType> getObservers() {
        return Collections.unmodifiableSet(mObservers);
    }
}
