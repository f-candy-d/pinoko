package com.f_candy_d.pinoko.controller;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.EntryObjectType;
import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.model.OneDayTimeTable;
import com.f_candy_d.pinoko.utils.AssignmentFormer;
import com.f_candy_d.pinoko.utils.AttendanceFormer;
import com.f_candy_d.pinoko.utils.CourseFormer;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.utils.EventFormer;
import com.f_candy_d.pinoko.utils.InstructorFormer;
import com.f_candy_d.pinoko.utils.LocationFormer;
import com.f_candy_d.pinoko.utils.NotificationFormer;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;
import com.f_candy_d.pinoko.utils.AHBottomNavigationObserver;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.SQLQuery;
import com.f_candy_d.pinoko.utils.SQLWhere;
import com.f_candy_d.pinoko.Savable;
import com.f_candy_d.pinoko.view.CardListFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CardListFragment.OnFragmentInteractionListener,
        AHBottomNavigationObserver.NotificationListener {

    /**
     * Fragment types.
     * These values are used as position of AHBottomNavigation.
     */
    private static final int FRAGMENT_ONE_DAY_SCHEDULE = 0;
    private static final int FRAGMENT_WEEKLY_SCHEDULE = 1;
    private static final int FRAGMENT_ASSIGNMENTS = 2;
    private static final int FRAGMENT_NOTIFICATIONS = 3;

    private static final int VIEWPAGER_OFFSCREEN_LIMIT = 3;
    private static final long FAB_ANIMATION_DURATION = 300;

    // UI
    private AHBottomNavigation mBottomNavigation;
    private FloatingActionButton mFab;

    // Misc
    private AHBottomNavigationViewPager mViewPager;
    private AHBottomNavigationVIewPagerAdapter mPagerAdapter;
    private CardListFragment mCurrentFragment;
    private AHBottomNavigationObserver mBottomNavigationObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO; Test code for DB
        saveTest();
        loadTest();
//        exprTest();
//        sqlWhereTest();
//        selectTest();
//        updateTest();

        init();
        initUI();
        initState();
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

    private void init() {
        // ViewPager
        ArrayList<CardListFragment> fragments = new ArrayList<>(4);

        // Add fragments to the ViewPager
        CardListFragment oneDayTTF = CardListFragment.newInstance(FRAGMENT_ONE_DAY_SCHEDULE);
        fragments.add(FRAGMENT_ONE_DAY_SCHEDULE, oneDayTTF);

        CardListFragment weeklyTTF = CardListFragment.newInstance(FRAGMENT_WEEKLY_SCHEDULE);
        fragments.add(FRAGMENT_WEEKLY_SCHEDULE, weeklyTTF);

        CardListFragment assignmentF = CardListFragment.newInstance(FRAGMENT_ASSIGNMENTS);
        fragments.add(FRAGMENT_ASSIGNMENTS, assignmentF);

        CardListFragment notificationF = CardListFragment.newInstance(FRAGMENT_NOTIFICATIONS);
        fragments.add(FRAGMENT_NOTIFICATIONS, notificationF);

        mViewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager_cm);
        mViewPager.setOffscreenPageLimit(VIEWPAGER_OFFSCREEN_LIMIT);
        mPagerAdapter = new AHBottomNavigationVIewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initUI() {
        /**
         * Tool Bar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Floating Action Button
         */
        mFab = (FloatingActionButton) findViewById(R.id.fab_abm);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked((Integer) view.getTag());
            }
        });
//        mFab.setVisibility(View.INVISIBLE);

        /**
         * Navigation Drawer
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * AH Bottom Navigation
         */
        mBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_abm);

        // Create items
        AHBottomNavigationItem item0 = new AHBottomNavigationItem(
                "Today", R.drawable.ic_view_week, R.color.colorPrimary);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(
                "Week", R.drawable.ic_view_week, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(
                "Assignments", R.drawable.ic_settings, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(
                "Notifications", R.drawable.ic_menu_gallery, R.color.colorAccent);

        // Add items
        mBottomNavigation.addItem(item0);
        mBottomNavigation.addItem(item1);
        mBottomNavigation.addItem(item2);
        mBottomNavigation.addItem(item3);

        // Enable the translation of the FloatingActionButton
        mBottomNavigation.manageFloatingActionButtonBehavior(mFab);

        // Change colors
        mBottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        mBottomNavigation.setInactiveColor(getResources().getColor(R.color.colorBottomNavigationInactive));

        // Show item title always
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Notification
        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(this, R.color.cardview_light_background))
                .setTextColor(ContextCompat.getColor(this, R.color.cardview_dark_background))
                .build();
        mBottomNavigation.setNotification(notification, 3);

        // Setup observer
        int flags = AHBottomNavigationObserver.STATE | AHBottomNavigationObserver.TAB_SELECTION;
        mBottomNavigationObserver = new AHBottomNavigationObserver(flags, mBottomNavigation);
        mBottomNavigationObserver.setNotificationListener(this);
    }

    private void initState() {
        mFab.setTag(FRAGMENT_ONE_DAY_SCHEDULE);
        // Set current item programmatically
        mBottomNavigation.setCurrentItem(FRAGMENT_ONE_DAY_SCHEDULE, false);
        // Default fragment
        mViewPager.setCurrentItem(FRAGMENT_ONE_DAY_SCHEDULE, false);
        mCurrentFragment = mPagerAdapter.getCurrentFragment();
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

        Calendar today = Calendar.getInstance();
        long b1 = today.getTimeInMillis();
        today.add(Calendar.HOUR, 2);
        long e1 = today.getTimeInMillis();
        today.add(Calendar.HOUR, 3);
        long b2 = today.getTimeInMillis();
        today.add(Calendar.HOUR, 2);
        long e2 = today.getTimeInMillis();

        TimeBlockFormer timeBlock = TimeBlockFormer.createWithEntry();
        timeBlock.setType(TimeBlockFormer.Type.EVERYDAY)
                .setCategory(TimeBlockFormer.Category.COURSE)
                .setTargetID(1)
                .setDatetimeBegin(b1)
                .setDatetimeEnd(e1)
                .setTimeTableID(4)
                .setDayOfWeek(DayOfWeek.WEDNESDAY);

        TimeBlockFormer timeBlock2 = TimeBlockFormer.createWithEntry();
        timeBlock2.setType(TimeBlockFormer.Type.ONE_DAY)
                .setCategory(TimeBlockFormer.Category.EVENT)
                .setTargetID(1)
                .setDatetimeBegin(b2)
                .setDatetimeEnd(e2)
                .setTimeTableID(4)
                .setDayOfWeek(DayOfWeek.WEDNESDAY);

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
        entries.add(timeBlock2);
        entries.add(assignment);
        entries.add(event);
        entries.add(notification);
        entries.add(attendance);

        Log.d("mylog", "BEFORE SAVE ############################");
        Log.d("mylog", timeBlock.getEntry().toString());
        Log.d("mylog", "BEFORE SAVE ############################");
        Log.d("mylog", timeBlock2.getEntry().toString());

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

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        // Hide or show FAB
        if (!wasSelected) {
//            if (position == FRAGMENT_ASSIGNMENTS || position == FRAGMENT_NOTIFICATIONS) {
//                // If FAB's visibility is VISIBLE, the previous position is 2 or 3.
//                // So the first thing we do is to hide FAB with animation,
//                // and then show it again.
//                // If FAB's visibility is GONE, the previous position is 0 or 1.
//                // In this case, simply show FAB with animation.
//                if (mFab.getVisibility() == View.VISIBLE) {
//                    hideFAB(true);
//                } else if (mFab.getVisibility() == View.INVISIBLE) {
//                    showFAB(false);
//                }
//                mFab.setTag(position);
//
//            } else {
//                hideFAB(false);
//            }
            if (mFab.getVisibility() == View.VISIBLE) {
                hideFAB(true);
            } else {
                showFAB(false);
            }
            mFab.setTag(position);
        }

        return onSwitchFragments(position, wasSelected);
    }

    @Override
    public void onPositionChange(int y) {
        // Nothing to do...
    }

    @Override
    public void onNavigationStateChanged(AHBottomNavigationObserver.State state) {
        if (mCurrentFragment == null) {
            mCurrentFragment = mPagerAdapter.getCurrentFragment();
        }

        int id = mCurrentFragment.getFragmentId();
        if (id == FRAGMENT_ASSIGNMENTS || id == FRAGMENT_NOTIFICATIONS) {
            if (state == AHBottomNavigationObserver.State.SHOWN
                    && mFab.getVisibility() == View.INVISIBLE) {
                // Show FAB after BottomNavigation appeared
                showFAB(false);

            } else if (state == AHBottomNavigationObserver.State.HIDDEN
                    && mFab.getVisibility() == View.VISIBLE) {
                // Hide FAB after BottomNavigation disappeared
                hideFAB(false);
            }
        }
    }

    @Override
    public CardAdapter getAdapter(int fragmentId) {
        if (fragmentId == FRAGMENT_ONE_DAY_SCHEDULE) {
            // TODO; test code
            OneDayTimeTable oneDayTimeTable = new OneDayTimeTable(4, DayOfWeek.WEDNESDAY, this);
            return new DayScheduleCardAdapter(oneDayTimeTable, this);
        } else {
            return new WeeklyScheduleCardAdapter();
        }
    }

    private boolean onSwitchFragments(final int position, final boolean wasSelected) {
        // Avoid null pointer error
        if (mCurrentFragment == null) {
            mCurrentFragment = mPagerAdapter.getCurrentFragment();
        }

        if (wasSelected) {
            mCurrentFragment.refresh();
        } else {
            switchFragments(position);
        }

        return true;
    }

    private void switchFragments(final int fragmentID) {
        mViewPager.setCurrentItem(fragmentID, false);
        mCurrentFragment = mPagerAdapter.getCurrentFragment();
    }

    private void showFAB(final boolean hideAfterAnim) {
        // Show FAB with animation
        mFab.setVisibility(View.VISIBLE);
        mFab.setAlpha(0f);
        mFab.setScaleX(0f);
        mFab.setScaleY(0f);
        mFab.animate().alpha(1).scaleX(1).scaleY(1).setDuration(FAB_ANIMATION_DURATION)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (hideAfterAnim) {
                            // Hide FAB again!
                            hideFAB(false);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    private void hideFAB(final boolean showAfterAnim) {
        // Hide FAB with animation
        mFab.animate().alpha(0).scaleX(0).scaleY(0).setDuration(FAB_ANIMATION_DURATION)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mFab.setVisibility(View.INVISIBLE);
                        if (showAfterAnim) {
                            // Show FAB again!
                            showFAB(false);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mFab.setVisibility(View.INVISIBLE);
                        if (showAfterAnim) {
                            showFAB(false);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    private void onFabClicked(final int bottomNavigationTabPos) {
        switch (bottomNavigationTabPos) {

            case FRAGMENT_ONE_DAY_SCHEDULE:
                Intent intent = EditEntryObjectActivity.createIntent(EntryObjectType.MERGABLE_TIME_BLOCK);
                intent.setClass(this, EditEntryObjectActivity.class);
                startActivity(intent);
                break;
        }
    }
}
