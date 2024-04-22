package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.content.Context;
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

    /**
     * Apply the ViewHolder pattern
     * to reduce the findViewById frequency
     */
    private static class ViewHolder {
        private TextView txtTitle;
        private TextView txtScore;
        private ImageView imgAnswerStatus;
        private TextView txtAnswerCount;
        private TextView txtViewCount;
        private ImageView imgOwnerAvatar;
        private TextView txtOwnerName;
        private TextView txtOwnerReputation;

        /**
         * binding view to viewHolder
         *
         * @param convertView
         */
        void bindingView(View convertView) {
            txtTitle = convertView.findViewById(R.id.txtListItemTitle);
            txtScore = convertView.findViewById(R.id.txtScore);
            imgAnswerStatus = convertView.findViewById(R.id.imgAnswerStatus);
            txtAnswerCount = convertView.findViewById(R.id.txtAnswerCount);
            txtViewCount = convertView.findViewById(R.id.txtViewCount);
            // owner area
            imgOwnerAvatar = convertView.findViewById(R.id.imgOwnerAvatar);
            txtOwnerName = convertView.findViewById(R.id.txtOwnerName);
            txtOwnerReputation = convertView.findViewById(R.id.txtOwnerReputation);

        }

        /**
         * binding data to view holder
         *
         * @param item
         */
        void bindingData(Question item, Context context) {
            txtTitle.setText(item.getTitle());
            txtScore.setText(String.valueOf(item.getScore()));
            // get answer status icon
            @SuppressLint("UseCompatLoadingForDrawables") Drawable checkDrawable =
                    context.getDrawable(item.isAnswer() ?
                            R.drawable.checked :
                            R.drawable.checked_inactive);
            imgAnswerStatus.setImageDrawable(checkDrawable);
            txtAnswerCount.setText(String.valueOf(item.getAnswerCount()));
            txtViewCount.setText(String.valueOf(item.getViewCount()));

            // owner area
            String avatarUrl = item.getOwner().getUserAvatarUrl();
            Picasso.with(context).load(avatarUrl).into(imgOwnerAvatar);
            txtOwnerName.setText((item.getOwner().getUserDisplayName()));
            txtOwnerReputation.setText(String.valueOf(item.getOwner().getReputation()));
        }

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.list_item_design, viewGroup, false);
            // binding view to ViewHolder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.bindingView(convertView);
            convertView.setTag(viewHolder);
        }
        final Question item = (Question) getItem(i);
        // binding data
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.bindingData(item, convertView.getContext());
        // set delegate click event
        convertView.setOnClickListener(view -> listener.onQuestionClicked(item));
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
