package com.example.adiputra.sewain;

public class History {
    private int gambar;
    private String nama;
    private String alamat;
    private String status;

    public History(int gambar, String nama, String alamat, String status){
        this.gambar = gambar;
        this.nama = nama;
        this.alamat = alamat;
        this.status = status;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
