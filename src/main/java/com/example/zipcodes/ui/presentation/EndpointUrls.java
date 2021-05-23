package com.example.zipcodes.ui.presentation;

public final class EndpointUrls {

    private static final String PATH_PREFECTURES = "prefectures";
    private static final String PATH_CITIES = "cities";

    public static final String PREFECTURES = "/" + PATH_PREFECTURES;

    // @formatter:off
    public static final String PREFECTURE = 
            PREFECTURES
            + "/{" + KeyNames.PREFECTURE_CODE + "}";
    // @formatter:on

    public static final String CITIES = "/" + PATH_CITIES;

    public static final String CITIES_WITH_PREFECTURES = PREFECTURE + CITIES;

    // @formatter:off
    public static final String CITY = 
            CITIES
            + "/{" + KeyNames.JAPANESE_LOCAL_GOVERNMENT_CODE + "}";
    // @formatter:on
}
