package com.example.tejaskhorana.cronos;

/**
 * Created by tejaskhorana on 6/24/17.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ScheduleAdapter extends BaseAdapter{
    Context context;
    CalendarEvent[] calendarEvents;
    LayoutInflater inflater;

    public ScheduleAdapter(Context applicationContext, CalendarEvent[] calendarEvents) {
        this.context = applicationContext;
        this.calendarEvents = calendarEvents;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.calendar_event, null);
        TextView eventname = (TextView) view.findViewById(R.id.eventname);
        TextView eventlocation = (TextView) view.findViewById(R.id.eventlocation);
        TextView eventtime = (TextView) view.findViewById(R.id.eventtime);
        ImageView eventimage = (ImageView) view.findViewById(R.id.eventimage);
        eventname.setText(calendarEvents[position].getCalendarEventName());
        eventlocation.setText(calendarEvents[position].getCalendarEventLocation());
        eventtime.setText(calendarEvents[position].getCalendarEventStartTime() + " - " +  calendarEvents[position].getCalendarEventEndTime());
        eventimage.setImageResource(calendarEvents[position].getCalendarEventImage());
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        Log.d("number items", ""+ calendarEvents.length);
        return calendarEvents.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
