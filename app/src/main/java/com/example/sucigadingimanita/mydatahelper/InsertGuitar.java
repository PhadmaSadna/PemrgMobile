package com.example.sucigadingimanita.mydatahelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertGuitar extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1,btn2;
    EditText nama,jenis,review,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_guitar);

        dbHelper = new MyDataHelper(this);
        nama = (EditText) findViewById(R.id.editText2);
        jenis = (EditText) findViewById(R.id.ediText3);
        review = (EditText) findViewById(R.id.ediText4);
        harga = (EditText) findViewById(R.id.ediText5);

        //
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        //
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.execSQL("INSERT INTO guitar(nama,jenis,review,harga) values('"
                        + nama.getText().toString() + "','" + jenis.getText().toString() +
                        "','" + review.getText().toString() + "','" + harga.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), " Berhasil ditambahkan", Toast.LENGTH_LONG).show();


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

