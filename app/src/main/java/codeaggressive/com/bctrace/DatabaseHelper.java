package codeaggressive.com.bctrace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BCTrace.db";

    public static final String GENERATE_TABLE_NAME = "Generate";
    public static final String CONSUME_TABLE_NAME = "Consume";
    public static final String GENERATE_HISTORY_TABLE = "Generate_history";
    public static final String SCAN_HISTORY_TABLE = "Scan_history";
    public static final String CONSUME_HISTORY_TABLE = "Consume_history";

    public static final String GENERATE_COL_1 = "batch";
    public static final String GENERATE_COL_2 = "mixer";
    public static final String GENERATE_COL_3 = "sku";
    public static final String GENERATE_COL_4 = "keg";
    public static final String GENERATE_COL_5 = "date";
    public static final String GENERATE_COL_6 = "time";
    public static final String GENERATE_COL_7 = "operator";
    public static final String GENERATE_COL_8 = "bin";
    public static final String GENERATE_COL_9 = "keg_1";
    public static final String GENERATE_COL_10 = "keg_2";
    public static final String GENERATE_COL_11 = "keg_3";
    public static final String GENERATE_COL_12 = "keg_4";
    public static final String GENERATE_COL_13 = "keg_5";
    public static final String GENERATE_COL_14 = "keg_6";
    public static final String GENERATE_COL_15 = "keg_7";
    public static final String GENERATE_COL_16 = "keg_8";
    public static final String GENERATE_COL_17 = "keg_9";
    public static final String GENERATE_COL_18 = "keg_10";
    public static final String GENERATE_COL_19 = "keg_11";
    public static final String GENERATE_COL_20 = "keg_12";


    public static final String CONSUME_COL_1 = "batch";
    public static final String CONSUME_COL_2 = "mixer";
    public static final String CONSUME_COL_3 = "sku";
    public static final String CONSUME_COL_4 = "keg";
    public static final String CONSUME_COL_5 = "g_date";
    public static final String CONSUME_COL_6 = "g_time";
    public static final String CONSUME_COL_7 = "operator";
    public static final String CONSUME_COL_8 = "bin";
    public static final String CONSUME_COL_9 = "c_date";
    public static final String CONSUME_COL_10 = "c_time";
    public static final String CONSUME_COL_11 = "machine";
    public static final String CONSUME_COL_12 = "usage";
    public static final String CONSUME_COL_13 = "consumed";


    public static final String GENERATE_HIS_1 = "batch";
    public static final String GENERATE_HIS_2 = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table " + GENERATE_TABLE_NAME + " (batch TEXT primary key, mixer TEXT, sku TEXT, keg TEXT, date TEXT, time TEXT, operator TEXT, bin TEXT, keg_1 TEXT, keg_2 TEXT, keg_3 TEXT, keg_4 TEXT, keg_5 TEXT, keg_6 TEXT, keg_7 TEXT, keg_8 TEXT, keg_9 TEXT, keg_10 TEXT, keg_11 TEXT, keg_12 TEXT)");
        DB.execSQL("create table " + CONSUME_TABLE_NAME + "(batch TEXT, mixer TEXT, sku TEXT, keg TEXT, g_date TEXT, g_time TEXT, operator TEXT, bin TEXT, c_date TEXT, c_time TEXT, machine TEXT, usage TEXT, consumed TEXT)");
        DB.execSQL("create table " + GENERATE_HISTORY_TABLE + "(batch TEXT, image BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS " + GENERATE_TABLE_NAME);
        DB.execSQL("DROP TABLE IF EXISTS " + CONSUME_TABLE_NAME);
        DB.execSQL("DROP TABLE IF EXISTS " + GENERATE_HISTORY_TABLE);
        onCreate(DB);
    }

    public boolean insertGenerateData(String batchNo, String mixer, String sku, String kegNo, String edate, String etime, String operator, String binNo, String keg_1, String keg_2, String keg_3, String keg_4, String keg_5, String keg_6, String keg_7, String keg_8, String keg_9, String keg_10, String keg_11, String keg_12) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GENERATE_COL_1, batchNo);
        contentValues.put(GENERATE_COL_2, mixer);
        contentValues.put(GENERATE_COL_3, sku);
        contentValues.put(GENERATE_COL_4, kegNo);
        contentValues.put(GENERATE_COL_5, edate);
        contentValues.put(GENERATE_COL_6, etime);
        contentValues.put(GENERATE_COL_7, operator);
        contentValues.put(GENERATE_COL_8, binNo);
        contentValues.put(GENERATE_COL_9, keg_1);
        contentValues.put(GENERATE_COL_10, keg_2);
        contentValues.put(GENERATE_COL_11, keg_3);
        contentValues.put(GENERATE_COL_12, keg_4);
        contentValues.put(GENERATE_COL_13, keg_5);
        contentValues.put(GENERATE_COL_14, keg_6);
        contentValues.put(GENERATE_COL_15, keg_7);
        contentValues.put(GENERATE_COL_16, keg_8);
        contentValues.put(GENERATE_COL_17, keg_9);
        contentValues.put(GENERATE_COL_18, keg_10);
        contentValues.put(GENERATE_COL_19, keg_11);
        contentValues.put(GENERATE_COL_20, keg_12);
        long result = DB.insert(GENERATE_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean insertConsumeDate(String batchNo, String mixer, String sku, String kegNo, String date, String time, String operator, String binNo, String dateResult, String timeResult, String machine, String usageType, String consumed_kegs) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONSUME_COL_1, batchNo);
        contentValues.put(CONSUME_COL_2, mixer);
        contentValues.put(CONSUME_COL_3, sku);
        contentValues.put(CONSUME_COL_4, kegNo);
        contentValues.put(CONSUME_COL_5, date);
        contentValues.put(CONSUME_COL_6, time);
        contentValues.put(CONSUME_COL_7, operator);
        contentValues.put(CONSUME_COL_8, binNo);
        contentValues.put(CONSUME_COL_9, dateResult);
        contentValues.put(CONSUME_COL_10, timeResult);
        contentValues.put(CONSUME_COL_11, machine);
        contentValues.put(CONSUME_COL_12, usageType);
        contentValues.put(CONSUME_COL_13, consumed_kegs);
        long result = DB.insert(CONSUME_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean insertGenerateImage(String batchNo, byte[] image) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GENERATE_HIS_1, batchNo);
        contentValues.put(GENERATE_HIS_2, image);
        long result = DB.insert(GENERATE_HISTORY_TABLE, null, contentValues);
        return result != -1;
    }

    public Cursor retrieveGenerateImage() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from " + GENERATE_HISTORY_TABLE, null);
        return cursor;
    }

    public Cursor retrieveGenerateData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from " + GENERATE_TABLE_NAME, null);
        return cursor;
    }

    public Cursor retrieveConsumeData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from " + CONSUME_TABLE_NAME, null);
        return cursor;
    }

    public Cursor getBatchData(String batch) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String qry = "select * from " + GENERATE_TABLE_NAME + " where batch=" + batch;
        Cursor cursor = DB.rawQuery(qry, null);
        return cursor;
    }
}
