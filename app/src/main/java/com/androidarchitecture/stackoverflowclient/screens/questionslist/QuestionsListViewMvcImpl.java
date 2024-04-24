package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.androidarchitecture.stackoverflowclient.screens.common.BaseObservableViewMvc;

import java.util.List;

public class QuestionsListViewMvcImpl
        extends BaseObservableViewMvc<QuestionsListViewMvc.Listener>
        implements QuestionListRecyclerViewAdapter.QuestionClickListener,
        QuestionsListViewMvc {

    private final QuestionListRecyclerViewAdapter mQuestionListAdapter;
    /**
     * extract the view implementation of
     * main activity to MVC view
     */
    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.activity_main, parent, false));
        // setup adapter for list view
        RecyclerView mListView = findViewById(R.id.questionListView);
        mQuestionListAdapter = new QuestionListRecyclerViewAdapter(this);
        mListView.setAdapter(mQuestionListAdapter);
    }


    @Override
    public void onQuestionClicked(Question item) {
        for(Listener listener: getObservers()) {
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
