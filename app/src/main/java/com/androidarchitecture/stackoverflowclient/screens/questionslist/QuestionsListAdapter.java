package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidarchitecture.stackoverflowclient.R;
import com.androidarchitecture.stackoverflowclient.questions.Question;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Todo: refactor to use ArrayAdapter<Question> to
 * get rid of managing List<Question> manually
 */
public class QuestionsListAdapter extends BaseAdapter {
    private List<Question> mQuestionsList;
    private OnQuestionItemClickListener listener;

    public QuestionsListAdapter(List<Question> questionsList, OnQuestionItemClickListener listener) {
        this.mQuestionsList = questionsList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mQuestionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return mQuestionsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.list_item_design, viewGroup, false);
        }
        final Question item = (Question) getItem(i);

        // set title to layout
        ((TextView) convertView.findViewById(R.id.txtListItemTitle))
                .setText(item.getTitle());
        ((TextView) convertView.findViewById(R.id.txtScore))
                .setText(String.valueOf(item.getScore()));
        // get answer status icon
        @SuppressLint("UseCompatLoadingForDrawables") Drawable checkDrawable =
                viewGroup.getContext().getDrawable(item.isAnswer() ?
                        R.drawable.checked :
                        R.drawable.checked_inactive);
        ((ImageView) convertView.findViewById(R.id.imgAnswerStatus))
                .setImageDrawable(checkDrawable);
        ((TextView) convertView.findViewById(R.id.txtAnswerCount))
                .setText(String.valueOf(item.getAnswerCount()));
        ((TextView) convertView.findViewById(R.id.txtViewCount))
                .setText(String.valueOf(item.getViewCount()));

        // owner area
        ImageView avatar = convertView.findViewById(R.id.imgOwnerAvatar);
        String avatarUrl = item.getOwner().getUserAvatarUrl();
        Picasso.with(viewGroup.getContext()).load(avatarUrl).into(avatar);
        ((TextView) convertView.findViewById(R.id.txtOwnerName))
                .setText((item.getOwner().getUserDisplayName()));
        ((TextView) convertView.findViewById(R.id.txtOwnerReputation))
                .setText(String.valueOf(item.getOwner().getReputation()));

        // set delegate click event
        convertView.setOnClickListener(view-> listener.onQuestionClicked(item));
        return convertView;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.mQuestionsList = questionsList;
    }

    public List<Question> getQuestionsList() {
        return mQuestionsList;
    }

    /**
     * callback when user click on item
     */
    public interface OnQuestionItemClickListener {
        void onQuestionClicked(Question item);
    }
}
