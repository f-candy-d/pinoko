package com.f_candy_d.pinoko.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v4.util.LongSparseArray;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;
import com.f_candy_d.pinoko.utils.EntryHelper;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by daichi on 17/08/08.
 */

public class Event extends EntryObject {

    private long mId;
    private String mName;
    private String mNote;
    private LongSparseArray<Location> mLocations;

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
            ArrayList<Location> locations = new ArrayList<>(mLocations.size());
            for (int i = 0; i < mLocations.size(); ++i) {
                locations.add(mLocations.valueAt(i));
            }

            dest.writeList(locations);
        }
    }

    @SuppressWarnings("unchecked")
    public Event(final Parcel in) {
        init();

        mId = in.readLong();
        mName = in.readString();
        mNote = in.readString();

        final int size = in.readInt();
        if (0 < size) {
            ArrayList<Location> locations = in.readArrayList(this.getClass().getClassLoader());
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
        if (mLocations.size() != 0) {
            EntryHelper.setEventLocationId(entry, mLocations.keyAt(0));
        } else {
            EntryHelper.setEventLocationId(entry, DBContract.NULL_ID);
        }

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
        if (id != DBContract.NULL_ID) {
            mLocations.put(id, null);
        }

        mNote = EntryHelper.getEventNote(entry, null);
    }

    /**
     * Class methods
     */

    public Event() {
        this(null, null);
    }

    public Event(final Entry entry, final Context context) {
        init();
        if (entry != null) {
            construct(entry);
            if (context != null) {
                complementData(context);
            }
        }
    }

    private void init() {
        mId = DBContract.NULL_ID;
        mLocations = new LongSparseArray<>();
        mName = null;
        mNote = null;
    }

    public void complementData(@NonNull final Context context) {
        DBDataManager dataManager = new DBDataManager(context, DBDataManager.Mode.READ);
        if (dataManager.isOpen()) {
            // Location
            long[] locIds = new long[mLocations.size()];
            for (int i = 0; i < mLocations.size(); ++i) {
                locIds[i] = mLocations.keyAt(i);
            }

            if (locIds.length != 0) {
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

    public void addLocation(@NonNull final Location location) {
        mLocations.put(location.getId(), location);
    }

    // TODO; Test
    public Location getLocation() {
        return (mLocations.size() != 0) ? mLocations.valueAt(0) : new Location();
    }

    public void addLocations(@NonNull final Collection<Location> locations) {
        for (Location location : locations) {
            mLocations.put(location.getId(), location);
        }
    }

    public void setLocations(@NonNull final Collection<Location> locations) {
        mLocations.clear();
        addLocations(locations);
    }

    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>(mLocations.size());
        for (int i = 0; i < mLocations.size(); ++i) {
            locations.add(mLocations.valueAt(i));
        }

        return locations;
    }
}
