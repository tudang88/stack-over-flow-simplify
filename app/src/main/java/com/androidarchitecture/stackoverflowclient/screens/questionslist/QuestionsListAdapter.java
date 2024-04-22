package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Todo: refactor to use ArrayAdapter<Question> to
 * get rid of managing List<Question> manually
 */
public class QuestionsListAdapter extends BaseAdapter implements QuestionItemViewMvc.Listener {
    private List<Question> mQuestionsList;
    private final OnQuestionItemClickListener listener;

    public QuestionsListAdapter(List<Question> questionsList, OnQuestionItemClickListener listener) {
        this.mQuestionsList = questionsList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mQuestionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return mQuestionsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            QuestionItemViewMvc mvcView = new QuestionItemViewMvcImpl(LayoutInflater.from(viewGroup.getContext()), viewGroup);
            // register item click listener
            mvcView.registerListener(this);
            convertView = mvcView.getRootView();
            convertView.setTag(mvcView);
        }
        final Question item = (Question) getItem(i);
        // binding data
        QuestionItemViewMvc itemViewMvc = (QuestionItemViewMvc) convertView.getTag();
        itemViewMvc.bindQuestion(item);
        return convertView;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.mQuestionsList = questionsList;
    }

    @Override
    public void onQuestionClicked(Question question) {
        listener.onQuestionClicked(question);
    }

    /**
     * callback when user click on item
     */
    public interface OnQuestionItemClickListener {
        void onQuestionClicked(Question item);
    }
}
