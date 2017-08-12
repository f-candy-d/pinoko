package com.f_candy_d.pinoko.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Assignment;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.utils.ThrowExceptionHelper;
import com.f_candy_d.pinoko.view.EditAssignmentFragment;
import com.f_candy_d.pinoko.view.EditCourseTimeBlockFragment;
import com.f_candy_d.pinoko.view.EditEntryObjectFragment;
import com.f_candy_d.pinoko.view.EditEventTimeBlockFragment;

public class EditEntryObjectActivity extends AppCompatActivity
        implements EditCourseTimeBlockFragment.MessageListener {

    public enum ViewType {
        EDIT_COURSE_TIME_BLOCK,
        EDIT_EVENT_TIME_BLOCK,
        EDIT_COURSE,
        EDIT_INSTRUCTOR,
        EDIT_LOCATION,
        EDIT_ASSIGNMENT
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

    @SuppressWarnings("unchecked")
    private void launchFragment() {
        Fragment fragment = null;

        switch (mViewType) {
            case EDIT_COURSE_TIME_BLOCK:
                if (mContent != null) {
                    MergeableTimeBlock<?> timeBlock;
                    if (mContent instanceof MergeableTimeBlock
                            && (timeBlock = (MergeableTimeBlock<?>) mContent).getBindType() == Course.class) {
                        // Open as edit mode
                        fragment = EditCourseTimeBlockFragment
                                .newInstance(timeBlock.getTimeTableId(), (MergeableTimeBlock<Course>) timeBlock);
                    } else {
                        ThrowExceptionHelper
                                .throwClassCastException(MergeableTimeBlock.class, mContent.getClass());
                    }

                } else {
                    // Open as create mode
                    fragment = EditCourseTimeBlockFragment.newInstance(mTimeTableId, null);
                }
                break;


            case EDIT_EVENT_TIME_BLOCK:
                if (mContent != null) {
                    MergeableTimeBlock<?> timeBlock;
                    if (mContent instanceof MergeableTimeBlock
                            && (timeBlock = (MergeableTimeBlock<?>) mContent).getBindType() == Event.class) {
                        // Open as edit mode
                        fragment = EditEventTimeBlockFragment
                                .newInstance(timeBlock.getTimeTableId(), (MergeableTimeBlock<Event>) timeBlock);
                    } else {
                        ThrowExceptionHelper
                                .throwClassCastException(MergeableTimeBlock.class, mContent.getClass());
                    }

                } else {
                    // Open as create mode
                    fragment = EditEventTimeBlockFragment.newInstance(mTimeTableId, null);
                }
                break;

            case EDIT_ASSIGNMENT:
                if (mContent != null) {
                    if (mContent instanceof Assignment) {
                        fragment = EditAssignmentFragment.newInstance(mTimeTableId, (Assignment) mContent);
                    } else {
                        ThrowExceptionHelper.throwClassCastException(Assignment.class, mContent.getClass());
                    }

                } else {
                    fragment = EditAssignmentFragment.newInstance(mTimeTableId, null);
                }
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, fragment, null).commit();
        }
    }

    /**
     * region; EditCourseTimeBlockFragment.MessageListener implementation
     */
    @Override
    public void onFinishEditing(EntryObject content, boolean isCanceled) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_ENTRY_OBJECT, content);
        setResult((isCanceled) ? RESULT_CANCELED : RESULT_OK, intent);
        finish();
    }
}
