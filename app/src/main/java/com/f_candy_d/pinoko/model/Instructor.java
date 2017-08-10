package com.f_candy_d.pinoko.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.EntryHelper;

/**
 * Created by daichi on 8/9/17.
 */

public class Instructor extends EntryObject {

    private long mId;
    private String mName;
    private String mLab;
    private String mMail;
    private String mPhoneNumber;
    private String mNote;

    /**
     * region; Parcelable implement
     */

    public static final Parcelable.Creator<Instructor> CREATOR =
            new Creator<Instructor>() {
                @Override
                public Instructor createFromParcel(Parcel source) {
                    return new Instructor(source);
                }

                @Override
                public Instructor[] newArray(int size) {
                    return new Instructor[size];
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
        dest.writeString(mLab);
        dest.writeString(mMail);
        dest.writeString(mPhoneNumber);
        dest.writeString(mNote);
    }

    public Instructor(final Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mLab = in.readString();
        mMail = in.readString();
        mPhoneNumber = in.readString();
        mNote = in.readString();
    }

    /**
     * Abstract method implement
     */

    @Override
    protected void construct(@NonNull Entry entry) {
        if ((mId = EntryHelper.getInstructorId(entry, DBContract.NULL_ID)) == DBContract.NULL_ID) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.InstructorEntry.ATTR_ID);
        }
        if ((mName = EntryHelper.getInstructorName(entry, null)) == null) {
            throwExceptionForExpectedAttributeIsMissing(DBContract.InstructorEntry.ATTR_NAME);
        }

        mLab = EntryHelper.getInstructorLab(entry, null);
        mMail = EntryHelper.getInstructorMail(entry, null);
        mPhoneNumber = EntryHelper.getInstructorPhoneNumber(entry, null);
        mNote = EntryHelper.getInstructorNote(entry, null);
    }

    @Override
    public Entry toEntry() {
        Entry entry = new Entry(DBContract.InstructorEntry.TABLE_NAME);
        EntryHelper.setInstructorId(entry, mId);
        EntryHelper.setInstructorName(entry, mName);
        EntryHelper.setInstructorLab(entry, mLab);
        EntryHelper.setInstructorMail(entry, mMail);
        EntryHelper.setInstructorPhoneNumber(entry, mPhoneNumber);
        EntryHelper.setInstructorNote(entry, mNote);

        return entry;
    }

    /**
     * Class methods
     */

    public Instructor(@NonNull final Entry entry) {
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

    public String getLab() {
        return mLab;
    }

    public void setLab(String lab) {
        mLab = lab;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }
}
