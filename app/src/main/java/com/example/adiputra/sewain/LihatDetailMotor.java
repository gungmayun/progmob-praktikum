package com.example.adiputra.sewain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LihatDetailMotor extends AppCompatActivity implements View.OnClickListener{
    private EditText editText_id;
    private EditText editText_nopol;
    private EditText editText_jenis;
    private EditText editText_tahun;
    private EditText editText_harga;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_motor);
        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.MTR_ID);

        editText_id = (EditText) findViewById(R.id.editText_id);
        editText_nopol = (EditText) findViewById(R.id.editText_nopol);
        editText_jenis = (EditText) findViewById(R.id.editText_jenis);
        editText_tahun = (EditText) findViewById(R.id.editText_tahun);
        editText_harga = (EditText) findViewById(R.id.editText_harga);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editText_id.setText(id);

        getMotor();
    }
    private void getMotor(){
        class GetMotor extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailMotor.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMotor(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_MTR,id);
                return s;
            }
        }
        GetMotor gm = new GetMotor();
        gm.execute();
    }
    private void showMotor(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nopol = c.getString(Konfigurasi.TAG_NOPOL);
            String jenis = c.getString(Konfigurasi.TAG_JENIS);
            String tahun = c.getString(Konfigurasi.TAG_TAHUN);
            String harga = c.getString(Konfigurasi.TAG_HARGA);
            editText_nopol.setText(nopol);
            editText_jenis.setText(jenis);
            editText_tahun.setText(tahun);
            editText_harga.setText(harga);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateMotor(){
        final String nopol = editText_nopol.getText().toString().trim();
        final String jenis = editText_jenis.getText().toString().trim();
        final String tahun = editText_tahun.getText().toString().trim();
        final String harga = editText_harga.getText().toString().trim();
        class UpdateMotor extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailMotor.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailMotor.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_MTR_ID,id);
                hashMap.put(Konfigurasi.KEY_MTR_NOPOL,nopol);
                hashMap.put(Konfigurasi.KEY_MTR_JENIS,jenis);
                hashMap.put(Konfigurasi.KEY_MTR_TAHUN,tahun);
                hashMap.put(Konfigurasi.KEY_MTR_HARGA,harga);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_MTR,hashMap);

                return s;
            }
        }

        UpdateMotor um = new UpdateMotor();
        um.execute();
    }
    private void deleteMotor(){
        class DeleteMotor extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailMotor.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailMotor.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_DELETE_MTR, id);
                return s;
            }
        }

        DeleteMotor dm = new DeleteMotor();
        dm.execute();
    }

    private void confirmDeleteMotor(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Motor ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteMotor();
                        startActivity(new Intent(LihatDetailMotor.this,LihatMotor.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateMotor();
        }

        if(v == buttonDelete){
            confirmDeleteMotor();
        }
    }
}

