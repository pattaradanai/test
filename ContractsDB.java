package com.example.billz.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Billz on 9/19/2015 AD.
 */
public class ContractsDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cintracts.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contracts";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE_NUMBER = "phone_number";


    public ContractsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable = "CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT," +
                "%s TEXT)";

        sqlCreateTable = String.format(sqlCreateTable, TABLE_NAME, COL_ID, COL_NAME, COL_PHONE_NUMBER);

        db.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

