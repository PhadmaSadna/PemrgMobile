package com.example.someone.guitarreview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    CheckBox chkRemember;
    EditText username;
    EditText password;
    Button btnLogin;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initComponents();
        this.checkSavedCredentials();

    }

    private void initComponents() {
        this.chkRemember = findViewById(R.id.chk_remember);
        this.btnLogin = findViewById(R.id.btnLogin);
        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
    }



    private void login() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();



        boolean loginCorrect = this.checkCredentials(username,password);

        if (loginCorrect){
            boolean remember =this.chkRemember.isChecked();

            if (remember) {
                this.SavedCredentials();
            }
            this.openHome(username);
        }
        else {
            Toast.makeText(this.getApplicationContext(),"salah username atau password", Toast.LENGTH_SHORT).show();
        }
    }

    private void openHome(String username) {
        Intent intent = new Intent(this.getApplicationContext(),MainActivity.class);
        intent.putExtra("username", username);
        this.startActivity(intent);
    }

    private void SavedCredentials() {
        SharedPreferences handler = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();
        editor.putString("username", this.username.getText().toString());
        editor.putString("password", this.password.getText().toString());
        editor.apply();
    }

    private boolean checkCredentials(String username, String password) {
        if (username.equals("admin") && password.equals("admin")){
            return true;
        }
        else {
            return false;
        }
    }

    private void checkSavedCredentials() {
        SharedPreferences handler = this.getSharedPreferences("data",Context.MODE_PRIVATE);
        String username = handler.getString("username", "");
        String password = handler.getString("password", "");

        boolean loginCorrect = this.checkCredentials(username,password);

        if (loginCorrect){
            this.openHome(username);
        }

    }

    public void  button_onClick(View view){ this.login();}
}
