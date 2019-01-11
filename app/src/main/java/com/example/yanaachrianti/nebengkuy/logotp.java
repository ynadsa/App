package com.example.yanaachrianti.nebengkuy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class logotp extends AppCompatActivity {

    private EditText xTelp, xKode;
    private Button xKirim;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    String xIdverifikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logotp);
        FirebaseApp.initializeApp(this);

        userIsLoggedIn();

        xTelp = findViewById(R.id.no_tlp);
        xKode = findViewById(R.id.kode);

        xKirim = findViewById(R.id.lanjut);

        xKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xIdverifikasi != null)
                    verifyNomordenganKode();
                else
                    startVerifikasiNomer();
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);

                xIdverifikasi = verificationId;

                xKirim.setText("Kode Verifikasi");
            }
        };
    }

    private void verifyNomordenganKode(){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(xIdverifikasi, xKode.getText().toString());
        signInWithPhoneCredential(credential);
    }

    private void signInWithPhoneCredential(PhoneAuthCredential phoneAuthCredential){
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                    userIsLoggedIn();
            }
        });
    }

    private void userIsLoggedIn(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            startActivity(new Intent(getApplicationContext(), depan.class));
            finish();
            return;
        }
    }

    private void startVerifikasiNomer() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                xTelp.getText().toString(),
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }
}
