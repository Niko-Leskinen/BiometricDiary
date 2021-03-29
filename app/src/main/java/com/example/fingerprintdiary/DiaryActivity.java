package com.example.fingerprintdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.Date;
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
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        entries = dbHelper.getAllEntries();
        DiaryAdapter adapter = new DiaryAdapter(entries);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // TabLayout switch
        TabLayout tabLayout = findViewById(R.id.tabLayout_entries);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        calendarView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        recyclerView.setVisibility(View.GONE);
                        calendarView.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Calendar
        TextView testText = findViewById(R.id.dateTest);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;  // Months start at 0 (January) so add 1
                testText.setText(""+dayOfMonth+"."+month+"."+year);
            }
        });

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
                return true;

            case R.id.menuAction_settings:
                // TODO
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}