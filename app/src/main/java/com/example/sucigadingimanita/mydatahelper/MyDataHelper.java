package com.example.sucigadingimanita.mydatahelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {
    private static final String NAMA_DB = "guitar.db";
    private static final String NAMA_TABEL = "guitar";
    private static final int VERSI_DB = 2;
    private static final String ID = "id";
    private static final String NAMA="nama";
    private static final String JENIS= "jenis";
    private static final String REVIEW = "review";   // menambahkan field sinopsis
    private static final String HARGA = "harga"; // menambahkan field sutradara


    private static final String CREATE_TABLE = "CREATE TABLE " + NAMA_TABEL + "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA + " VARCHAR(255)," +
                    " "+ JENIS +" VARCHAR(255) );";

    //membuat tabel baru dengan field tambahan
    private static final String CREATE_TABLE_REVISI =
            "CREATE TABLE " + NAMA_TABEL + "(" +ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA + " VARCHAR(255), "+
                    JENIS +" VARCHAR(255), " + REVIEW + " VARCHAR(255)," + HARGA + " VARCHAR(255));";

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


