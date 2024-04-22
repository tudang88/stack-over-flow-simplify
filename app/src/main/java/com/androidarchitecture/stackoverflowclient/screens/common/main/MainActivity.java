package com.androidarchitecture.stackoverflowclient.screens.common.main;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.common.Constants;
import com.androidarchitecture.stackoverflowclient.networking.StackoverflowApi;
import com.androidarchitecture.stackoverflowclient.networking.StackoverflowApiService;
import com.androidarchitecture.stackoverflowclient.networking.questions.QuestionListResponseSchema;
import com.androidarchitecture.stackoverflowclient.networking.questions.QuestionSchema;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.androidarchitecture.stackoverflowclient.questions.User;
import com.androidarchitecture.stackoverflowclient.screens.common.controllers.BaseActivity;
import com.androidarchitecture.stackoverflowclient.screens.questionslist.QuestionsListViewMvcImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements QuestionsListViewMvcImpl.OnQuestionClickedListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private final StackoverflowApi mService = StackoverflowApiService.getInstance().getService();
    private QuestionsListViewMvcImpl mQuestionsListMvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionsListMvcView = new QuestionsListViewMvcImpl(getLayoutInflater(), null);
        mQuestionsListMvcView.registerListener(this);
        setContentView(mQuestionsListMvcView.getMvcRootView());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQuestionsListMvcView.unregisterListener(this);
    }

    private void fetchQuestionList() {
        mService.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE).enqueue(
                new Callback<QuestionListResponseSchema>() {
                    @Override
                    public void onResponse(@NonNull Call<QuestionListResponseSchema> call, @NonNull Response<QuestionListResponseSchema> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            bindQuestions(response.body().getQuestions());
                        } else {
                            Log.e(TAG, "onResponse@ Failure");
                            networkFailure();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<QuestionListResponseSchema> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure@ Failure");
                        networkFailure();
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestionList();
    }

    private void bindQuestions(List<QuestionSchema> response) {
        List<Question> questions = new ArrayList<>(response.size());
        for (QuestionSchema q : response) {
            questions.add(new Question(q.getId(), q.getTitle(),
                    q.getBody(), q.getScore(),
                    q.getAnswerCount(), q.getViewCount(),
                    q.isAnswer(),
                    new User(q.getOwner().getUserDisplayName(),
                            q.getOwner().getUserAvatarUrl(),
                            q.getOwner().getReputation())));
        }
        // update adapter
        mQuestionsListMvcView.bindQuestions(questions);
    }

    private void networkFailure() {
        Toast.makeText(this, R.string.network_failed, Toast.LENGTH_SHORT).show();
    }

    /**
     * onClick question item
     *
     * @param item
     */
    @Override
    public void onQuestionClicked(Question item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}