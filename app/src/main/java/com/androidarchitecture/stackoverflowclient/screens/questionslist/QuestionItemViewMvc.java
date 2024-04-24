package com.androidarchitecture.stackoverflowclient.screens.questionslist;



import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.androidarchitecture.stackoverflowclient.screens.common.ObservableViewMvc;

/**
 * the interface for making ListItem view abstraction
 */
public interface QuestionItemViewMvc extends ObservableViewMvc<QuestionItemViewMvc.Listener> {
    interface Listener {
        void onQuestionClicked(Question question);
    }
    void bindQuestion(Question question);
}