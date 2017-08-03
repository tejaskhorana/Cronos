package com.example.tejaskhorana.cronos;

import android.provider.BaseColumns;

/**
 * Created by tejaskhorana on 6/30/17.
 */

public class CronosContract {

    private CronosContract() {}

    public static class CronosEntry {
        public static final String CALENDAR_TABLE_NAME = "calendarevents";
        public static final String CALENDAR_EVENT_ID = "calendareventid";
        public static final String CALENDAR_EVENT_NAME = "calendareventname";
        public static final String CALENDAR_EVENT_LOCATION = "calendareventlocation";
        public static final String CALENDAR_EVENT_TIME = "calendareventtime";
        public static final String CALENDAR_EVENT_IMAGE = "calendareventimage";

        public static final String DELIVERABLE_TABLE_NAME = "deliverables";
        public static final String DELIVERABLE_ID = "deliverableid";
        public static final String DELIVERABLE_TYPE = "deliverabletype";
        public static final String DELIVERABLE_NAME = "deliverablename";
        public static final String DELIVERABLE_NOTES = "deliverablenotes";
        public static final String DELIVERABLE_DUEDATE = "deliverableduedate";
    }

}
