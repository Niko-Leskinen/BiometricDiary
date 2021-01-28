package com.example.fingerprintdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

public class DiaryActivity extends AppCompatActivity {

    List<DiaryEntry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        Toolbar diaryToolbar = findViewById(R.id.diary_toolbar);
        setSupportActionBar(diaryToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_newDiaryEntry);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(DiaryActivity.this, CreateDiaryEntryActivity.class);
            startActivity(intent);
            this.finish();
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_entries);

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        entries = dbHelper.getAllEntries();
        DiaryAdapter adapter = new DiaryAdapter(entries);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}