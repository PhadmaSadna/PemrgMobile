package com.example.someone.guitarreview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertMovie extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1,btn2;
    EditText judul,tahun,sinopsis,sutradara,produser; // pengesetan komponen untuk tabel field yang baru

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_movie);

        //mendapatkan id editText 2-6 dan di isi ke variable local judul, tahun, sinopsis, sutradara, produser
        dbHelper = new MyDataHelper(this);
        judul = (EditText) findViewById(R.id.editText2);
        tahun = (EditText) findViewById(R.id.ediText3);
        sinopsis = (EditText) findViewById(R.id.ediText4);
        sutradara = (EditText) findViewById(R.id.ediText5);
        produser = (EditText) findViewById(R.id.ediText6);

        //
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        //
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase(); //memanggil database agar dapat mengisi data
                //query untuk mengeksekusi berdasarkan value
                db.execSQL("INSERT INTO sinetrons(judul,tahunrilis,sinopsis,sutradara,produser) values('"
                        + judul.getText().toString() + "','" + tahun.getText().toString() +
                        "','" + sinopsis.getText().toString() + "','" + sutradara.getText().toString()
                        + "','" + produser.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), " Berhasil ditambahkan", Toast.LENGTH_LONG).show();

                //menampilkan daftar item movie yang sudah ditambahkan
                MainActivity.layarutama.TampilkanList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}

