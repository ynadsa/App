package com.example.yanaachrianti.nebengkuy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yanaachrianti.nebengkuy.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    private Button xDaftar,xMasuk;
    LinearLayout mainLayout;

    private FirebaseAuth xAuth;
    private DatabaseReference Nebengkuy_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xAuth = FirebaseAuth.getInstance();
        Nebengkuy_db = FirebaseDatabase.getInstance().getReference("nebenger");

        xDaftar = (Button) findViewById(R.id.klik_daftar);
        xMasuk = (Button) findViewById(R.id.klik_masuk);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        xDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDaftar();
            }
        });

        xMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogMasuk();
            }
        });
    }

    private void showDialogMasuk (){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("MASUK");
        dialog.setMessage("Lengkapi data berikut");

        LayoutInflater inflater = LayoutInflater.from(this);
        View lay_log = inflater.inflate(R.layout.lay_log,null);

        final MaterialEditText email = lay_log.findViewById(R.id.email);
        final MaterialEditText password = lay_log.findViewById(R.id.password);

        dialog.setView(lay_log);

        //Button
        dialog.setPositiveButton("MASUK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        //cek validasi
                        if (TextUtils.isEmpty(email.getText().toString())) {
                            Snackbar.make(mainLayout, "Masukkan alamat email", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(password.getText().toString())) {
                            Snackbar.make(mainLayout, "Masukkan password", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if (password.getText().toString().length() < 8) {
                            Snackbar.make(mainLayout, "Password terlalu pendek. Minimal 8 karakter", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        //Masuk
                        xAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, depan.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(mainLayout, "Gagal" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        dialog.show();
    }

    private void showDialogDaftar(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("DAFTAR");
        dialog.setMessage("Lengkapi data berikut");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register = inflater.inflate(R.layout.register,null);

        final MaterialEditText nama = register.findViewById(R.id.nama);
        final MaterialEditText email = register.findViewById(R.id.email);
        final MaterialEditText no_tlp = register.findViewById(R.id.no_tlp);
        final MaterialEditText password = register.findViewById(R.id.password);

        dialog.setView(register);

        //Button
        dialog.setPositiveButton("DAFTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //cek validasi
                if (TextUtils.isEmpty(nama.getText().toString())){
                    Snackbar.make(mainLayout, "Masukkan nama",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(mainLayout, "Masukkan alamat email",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(no_tlp.getText().toString())){
                    Snackbar.make(mainLayout, "Masukkan Nomor telepon",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    Snackbar.make(mainLayout, "Masukkan password",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (password.getText().toString().length() <8){
                    Snackbar.make(mainLayout,"Password terlalu pendek. Minimal 8 karakter", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                //Register
                xAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //save ke db
                        User user = new User();
                        user.setNama(nama.getText().toString());
                        user.setEmail (email.getText().toString());
                        user.setNo_tlp(no_tlp.getText().toString());
                        user.setPassword (password.getText().toString());

                        //key
                        Nebengkuy_db.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Snackbar.make(mainLayout,"Daftar Berhasil", Snackbar.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(mainLayout,"Daftar gagal "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mainLayout,"Daftar gagal "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });


        dialog.setNegativeButton("BATALKAN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
}
