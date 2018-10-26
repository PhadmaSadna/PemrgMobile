package com.example.sucigadingimanita.mydatahelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateGuitar extends AppCompatActivity {
    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1,btn2;
    EditText nama,jenis,review,harga;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_guitar);

        dbHelper = new MyDataHelper(this);

        //Mengeset komponen pada tampilan ketika update
        nama = (EditText) findViewById(R.id.editText2);
        jenis = (EditText) findViewById(R.id.ediText3);
        review = (EditText) findViewById(R.id.ediText4);
        harga = (EditText) findViewById(R.id.ediText5);


        //menampilkan data
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("Select * from guitar WHERE nama = '"+getIntent().getStringExtra("nama")+"'",
                null);
        cursor.moveToFirst();

        //mengeset data yang telah dipilih ke setiap komponen sebelumnya
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1));
            jenis.setText(cursor.getString(2));
            review.setText(cursor.getString(3));
            harga.setText(cursor.getString(4));
        }


        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase(); //memanggil database untuk membaca data
                //query update
                db.execSQL("UPDATE guitar set nama = '" + nama.getText().toString() + "', jenis = '" + jenis.getText().toString() +
                        "' , review = '" + review.getText().toString() + "'  , harga = '" + harga.getText().toString() +
                        "'  where nama = '" +getIntent().getStringExtra("nama")+ "'");
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

