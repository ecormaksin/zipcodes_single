package com.example.zipcodes.domain.validation.notallblank;

final class ValidValueLengthGetter {

    public static int length(final String value) {
        final String replaced = value.replaceAll("[ 　\t]", "");
        return replaced.length();
    }
}
