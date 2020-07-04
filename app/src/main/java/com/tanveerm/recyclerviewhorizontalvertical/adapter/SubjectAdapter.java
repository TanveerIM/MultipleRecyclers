package com.tanveerm.recyclerviewhorizontalvertical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanveerm.recyclerviewhorizontalvertical.R;
import com.tanveerm.recyclerviewhorizontalvertical.ativities.MainActivity;
import com.tanveerm.recyclerviewhorizontalvertical.model.Chapter;
import com.tanveerm.recyclerviewhorizontalvertical.model.Subjects;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Subjects> mSubjects = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public SubjectAdapter(Context context, ArrayList<Subjects> subjectsArrayList) {
        this.context = context;
        this.mSubjects = subjectsArrayList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.single_subject, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.mRecyclerView.setAdapter(new ChapterAdapter(context, mSubjects.get(position).chapters));
        holder.mRecyclerView.setHasFixedSize(true);
        holder.mTextViewHeading.setText(mSubjects.get(position).getSubjectName());
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        TextView mTextViewHeading;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecyclerView = itemView.findViewById(R.id.recyclerView_chapters);
            mTextViewHeading = itemView.findViewById(R.id.text_subject_name);
        }
    }
}
