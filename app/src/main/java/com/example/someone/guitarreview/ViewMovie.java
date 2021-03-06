package com.example.someone.guitarreview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMovie extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn2;
    TextView id,judul,tahun,sinopsis,sutradara,produser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        //mendapatkan id editText 2-6 dan di isi ke variable local judul, tahun, sinopsis, sutradara, produser
        dbHelper = new MyDataHelper(this);
        id = (TextView) findViewById(R.id.textview1);
        judul = (TextView) findViewById(R.id.textview2);
        tahun = (TextView) findViewById(R.id.textview3);
        sinopsis = (TextView) findViewById(R.id.textview4);
        sutradara = (TextView) findViewById(R.id.textview5);
        produser = (TextView) findViewById(R.id.textview6);

        SQLiteDatabase db = dbHelper.getReadableDatabase(); //memanggil database untuk membaca data
        //query untuk mengambil data ke data sinetron dan disimpan di cursor
        cursor = db.rawQuery("Select * from sinetrons WHERE judul = '"+getIntent().getStringExtra("judul")+
                "'",null);
        cursor.moveToFirst(); //pindah ke record pertama

        //menampilkan nilai balikan dari query diatas pada edit text
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0));
            judul.setText(cursor.getString(1));
            tahun.setText(cursor.getString(2));
            sinopsis.setText(cursor.getString(3));
            sutradara.setText(cursor.getString(4));
            produser.setText(cursor.getString(5));

        }

        btn2 = (Button) findViewById(R.id.button1);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


