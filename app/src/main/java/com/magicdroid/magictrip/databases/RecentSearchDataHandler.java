package com.magicdroid.magictrip.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.magicdroid.magictrip.models.RecentDataEntity;
import com.magicdroid.magictrip.utililty.AppConstants;
import java.util.ArrayList;

/**
 * Created by Sumanta.Longjam on 22-08-2016.
 */
public class RecentSearchDataHandler implements AppConstants {

    public static void saveData(Context context, RecentDataEntity entity) {
        SQLiteDatabase db = MagicSqliteHelper.getWritableDatabase(context);
        try {
            ContentValues values = new ContentValues();
            values.put(CITY_ID, entity.getCityId());
            values.put(ORIGIN_CITY_ID, entity.getOriginCityId());
            values.put(CITY_NAME, entity.getCityName());
            values.put(STATE, entity.getStateName());
            values.put(COUNTRY, entity.getCountryName());
            values.put(LATITUDE, entity.getLatitude());
            values.put(LONGITUDE, entity.getLongitude());
            values.put(TIMESTAMP, entity.getTimestamp());
            db.insertWithOnConflict(RECENT_SEARCH_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        deleteOldData(context);
    }

    public static ArrayList<RecentDataEntity> getAllData(Context context) {
        ArrayList<RecentDataEntity> entityList = new ArrayList<RecentDataEntity>();
        String selectQuery = "SELECT "+CITY_ID+", "+ORIGIN_CITY_ID+", "+CITY_NAME+", "
                +STATE+", "+COUNTRY+", "+LATITUDE+", "+LONGITUDE+", "+TIMESTAMP+" FROM "+RECENT_SEARCH_TABLE+" ORDER BY "+TIMESTAMP+" desc";
        SQLiteDatabase db = MagicSqliteHelper.getWritableDatabase(context);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                RecentDataEntity entity = new RecentDataEntity();
                entity.setCityId(cursor.getString(0));
                entity.setOriginCityId(cursor.getLong(1));
                entity.setCityName(cursor.getString(2));
                entity.setStateName(cursor.getString(3));
                entity.setCountryName(cursor.getString(4));
                entity.setLatitude(cursor.getLong(5));
                entity.setLongitude(cursor.getLong(6));
                entity.setTimestamp(cursor.getLong(7));
                entityList.add(entity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entityList;
    }

    public static void updateRecentSearch(Context context, long timestamp) {
        SQLiteDatabase db = MagicSqliteHelper.getWritableDatabase(context);
        try {
            ContentValues values = new ContentValues();
            values.put(TIMESTAMP, System.currentTimeMillis());
            db.update(RECENT_SEARCH_TABLE, values, TIMESTAMP + " = ?", new String[] { String.valueOf(timestamp) });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteByTimestamp(Context context, long timestamp) {
        SQLiteDatabase db = MagicSqliteHelper.getWritableDatabase(context);
        try {
            db.execSQL("DELETE FROM "+RECENT_SEARCH_TABLE+" WHERE " +TIMESTAMP+ "="+timestamp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteOldData(Context context) {
        SQLiteDatabase db = MagicSqliteHelper.getWritableDatabase(context);
        try {
            db.execSQL("DELETE FROM "+RECENT_SEARCH_TABLE+" WHERE "
            +TIMESTAMP+ " not in (SELECT "+TIMESTAMP+" FROM "+RECENT_SEARCH_TABLE
            +" ORDER BY "+TIMESTAMP+ " desc LIMIT 20)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
