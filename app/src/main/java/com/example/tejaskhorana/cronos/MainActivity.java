package com.example.tejaskhorana.cronos;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.widget.AdapterViewFlipper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EasyPermissions.PermissionCallbacks {

    //Access my database
    //CronosDbHelper cDbHelper = new CronosDbHelper(getApplicationContext());

    //Set up Google Calendar API
    GoogleAccountCredential mCredential;
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { CalendarScopes.CALENDAR_READONLY };

    //To Do List Prep
    private ListView simpleListView;
    //Deliverable d1 = new Deliverable(Deliverable.DeliverableType.GOAL, "Pokemon (Go) Hunting", "swaggy", new Date(2000, 20, 20));
    //Deliverable d2 = new Deliverable(Deliverable.DeliverableType.GOAL, "Catch 10 Pidgeys", "boi", new Date(2000, 20, 20));
    //Deliverable d3 = new Deliverable(Deliverable.DeliverableType.GOAL, "Save 10 Rhydons", "tell'em", new Date(2000, 20, 20));

    //Deliverable[] dAll = {d1, d2, d3, d3, d2, d3, d3, d2, d3, d3, d2, d3, d3, d2, d3, d3};

    //Calendar Prep
    private AdapterViewFlipper simpleAdapterViewFlipper;
    ArrayList<CalendarEvent> allCalendarEvents = new ArrayList<CalendarEvent>();

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        setContentView(R.layout.home_activity);

        // Initialize credentials and service object.
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        getEventsFullDay();

        simpleListView = (ListView) findViewById(R.id.todoList);
        TodoListAdapter todoListAdapter = new TodoListAdapter(getApplicationContext(), dAll);
        simpleListView.setAdapter(todoListAdapter);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        gestureDetector = new GestureDetector(this, customGestureDetector);  */

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //added because of tabbed layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("To Do"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Notes"));
        tabLayout.addTab(tabLayout.newTab().setText("Alarm"));

        final NonswipableViewPager viewPager = (NonswipableViewPager) findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PagerAdapter.PageType currentPageType = pagerAdapter.getPageType(viewPager.getCurrentItem());
                if(currentPageType != null) {
                    switch(currentPageType) {
                        case TODO: {
                            Snackbar.make(view, "TODO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            Todo h = new Todo("TESTING TODO FROM MAIN!", "hope it works!", new org.joda.time.DateTime(), null, false);
                            TodoFragment.addToDeliverables(h);
                            break;
                        }
                        case CALENDAR: {
                            Snackbar.make(view, "CALENDAR", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                        }
                        case NOTES: {
                            Snackbar.make(view, "NOTES", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                        }
                        case ALARM: {
                            Snackbar.make(view, "ALARM", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                        }
                    }
                } else {
                    Snackbar.make(view, "Fail", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    /* override onTouchEvent to re-route all touch events */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    public boolean getEventsFullDay() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (!isDeviceOnline()) {
            return false;
        } else {
            new MakeRequestTask(mCredential).execute();
            return true;
        }
        return false;
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }

    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getEventsFullDay();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //do nothing
    }

    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //do nothing
    }




    /**
     * An asynchronous task that handles the Google Calendar API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.calendar.Calendar mService = null;
        private Exception mLastError = null;

        MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.calendar.Calendar.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Google Calendar API Android Quickstart")
                    .build();
        }

        /**
         * Background task to call Google Calendar API.
         *
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of the next 10 events from the primary calendar.
         *
         * @return List of Strings describing returned events.
         * @throws IOException
         */
        private List<String> getDataFromApi() throws IOException {
            // List the next 10 events from the primary calendar.
            DateTime now = new DateTime(System.currentTimeMillis());
            List<String> eventStrings = new ArrayList<String>();
            Events events = mService.events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            List<Event> items = events.getItems();

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                DateTime end = event.getEnd().getDateTime();

                if (start == null) {
                    // All-day events don't have start times, so just use
                    // the start date.
                    start = event.getStart().getDate();
                }
                if(end == null) {
                    end = event.getEnd().getDate();
                }
                Log.d("initial inputs", start + " " + end);

                eventStrings.add(
                        String.format("--eventname--%s--eventname-- --startdatetime--%s--startdatetime-- --enddatetime--%s--enddatetime-- --location--%s--location--  ", event.getSummary(), start, end, event.getLocation()));
            }
            return eventStrings;
        }


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(List<String> output) {
            String eventnameBuffer = "--eventname--";
            String startdatetimeBuffer = "--startdatetime--";
            String enddatetimeBuffer = "--enddatetime--";
            String locationBuffer = "--location--";

            for(String ioutput : output) {

                int i = ioutput.indexOf(eventnameBuffer);
                int j = ioutput.lastIndexOf(eventnameBuffer);
                String eventname = ioutput.substring(i+eventnameBuffer.length(), j);
                i = ioutput.indexOf(startdatetimeBuffer);
                j = ioutput.lastIndexOf(startdatetimeBuffer);
                String eventstartdateraw = ioutput.substring(i+startdatetimeBuffer.length(), j);
                i = ioutput.indexOf(enddatetimeBuffer);
                j = ioutput.lastIndexOf(enddatetimeBuffer);
                String eventenddateraw = ioutput.substring(i+enddatetimeBuffer.length(), j);
                i = ioutput.indexOf(locationBuffer);
                j = ioutput.lastIndexOf(locationBuffer);
                String eventlocation = ioutput.substring(i+locationBuffer.length(), j);

                org.joda.time.DateTime eventstartdatetime = org.joda.time.DateTime.parse(eventstartdateraw);
                org.joda.time.DateTime eventenddatetime = org.joda.time.DateTime.parse(eventenddateraw);

                String starttimeminute = ( eventstartdatetime.minuteOfHour().get() == 0 ? "00" : ""+eventenddatetime.minuteOfHour().get());
                String endtimeminute = ( eventenddatetime.minuteOfHour().get() == 0 ? "00" : ""+eventenddatetime.minuteOfHour().get());

                allCalendarEvents.add(new CalendarEvent(eventname, eventlocation, eventstartdatetime.hourOfDay().get()+":"+starttimeminute,  eventenddatetime.hourOfDay().get()+":"+endtimeminute, R.drawable.calendar));

            }

            CalendarEvent[] cAll = {};
            cAll = allCalendarEvents.toArray(cAll);
            simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), cAll);
            simpleAdapterViewFlipper.setAdapter(scheduleAdapter);



        }

        @Override
        protected void onCancelled() {
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            REQUEST_AUTHORIZATION);
                } else {

                }
            } else {

            }
        }
    }







    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e1.getY() < simpleAdapterViewFlipper.getHeight() && e2.getY() < simpleAdapterViewFlipper.getHeight()) {
                Log.d("children", ""+simpleAdapterViewFlipper.getChildCount());
                Log.d("current child", ""+simpleAdapterViewFlipper.getDisplayedChild());
                if (e1.getX() > e2.getX() && simpleAdapterViewFlipper.getDisplayedChild() < allCalendarEvents.size()-1) {
                    simpleAdapterViewFlipper.showNext();
                }

                if (e1.getX() < e2.getX() && simpleAdapterViewFlipper.getDisplayedChild() > 0) {
                    simpleAdapterViewFlipper.showPrevious();
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


}
