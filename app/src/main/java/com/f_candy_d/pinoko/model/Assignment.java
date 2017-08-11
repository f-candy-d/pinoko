package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.ParcelHelper;

import java.util.Calendar;

/**
 * Created by daichi on 8/12/17.
 */

public class Assignment extends EntryObject {

    private long mId;
    private long mTimeBlockId;
    private long mDeadline;
    private boolean mIsDone;
    private String mName;
    private String mNote;

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<Assignment> CREATOR =
            new Creator<Assignment>() {
                @Override
                public Assignment createFromParcel(Parcel source) {
                    return new Assignment(source);
                }

                @Override
                public Assignment[] newArray(int size) {
                    return new Assignment[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeLong(mTimeBlockId);
        dest.writeLong(mDeadline);
        ParcelHelper.writeBool(mIsDone, dest);
        dest.writeString(mName);
        dest.writeString(mNote);
    }

    public Assignment(final Parcel in) {
        mId = in.readLong();
        mTimeBlockId = in.readLong();
        mDeadline = in.readLong();
        mIsDone = ParcelHelper.readBool(in);
        mName = in.readString();
        mNote = in.readString();
    }

    /**
     * region; Abstract method implementation
     */

    @Override
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getAssignmentId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.AssignmentEntry.ATTR_ID);
        }
        if ((mTimeBlockId = EntryHelper.getAssignmentTimeBlockId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.AssignmentEntry.ATTR_TIME_BLOCK_ID);
        }
        if ((mDeadline = EntryHelper.getAssignmentDeadline(entry, -1)) == -1) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.AssignmentEntry.ATTR_DEADLINE);
        }
        if ((mName = EntryHelper.getAssignmentName(entry, null)) == null) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.AssignmentEntry.ATTR_NAME);
        }

        mIsDone = EntryHelper.getAssignmentIsDone(entry);
        mNote = EntryHelper.getAssignmentNote(entry, null);
    }

    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.AssignmentEntry.TABLE_NAME);
        EntryHelper.setAssignmentId(entry, mId);
        EntryHelper.setAssignmentTimeBlockId(entry, mTimeBlockId);
        EntryHelper.setAssignmentDeadline(entry, mDeadline);
        EntryHelper.setAssignmentIsDone(entry, mIsDone);
        EntryHelper.setAssignmentName(entry, mNote);
        EntryHelper.setAssignmentNote(entry, mNote);

        return entry;
    }

    /**
     * region; Class methods
     */

    public Assignment() {}

    public Assignment(@NonNull Entry entry) {
        construct(entry);
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getTimeBlockId() {
        return mTimeBlockId;
    }

    public void setTimeBlockId(long timeBlockId) {
        mTimeBlockId = timeBlockId;
    }

    public long getDeadline() {
        return mDeadline;
    }

    public void setDeadline(long deadline) {
        mDeadline = deadline;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
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

    public String getDeadlineAsString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDeadline);
        return String.valueOf(calendar.get(Calendar.YEAR)) + " / "
                + DayOfWeek.from(calendar.get(Calendar.DAY_OF_WEEK)).toString() + " / "
                + String.valueOf(calendar.get(Calendar.DATE)) + " / "
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
                + String.valueOf(calendar.get(Calendar.MINUTE));
    }
}
