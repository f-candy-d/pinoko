package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.EntryObjectType;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by daichi on 17/08/08.
 */

public class MergeableTimeBlock<T extends EntryObject> extends EntryObject implements Parcelable {

    private T mContent = null;
    private ArrayList<MergeableTimeBlock<T>> mMerged = null;

    // Attributes
    private long mId;
    private long mTargetId;
    private long mDatetimeBegin = Long.MIN_VALUE;
    private long mDatetimeEnd = Long.MAX_VALUE;
    private int mTimeTableId;
    private TimeBlockFormer.Type mType;
    private TimeBlockFormer.Category mCategory;
    private DayOfWeek mDayOfWeek;

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<MergeableTimeBlock> CREATOR =
            new Creator<MergeableTimeBlock>() {
                @Override
                public MergeableTimeBlock createFromParcel(Parcel source) {
                    return new MergeableTimeBlock(source);
                }

                @Override
                public MergeableTimeBlock[] newArray(int size) {
                    return new MergeableTimeBlock[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeLong(mTargetId);
        dest.writeLong(mDatetimeBegin);
        dest.writeLong(mDatetimeEnd);
        dest.writeInt(mTimeTableId);
        dest.writeSerializable(mType);
        dest.writeSerializable(mCategory);
        dest.writeSerializable(mDayOfWeek);

        if (mMerged == null || mMerged.size() == 0) {
            dest.writeInt(0);
        } else {
            dest.writeInt(mMerged.size());
            dest.writeSerializable(mMerged.get(0).getClass());
            dest.writeList(mMerged);
        }

        if (mContent != null) {
            dest.writeSerializable(mContent.getClass());
            dest.writeParcelable(mContent, flags);
        } else {
            dest.writeSerializable(null);
        }
    }

    public MergeableTimeBlock(final Parcel in) {
        mId = in.readInt();
        mTargetId = in.readLong();
        mDatetimeBegin = in.readLong();
        mDatetimeEnd = in.readLong();
        mTimeTableId = in.readInt();
        mType = (TimeBlockFormer.Type) in.readSerializable();
        mCategory = (TimeBlockFormer.Category) in.readSerializable();
        mDayOfWeek = (DayOfWeek) in.readSerializable();

        final int size = in.readInt();
        if (size == 0) {
            mMerged = null;
        }  else {
            mMerged = new ArrayList<>(size);
            Class<?> classType = (Class<?>) in.readSerializable();
            in.readList(mMerged, classType.getClassLoader());
        }

        Class<?> classType = (Class<?>) in.readSerializable();
        if (classType != null) {
            mContent = in.readParcelable(classType.getClassLoader());
        } else {
            mContent = null;
        }
    }

    /**
     * region; abstract methods implementation
     */

    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.TimeBlockEntry.TABLE_NAME);
        EntryHelper.setTimeBlockId(entry, mId);
        EntryHelper.setTimeBlockType(entry, mType);
        EntryHelper.setTimeBlockCategory(entry, mCategory);
        EntryHelper.setTImeBlockTargetId(entry, mTargetId);
        EntryHelper.setTimeBlockDatetimeBegin(entry, mDatetimeBegin);
        EntryHelper.setTimeBlockDatetimeEnd(entry, mDatetimeEnd);
        EntryHelper.setTimeBlockTimeTableId(entry, mTimeTableId);
        EntryHelper.setTimeBlockDayOfWeek(entry, mDayOfWeek);

        return entry;
    }

    @Override
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getTimeBlockId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.TimeBlockEntry.ATTR_ID);
        }

        mType = EntryHelper.getTimeBlockType(entry);
        mCategory = EntryHelper.getTimeBlockCategory(entry);
        mTargetId = EntryHelper.getTimeBlockTargetId(entry, DBContract.NULL_ID);
        mDatetimeBegin = EntryHelper.getTimeBlockDatetimeBegin(entry, mDatetimeBegin);
        mDatetimeEnd = EntryHelper.getTimeBlockDatetimeEnd(entry, mDatetimeEnd);
        mTimeTableId = EntryHelper.getTimeBlockTimeTableId(entry);
        mDayOfWeek = EntryHelper.getTimeBlockDayOfWeek(entry);
    }

    @Override
    public EntryObjectType getEntryObjectType() {
        return EntryObjectType.MERGABLE_TIME_BLOCK;
    }

    /**
     * region; MergeableTimeBlock class methods
     */

    public MergeableTimeBlock() {}

    public MergeableTimeBlock(final Entry entry) {
        if (entry != null) {
            construct(entry);
        }
    }

    public MergeableTimeBlock<T> mergeWith(@NonNull final MergeableTimeBlock<T> timeBlock) {
        final long beginTime = timeBlock.getDatetimeBegin();
        final long endTime = timeBlock.getDatetimeEnd();

        if ((mDatetimeBegin <= beginTime && beginTime <= mDatetimeEnd) ||
                (mDatetimeBegin <= endTime && endTime <= mDatetimeEnd)) {

            if (mMerged == null) {
                mMerged = new ArrayList<>();
                if (mContent != null) {
                    MergeableTimeBlock<T> tb = new MergeableTimeBlock<>(toEntry());
                    tb.setContent(mContent);
                    mMerged.add(tb);
                    mContent = null;
                }
            }

            for (int i = 0; i < mMerged.size() + 1; ++i) {

                if (i == mMerged.size()) {
                    mMerged.add(timeBlock);
                    sort();
                    // Update
                    mDatetimeEnd = endTime;

                } else if (beginTime < mMerged.get(i).getDatetimeBegin()) {
                    mMerged.add(i, timeBlock);
                    // Update
                    if (i == 0) {
                        mDatetimeBegin = beginTime;
                    }
                    break;
                }
            }
            return null;

        } else {
            // If merge failed, return again
            return timeBlock;
        }
    }

    public void setContent(@NonNull final T content) {
        mContent = content;
    }

    public long getDatetimeBegin() {
        return mDatetimeBegin;
    }

    public long getDatetimeEnd() {
        return mDatetimeEnd;
    }

    public T getContent() {
        return mContent;
    }

    public ArrayList<MergeableTimeBlock<T>> getMerged() {
        return mMerged;
    }

    public void setDatetimeBegin(long datetimeBegin) {
        mDatetimeBegin = datetimeBegin;
    }

    public void setDatetimeEnd(long datetimeEnd) {
        mDatetimeEnd = datetimeEnd;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public TimeBlockFormer.Type getType() {
        return mType;
    }

    public void setType(TimeBlockFormer.Type type) {
        mType = type;
    }

    public TimeBlockFormer.Category getCategory() {
        return mCategory;
    }

    public void setCategory(TimeBlockFormer.Category category) {
        mCategory = category;
    }

    public long getTargetId() {
        return mTargetId;
    }

    public void setTargetId(long targetId) {
        mTargetId = targetId;
    }

    public int getTimeTableId() {
        return mTimeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        mTimeTableId = timeTableId;
    }

    public DayOfWeek getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        mDayOfWeek = dayOfWeek;
    }

    public boolean hasContent() {
        return (mContent != null);
    }

    public void sort() {
        if (mMerged != null) {
            final Comparator<MergeableTimeBlock<T>> comparator =
                    new Comparator<MergeableTimeBlock<T>>() {
                        @Override
                        public int compare(MergeableTimeBlock<T> o1, MergeableTimeBlock<T> o2) {
                            if (o1.getDatetimeBegin() < o2.getDatetimeBegin()) {
                                return -1;
                            } else if (o1.getDatetimeBegin() > o2.getDatetimeBegin()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    };

            Collections.sort(mMerged, comparator);
        }
    }

    public String getDatetimeBeginAsString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDatetimeBegin);
        return String.valueOf(calendar.get(Calendar.YEAR)) + " / "
                + DayOfWeek.from(calendar.get(Calendar.MONTH)).toString() + " / "
                + String.valueOf(calendar.get(Calendar.DATE)) + " / "
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
                + String.valueOf(calendar.get(Calendar.MINUTE));
    }

    public String getDatetimeEndAsString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDatetimeEnd);
        return String.valueOf(calendar.get(Calendar.YEAR)) + " / "
                + DayOfWeek.from(calendar.get(Calendar.MONTH)).toString() + " / "
                + String.valueOf(calendar.get(Calendar.DATE)) + " / "
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
                + String.valueOf(calendar.get(Calendar.MINUTE));
    }
}