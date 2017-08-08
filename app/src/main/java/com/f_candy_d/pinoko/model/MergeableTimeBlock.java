package com.f_candy_d.pinoko.model;

import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by daichi on 17/08/08.
 */

public class MergeableTimeBlock<T extends MergeableTimeBlock.RequiresInterface> {

    public interface RequiresInterface {
        long getDatetimeBegin();
        long getDatetimeEnd();
        void setDatetimeBegin(final long datetimeBegin);
        void setDatetimeEnd(final long datetimeEnd);
        TimeBlockFormer.Category getCategory();
    }

    private ArrayList<T> mContents = new ArrayList<>();
    private long mEarliestTime = Long.MIN_VALUE;
    private long mLatestTime = Long.MAX_VALUE;
    private Comparator<T> mSortComparator;
    private TimeBlockFormer.Category mCategory;

    public MergeableTimeBlock(@NonNull final TimeBlockFormer.Category category) {
        this(category, null);
    }

    public MergeableTimeBlock(@NonNull final TimeBlockFormer.Category category,
                              final Comparator<T> sortComparator) {
        mCategory = category;
        mSortComparator = sortComparator;
    }

    public MergeableTimeBlock<T> mergeWith(@NonNull final MergeableTimeBlock<T> timeBlock) {

        if (mCategory != timeBlock.getCategory()) {
            return timeBlock;
        }

        final long beginTime = timeBlock.getEarliestTime();
        final long endTime = timeBlock.getLatestTime();

        if ((mEarliestTime <= beginTime && beginTime <= mLatestTime) ||
                (mEarliestTime <= endTime && endTime <= mLatestTime)) {

            for (int i = 0; i < mContents.size() + 1; ++i) {

                if (i == mContents.size()) {
                    mContents.addAll(timeBlock.getContents());
                    sort();
                    // Update
                    mLatestTime = endTime;

                } else if (beginTime < mContents.get(i).getDatetimeBegin()) {
                    mContents.addAll(timeBlock.getContents());
                    // Update
                    if (i == 0) {
                        mEarliestTime = beginTime;
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

    public void addContent(@NonNull final T content) {
        mContents.add(content);
        if (content.getDatetimeBegin() < mEarliestTime) {
            mEarliestTime = content.getDatetimeBegin();
        }
        if (mLatestTime < content.getDatetimeEnd()) {
            mLatestTime = content.getDatetimeEnd();
        }
    }

    public long getEarliestTime() {
        return mEarliestTime;
    }

    public long getLatestTime() {
        return mLatestTime;
    }

    public TimeBlockFormer.Category getCategory() {
        return mCategory;
    }

    public ArrayList<T> getContents() {
        return mContents;
    }

    public Comparator<T> getSortComparator() {
        return mSortComparator;
    }

    public void setSortComparator(Comparator<T> sortComparator) {
        mSortComparator = sortComparator;
    }

    public void sort() {
        if (mSortComparator != null) {
            Collections.sort(mContents, mSortComparator);
        }
    }
}