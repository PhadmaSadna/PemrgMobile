package com.example.someone.guitarreview;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView Listview01;

    protected Cursor cursor;
    MyDataHelper datasinetron;

    public static MainActivity layarutama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layarutama = this;
        TampilkanList();


        Button btn2 = (Button) findViewById(R.id.tombol);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, InsertMovie.class);

                startActivity(intent1);
            }
        });
    }

    public void onClick(){

    }

    public void TampilkanList(){
        datasinetron = new MyDataHelper(this);
        SQLiteDatabase db = datasinetron.getWritableDatabase(); //memanggil database untuk membaca data
        //query untuk mengambil data ke data sinetron dan disimpan di cursor
        cursor = db.rawQuery("Select * from sinetrons",null);
        final String[] databaru = new String[cursor.getCount()];

        cursor.moveToFirst(); //pindah ke record pertama

        //cursor memilih data index 1
        for(int cc=0; cc<cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            databaru[cc]= cursor.getString(1);

        }

        //menampilkan list view
        Listview01 = (ListView) findViewById(R.id.listview1);
        Listview01.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,databaru));
        Listview01.setSelected(true);
        Listview01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = databaru[position];
                final CharSequence[] dialogitem = {"Lihat Sinetron","Update Sinetron","Hapus Sinetron"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


                //mengeset judul untuk alert dialog
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:

                                Intent intent0 = new Intent(getApplicationContext(), ViewMovie.class);
                                intent0.putExtra("judul",selection);
                                startActivity(intent0);
                                break;

                            case 1:

                                Intent intent1 = new Intent(getApplicationContext(), UpdateMovie.class);
                                intent1.putExtra("judul",selection);
                                startActivity(intent1);

                                break;

                            case 2:
                                SQLiteDatabase db = datasinetron.getWritableDatabase();
                                db.execSQL("DELETE FROM sinetrons WHERE judul = '"+ selection + "'");
                                TampilkanList();
                                break;
                        }
                    }
                }) ;
                builder.create().show();
            }
        });


        ((ArrayAdapter)Listview01.getAdapter()).notifyDataSetInvalidated();
        cursor.close();

    }
}
