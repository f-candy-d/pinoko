package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.ParcelHelper;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by daichi on 17/08/08.
 */

public class MergeableTimeBlock<T extends EntryObject> extends EntryObject implements Parcelable {

    private final Class<T> mBindType;
    private T mContent = null;
    private ArrayList<MergeableTimeBlock<T>> mMergedBlocks = null;

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
        dest.writeSerializable(mBindType);
        ParcelHelper.writeEnum(mType, dest);
        ParcelHelper.writeEnum(mCategory, dest);
        ParcelHelper.writeEnum(mDayOfWeek, dest);

        if (mMergedBlocks == null || mMergedBlocks.size() == 0) {
            dest.writeInt(0);
        } else {
            dest.writeInt(mMergedBlocks.size());
            dest.writeList(mMergedBlocks);
        }

        if (mContent != null) {
            dest.writeSerializable(mContent.getClass());
            dest.writeParcelable(mContent, flags);
        } else {
            dest.writeSerializable(null);
        }
    }

    @SuppressWarnings("unchecked")
    public MergeableTimeBlock(final Parcel in) {
        mId = in.readLong();
        mTargetId = in.readLong();
        mDatetimeBegin = in.readLong();
        mDatetimeEnd = in.readLong();
        mTimeTableId = in.readInt();
        mBindType = (Class<T>) in.readSerializable();
        mType = ParcelHelper.readEnum(TimeBlockFormer.Type.class, in);
        mCategory = ParcelHelper.readEnum(TimeBlockFormer.Category.class, in);
        mDayOfWeek = ParcelHelper.readEnum(DayOfWeek.class, in);

        final int size = in.readInt();
        if (size == 0) {
            mMergedBlocks = null;
        }  else {
            mMergedBlocks = in.readArrayList(this.getClass().getClassLoader());
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

    /**
     * region; MergeableTimeBlock class methods
     */

    public MergeableTimeBlock(@NonNull final Class<T> bindType) {
        this(bindType, null);
    }

    public MergeableTimeBlock(@NonNull final Class<T> bindType, final Entry entry) {
        mBindType = bindType;
        if (entry != null) {
            construct(entry);
        }
    }

    @SuppressWarnings("unchecked")
    public MergeableTimeBlock<?> mergeWith(@NonNull final MergeableTimeBlock<?> timeBlock) {
        if (mBindType != timeBlock.getBindType()) {
            return timeBlock;
        }

        final long beginTime = timeBlock.getDatetimeBegin();
        final long endTime = timeBlock.getDatetimeEnd();

        if ((mDatetimeBegin <= beginTime && beginTime < mDatetimeEnd) ||
                (mDatetimeBegin < endTime && endTime <= mDatetimeEnd)) {

            if (!hasBlocks()) {
                mMergedBlocks = new ArrayList<>();
                if (hasContent()) {
                    MergeableTimeBlock<T> tb = new MergeableTimeBlock<>(mBindType, toEntry());
                    tb.setContent(mContent);
                    mMergedBlocks.add(tb);
                    mContent = null;
                }
            }

            for (int i = 0; i < mMergedBlocks.size() + 1; ++i) {

                if (i == mMergedBlocks.size()) {
                    mMergedBlocks.add((MergeableTimeBlock<T>) timeBlock);
                    sortMergedBlocks();
                    // Update
                    mDatetimeEnd = endTime;

                } else if (beginTime < mMergedBlocks.get(i).getDatetimeBegin()) {
                    mMergedBlocks.add(i, (MergeableTimeBlock) timeBlock);
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

    public ArrayList<MergeableTimeBlock<T>> getMergedBlocks() {
        return mMergedBlocks;
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

    public Class<T> getBindType() {
        return mBindType;
    }

    public boolean hasBlocks() { return (mMergedBlocks != null); }

    public void sortMergedBlocks() {
        if (mMergedBlocks != null) {
            final Comparator<MergeableTimeBlock> comparator =
                    new Comparator<MergeableTimeBlock>() {
                        @Override
                        public int compare(MergeableTimeBlock o1, MergeableTimeBlock o2) {
                            if (o1.getDatetimeBegin() < o2.getDatetimeBegin()) {
                                return -1;
                            } else if (o1.getDatetimeBegin() > o2.getDatetimeBegin()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    };

            sortMergedBlocks(comparator);
        }
    }

    public void sortMergedBlocks(@NonNull final Comparator<MergeableTimeBlock> comparator) {
        if (hasBlocks()) {
            Collections.sort(mMergedBlocks, comparator);
        }
    }

    public String getDatetimeBeginAsString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDatetimeBegin);
        return String.valueOf(calendar.get(Calendar.YEAR)) + " / "
                + DayOfWeek.from(calendar.get(Calendar.DAY_OF_WEEK)).toString() + " / "
                + String.valueOf(calendar.get(Calendar.DATE)) + " / "
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
                + String.valueOf(calendar.get(Calendar.MINUTE));
    }

    public String getDatetimeEndAsString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDatetimeEnd);
        return String.valueOf(calendar.get(Calendar.YEAR)) + " / "
                + DayOfWeek.from(calendar.get(Calendar.DAY_OF_WEEK)).toString() + " / "
                + String.valueOf(calendar.get(Calendar.DATE)) + " / "
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : "
                + String.valueOf(calendar.get(Calendar.MINUTE));
    }

    /**
     * If ? == T, return 'mContent' of 'timeBlock' as type T. Otherwise, return null.
     */
    public static <T extends EntryObject> T getCastedContent(MergeableTimeBlock<?> timeBlock,
                                                             Class<T> tClass) {
        if (timeBlock.getBindType() == tClass) {
            return tClass.cast(timeBlock.getContent());
        } else {
            return null;
        }
    }
}