package com.f_candy_d.pinoko.utils;

import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/10/17.
 */

public class EnumSerializer {

    public static <T extends Enum<T>> void writeToParcel(@NonNull final T enumValue, @NonNull final Parcel parcel) {
        parcel.writeInt(enumValue.ordinal());
    }

    public static <T extends Enum<T>> T readFromParcel(@NonNull Class<T> eClass, @NonNull final Parcel parcel) {
        final int ordinal = parcel.readInt();
        return eClass.getEnumConstants()[ordinal];
    }
}

