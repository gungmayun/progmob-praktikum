package com.example.example.sewain;

public class Konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="https://kelompok23.000webhostapp.com/tambahmotor.php";
    public static final String URL_GET_ALL = "https://kelompok23.000webhostapp.com/lihatmotor.php?email=";
    public static final String URL_GET_MTR = "https://kelompok23.000webhostapp.com/lihatdetailmotor.php?id=";
    public static final String URL_UPDATE_MTR = "https://kelompok23.000webhostapp.com/updatemotor.php";
    public static final String URL_DELETE_MTR = "https://kelompok23.000webhostapp.com/deletemotor.php?id=";

    public static final String URL_ADD_MTA="https://kelompok23.000webhostapp.com/tambahmitra.php";
    public static final String URL_UPDATE_MTA = "https://kelompok23.000webhostapp.com/updatemitra.php";
    public static final String URL_GET_MTA = "https://kelompok23.000webhostapp.com/lihatmitra.php?id_mitra=";


    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_MTR_ID = "id";
    public static final String KEY_MTR_NOPOL = "nopol";
    public static final String KEY_MTR_JENIS = "jenis";
    public static final String KEY_MTR_TAHUN = "tahun";
    public static final String KEY_MTR_HARGA = "harga";
    public static final String KEY_MTR_EMAIL = "email";

    public static final String KEY_MTA_ID="id";
    public static final String KEY_MTA_NAMA = "nama";
    public static final String KEY_MTA_EMAIL = "email";
    public static final String KEY_MTA_PASSWORD = "password";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NOPOL = "nopol";
    public static final String TAG_JENIS = "jenis";
    public static final String TAG_TAHUN = "tahun";
    public static final String TAG_HARGA = "harga";

    public static final String TAG_JSON_ARRAY1="result";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASSWORD = "password";
    public static final String TAG_ID_MTA = "id_mitra";

    //ID karyawan
    //mtr itu singkatan dari motor
    public static final String MTR_ID = "mtr_id";
    public static final String MTA_ID = "mta_id";
}

