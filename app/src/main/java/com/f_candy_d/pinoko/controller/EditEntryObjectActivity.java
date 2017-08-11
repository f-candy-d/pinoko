package com.f_candy_d.pinoko.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.view.EditCourseTimeBlockFragment;

public class EditEntryObjectActivity extends AppCompatActivity
        implements EditCourseTimeBlockFragment.MessageListener<MergeableTimeBlock<Course>> {

    public enum ViewType {
        EDIT_COURSE_TIME_BLOCK,
        EDIT_EVENT_TIME_BLOCK,
        EDIT_COURSE,
        EDIT_INSTRUCTOR,
        EDIT_LOCATION
    }

    private static final String EXTRA_ENTRY_OBJECT = "entryObject";
    private static final String EXTRA_VIEW_TYPE = "viewType";
    private static final String EXTRA_TIMETABLE_ID = "timeTableId";

    public static final String RESULT_ENTRY_OBJECT = "resultEntryObject";

    private static final int FAILED_TIMETABLE_ID = -1;

    private EntryObject mContent;
    private ViewType mViewType;
    private int mTimeTableId;

    public static Intent createIntentWithArg(final int timeTableId,
                                             final EntryObject entryObject,
                                             @NonNull final ViewType viewType) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIMETABLE_ID, timeTableId);
        intent.putExtra(EXTRA_ENTRY_OBJECT, entryObject);
        intent.putExtra(EXTRA_VIEW_TYPE, viewType);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry_object);

        final Intent intent = getIntent();
        mContent = intent.getParcelableExtra(EXTRA_ENTRY_OBJECT);
        mViewType = (ViewType) intent.getSerializableExtra(EXTRA_VIEW_TYPE);
        mTimeTableId = intent.getIntExtra(EXTRA_TIMETABLE_ID, FAILED_TIMETABLE_ID);

        launchFragment();
    }

    private void launchFragment() {
        Fragment fragment = null;

        switch (mViewType) {
            case EDIT_COURSE_TIME_BLOCK:
                if (mContent != null) {
                    MergeableTimeBlock timeBlock = (MergeableTimeBlock) mContent;
                        fragment = EditCourseTimeBlockFragment
                                .newInstance(timeBlock.getTimeTableId(), timeBlock);

                } else {
                    fragment = EditCourseTimeBlockFragment.newInstance(mTimeTableId, null);
                }

                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, fragment, null).commit();
        }
    }

    private void finishEditing(@NonNull final Intent result, final boolean isCanceled) {
        setResult((isCanceled) ? RESULT_CANCELED : RESULT_OK, result);
        finish();
    }

    /**
     * region; EditCourseTimeBlockFragment.MessageListener<MergeableTimeBlock<Course>> implementation
     */
    @Override
    public void onFinishEditing(MergeableTimeBlock<Course> content, boolean isCanceled) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_ENTRY_OBJECT, content);
        finishEditing(intent, isCanceled);
    }
}
