package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;
import com.f_candy_d.pinoko.utils.SQLQuery;
import com.f_candy_d.pinoko.utils.SQLWhere;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by daichi on 17/08/08.
 */

public class OneDayTimeTable {

    private ArrayList<MergeableTimeBlock> mTimeBlocks;
    private final DayOfWeek mDayOfWeek;
    private DBDataManager mDataManager;
    private final int mTimeTableId;
    private final Context mContext;

    public OneDayTimeTable(int timeTableId, @NonNull final DayOfWeek dayOfWeek, @NonNull final Context context) {
        mDayOfWeek = dayOfWeek;
        mTimeBlocks = new ArrayList<>();
        mDataManager = new DBDataManager(context);
        mTimeTableId = timeTableId;
        mContext = context;

        construct();
    }

    private <T extends EntryObject>
    void addTimeBlock(@NonNull final Entry entry, @NonNull final T content) {
        final MergeableTimeBlock<T> timeBlock = new MergeableTimeBlock<>(entry);
        timeBlock.setContent(content);

        mTimeBlocks.add(timeBlock);
    }

    public void fillSpace() {
//        sortAscendingAndAffiliationOrder();
//        for (int i = 0; i < mCourseTimeBlocks.size() - 1; ++i) {
//            if (mCourseTimeBlocks.get(i).getEntryAffiliation().equals(mCourseTimeBlocks.get(i + 1).getEntryAffiliation())
//                    && mCourseTimeBlocks.get(i).getDatetimeEnd() != mCourseTimeBlocks.get(i + 1).getDatetimeBegin()) {
//                MergeableTimeBlock blankTimeBlock = makeBlankTimeBlock(
//                        mCourseTimeBlocks.get(i).getDatetimeEnd(),
//                        mCourseTimeBlocks.get(i + 1).getDatetimeBegin());
//
//                mCourseTimeBlocks.add(i + 1, blankTimeBlock);
//            }
//        }
    }

    private MergeableTimeBlock makeBlankTimeBlock(final long beginTime, final long endTime9) {
        return null;
    }

    private void mergeAllIfPossible() {
    }

    private void construct() {
        SQLQuery query = new SQLQuery();
        SQLWhere.SpecExpr onToday = new SQLWhere.SpecExpr();
        SQLWhere.CondExpr onDayOfWeek = new SQLWhere.CondExpr();
        SQLWhere.CondExpr typeIsWeekly = new SQLWhere.CondExpr();
        SQLWhere.CondExpr categoryIs = new SQLWhere.CondExpr();
        SQLWhere.CondExpr timeTableIdIs = new SQLWhere.CondExpr();

        // (begin of the day) <= ATTR_DATETIME_BEGIN <= (end of the day)
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.MILLISECOND, 0);
        final long todayBegin = today.getTimeInMillis();
        today.add(Calendar.DAY_OF_MONTH, 1);
        final long todayEnd = today.getTimeInMillis();
        onToday.between(DBContract.TimeBlockEntry.ATTR_DATETIME_BEGIN, todayBegin, todayEnd);

        // ATTR_DAY_OF_WEEK == mDayOfWeek.toInt()
        onDayOfWeek.l(DBContract.TimeBlockEntry.ATTR_DAY_OF_WEEK).equalTo().r(mDayOfWeek.toInt());

        // ATTR_TYPE == WEEKLY
        typeIsWeekly.l(DBContract.TimeBlockEntry.ATTR_TYPE).equalTo().r(TimeBlockFormer.Type.WEEKLY.toInt());

        // ATTR_CATEGORY == EVENT or COURSE
        categoryIs.l(DBContract.TimeBlockEntry.ATTR_CATEGORY).equalTo().r(TimeBlockFormer.Category.COURSE.toInt());

        //ATTR_TIME_TABLE_ID == mTimeTableId
        timeTableIdIs.l(DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID).equalTo().r(mTimeTableId);

        // Collect today's time block entries
        SQLWhere.LogicExpr where = new SQLWhere.LogicExpr();
        where.l(timeTableIdIs)
        .and()
        .r(new SQLWhere.LogicExpr().l(onToday, true)
                .or()
                .r(new SQLWhere.LogicExpr().l(typeIsWeekly)
                        .and()
                        .r(onDayOfWeek), true), true);
        query.putTables(DBContract.TimeBlockEntry.TABLE_NAME);
        query.setSelection(where);

        mDataManager.openAsWritableAppend();
        ArrayList<Entry> results = mDataManager.select(query, DBContract.TimeBlockEntry.TABLE_NAME);
        // Collect course or event entries
        Entry contentEntry;
        Event event;
        Course course;
        for (Entry timeBlockEntry : results) {
            switch (EntryHelper.getTimeBlockCategory(timeBlockEntry)) {
                case EVENT:
                     contentEntry = mDataManager.selectWhereIdIs(
                             DBContract.EventEntry.TABLE_NAME,
                             EntryHelper.getTimeBlockTargetId(timeBlockEntry),
                             DBContract.EventEntry.TABLE_NAME);

                    if (contentEntry != null) {
                        event = new Event(contentEntry, mContext);
                        addTimeBlock(timeBlockEntry, event);
                    }
                    break;

                case COURSE:
                    contentEntry = mDataManager.selectWhereIdIs(
                            DBContract.CourseEntry.TABLE_NAME,
                            EntryHelper.getTimeBlockTargetId(timeBlockEntry),
                            DBContract.CourseEntry.TABLE_NAME);

                    if (contentEntry != null) {
                        course = new Course(contentEntry, mContext);
                        addTimeBlock(timeBlockEntry, course);
                    }
                    break;
            }
        }

        mDataManager.close();
    }

    public ArrayList<MergeableTimeBlock> getTimeBlocks() {
        return mTimeBlocks;
    }
}
