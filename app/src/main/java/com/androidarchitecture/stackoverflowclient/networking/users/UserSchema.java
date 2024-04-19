package com.androidarchitecture.stackoverflowclient.networking.users;

import com.google.gson.annotations.SerializedName;

public class UserSchema {
    @SerializedName("display_name")
    private final String mUserDisplayName;
    @SerializedName("profile_image")
    private final String mUserAvatarUrl;

    @SerializedName("reputation")
    private final int mReputation;

    public UserSchema(String userDisplayName, String userAvatarUrl, int reputation) {
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
