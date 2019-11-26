package com.example.example.sewain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spark.submitbutton.SubmitButton;

import java.util.HashMap;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;

public class TambahMotor extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelper myDb;
    private EditText editID, editNopol, editJenis, editTahun, editHarga;
    private SubmitButton addData;
    private Button viewData;
    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_motor);
        //myDb = new DatabaseHelper(this);
        editID = (EditText) findViewById(R.id.editText_id);
        editNopol = (EditText) findViewById(R.id.editText_nopol);
        editJenis = (EditText) findViewById(R.id.editText_jenis);
        editTahun = (EditText) findViewById(R.id.editText_tahun);
        editHarga = (EditText) findViewById(R.id.editText_harga);

//        Log.d("haloa","tesssss: " + Preferences.getLoggedInUser(getApplicationContext()));
//        editID.setText(Preferences.getLoggedInUser(context));
        //PHP

        addData = (SubmitButton) findViewById(R.id.button_add);


        addData.setOnClickListener(this);

//        login_username = Preferences.getLoggedInUser(this.getBaseContext());
//        Log.d("maudong",Preferences.getLoggedInUser(getParent().getBaseContext()));
//        update = (Button) findViewById(R.id.button_update);
//        delete = (Button) findViewById(R.id.button_delete);

//        AddData();
//        viewAll();
//        UpdateData();
//        deleteData();
    }

    private void addMotor() {

        final String nopol = editNopol.getText().toString().trim();
        final String jenis = editJenis.getText().toString().trim();
        final String tahun = editTahun.getText().toString().trim();
        final String harga = editHarga.getText().toString().trim();
        final String login_username = Preferences.getLoggedInUser(getBaseContext());
        class AddMotor extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahMotor.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahMotor.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_MTR_NOPOL, nopol);
                params.put(Konfigurasi.KEY_MTR_JENIS, jenis);
                params.put(Konfigurasi.KEY_MTR_TAHUN, tahun);
                params.put(Konfigurasi.KEY_MTR_HARGA, harga);
                params.put("email",login_username);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }
        AddMotor am = new AddMotor();
        am.execute();
    }
    @Override
    public void onClick(View v) {

            addMotor();
        }
    }

//    public void deleteData() {
//        delete.setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//
//                    public void onClick(View v) {
//                        Integer deletedRows = myDb.deleteData(editID.getText().toString());
//
//                        if (deletedRows > 0) {
//
//                            Toast.makeText(TambahMotor.this, "Data Deleted", Toast.LENGTH_LONG).show();
//                            clearText();
//                        }
//                        else
//
//                            Toast.makeText(TambahMotor.this,"Data Failed to Deleted!",Toast.LENGTH_LONG).show();
//
//                    }
//
//                }
//
//        );
//
//    }
//
//
//
//    //fungsi update
//
//    public void UpdateData() {
//
//        update.setOnClickListener(
//
//                new View.OnClickListener() {
//
//                    @Override
//
//                    public void onClick(View v) {
//
//                        boolean isUpdate = myDb.updateData(editID.getText().toString(),
//                                editNopol.getText().toString(),
//
//                                editJenis.getText().toString(),
//
//                                editTahun.getText().toString(),
//
//                                editHarga.getText().toString());
//
//                        if(isUpdate == true){
//                            Toast.makeText(TambahMotor.this,"Data Updated",Toast.LENGTH_LONG).show();
//                            clearText();
//                        }
//
//                        else
//
//                            Toast.makeText(TambahMotor.this,"Data Failed to Update",Toast.LENGTH_LONG).show();
//
//                    }
//
//                }
//
//        );
//
//    }
//
//
//
//    //fungsi tambah
//
//    public void AddData() {
//
//        addData.setOnClickListener(
//
//                new View.OnClickListener() {
//
//                    @Override
//
//                    public void onClick(View v) {
//
//                        boolean isInserted = myDb.insertData(editNopol.getText().toString(),
//
//                                editJenis.getText().toString(),
//
//                                editTahun.getText().toString(),
//
//                                editHarga.getText().toString() );
//
//                        if(isInserted == true) {
//
//                            Toast.makeText(TambahMotor.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                            clearText();
//                        }
//
//                        else
//
//                            Toast.makeText(TambahMotor.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
//
//                    }
//
//                }
//
//        );
//
//    }
//
//
//
//    public void viewAll() {
//
//        viewAll.setOnClickListener(
//
//                new View.OnClickListener(){
//
//                    @Override
//
//                    public void onClick(View v) {
//
//                        Cursor res = myDb.getAllData();
//
//                        if(res.getCount() == 0) {
//
//                            // show message
//
//                            showMessage("Error","Noting Found");
//
//                            return;
//
//                        }
//
//
//
//                        StringBuffer buffer = new StringBuffer();
//
//                        while (res.moveToNext() ) {
//
//                            buffer.append("ID  : "+ res.getString(0)+"\n");
//
//                            buffer.append("NOPOL  : "+ res.getString(1)+"\n");
//
//                            buffer.append("JENIS MOTOR  : "+ res.getString(2)+"\n");
//
//                            buffer.append("TAHUN MOTOR  : "+ res.getString(3)+"\n");
//
//                            buffer.append("HARGA  : "+ res.getString(4)+"\n\n");
//
//                        }
//
//
//                        showMessage("Data",buffer.toString());
//
//                    }
//
//                }
//
//        );
//
//    }
//
//
//
//    //membuat alert dialog
//
//    public void showMessage(String title, String Message){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setCancelable(true);
//
//        builder.setTitle(title);
//
//        builder.setMessage(Message);
//
//        builder.show();
//
//    }
//
//    //clear text setelah menekan tombol
//
//    public void clearText(){
//        editNopol.getText().clear();
//        editJenis.getText().clear();
//        editTahun.getText().clear();
//        editHarga.getText().clear();
//    }

