package com.afsmith.tyneweartrafficviewer.util;

import java.util.List;

public class TypeConversionLibrary {
    /**
     * Safely cast all the elements of a list to a given subtype. Any elements
     * that cannot be cast to the specified subtype are removed.
     * @param input The list of supertypes
     * @param clazz The subtype class to convert to
     * @return A list of subtypes
     */
    public static <T> List<T> downcastList(List<? super T> input, Class<T> clazz) {
        return input.stream()
                    .filter(clazz::isInstance)
                    .map(clazz::cast)
                    .toList();
    }
}
