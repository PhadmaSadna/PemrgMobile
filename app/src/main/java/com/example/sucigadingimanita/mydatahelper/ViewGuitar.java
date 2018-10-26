package com.example.sucigadingimanita.mydatahelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewGuitar extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn2;
    TextView id,nama,jenis,review,harga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guitar);

        //mendapatkan id editText 2-6 dan di isi ke variable local judul, tahun, sinopsis, sutradara, produser
        dbHelper = new MyDataHelper(this);

        id = (TextView) findViewById(R.id.textview1);
        nama = (TextView) findViewById(R.id.textview2);
        jenis= (TextView) findViewById(R.id.textview3);
        review= (TextView) findViewById(R.id.textview4);
        harga = (TextView) findViewById(R.id.textview5);

        SQLiteDatabase db = dbHelper.getReadableDatabase(); //memanggil database untuk membaca data
        //query untuk mengambil data ke data sinetron dan disimpan di cursor
        cursor = db.rawQuery("Select * from guitar WHERE nama = '"+getIntent().getStringExtra("nama")+
                "'",null);
        cursor.moveToFirst(); //pindah ke record pertama

        //menampilkan nilai balikan dari query diatas pada edit text
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0));
            nama.setText(cursor.getString(1));
            jenis.setText(cursor.getString(2));
            review.setText(cursor.getString(3));
            harga.setText(cursor.getString(4));

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


