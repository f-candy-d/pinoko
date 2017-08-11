package com.f_candy_d.pinoko.utils;

import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/10/17.
 */

public class ParcelHelper {

    public static <T extends Enum<T>> void writeEnum(@NonNull final T enumValue, @NonNull final Parcel parcel) {
        parcel.writeInt(enumValue.ordinal());
    }

    public static <T extends Enum<T>> T readEnum(@NonNull Class<T> eClass, @NonNull final Parcel parcel) {
        final int ordinal = parcel.readInt();
        return eClass.getEnumConstants()[ordinal];
    }

    public static void writeBool(final boolean value, @NonNull final Parcel parcel) {
        parcel.writeInt((value) ? 1 : 0);
    }

    public static boolean readBool(@NonNull final Parcel parcel) {
        final int boolValue = parcel.readInt();
        return (boolValue != 0);
    }
}

