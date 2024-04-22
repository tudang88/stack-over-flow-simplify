package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuestionItemViewMvcImpl implements QuestionItemViewMvc {
    private final List<Listener> mObservers = new ArrayList<>();
    private final View mRootView;
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
        mRootView = inflater.
                inflate(R.layout.list_item_design, parent, false);
        txtTitle = mRootView.findViewById(R.id.txtListItemTitle);
        txtScore = mRootView.findViewById(R.id.txtScore);
        imgAnswerStatus = mRootView.findViewById(R.id.imgAnswerStatus);
        txtAnswerCount = mRootView.findViewById(R.id.txtAnswerCount);
        txtViewCount = mRootView.findViewById(R.id.txtViewCount);
        // owner area
        imgOwnerAvatar = mRootView.findViewById(R.id.imgOwnerAvatar);
        txtOwnerName = mRootView.findViewById(R.id.txtOwnerName);
        txtOwnerReputation = mRootView.findViewById(R.id.txtOwnerReputation);
        // set on click listener
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Listener listener:mObservers) {
                    listener.onQuestionClicked(mQuestion);
                }
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
                mRootView.getContext().getDrawable(question.isAnswer() ?
                        R.drawable.checked :
                        R.drawable.checked_inactive);
        imgAnswerStatus.setImageDrawable(checkDrawable);
        txtAnswerCount.setText(String.valueOf(question.getAnswerCount()));
        txtViewCount.setText(String.valueOf(question.getViewCount()));

        // owner area
        String avatarUrl = question.getOwner().getUserAvatarUrl();
        Picasso.with(mRootView.getContext()).load(avatarUrl).into(imgOwnerAvatar);
        txtOwnerName.setText((question.getOwner().getUserDisplayName()));
        txtOwnerReputation.setText(String.valueOf(question.getOwner().getReputation()));

    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void registerListener(Listener listener) {
        mObservers.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mObservers.remove(listener);
    }
}
