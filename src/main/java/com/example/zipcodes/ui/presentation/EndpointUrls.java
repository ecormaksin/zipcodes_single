package com.example.zipcodes.ui.presentation;

public final class EndpointUrls {

    public static final String PREFECTURES_GET_LIST = "/prefectures";

    public static final String PREFECTURE_GET = PREFECTURES_GET_LIST + "/{prefectureCode}";

    public static final String CITIES_GET_LIST = "/cities";

    public static final String CITY_GET = CITIES_GET_LIST + "/{japaneseLocalGovernmentCode}";
}
