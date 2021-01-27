package com.example.fingerprintdiary;

public class DiaryEntry {
    int _id;
    String _text;

    public DiaryEntry() {}

    public DiaryEntry(int id, String text) {
        this._id = id;
        this._text = text;
    }

    // Getters & Setters
    public void setID(int id) { this._id = id; }
    public int getID() { return this._id; }
    public void setText(String text) { this._text =  text; }
    public String getText() { return this._text; }
}
