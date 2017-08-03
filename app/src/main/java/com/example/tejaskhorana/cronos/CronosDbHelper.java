package com.example.tejaskhorana.cronos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tejaskhorana on 6/30/17.
 */

public class CronosDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cronos.db";

    public static final String SQL_CREATE_CALENDAR_ENTRIES =
            "CREATE TABLE " + CronosContract.CronosEntry.CALENDAR_TABLE_NAME + " (" +
                    CronosContract.CronosEntry.CALENDAR_EVENT_ID + " INTEGER," +
                    CronosContract.CronosEntry.CALENDAR_EVENT_NAME + " TEXT," +
                    CronosContract.CronosEntry.CALENDAR_EVENT_LOCATION + " TEXT," +
                    CronosContract.CronosEntry.CALENDAR_EVENT_TIME + " DATETIME" +
                    CronosContract.CronosEntry.CALENDAR_EVENT_IMAGE + " INTEGER)";

    public static final String SQL_CREATE_DELIVERABLES =
            "CREATE TABLE " + CronosContract.CronosEntry.DELIVERABLE_TABLE_NAME + " (" +
                    CronosContract.CronosEntry.DELIVERABLE_ID + " INTEGER," +
                    CronosContract.CronosEntry.DELIVERABLE_TYPE + " TEXT," +
                    CronosContract.CronosEntry.DELIVERABLE_NAME + " TEXT," +
                    CronosContract.CronosEntry.DELIVERABLE_NOTES + " TEXT" +
                    CronosContract.CronosEntry.DELIVERABLE_DUEDATE + " DATETIME" +
                    CronosContract.CronosEntry.CALENDAR_EVENT_IMAGE + " INTEGER)";

    public static final String SQL_DELETE_CALENDAR_ENTRIES =
            "DROP TABLE IF EXISTS " + CronosContract.CronosEntry.CALENDAR_TABLE_NAME;

    public static final String SQL_DELETE_DELIVERABLES =
            "DROP TABLE IF EXISTS " + CronosContract.CronosEntry.DELIVERABLE_TABLE_NAME;

    public CronosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CALENDAR_ENTRIES);
        db.execSQL(SQL_CREATE_DELIVERABLES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CALENDAR_ENTRIES);
        db.execSQL(SQL_DELETE_DELIVERABLES);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
