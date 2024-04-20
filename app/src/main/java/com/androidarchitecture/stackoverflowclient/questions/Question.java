package com.androidarchitecture.stackoverflowclient.questions;

public class Question {
    private final String mId;
    private final String mTitle;
    private final String mBody;
    private final int mScore;
    private final boolean mIsAnswer;
    private final int mAnswerCount;
    private final int mViewCount;

    private final User mOwner;
    public Question(String id, String title, String body, int score, int answerCount, int viewCount, boolean isAnswer, User owner) {
        this.mId = id;
        this.mTitle = title;
        this.mBody = body;
        this.mScore = score;
        this.mAnswerCount = answerCount;
        this.mViewCount = viewCount;
        this.mIsAnswer = isAnswer;
        this.mOwner = owner;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public int getScore() {
        return mScore;
    }

    public boolean isAnswer() {
        return mIsAnswer;
    }

    public int getAnswerCount() {
        return mAnswerCount;
    }

    public int getViewCount() {
        return mViewCount;
    }

    public User getOwner() {
        return mOwner;
    }
}
