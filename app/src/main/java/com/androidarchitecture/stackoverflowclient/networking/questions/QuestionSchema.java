package com.androidarchitecture.stackoverflowclient.networking.questions;

import com.androidarchitecture.stackoverflowclient.networking.users.UserSchema;
import com.google.gson.annotations.SerializedName;

public class QuestionSchema {
    @SerializedName("title")
    private final String mTitle;
    @SerializedName("question_id")
    private final String mId;
    @SerializedName("body")
    private final String mBody;
    @SerializedName("owner")
    private final UserSchema mOwner;
    @SerializedName("is_answered")
    private final boolean isAnswer;
    @SerializedName("answer_count")
    private final int mAnswerCount;
    @SerializedName("view_count")
    private final int mViewCount;
    @SerializedName("score")
    private final int mScore;

    public QuestionSchema(String title, String id, String body,
                          UserSchema owner, boolean answer,
                          int answerCount, int viewCount,
                          int score) {
        this.mTitle = title;
        this.mId = id;
        this.mBody = body;
        this.mOwner = owner;
        this.isAnswer = answer;
        this.mAnswerCount = answerCount;
        this.mViewCount = viewCount;
        this.mScore = score;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public String getBody() {
        return mBody;
    }

    public UserSchema getOwner() {
        return mOwner;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public int getViewCount() {
        return mViewCount;
    }

    public int getScore() {
        return mScore;
    }
}
