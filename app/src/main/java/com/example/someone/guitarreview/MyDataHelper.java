package com.example.someone.guitarreview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    private static final String NAMA_DB = "sinetrons.db";
    private static final String NAMA_TABEL = "sinetrons";
    private static final int VERSI_DB = 2;
    private static final String ID = "_id";
    private static final String JUDUL="judul";
    private static final String TAHUNRILIS = "tahunrilis";
    private static final String SINOPSIS = "sinopsis";   // menambahkan field sinopsis
    private static final String SUTRADARA = "sutradara"; // menambahkan field sutradara
    private static final String PRODUSER = "produser";  // menambahkan field produser


    private static final String CREATE_TABLE =
            "CREATE TABLE " + NAMA_TABEL + "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255)," +
                    " "+ TAHUNRILIS +" VARCHAR(255) );";

    //membuat tabel baru dengan field tambahan
    private static final String CREATE_TABLE_REVISI =
            "CREATE TABLE " + NAMA_TABEL + "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JUDUL + " VARCHAR(255), "+
                    TAHUNRILIS +" VARCHAR(255), " + SINOPSIS + " VARCHAR(255)," + SUTRADARA + " VARCHAR(255)," + PRODUSER + " VARCHAR(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ NAMA_TABEL;

    //konstruktor
    public MyDataHelper(Context cont){
        super(cont,NAMA_DB,null,VERSI_DB);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_REVISI);
    }

    //mengubah skema database lama untuk direvisi yang baru
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE_REVISI);
        }


    }
}


