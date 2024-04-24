package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.androidarchitecture.stackoverflowclient.screens.common.BaseObservableViewMvc;
import com.squareup.picasso.Picasso;


public class QuestionItemViewMvcImpl
        extends BaseObservableViewMvc<QuestionItemViewMvc.Listener>
        implements QuestionItemViewMvc {
    private final TextView txtTitle;
    private final TextView txtScore;
    private final ImageView imgAnswerStatus;
    private final TextView txtAnswerCount;
    private final TextView txtViewCount;
    private final ImageView imgOwnerAvatar;
    private final TextView txtOwnerName;
    private final TextView txtOwnerReputation;
    private Question mQuestion;
    public QuestionItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.
                inflate(R.layout.list_item_design, parent, false));
        txtTitle = findViewById(R.id.txtListItemTitle);
        txtScore = findViewById(R.id.txtScore);
        imgAnswerStatus = findViewById(R.id.imgAnswerStatus);
        txtAnswerCount = findViewById(R.id.txtAnswerCount);
        txtViewCount = findViewById(R.id.txtViewCount);
        // owner area
        imgOwnerAvatar = findViewById(R.id.imgOwnerAvatar);
        txtOwnerName = findViewById(R.id.txtOwnerName);
        txtOwnerReputation = findViewById(R.id.txtOwnerReputation);
        // set on click listener
        getRootView().setOnClickListener(view -> {
            for (Listener listener:getObservers()) {
                listener.onQuestionClicked(mQuestion);
            }
        });
    }

    @Override
    public void bindQuestion(Question question) {
        // store question
        mQuestion = question;
        // binding to view
        txtTitle.setText(question.getTitle());
        txtScore.setText(String.valueOf(question.getScore()));
        // get answer status icon
        @SuppressLint("UseCompatLoadingForDrawables") Drawable checkDrawable =
                getContext().getDrawable(question.isAnswer() ?
                        R.drawable.checked :
                        R.drawable.checked_inactive);
        imgAnswerStatus.setImageDrawable(checkDrawable);
        txtAnswerCount.setText(String.valueOf(question.getAnswerCount()));
        txtViewCount.setText(String.valueOf(question.getViewCount()));

        // owner area
        String avatarUrl = question.getOwner().getUserAvatarUrl();
        Picasso.with(getContext()).load(avatarUrl).into(imgOwnerAvatar);
        txtOwnerName.setText((question.getOwner().getUserDisplayName()));
        txtOwnerReputation.setText(String.valueOf(question.getOwner().getReputation()));

    }
}
