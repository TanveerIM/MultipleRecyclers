package com.tanveerm.recyclerviewhorizontalvertical.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tanveerm.recyclerviewhorizontalvertical.R;
import com.tanveerm.recyclerviewhorizontalvertical.model.Chapter;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Chapter> chapters;
    private LayoutInflater mLayoutInflater;

    public ChapterAdapter(Context context, ArrayList<Chapter> chapters) {
        this.mContext = context;
        this.chapters = chapters;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.single_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);

        Log.e("TAG","URL: " +chapter.imageUrl);
        Picasso.get().load(chapter.imageUrl).into(holder.imageViewChapter);
        holder.textViewChapterName.setText(chapter.chapterName);
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewChapter;
        public TextView textViewChapterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewChapterName = itemView.findViewById(R.id.text_chapter_name);
            imageViewChapter = itemView.findViewById(R.id.image_chapter);
        }
    }
}
