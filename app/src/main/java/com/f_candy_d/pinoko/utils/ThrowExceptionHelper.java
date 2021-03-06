package com.f_candy_d.pinoko.utils;

import android.support.annotation.NonNull;

/**
 * Created by daichi on 8/11/17.
 */

public class ThrowExceptionHelper {

    private ThrowExceptionHelper() {}

    public static void
    throwClassCastException(@NonNull final Class<?> required, @NonNull final Class<?> passed) {
        throw new ClassCastException(
                "Required type is " + required.getName() + " , but "
                        + passed.getClass().getName() + " was passed");
    }

    public static void throwAtemptToAccessToEmptyArrayException() {
        throw new IllegalStateException(
                "Attempt to access to a null or empty array");
    }

    public static void throwMissingRequiredParameterException(final String missingParam) {
        throw new IllegalArgumentException(
                "Some required parameter is missing -> " + missingParam);
    }
}
