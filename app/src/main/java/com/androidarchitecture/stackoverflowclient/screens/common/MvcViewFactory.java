package com.androidarchitecture.stackoverflowclient.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionItemViewMvc;
import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionItemViewMvcImpl;
import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionsListViewMvc;
import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionsListViewMvcImpl;

public class MvcViewFactory {
    public final LayoutInflater mLayoutInflater;

    public MvcViewFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListViewMvcImpl(mLayoutInflater, parent, this);
    }

    public QuestionItemViewMvc getQuestionItemViewMvc(@Nullable ViewGroup parent) {
        return new QuestionItemViewMvcImpl(mLayoutInflater, parent);
    }
}
