package com.farzinzeynal.camerabakuadmin.ui.login;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.farzinzeynal.camerabakuadmin.R;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {


    TextInputEditText editText_userName, editText_password;
    Button button_login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_userName = findViewById(R.id.et_username);
        editText_password = findViewById(R.id.et_password);
        button_login = findViewById(R.id.login);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_userName.getText().toString().equals("a") &&
                        editText_password.getText().toString().equals("a"))
                {
                    startActivity(new Intent(LoginActivity.this,MainScreen.class));
                }
                else if(editText_userName.getText().toString().length()==0 && editText_password.getText().toString().length()==0)
                {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Diqqət")
                            .setMessage("İstifadəçi adı və ya şifrə daxil edin")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                }
                else
                    {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Diqqət")
                                .setMessage("İstifadəçi adı və ya şifrə yanlışdır")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       dialog.cancel();
                                    }
                                }).show();
                    }
            }
        });

      

    }



}