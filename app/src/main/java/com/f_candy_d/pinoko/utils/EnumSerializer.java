package com.f_candy_d.pinoko.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by daichi on 8/10/17.
 */

public class EnumSerializer {

    public static <T extends Enum<T>> void writeTo(@NonNull final T enumValue, @NonNull final Parcel parcel) {
//        parcel.writeSerializable(enumValue.getClass());
        parcel.writeInt(enumValue.ordinal());
        Log.d("mylogQ", "writeTo():: " + enumValue.toString() + "#oridinal=" + String.valueOf(enumValue.ordinal()));
    }

    public static <T extends Enum<T>> T readFrom(@NonNull Class<T> enumClass, @NonNull final Parcel parcel) {
        final int ordinal = parcel.readInt();
        Log.d("mylogQ", "readFrom()::" + enumClass.getName() + "#ordinal=" + String.valueOf(ordinal));
//        return enumClass.getEnumConstants()[ordinal];
        return enumClass.getEnumConstants()[0];
    }
}

