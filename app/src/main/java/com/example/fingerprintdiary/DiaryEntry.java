package com.example.fingerprintdiary;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiaryEntry {
    int _id;
    String _date;
    String _title;
    String _text;

    public DiaryEntry() {}

    public DiaryEntry(String date, String title, String text) {
        this._date = date;
        this._title = title;
        this._text = text;
    }

    public static DiaryEntry createDiaryEntry (Context context, String title, String text) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String finalDate = dateFormat.format(date);

        DiaryEntry newEntry = new DiaryEntry(finalDate, title, text);

        return newEntry;
    }

    // Getters & Setters
    public void setID(int id) { this._id = id; }
    public int getID() { return this._id; }
    public void setDate(String date) { this._date = date; }
    public String getDate() { return this._date; }
    public void setTitle(String title) { this._title =  title; }
    public String getTitle() { return this._title; }
    public void setText(String text) { this._text =  text; }
    public String getText() { return this._text; }
}