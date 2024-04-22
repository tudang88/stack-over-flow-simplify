package com.androidarchitecture.stackoverflowclient.screens.questionslist;


import android.view.View;

import com.androidarchitecture.stackoverflowclient.questions.Question;

/**
 * the interface for making ListItem view abstraction
 */
public interface QuestionItemViewMvc {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestion(Question question);
    View getRootView();
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
}