package com.example.fingerprintdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiaryEntries.db";
    private static final String TABLE_ENTRIES = "entries";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT ="text";

    public DiaryEntryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_ENTRIES + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
            TABLE_ENTRIES;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // Add a new DiaryEntry object in to the DB
    void addEntry(DiaryEntry diaryEntry) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, diaryEntry.getID());
        values.put(KEY_TEXT, diaryEntry.getText());
        db.insert(TABLE_ENTRIES, null, values);

        db.close();
    }

    // Return all entries in the DB as list
    public List<DiaryEntry> getAllEntries() {
        List<DiaryEntry> entryList = new ArrayList<DiaryEntry>();

        String selectQuery = "SELECT * FROM " + TABLE_ENTRIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                DiaryEntry diaryEntry = new DiaryEntry();
                diaryEntry.setID(Integer.parseInt(cursor.getString(0)));
                diaryEntry.setText(cursor.getString(1));
                entryList.add(diaryEntry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }
}