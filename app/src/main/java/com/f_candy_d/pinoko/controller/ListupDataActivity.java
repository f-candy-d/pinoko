package com.f_candy_d.pinoko.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.Savable;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.utils.CourseFormer;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.ThrowExceptionHelper;
import com.f_candy_d.pinoko.view.CardListFragment;

import java.util.ArrayList;

public class ListupDataActivity extends AppCompatActivity
        implements CardListFragment.OnFragmentInteractionListener {

    public static final int VIEW_TYPE_LIST_UP_COURSES = 0;
    public static final int VIEW_TYPE_LIST_UP_LOCATIONS = 1;
    public static final int VIEW_TYPE_LIST_UP_INSTRUCTORS = 2;

    private static final int REQUEST_CODE_MAKE_NEW_COURSE = 1111;

    public static final String EXTRA_VIEW_TYPE = "view_type";

    private int mViewType;
    @Nullable private CardListFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listup_data);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_VIEW_TYPE)) {
            mViewType = intent.getIntExtra(EXTRA_VIEW_TYPE, VIEW_TYPE_LIST_UP_COURSES);
        } else {
            ThrowExceptionHelper.throwMissingRequiredParameterException("View Type");
        }

        init();
        initUI();
        launchFragment();
    }

    private void init() {}

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToMakeNewContentScreen();
            }
        });
    }

    @Override
    public CardAdapter getAdapter(int fragmentId) {
        switch (fragmentId) {

            case VIEW_TYPE_LIST_UP_COURSES:
                DBDataManager dataManager = new DBDataManager(this);
                dataManager.openAsReadable();
                if (dataManager.isOpen()) {
                    ArrayList<Entry> results = dataManager.selectAllOf(DBContract.CourseEntry.TABLE_NAME);
                    if (results != null) {
                        ArrayList<Course> courses = new ArrayList<>(results.size());
                        for (Entry entry : results) {
                            courses.add(new Course(entry));
                        }
                        return new CourseCardAdapter(courses, this);
                    }
                }
                break;
        }

        throw new IllegalArgumentException();
    }

    private void launchFragment() {
        mFragment = null;
        switch (mViewType) {

            case VIEW_TYPE_LIST_UP_COURSES:
                mFragment = CardListFragment.newInstance(VIEW_TYPE_LIST_UP_COURSES);
                break;

        }

        if (mFragment!= null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mFragment).commit();
        }
    }

    private void switchToMakeNewContentScreen() {
        Intent intent = null;
        int requestCode = -1;

        switch (mViewType) {

            case VIEW_TYPE_LIST_UP_COURSES:
                intent = EditEntryObjectActivity.createIntentWithArg(
                        null, EditEntryObjectActivity.ViewType.EDIT_COURSE, this);
                requestCode = REQUEST_CODE_MAKE_NEW_COURSE;
                break;
        }

        if (intent != null && requestCode != -1) {
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            reflectResultForRequest(requestCode, data.getExtras());
        }
    }

    private void reflectResultForRequest(final int requestCode, @NonNull final Bundle result) {
        switch (requestCode) {

            case REQUEST_CODE_MAKE_NEW_COURSE:
                final Course course = result.getParcelable(EditEntryObjectActivity.RESULT_ENTRY_OBJECT);
                if (mViewType == VIEW_TYPE_LIST_UP_COURSES && course != null && mFragment != null) {
                    final long id = saveNewData(new CourseFormer(course.toEntry()));
                    course.setId(id);
                    mFragment.getAdapter().insertData(course);
                }
                break;
        }
    }

    private long saveNewData(@NonNull final Savable newData) {
        DBDataManager dataManager = new DBDataManager(this);
        dataManager.openAsWritableAppend();
        if (dataManager.isOpen()) {
            final long id = dataManager.insert(newData);
            dataManager.close();
            return id;
        }

        return DBContract.NULL_ID;
    }
}
