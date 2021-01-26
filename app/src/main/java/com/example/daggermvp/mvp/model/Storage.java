package com.example.daggermvp.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    @Inject
    public Storage(Context context) {
        super(context, "countries_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch(SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addCountry(Country country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, country.getName());
        values.put(CAPITAL, country.getCapital());
        values.put(FLAG, country.getFlag());
        values.put(POPULATION, country.getPopulation());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch(SQLException e) {
            Log.d(TAG, e.getMessage());
        }

        db.close();
    }

    public List<Country> getSavedCountries() {
        List<Country> countryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Country country = new Country();
                            country.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                            country.setCapital(cursor.getString(cursor.getColumnIndex(CAPITAL)));
                            country.setFlag(cursor.getString(cursor.getColumnIndex(FLAG)));
                            country.setPopulation(cursor.getInt(cursor.getColumnIndex(POPULATION)));

                            countryList.add(country);

                        } while (cursor.moveToNext());
                    }
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return countryList;
    }


    private static final String NAME = "name";
    private static final String CAPITAL = "capital";
    private static final String FLAG = "flag";
    private static final String POPULATION = "population";
    private static final String TABLE_NAME = "countries";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            NAME + " text not null," +
            CAPITAL + " text not null," +
            FLAG + " text not null," +
            POPULATION + " integer not null)";

}
