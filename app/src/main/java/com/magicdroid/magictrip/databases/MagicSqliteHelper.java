package com.magicdroid.magictrip.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.magicdroid.magictrip.utililty.AppConstants;

/**
 * Created by magic on 08/04/17.
 */
public class MagicSqliteHelper extends SQLiteOpenHelper implements AppConstants{

    private static final String DATABASE_NAME = "magic.db";
    private static final int DATABASE_VERSION = 1;

    private final String CREATE_TABLE_RECENT_SEARCH = "CREATE TABLE "
            + RECENT_SEARCH_TABLE + "(" + CITY_ID + " TEXT," + ORIGIN_CITY_ID + " INTEGER,"
            + CITY_NAME + " TEXT," + STATE + " TEXT," + COUNTRY + " TEXT,"+ LATITUDE + " INTEGER,"
            + LONGITUDE + " INTEGER," + TIMESTAMP + " INTEGER);";

    private static MagicSqliteHelper magicSqliteHelper;

    private MagicSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static MagicSqliteHelper getGiftSqliteHelper(Context context) {
        if (magicSqliteHelper == null) {
            magicSqliteHelper = new MagicSqliteHelper(context);
        }
        return magicSqliteHelper;
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        return getGiftSqliteHelper(context).getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase(Context context) {
        return getGiftSqliteHelper(context).getReadableDatabase();
    }

    public static void closeDatabase(Context context) {
        getGiftSqliteHelper(context).close();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_RECENT_SEARCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }
}
