package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;

/**
 * Created by daichi on 8/9/17.
 */

public class Location extends EntryObject {

    private long mId;
    private String mName;
    private String mNote;

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<Location> CREATOR =
            new Creator<Location>() {
                @Override
                public Location createFromParcel(Parcel source) {
                    return new Location(source);
                }

                @Override
                public Location[] newArray(int size) {
                    return new Location[0];
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
    }

    public Location(final Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mNote = in.readString();
    }

    /**
     * Abstract method implementation
     */

    @Override
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getLocationId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.LocationEntry.ATTR_ID);
        }
        if ((mName = EntryHelper.getLocationName(entry, null)) == null) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.LocationEntry.ATTR_NAME);
        }

        mNote = EntryHelper.getLocationNote(entry, null);
    }

    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.LocationEntry.TABLE_NAME);
        EntryHelper.setLocationId(entry, mId);
        EntryHelper.setLocationName(entry, mName);
        EntryHelper.setLocationNote(entry, mNote);

        return entry;
    }

    /**
     * Class methods
     */

    public Location(@NonNull final Entry entry) {
        construct(entry);
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
