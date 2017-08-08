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

public class Event extends EntryObject implements MergeableTimeBlock.RequiresInterface {

    private long mId;
    private String mName;
    private String mNote;
    private LongSparseArray<Location> mLocations;
    private long mDatetimeBegin;
    private long mDatetimeEnd;
    private final Context mContext;

    public Event(@NonNull final Context context) {
        this(null, context);
    }

    public Event(final Entry entry, @NonNull final Context context) {
        mLocations = new LongSparseArray<>();
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
    public TimeBlockFormer.Category getCategory() {
        return TimeBlockFormer.Category.EVENT;
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
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getEventId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.EventEntry.ATTR_ID);
        }
        if ((mName = EntryHelper.getEventName(entry, null)) == null) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.EventEntry.ATTR_NAME);
        }
        // TODO; Deal with multiple location & instructor ids sometime soon...
        long id = EntryHelper.getEventLocationId(entry, DBContract.NULL_ID);
        if (id == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.EventEntry.ATTR_LOCATION_ID);
        }
        mLocations.put(id, null);

        mNote = EntryHelper.getEventNote(entry, null);
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

    public Context getContext() {
        return mContext;
    }
}
