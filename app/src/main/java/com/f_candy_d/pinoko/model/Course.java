package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

/**
 * Created by daichi on 17/08/08.
 */

public class Course extends EntryObject implements MergeableTimeBlock.RequiresInterface {

    private long mId;
    private String mName;
    private String mNote;
    private LongSparseArray<Location> mLocations;
    private LongSparseArray<Instructor> mInstructors;
    private int mLength;
    private long mDatetimeBegin;
    private long mDatetimeEnd;
    private Context mContext;

    public Course(@NonNull final Context context) {
        this(null, context);
    }

    public Course(final Entry entry, @NonNull final Context context) {
        mLocations = new LongSparseArray<>();
        mInstructors = new LongSparseArray<>();
        mContext = context;
        if (entry != null) {
            construct(entry);
        }
    }

    @Override
    public long getDatetimeBegin() {
        return mDatetimeBegin;
    }

    @Override
    public long getDatetimeEnd() {
        return mDatetimeEnd;
    }

    @Override
    public void setDatetimeBegin(long datetimeBegin) {
        mDatetimeBegin = datetimeBegin;
    }

    @Override
    public void setDatetimeEnd(long datetimeEnd) {
        mDatetimeEnd = datetimeEnd;
    }

    @Override
    public TimeBlockFormer.Category getCategory() {
        return TimeBlockFormer.Category.COURSE;
    }

    @Override
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getCourseId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.CourseEntry.ATTR_ID);
        }
        if ((mName = EntryHelper.getCourseName(entry, null)) == null) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.CourseEntry.ATTR_NAME);
        }
        if ((mLength = EntryHelper.getCourseLength(entry, 0)) == 0) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.CourseEntry.ATTR_LENGTH);
        }

        // TODO; Deal with multiple location & instructor ids sometime soon...
        long id = EntryHelper.getCourseLocationId(entry, DBContract.NULL_ID);
        if (id == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.CourseEntry.ATTR_LOCATION_ID);
        }
        mLocations.put(id, null);

        id = EntryHelper.getCourseInstructorId(entry, DBContract.NULL_ID);
        if (id == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.CourseEntry.ATTR_INSTRUCTOR_ID);
        }
        mInstructors.put(id, null);

        mNote = EntryHelper.getCourseNote(entry, null);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }
}
