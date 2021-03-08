package com.example.fingerprintdiary;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView title;
        public TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.diaryDate);
            title = (TextView) view.findViewById(R.id.diaryTitle);
            textView = (TextView) view.findViewById(R.id.diaryEntry);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    private List<DiaryEntry> mEntries;

    public DiaryAdapter(List<DiaryEntry> entries) {
        mEntries = entries;
    }

    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View entryView = inflater.inflate(R.layout.entry, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(entryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.ViewHolder viewHolder, final int position) {

        DiaryEntry diaryEntry = mEntries.get(position);

        TextView date = viewHolder.date;
        date.setText(diaryEntry.getDate());
        TextView title = viewHolder.title;
        title.setText(diaryEntry.getTitle());
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Set underline to title text
        TextView textView = viewHolder.textView;
        textView.setText(diaryEntry.getText());
    }


    @Override
    public int getItemCount() {
        return mEntries.size();
    }
}
