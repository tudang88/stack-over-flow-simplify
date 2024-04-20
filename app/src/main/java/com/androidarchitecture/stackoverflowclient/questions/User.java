package com.androidarchitecture.stackoverflowclient.questions;

public class User {
    private final String mUserDisplayName;
    private final String mUserAvatarUrl;
    private final int mReputation;

    public User(String userDisplayName, String userAvatarUrl, int reputation) {
        this.mUserDisplayName = userDisplayName;
        this.mUserAvatarUrl = userAvatarUrl;
        this.mReputation = reputation;
    }

    public String getUserDisplayName() {
        return mUserDisplayName;
    }

    public String getUserAvatarUrl() {
        return mUserAvatarUrl;
    }

    public int getReputation() {
        return mReputation;
    }
}
