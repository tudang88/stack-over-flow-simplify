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
        // set title to layout
        ((TextView) convertView.findViewById(R.id.txtListItemTitle))
                .setText(((Question) getItem(i)).getTitle());
        ((TextView) convertView.findViewById(R.id.txtScore))
                .setText(String.valueOf(((Question) getItem(i)).getScore()));
        // get answer status icon
        @SuppressLint("UseCompatLoadingForDrawables") Drawable checkDrawable =
                viewGroup.getContext().getDrawable(((Question) getItem(i)).isAnswer() ?
                        R.drawable.checked :
                        R.drawable.checked_inactive);
        ((ImageView) convertView.findViewById(R.id.imgAnswerStatus))
                .setImageDrawable(checkDrawable);
        ((TextView) convertView.findViewById(R.id.txtAnswerCount))
                .setText(String.valueOf(((Question) getItem(i)).getAnswerCount()));
        ((TextView) convertView.findViewById(R.id.txtViewCount))
                .setText(String.valueOf(((Question) getItem(i)).getViewCount()));

        // owner area
        ImageView avatar = convertView.findViewById(R.id.imgOwnerAvatar);
        String avatarUrl = ((Question) getItem(i)).getOwner().getUserAvatarUrl();
        Picasso.with(viewGroup.getContext()).load(avatarUrl).into(avatar);
        ((TextView) convertView.findViewById(R.id.txtOwnerName))
                .setText((((Question) getItem(i)).getOwner().getUserDisplayName()));
        ((TextView) convertView.findViewById(R.id.txtOwnerReputation))
                .setText(String.valueOf(((Question) getItem(i)).getOwner().getReputation()));

        // set delegate click event
        ConstraintLayout qLayout = convertView.findViewById(R.id.layoutQuestion);
        Question item = (Question) getItem(i);
        qLayout.setOnClickListener(view -> listener.onQuestionClicked(item));

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
