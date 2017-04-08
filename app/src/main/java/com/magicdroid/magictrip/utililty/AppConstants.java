package com.magicdroid.magictrip.utililty;

/**
 * Created by magic on 29/3/17.
 */

public interface AppConstants {
    int CONNECTION_TIME_OUT = 120;

    /*
    *  recent search database key
    */
    String RECENT_SEARCH_TABLE = "r_s_tlb";
    String CITY_ID = "r_s_city_id";
    String ORIGIN_CITY_ID = "r_s_o_city_id";
    String CITY_NAME = "r_s_city_name";
    String STATE = "r_s_state";
    String COUNTRY = "r_s_country";
    String LATITUDE = "r_s_lat";
    String LONGITUDE = "r_s_lng";
    String TIMESTAMP = "r_stime";

    /*
    *
    */
    int CITY = 1;
    int ZOMATO = 2;
    int ZOMATO_GOOGLE = 3;
}
