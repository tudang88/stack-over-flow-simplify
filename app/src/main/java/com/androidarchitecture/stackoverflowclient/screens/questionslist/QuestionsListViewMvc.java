package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.view.View;

import com.androidarchitecture.stackoverflowclient.questions.Question;

import java.util.List;

public interface QuestionsListViewMvc {
    interface OnQuestionClickedListener {
        void onQuestionClicked(Question q);
    }

    View getMvcRootView();

    void bindQuestions(List<Question> questions);

}
