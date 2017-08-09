package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/9/17.
 */

public class Location extends EntryObject {

    private long mId;

    /**
     * region; Parcelable implementation
     */

    public static final Parcelable.Creator<Location> CREATOR =
            new Creator<Location>() {
                @Override
                public Location createFromParcel(Parcel source) {
                    return null;
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

    }

    /**
     * Abstract method implementation
     */

    @Override
    protected void construct(@NonNull Entry entry) {

    }

    @Override
    public Entry toEntry() {
        return null;
    }

    public Location(@NonNull final Entry entry) {

    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }
}
