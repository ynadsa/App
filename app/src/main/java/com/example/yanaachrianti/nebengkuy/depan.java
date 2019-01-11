package com.example.yanaachrianti.nebengkuy;

import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class depan extends AppCompatActivity {
    private CircleImageView xProfil;
    private TextView xBuat, xCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depan);

        CircleImageView xProfil = (CircleImageView) findViewById(R.id.profil);

        xProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(depan.this, profil.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        TextView xBuat = (TextView) findViewById(R.id.buat);

        xBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(depan.this, motor.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        TextView xCari = (TextView) findViewById(R.id.cari);

        xCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(depan.this, list_tebengan.class);
                finish();
                return;
            }
        });
    }
}
