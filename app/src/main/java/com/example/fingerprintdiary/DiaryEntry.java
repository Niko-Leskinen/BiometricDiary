package com.example.fingerprintdiary;

import java.util.ArrayList;

public class DiaryEntry {
    private String mText;

    public DiaryEntry(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    private static int lastEntryId = 0;

    public static ArrayList<DiaryEntry> createEntriesList(int numEntries) {
        ArrayList<DiaryEntry> entries = new ArrayList<DiaryEntry>();

        for (int i = 1; i <= numEntries; i++) {
            entries.add(new DiaryEntry("Text"+ ++lastEntryId));
        }
        return entries;
    }
}
