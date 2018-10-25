package com.example.someone.guitarreview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateMovie extends AppCompatActivity {
    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1,btn2;
    EditText judul,tahun,sinopsis,sutradara,produser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        dbHelper = new MyDataHelper(this);

        //Mengeset komponen pada tampilan ketika update
        judul = (EditText) findViewById(R.id.editText2);
        tahun = (EditText) findViewById(R.id.ediText3);
        sinopsis = (EditText) findViewById(R.id.ediText4);
        sutradara = (EditText) findViewById(R.id.ediText5);
        produser = (EditText) findViewById(R.id.ediText6);


        //menampilkan data
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * from sinetrons WHERE judul = '"+getIntent().getStringExtra("judul")+"'",
                null);
        cursor.moveToFirst();

        //mengeset data yang telah dipilih ke setiap komponen sebelumnya
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(1));
            tahun.setText(cursor.getString(2));
            sinopsis.setText(cursor.getString(3));
            sutradara.setText(cursor.getString(4));
            produser.setText(cursor.getString(5));
        }


        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase(); //memanggil database untuk membaca data
                //query update
                db.execSQL("UPDATE sinetrons set judul = '" + judul.getText().toString() + "', tahunrilis = '" + tahun.getText().toString() +
                        "' , sinopsis = '" + sinopsis.getText().toString() + "'  , sutradara = '" + sutradara.getText().toString() +
                        "'  , produser = '" + produser.getText().toString() + "' where judul = '" +getIntent().getStringExtra("judul")+ "'");
                Toast.makeText(getApplicationContext(), " Berhasil Edit", Toast.LENGTH_LONG).show();

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

