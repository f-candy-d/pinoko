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

public class Event extends EntryObject {

    private long mId;
    private String mName;
    private String mNote;
    private LongSparseArray<Location> mLocations = new LongSparseArray<>();

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<Event> CREATOR =
            new Creator<Event>() {
                @Override
                public Event createFromParcel(Parcel source) {
                    return new Event(source);
                }

                @Override
                public Event[] newArray(int size) {
                    return new Event[size];
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

        dest.writeInt(mLocations.size());
        if (0 < mLocations.size()) {
            Location[] locations = new Location[mLocations.size()];
            for (int i = 0; i < mLocations.size(); ++i) {
                locations[i] = mLocations.valueAt(i);
            }

            dest.writeParcelableArray(locations, flags);
        }
    }

    public Event(final Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mNote = in.readString();

        final int size = in.readInt();
        if (0 < size) {
            Location[] locations = (Location[]) in.readParcelableArray(Location.class.getClassLoader());
            for (Location location : locations) {
                mLocations.put(location.getId(), location);
            }
        }
    }

    /**
     * Abstract method implementation
     */

    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.EventEntry.TABLE_NAME);
        EntryHelper.setEventId(entry, mId);
        EntryHelper.setEventName(entry, mName);
        EntryHelper.setEventNote(entry, mNote);
        EntryHelper.setEventLocationId(entry, mLocations.keyAt(0));

        return entry;
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

    /**
     * Class methods
     */

    public Event(final Entry entry) {}

    public Event(final Entry entry, final Context context) {
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
            // Location
            long[] locIds = new long[mLocations.size()];
            for (int i = 0; i < mLocations.size(); ++i) {
                locIds[i] = mLocations.keyAt(i);
            }

            ArrayList<Entry> results = dataManager.selectWhereIdIsIn(
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

}
