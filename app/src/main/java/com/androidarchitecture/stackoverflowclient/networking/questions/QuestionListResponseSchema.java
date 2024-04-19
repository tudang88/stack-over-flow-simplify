package com.androidarchitecture.stackoverflowclient.networking.questions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionListResponseSchema {
    @SerializedName("items")
    private final List<QuestionSchema> mQuestions;

    public QuestionListResponseSchema(List<QuestionSchema> questions) {
        this.mQuestions = questions;
    }

    public List<QuestionSchema> getQuestions() {
        return mQuestions;
    }
}

