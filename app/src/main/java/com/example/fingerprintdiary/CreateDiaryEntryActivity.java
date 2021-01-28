package com.example.fingerprintdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class CreateDiaryEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diary_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_createDiary);
        toolbar.setNavigationOnClickListener(v -> exit());

        Button button = (Button) findViewById(R.id.diaryInputEnter);
        button.setOnClickListener(v -> {
            AlertDialog dialog = createEntryDialog();
            dialog.show();
        });
    }
    private AlertDialog createEntryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateDiaryEntryActivity.this);
        builder.setMessage(R.string.on_create_entry_message_confirmation);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            TextInputEditText inputTitle = findViewById(R.id.diaryInputTitle);
            String title = inputTitle.getText().toString();

            TextInputEditText inputText = findViewById(R.id.diaryInputText);
            String text = inputText.getText().toString();
            DiaryEntry diaryEntry = DiaryEntry.createDiaryEntry(CreateDiaryEntryActivity.this, title, text);
            DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(CreateDiaryEntryActivity.this);

            dbHelper.addEntry(diaryEntry);
            dbHelper.close();
            exit();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            // User was not ready
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
    private void exit() {
        Intent intent = new Intent(CreateDiaryEntryActivity.this, DiaryActivity.class);
        startActivity(intent);
        CreateDiaryEntryActivity.this.finish();
    }
}