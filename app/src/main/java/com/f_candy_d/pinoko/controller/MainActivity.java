package com.f_candy_d.pinoko.controller;

import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.Instructor;
import com.f_candy_d.pinoko.model.Location;
import com.f_candy_d.pinoko.model.TimeBlock;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.Savable;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        // TODO; Test code for DB
        saveTest();
        loadTest();
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initUI() {
        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fab
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // NavigationDrawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void saveTest() {
        ArrayList<Savable> entries = new ArrayList<>();
        Course course = new Course();
        course.setName("course")
                .setLocationIDA(1)
                .setLocationIDB(2)
                .setInstructorIDA(3)
                .setInstructorIDB(4)
                .setInstructorIDC(5)
                .setLength(6)
                .setNote("course note");

        Location location = new Location();
        location.setName("location")
                .setNote("location note");

        Instructor instructor = new Instructor();
        instructor.setName("instructor")
                .setLab("lab")
                .setMail("mail")
                .setPhoneNumber("phoneNumber")
                .setNote("instructor note");

        TimeBlock timeBlock = new TimeBlock();
        timeBlock.setType(TimeBlock.Type.COURSE)
                .setCategory(TimeBlock.Category.ONE_DAY)
                .setTargetID(1)
                .setDatetimeBegin(2000)
                .setDatetimeEnd(3000)
                .setTimeTableID(4);

        Assignment assignment = new Assignment();
        assignment.setName("assignment")
                .setTimeBlockID(1)
                .setNote("assignment note")
                .setIsDone(true);

        Event event = new Event();
        event.setName("event")
                .setLocationID(1)
                .setNote("event note");

        Entry entry6 = new Entry(DBContract.NotificationEntry.TABLE_NAME);
        entry6.set(DBContract.NotificationEntry.COL_NAME, "notification")
                .set(DBContract.NotificationEntry.COL_NOTE, "notificationNote")
                .set(DBContract.NotificationEntry.COL_CATEGORY, 1)
                .set(DBContract.NotificationEntry.COL_TARGET_ID, 2)
                .set(DBContract.NotificationEntry.COL_TYPE, 3)
                .set(DBContract.NotificationEntry.COL_IS_DONE, true)
                .set(DBContract.NotificationEntry.COL_DATETIME_BEGIN, 1000)
                .set(DBContract.NotificationEntry.COL_DATETIME_END, 2000);

        Entry entry7 = new Entry(DBContract.AttendanceEntry.TABLE_NAME);
        entry7.set(DBContract.AttendanceEntry.COL_TIME_BLOCK_ID, 1)
                .set(DBContract.AttendanceEntry.COL_PRESENT, 2)
                .set(DBContract.AttendanceEntry.COL_LATE, 3)
                .set(DBContract.AttendanceEntry.COL_ABSENT, 4)
                .set(DBContract.AttendanceEntry.COL_NOTE, "attendanceNote");

        entries.add(course);
        entries.add(location);
        entries.add(instructor);
        entries.add(timeBlock);
        entries.add(assignment);
        entries.add(event);
        entries.add(entry6);
        entries.add(entry7);

        DBDataManager dbDataManager = new DBDataManager(this, DBDataManager.Mode.TRUNCATE);
        long[] longs = dbDataManager.insert(entries);
        Log.d("mylog", "--------------------------------------------------------");
        Log.d("mylog", "#### Save Results");
        for (long id : longs) {
            Log.d("mylog", "saved -> " + String.valueOf(id));
        }
        Log.d("mylog", "--------------------------------------------------------");
    }

    private void loadTest() {
        DBDataManager dataManager = new DBDataManager(this, DBDataManager.Mode.APPEND);
        for (String name : DBContract.getTableNames()) {
            ArrayList<Entry> list = dataManager.getAllOf(name);
            for (Entry entry : list) {
                Log.d("mylog", "--------------------------------------------------------");
                Log.d("mylog", "#### " + name);
                Log.d("mylog", entry.toString());
                Log.d("mylog", "--------------------------------------------------------");
            }
        }
    }
}
