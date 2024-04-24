package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements
        QuestionListRecyclerViewAdapter.QuestionClickListener,
        QuestionsListViewMvc<QuestionsListViewMvc.Listener> {
    @Override
    public void registerListener(Listener listener) {
        mObservers.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mObservers.remove(listener);
    }

    private final View mvcRootView;
    private final QuestionListRecyclerViewAdapter mQuestionListAdapter;
    private final List<Listener> mObservers = new ArrayList<>();
    /**
     * extract the view implementation of
     * main activity to MVC view
     */
    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        mvcRootView = inflater.inflate(R.layout.activity_main, parent, false);
        // setup adapter for list view
        RecyclerView mListView = findViewById(R.id.questionListView);
        mQuestionListAdapter = new QuestionListRecyclerViewAdapter(this);
        mListView.setAdapter(mQuestionListAdapter);
    }

    private <T extends View> T findViewById(int resId) {
        return mvcRootView.findViewById(resId);
    }

    @Override
    public View getMvcRootView() {
        return mvcRootView;
    }

    @Override
    public void onQuestionClicked(Question item) {
        for(Listener listener: mObservers) {
            listener.onQuestionClicked(item);
        }
    }

    /**
     * binding question list to
     * list view
     * @param questions
     */
    @Override
    public void bindQuestions(List<Question> questions) {
        mQuestionListAdapter.submitList(questions);

    }
}
