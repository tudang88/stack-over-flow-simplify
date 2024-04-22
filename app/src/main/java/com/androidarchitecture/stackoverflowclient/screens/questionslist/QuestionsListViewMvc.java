package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.view.View;

import com.androidarchitecture.stackoverflowclient.questions.Question;

import java.util.List;

public interface QuestionsListViewMvc<ListenerType> {
    interface Listener {
        void onQuestionClicked(Question q);
    }
    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);
    View getMvcRootView();

    void bindQuestions(List<Question> questions);

}
