package com.example.yanaachrianti.nebengkuy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class daftar extends AppCompatActivity {
    private EditText xNama;
    private EditText xEmail;
    private EditText xNoTelp;
    private EditText xPasssword;
    private Button xDaftar;

    private FirebaseAuth xAuth;
    FirebaseDatabase Nebengkuy_db;
    private DatabaseReference user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar);

        xAuth = FirebaseAuth.getInstance();
        Nebengkuy_db = FirebaseDatabase.getInstance();
        user = Nebengkuy_db.getReference("user");

        xDaftar = (Button) findViewById(R.id.daftar);

        xDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });
    }


}
