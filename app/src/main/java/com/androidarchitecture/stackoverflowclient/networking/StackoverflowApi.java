package com.androidarchitecture.stackoverflowclient.networking;

import com.androidarchitecture.stackoverflowclient.common.Constants;
import com.androidarchitecture.stackoverflowclient.networking.questions.QuestionListResponseSchema;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackoverflowApi {
    @GET("questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&order=desc&sort=activity&site=stackoverflow&filter=withbody")
    Call<QuestionListResponseSchema> fetchLastActiveQuestions(@Query("pagesize") Integer pageSize);
}
