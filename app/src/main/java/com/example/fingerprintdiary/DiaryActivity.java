package com.example.fingerprintdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView_entries);

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        entries = dbHelper.getAllEntries();
        DiaryAdapter adapter = new DiaryAdapter(entries);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuAction_newEntry:
                Intent intent = new Intent(DiaryActivity.this, CreateDiaryEntryActivity.class);
                startActivity(intent);
                this.finish();
                return true;

            case R.id.menuAction_settings:
                // TODO
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}