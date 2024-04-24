package com.androidarchitecture.stackoverflowclient.screens.questionslist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.androidarchitecture.stackoverflowclient.questions.Question;

import java.util.Objects;

public class QuestionListRecyclerViewAdapter extends ListAdapter<Question, QuestionListRecyclerViewAdapter.QuestionListViewHolder>
implements QuestionItemViewMvc.Listener{

    private final QuestionClickListener listener;
    /**
     * Public constructor
     */
    public QuestionListRecyclerViewAdapter(QuestionClickListener listener) {
        super(new QuestionDiffCallback());
        this.listener = listener;
    }

    @Override
    public void onQuestionClicked(Question question) {
        listener.onQuestionClicked(question);
    }

    /**
     * DiffUtil for ListAdapter
     */
    public static class QuestionDiffCallback extends DiffUtil.ItemCallback<Question> {

        @Override
        public boolean areItemsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
            return oldItem.equals(newItem);
        }
    }
    /**
     * implement view holder for each item
     */
    public static class QuestionListViewHolder extends RecyclerView.ViewHolder {
        private final QuestionItemViewMvc mViewMvc;
        public QuestionListViewHolder(QuestionItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }
        public void bindQuestion(Question question) {
            mViewMvc.bindQuestion(question);
        }

    }

    @NonNull
    @Override
    public QuestionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionItemViewMvc itemViewMvc = new QuestionItemViewMvcImpl(inflater, parent);
        // register subscriber to item click event
        itemViewMvc.registerListener(this);
        return new QuestionListViewHolder(itemViewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListViewHolder holder, int position) {
        Question item = getItem(position);
        holder.bindQuestion(item);
    }
    /**
     * callback when user click on item
     */
    public interface QuestionClickListener {
        void onQuestionClicked(Question item);
    }
}
