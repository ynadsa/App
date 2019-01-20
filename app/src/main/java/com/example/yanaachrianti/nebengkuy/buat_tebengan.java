package com.example.yanaachrianti.nebengkuy;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yanaachrianti.nebengkuy.Model.Buat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class buat_tebengan extends AppCompatActivity {
    DatabaseReference Nebengkuy_db;
    EditText jml_kursi;
    EditText keterangan;
    EditText waktu;
    Spinner jenis;
    Button xBuat, xBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat_tebengan);

        jml_kursi = (EditText) findViewById(R.id.jml_kursi);
        keterangan = (EditText) findViewById(R.id.keterangan);
        waktu = (EditText) findViewById(R.id.jam);
        jenis = (Spinner) findViewById(R.id.jenis);
        xBuat = (Button) findViewById(R.id.buat);
        xBatal = (Button) findViewById(R.id.batal);

        //mengambil referensi ke FirabaseDatabase
        Nebengkuy_db = FirebaseDatabase.getInstance().getReference("Buat");

        //Fungsi tombol Buat
        xBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitTebengan();
            }
        });
    }

    private void submitTebengan(){
        String spinner = jenis.getSelectedItem().toString();
        String kursi = jml_kursi.getText().toString().trim();
        String ket = keterangan.getText().toString().trim();
        String jam = waktu.getText().toString().trim();

        if (!TextUtils.isEmpty(kursi) && !TextUtils.isEmpty(ket) && !TextUtils.isEmpty(jam)) {
            String id = Nebengkuy_db.push().getKey();

            Buat buat = new Buat (id, spinner, kursi, ket, jam);

            Nebengkuy_db.child(id).setValue(buat);

            Toast.makeText(this, "Tebengan ditambahkan", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(this, "Data belum lengkap", Toast.LENGTH_LONG).show();
        }


    }


}
