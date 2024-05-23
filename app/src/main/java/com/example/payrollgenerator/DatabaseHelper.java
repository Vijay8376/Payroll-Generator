package com.example.payrollgenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "employee_database";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Table Name
    private static final String TABLE_NAME = "employees";

    // Table Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BS = "basic_salary";
    public static final String COLUMN_PERQUISITES = "perquisites";
    public static final String COLUMN_HRA = "hra";
    public static final String COLUMN_OTHERS = "others";

    // Create Table Query
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_BS + " REAL,"
                    + COLUMN_PERQUISITES + " REAL,"
                    + COLUMN_HRA + " REAL,"
                    + COLUMN_OTHERS + " REAL"
                    + ")";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
