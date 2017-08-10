package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;

import java.util.ArrayList;

/**
 * Created by daichi on 17/08/08.
 */

public class Course extends EntryObject {

    private long mId;
    private String mName;
    private String mNote;
    private LongSparseArray<Location> mLocations = new LongSparseArray<>();
    private LongSparseArray<Instructor> mInstructors = new LongSparseArray<>();
    private int mLength;

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<Course> CREATOR =
            new Creator<Course>() {
                @Override
                public Course createFromParcel(Parcel source) {
                    return new Course(source);
                }

                @Override
                public Course[] newArray(int size) {
                    return new Course[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mNote);
        dest.writeInt(mLength);

        dest.writeInt(mLocations.size());
        if (0 < mLocations.size()) {
            ArrayList<Location> locations = new ArrayList<>(mLocations.size());
            for (int i = 0; i < mLocations.size(); ++i) {
                locations.add(mLocations.valueAt(i));
            }

//            dest.writeParcelableArray(locations, flags);
            dest.writeList(locations);
        }

        dest.writeInt(mInstructors.size());
        if (0 < mInstructors.size()) {
            ArrayList<Instructor> instructors = new ArrayList<>(mInstructors.size());
            for (int i = 0; i < mInstructors.size(); ++i) {
                instructors.add(mInstructors.valueAt(i));
            }

//            dest.writeParcelableArray(instructors, flags);
            dest.writeList(instructors);
        }
    }

    public Course(final Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mNote = in.readString();
        mLength = in.readInt();

        int size = in.readInt();
        if (0 < size) {
            ArrayList<Location> locations = (ArrayList<Location>) in.readArrayList(Location.class.getClassLoader());
            for (Location location : locations) {
                mLocations.put(location.getId(), location);
            }
        }

        size = in.readInt();
        if (0 < size) {
            ArrayList<Instructor> instructors = (ArrayList<Instructor>) in.readArrayList(Instructor.class.getClassLoader());
            for (Instructor instructor : instructors) {
                mInstructors.put(instructor.getId(), instructor);
            }
        }
    }

    /**
     * Abstract method implementation
     */
    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.CourseEntry.TABLE_NAME);
        EntryHelper.setCourseId(entry, mId);
        EntryHelper.setCourseName(entry, mName);
        EntryHelper.setCourseNote(entry, mNote);
        EntryHelper.setCourseLength(entry, mLength);
        EntryHelper.setCourseInstructorId(entry, mInstructors.keyAt(0));
        EntryHelper.setCourseLocationId(entry, mLocations.keyAt(0));

        return entry;
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

    /**
     * Class methods
     */
    public Course(final Entry entry) {
        this(entry, null);
    }

    public Course(final Entry entry, final Context context) {
        if (entry != null) {
            construct(entry);
            if (context != null) {
                complementData(context);
            }
        }
    }

    public void complementData(@NonNull final Context context) {
        DBDataManager dataManager = new DBDataManager(context, DBDataManager.Mode.READ);
        if (dataManager.isOpen()) {
            // Instructor
            long[] instIds = new long[mInstructors.size()];
            for (int i = 0; i < mInstructors.size(); ++i) {
                instIds[i] = mInstructors.keyAt(i);
            }

            ArrayList<Entry> results = dataManager.selectWhereIdIsIn(
                    DBContract.InstructorEntry.TABLE_NAME,
                    instIds,
                    DBContract.InstructorEntry.TABLE_NAME);

            Instructor instructor;
            mInstructors.clear();
            for (Entry entry : results) {
                instructor = new Instructor(entry);
                mInstructors.put(instructor.getId(), instructor);
            }

            results.clear();

            // Location
            long[] locIds = new long[mLocations.size()];
            for (int i = 0; i < mLocations.size(); ++i) {
                locIds[i] = mLocations.keyAt(i);
            }

            results = dataManager.selectWhereIdIsIn(
                    DBContract.LocationEntry.TABLE_NAME,
                    locIds,
                    DBContract.LocationEntry.TABLE_NAME);

            mLocations.clear();
            Location location;
            for (Entry entry : results) {
                location = new Location(entry);
                mLocations.put(location.getId(), location);
            }

            dataManager.close();
        }
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
