package com.example.yanaachrianti.nebengkuy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {
    private EditText xEmail, xPassword;
    private Button xDaftar, xLogin;

    private FirebaseAuth xAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        xAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    Intent intent = new Intent(login.this, depan.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        xEmail = (EditText) findViewById(R.id.email);
        xPassword = (EditText) findViewById(R.id.password);

        xLogin = (Button) findViewById(R.id.login);
        xDaftar = (Button) findViewById(R.id.daftar);

        xDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = xEmail.getText().toString();
                final String password = xPassword.getText().toString();

                xAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(login.this, "Daftar belum berhasil", Toast.LENGTH_SHORT).show();
                        }
                        //menyimpan data yg belum ada ke database
                        else {
                            String user_id = xAuth.getCurrentUser().getUid();
                            DatabaseReference nebengkuy_db = FirebaseDatabase.getInstance().getReference().child("user").child("nebenger").child("id_user");
                            nebengkuy_db.setValue(true);
                        }
                    }
                });
            }
        });

        xLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = xEmail.getText().toString();
                final String password = xPassword.getText().toString();

                xAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(login.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        xAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        xAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
