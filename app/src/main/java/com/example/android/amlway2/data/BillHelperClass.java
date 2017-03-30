package com.example.android.amlway2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.amlway2.data.ContractBill.billEntry;

/**
 * Created by Abdulrhman on 23/03/2017.
 */

public class BillHelperClass extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "amlway.dp";
    static final int DATABASE_VERSION = 1;
    final String not_null = "NOT NULL";

    public BillHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        ( _idINTEGER primary key not null AUTOINCREMENT, CUSTOMER_NAME text not null EMPLOYEE_NAME text not null, FROM text not null, TO text not null,ELEMENT text not null, WEIGHT REAL NOT NULL,COUNT INTEGER not null,PRICE real not null,TYPE text not null );
//        at android.database.sqlite.SQLiteConnection.nativePrepareStatement(Native Method)
        String SQL_CREATE = "create table " + billEntry.TABLE_NAME + " ( "
                + billEntry._ID + " INTEGER primary key  AUTOINCREMENT, "
                + billEntry.COLUMN_CUSTOMER_NAME + " TEXT not null, "
                + billEntry.COLUMN_EMPLOYEE_NAME + " TEXT not null, "
                + billEntry.COLUMN_FROM + " TEXT not null, "
                + billEntry.COLUMN_TO + " TEXT not null,"
                + billEntry.COLUMN_ELEMENT + " TEXT not null, "
                + billEntry.COLUMN_WEIGHT + " REAL NOT NULL,"
                + billEntry.COLUMN_COUNT + " INTEGER not null,"
                + billEntry.COLUMN_PRICE + " real not null,"
                + billEntry.COLUMN_Bill_TYPE + " text not null ); ";

        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + billEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
