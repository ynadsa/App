package com.example.yanaachrianti.nebengkuy.Model;

public class User {
    private String nama, email, no_tlp, password;

    public User() {
    }

    public User(String nama, String email, String no_tlp, String password) {
        this.nama = nama;
        this.email = email;
        this.no_tlp = no_tlp;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
