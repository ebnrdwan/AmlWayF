package com.example.android.amlway2.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Abdulrhman on 23/03/2017.
 */


public class ProviderClass extends ContentProvider {

    BillHelperClass billHelperClass;
    static final int bills = 100;
    static final int bills_row = 101;
    static UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        billHelperClass = new BillHelperClass(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase database = billHelperClass.getReadableDatabase();
        int match = uriMatcher.match(uri);
        Cursor returnCursor;
        switch (match) {
            case bills:

                returnCursor = database.query(ContractBill.billEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                if (returnCursor.equals(null))
                    throw new UnsupportedOperationException("invalid query operation ");

                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

                break;

            case bills_row:

                s = ContractBill.billEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};

                returnCursor = database.query(ContractBill.billEntry.TABLE_NAME, strings, s, strings1, null, null, s1);
                if (returnCursor.equals(null))
                    throw new UnsupportedOperationException("invalid query operation ");

                returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
            default:
                throw new UnsupportedOperationException("invalid uri");

        }
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase database = billHelperClass.getWritableDatabase();
        Uri insertedUri;
        int match = uriMatcher.match(uri);

        switch (match) {
            case bills:
                Long insertedrows = database.insert(ContractBill.billEntry.TABLE_NAME, null, contentValues);
                if (insertedrows > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    insertedUri = ContentUris.withAppendedId(ContractBill.billEntry.CONTENT_URI, insertedrows);
                    Toast.makeText(getContext(),"you have inserted "+ insertedrows, Toast.LENGTH_SHORT).show();
                    break;
                } else {

                    Toast.makeText(getContext(), "can't insert your data", Toast.LENGTH_SHORT).show();
                }

            default:
                throw new UnsupportedOperationException();


        }
        return insertedUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        SQLiteDatabase database = billHelperClass.getWritableDatabase();
        int deletedrows;
        int match = uriMatcher.match(uri);
        switch (match) {
            case bills:
                deletedrows = database.delete(ContractBill.billEntry.TABLE_NAME, s, strings);
                if (deletedrows > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                } else {
                    Toast.makeText(getContext(), "i can't delete this", Toast.LENGTH_SHORT).show();
                }

                break;

            case bills_row:
                s = ContractBill.billEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                deletedrows = database.delete(ContractBill.billEntry.TABLE_NAME, s, strings);

                break;
            default:
                throw new UnsupportedOperationException("invalid uri");
        }

        return deletedrows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase database = billHelperClass.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case bills:
                rowsUpdated = database.update(ContractBill.billEntry.TABLE_NAME, contentValues, s, strings);
                if (rowsUpdated > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);

                } else {
                    Toast.makeText(getContext(), "i can't update this", Toast.LENGTH_SHORT).show();
                }
                break;
            case bills_row:
                s = ContractBill.billEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};

                rowsUpdated = database.update(ContractBill.billEntry.TABLE_NAME, contentValues, s, strings);
                if (rowsUpdated > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                } else {
                    Toast.makeText(getContext(), "i can't update this", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                throw new UnsupportedOperationException("invalid uri");
        }
        return rowsUpdated;
    }

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ContractBill.CONTENT_AUTHORITY, ContractBill.PathBill, bills);
        uriMatcher.addURI(ContractBill.CONTENT_AUTHORITY, ContractBill.PathBill + "/#", bills_row);
        return uriMatcher;
    }

}
