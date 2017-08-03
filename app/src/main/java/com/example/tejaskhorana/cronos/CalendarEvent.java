package com.example.tejaskhorana.cronos;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tejaskhorana on 6/26/17.
 */

public class CalendarEvent {

    String calendarEventName;
    String calendarEventLocation;
    String calendarEventStartTime;
    String calendarEventEndTime;
    int calendarEventImage;

    public CalendarEvent(String calendarEventName, String calendarEventLocation, String calendarEventStartTime, String calendarEventEndTime, int calendarEventImage) {
        this.calendarEventName = calendarEventName;
        this.calendarEventLocation = calendarEventLocation;
        this.calendarEventStartTime = calendarEventStartTime;
        this.calendarEventEndTime = calendarEventEndTime;
        this.calendarEventImage = calendarEventImage;
    }

    public String getCalendarEventName() {
        return calendarEventName;
    }

    public String getCalendarEventLocation() {
        return calendarEventLocation;
    }

    public String getCalendarEventStartTime() {
        return calendarEventStartTime;
    }

    public String getCalendarEventEndTime() {
        return calendarEventEndTime;
    }

    public int getCalendarEventImage() {
        return calendarEventImage;
    }

    public void setCalendarEventName(String name) {
        calendarEventName = name;
    }

    public void setCalendarEventLocation(String location) {
        calendarEventLocation = location;
    }

    public void setCalendarEventStartTime(String time) {
        calendarEventStartTime = time;
    }

    public void setCalendarEventEndTime(String time) {
        calendarEventEndTime = time;
    }

    public void setCalendarEventImage(int image) {
        calendarEventImage = image;
    }

}
