package com.example.android.amlway2.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Abdulrhman on 23/03/2017.
 */

public final class ContractBill {
    public ContractBill() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.android.amlWay2";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PathBill = "bills";

    public static final class billEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PathBill).build();
        public static final String TABLE_NAME = "bills";
        public static final String COLUMN_ID = _ID;
        public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
        public static final String COLUMN_EMPLOYEE_NAME = "EMPLOYEE_NAME";
        public static final String COLUMN_FROM = "SOURCE";
        public static final String COLUMN_TO = "DESTINATION";
        public static final String COLUMN_PRICE = "PRICE";
        public static final String COLUMN_WEIGHT = "WEIGHT";
        public static final String COLUMN_COUNT = "COUNT";
        public static final String COLUMN_ELEMENT = "ELEMENT";
        public static final String COLUMN_Bill_TYPE = "TYPE";

    }
}
