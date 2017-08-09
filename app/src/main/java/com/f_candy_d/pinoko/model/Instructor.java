package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/9/17.
 */

public class Instructor extends EntryObject {

    private long mId;

    /**
     * region; Parcelable implement
     */

    public static final Parcelable.Creator<Instructor> CREATOR =
            new Creator<Instructor>() {
                @Override
                public Instructor createFromParcel(Parcel source) {
                    return null;
                }

                @Override
                public Instructor[] newArray(int size) {
                    return new Instructor[0];
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
     * Abstract method implement
     */

    @Override
    protected void construct(@NonNull Entry entry) {

    }

    @Override
    public Entry toEntry() {
        return null;
    }


    public Instructor(@NonNull final Entry entry) {

    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }
}
