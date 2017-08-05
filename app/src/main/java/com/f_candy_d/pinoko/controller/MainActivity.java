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
import com.f_candy_d.pinoko.model.AssignmentFormer;
import com.f_candy_d.pinoko.model.AttendanceFormer;
import com.f_candy_d.pinoko.model.CourseFormer;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EventFormer;
import com.f_candy_d.pinoko.model.InstructorFormer;
import com.f_candy_d.pinoko.model.LocationFormer;
import com.f_candy_d.pinoko.model.NotificationFormer;
import com.f_candy_d.pinoko.model.TimeBlockFormer;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.SQLQuery;
import com.f_candy_d.pinoko.utils.SQLWhere;
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
//        exprTest();
//        sqlWhereTest();
        selectTest();
        updateTest();
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
        CourseFormer course = CourseFormer.createWithEntry();
        course.setName("course")
                .setLocationId(1)
                .setInstructorId(1)
                .setLength(6)
                .setNote("course note");

        LocationFormer location = LocationFormer.createWithEntry();
        location.setName("location")
                .setNote("location note");

        InstructorFormer instructor = InstructorFormer.createWithEntry();
        instructor.setName("instructor")
                .setLab("lab")
                .setMail("mail")
                .setPhoneNumber("phoneNumber")
                .setNote("instructor note");

        TimeBlockFormer timeBlock = TimeBlockFormer.createWithEntry();
        timeBlock.setType(TimeBlockFormer.Type.EVERYDAY)
                .setCategory(TimeBlockFormer.Category.COURSE)
                .setTargetID(1)
                .setDatetimeBegin(2000)
                .setDatetimeEnd(3000)
                .setTimeTableID(4);


        AssignmentFormer assignment = AssignmentFormer.createWithEntry();
        assignment.setName("assignment")
                .setTimeBlockID(1)
                .setNote("assignment note")
                .setIsDone(true)
                .setDeadline(120170804);

        EventFormer event = EventFormer.createWithEntry();
        event.setName("event")
                .setLocationID(1)
                .setNote("event note");

        NotificationFormer notification = NotificationFormer.createWithEntry();
        notification.setName("notification")
                .setNote("notification note")
                .setCategory(NotificationFormer.Category.ATTENDANCE)
                .setTargetID(1)
                .setType(NotificationFormer.Type.CANCELLATION)
                .setIsDone(true)
                .setDatetimeBegin(2000)
                .setDatetimeEnd(3000);


        AttendanceFormer attendance = AttendanceFormer.createWithEntry();
        attendance.setTimeBlockID(1)
                .setPresent(2)
                .setLate(3)
                .setAbsent(4)
                .setNote("attendance note");

        entries.add(course);
        entries.add(location);
        entries.add(instructor);
        entries.add(timeBlock);
        entries.add(assignment);
        entries.add(event);
        entries.add(notification);
        entries.add(attendance);

        DBDataManager dbDataManager = new DBDataManager(this, DBDataManager.Mode.WRITE_TRUNCATE);
        long[] longs = dbDataManager.insert(entries);
        Log.d("mylog", "--------------------------------------------------------");
        Log.d("mylog", "#### Save Results");
        for (long id : longs) {
            Log.d("mylog", "saved -> " + String.valueOf(id));
        }
        Log.d("mylog", "--------------------------------------------------------");

        dbDataManager.close();
    }

    private void loadTest() {
        DBDataManager dataManager = new DBDataManager(this, DBDataManager.Mode.READ);
        for (String name : DBContract.getTableNames()) {
            ArrayList<Entry> list = dataManager.selectAllOf(name);
            for (Entry entry : list) {
                Log.d("mylog", "--------------------------------------------------------");
                Log.d("mylog", "#### " + name);
                Log.d("mylog", entry.toString());
                Log.d("mylog", "--------------------------------------------------------");
            }
        }
        dataManager.close();
    }

    private void exprTest() {
        SQLWhere.CondExpr condExprL = new SQLWhere.CondExpr();
        SQLWhere.CondExpr condExprR = new SQLWhere.CondExpr();
        SQLWhere.LogicExpr logicExpr = new SQLWhere.LogicExpr();


        condExprL.l("id_g").graterThanOrEqualTo().r(10);
        condExprR.l("name").equalTo().r("smith");
        logicExpr.l(condExprL).and().r(condExprR);

        Log.d("mylog", "Left operand  ::::::::: " + condExprL.toString());
        Log.d("mylog", "Right operand ::::::::: " + condExprR.toString());
        Log.d("mylog", "L && R        ::::::::: " + logicExpr.toString());

        logicExpr.left = null;
        Log.d("mylog", "R ONLY        ::::::::: " + logicExpr.toString());
        logicExpr.left = condExprL;
        logicExpr.right = null;
        Log.d("mylog", "L ONLY        ::::::::: " + logicExpr.toString());

        SQLWhere.CondExpr condExprL2 = new SQLWhere.CondExpr();
        SQLWhere.CondExpr condExprR2 = new SQLWhere.CondExpr();
        SQLWhere.LogicExpr logicExpr2 = new SQLWhere.LogicExpr();

        condExprL2.l("id_p").notEqualTo().r("id_q");
        condExprR2.l("sabori").lessThan().r(19);
        logicExpr2.l(condExprL2).or().r(condExprR2);

        Log.d("mylog", "Left2  operand         ::::::::: " + condExprL2.toString());
        Log.d("mylog", "Right2 operand         ::::::::: " + condExprR2.toString());
        logicExpr.l(condExprL).and().r(logicExpr2);
        Log.d("mylog", "L && (L2 || R2)        ::::::::: " + logicExpr.toString());

        SQLWhere.SpecExpr between = new SQLWhere.SpecExpr();
        SQLWhere.SpecExpr notbetween = new SQLWhere.SpecExpr();
        SQLWhere.SpecExpr like = new SQLWhere.SpecExpr();
        SQLWhere.SpecExpr in = new SQLWhere.SpecExpr();
        SQLWhere.SpecExpr isnull = new SQLWhere.SpecExpr();
        SQLWhere.SpecExpr isnotnull = new SQLWhere.SpecExpr();

        between.between("_id", 10, 20);
        notbetween.notBetween("_id", 100, 200);
        like.like("_id", "sabori's big ???? barger");
        in.in("_id", new String[]{"af", "fff", "fkfk", "ffkf"});
        isnull.isNull("_id");
        isnotnull.isNotNull("_id");

        Log.d("mylog", "between(\"_id\", 10, 20)                       ::::::::: " + between.toString());
        Log.d("mylog", "notBetween(\"_id\", 100, 200)                  ::::::::: " + notbetween.toString());
        Log.d("mylog", "like(\"_id\", \"sabori's big ???? barger\")    ::::::::: " + like.toString());
        Log.d("mylog", "in(\"_id\", new int[]{1,2,3,4,5,6,7,8,9})      ::::::::: " + in.toString());
        Log.d("mylog", "isNotNull(\"_id\");                            ::::::::: " + isnull.toString());
        Log.d("mylog", "isNull(\"_id\");                               ::::::::: " + isnotnull.toString());

        logicExpr.l(condExprL).and().r(condExprR);
        logicExpr2.l(logicExpr, true).and().r(between, true);
        Log.d("mylog", "logicExpr2.l(logicExpr, true).and().r(between, true)  :::::::" + logicExpr2.toString());
    }

    private void sqlWhereTest() {
        SQLWhere.CondExpr condExprL = new SQLWhere.CondExpr();
        SQLWhere.CondExpr condExprR = new SQLWhere.CondExpr();
        SQLWhere.LogicExpr logicExpr = new SQLWhere.LogicExpr();
        condExprL.l("id_g").graterThanOrEqualTo().r(10);
        condExprR.l("name").equalTo().r("smith");
        logicExpr.l(condExprL).and().r(condExprR);
        SQLWhere sqlWhere = new SQLWhere(condExprL);


        Log.d("mylog", "WHERE =======> " + sqlWhere.toString());
    }

    private void selectTest() {
        DBDataManager dataManager = new DBDataManager(this, DBDataManager.Mode.WRITE_APPEND);
        SQLWhere.CondExpr locIdCond = new SQLWhere.CondExpr().l(DBContract.CourseEntry.ATTR_LOCATION_ID).equalTo().r(DBContract.LocationEntry.ATTR_ID);
        SQLWhere.CondExpr instIdCond = new SQLWhere.CondExpr().l(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID).equalTo().r(DBContract.InstructorEntry.ATTR_ID);
        SQLWhere.CondExpr notifiIDCond = new SQLWhere.CondExpr().l(DBContract.CourseEntry.ATTR_ID).equalTo().r(DBContract.NotificationEntry.ATTR_TARGET_ID);
        SQLWhere.LogicExpr innerLogicExpr = new SQLWhere.LogicExpr().l(locIdCond).and().r(instIdCond);
        SQLWhere.LogicExpr logicExpr = new SQLWhere.LogicExpr().l(innerLogicExpr).and().r(notifiIDCond);
        SQLQuery query = new SQLQuery();
        query.putTables(
                DBContract.CourseEntry.TABLE_NAME,
                DBContract.LocationEntry.TABLE_NAME,
                DBContract.InstructorEntry.TABLE_NAME,
                DBContract.NotificationEntry.TABLE_NAME);
        query.setSelection(logicExpr);

        ArrayList<Entry> results = dataManager.select(query, "testEntry");

        for (Entry entry : results) {
            Log.d("mylog", "--------------------------------------------------------");
            Log.d("mylog", "#### " + entry.getAffiliation());
            Log.d("mylog", entry.toString());
            Log.d("mylog", "--------------------------------------------------------");
        }
    }

    private void updateTest() {
        DBDataManager dataManager = new DBDataManager(this, DBDataManager.Mode.WRITE_APPEND);
        ArrayList<Entry> entries = dataManager.selectWhereColumnIs(DBContract.CourseEntry.TABLE_NAME, DBContract.CourseEntry.ATTR_ID, 1, null);
        if (entries.size() != 0) {
            Entry entry = entries.get(0);
            Log.d("mylog", "BEFORE UPDATING --------------------------------------------------");
            Log.d("mylog", "#### " + entry.getAffiliation());
            Log.d("mylog", entry.toString());
            Log.d("mylog", "AFTER UPDATING --------------------------------------------------------");

            EntryHelper.setCourseName(entry, "updated name");
            EntryHelper.setCourseNote(entry, "updated note");
            EntryHelper.setCourseLength(entry, 10000);

            dataManager.update(new CourseFormer(entry));
            Log.d("mylog", "#### " + entry.getAffiliation());
            Log.d("mylog", entry.toString());
            Log.d("mylog", "-----------------------------------------------------------------------");
        }

        dataManager.close();
    }
}
