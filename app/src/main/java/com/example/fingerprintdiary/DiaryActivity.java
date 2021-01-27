package com.example.fingerprintdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    List<DiaryEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        Toolbar diaryToolbar = findViewById(R.id.diary_toolbar);
        setSupportActionBar(diaryToolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_entries);

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            dbHelper.addEntry(new DiaryEntry(i,"text"+i));
        }
        entries = dbHelper.getAllEntries();
        DiaryAdapter adapter = new DiaryAdapter(entries);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}