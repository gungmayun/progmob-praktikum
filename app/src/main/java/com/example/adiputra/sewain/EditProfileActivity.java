package com.example.adiputra.sewain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {
    Button btnUpdate;
    EditText edtNama, edtEmail, edtPassword, edtRePassword;
    private String id_mitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();

        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        edtNama = (EditText) findViewById(R.id.et_name);
        edtEmail = (EditText) findViewById(R.id.et_email);
        edtPassword = (EditText) findViewById(R.id.et_password);

        id_mitra = intent.getStringExtra(Konfigurasi.MTA_ID);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMitra();
            }
        });
        getMitra();
    }

    private void getMitra() {
        class GetMitra extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditProfileActivity.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showMitra(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.URL_GET_MTA,id_mitra);
                return s;
            }
        }
        GetMitra gm = new GetMitra();
        gm.execute();
    }

    private void showMitra(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY1);
            JSONObject c = result.getJSONObject(0);
            String id_mitra = c.getString(Konfigurasi.TAG_ID_MTA);
            String nama = c.getString(Konfigurasi.TAG_NAMA);
            String email = c.getString(Konfigurasi.TAG_EMAIL);
            String password = c.getString(Konfigurasi.TAG_PASSWORD);
            edtNama.setText(nama);
            edtEmail.setText(email);
            edtPassword.setText(password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateMitra() {
        final String nama = edtNama.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        class UpdateMitra extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditProfileActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditProfileActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_MTA_ID, id_mitra);
                hashMap.put(Konfigurasi.KEY_MTA_NAMA, nama);
                hashMap.put(Konfigurasi.KEY_MTA_EMAIL, email);
                hashMap.put(Konfigurasi.KEY_MTA_PASSWORD, password);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.URL_UPDATE_MTA, hashMap);

                return s;
            }
        }

        UpdateMitra um = new UpdateMitra();
        um.execute();
    }
    public void onClick(View v) {
            updateMitra();

    }
}