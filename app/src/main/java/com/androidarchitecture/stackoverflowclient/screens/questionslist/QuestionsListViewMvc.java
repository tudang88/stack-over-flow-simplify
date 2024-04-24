package com.androidarchitecture.stackoverflowclient.screens.questionslist;


import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.androidarchitecture.stackoverflowclient.screens.common.ObservableViewMvc;

import java.util.List;

public interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question q);
    }
    void bindQuestions(List<Question> questions);
}
