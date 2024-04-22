package com.androidarchitecture.stackoverflowclient.screens.questionslist;


/**
 * the interface for Control or Activity observer change in View
 * @param <ListenerType>
 */
public interface ObservableViewMvc<ListenerType> {
    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);
}