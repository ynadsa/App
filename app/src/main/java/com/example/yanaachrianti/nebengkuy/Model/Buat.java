package com.example.yanaachrianti.nebengkuy.Model;

public class Buat {
    String jenis, kursi, ket_kend, waktu ;

    public Buat(String id, String spinner, String kursi, String ket, String jam){
    }

    public Buat(String jenis, String kursi, String ket_kend, String waktu) {
        this.jenis = jenis;
        this.kursi = kursi;
        this.ket_kend = ket_kend;
        this.waktu = waktu;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKursi() {
        return kursi;
    }

    public void setKursi(String kursi) {
        this.kursi = kursi;
    }

    public String getKet_kend() {
        return ket_kend;
    }

    public void setKet_kend(String ket_kend) {
        this.ket_kend = ket_kend;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}


